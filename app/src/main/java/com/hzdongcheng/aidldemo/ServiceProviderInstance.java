package com.hzdongcheng.aidldemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.google.gson.Gson;
import com.hzdongcheng.aidldemo.bean.BoxStatus;
import com.hzdongcheng.drivers.IDriverManager;
import com.hzdongcheng.drivers.bean.Result;
import com.hzdongcheng.drivers.locker.ISlaveController;
import com.hzdongcheng.drivers.peripheral.scanner.IScannerController;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzq on 2018/9/13
 **/
public class ServiceProviderInstance {
    private static final String TAG = "ServiceProviderInstance";
    private static ServiceProviderInstance instance = new ServiceProviderInstance();
    private static IDriverManager driverManager;
    private static ISlaveController slaveController;
    private static IScannerController scannerController;
    private WeakReference<Context> contextWeakReference;
    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> schedule;
    private Intent driverIntent;

    /**
     * 监听Aidl客户端和服务端的状态
     */
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (driverManager != null&&contextWeakReference!=null){
                driverManager.asBinder().unlinkToDeath(mDeathRecipient, 0);
                contextWeakReference.get().unbindService(serviceConnection);
            }
        }
    };
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "->东城驱动服务连接成功");
            driverManager = IDriverManager.Stub.asInterface(iBinder);
            try {
                iBinder.linkToDeath(mDeathRecipient,0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG,"->东城驱动服务断开连接");
            driverManager = null;
            scannerController = null;
            slaveController = null;
            reconnectService();

        }
    };


    private ServiceProviderInstance(){

    }

    public static ServiceProviderInstance getInstance(){
        return instance;
    }
    /**
     * 服务重新连接(延时3秒)
     */
    private synchronized void reconnectService() {

        if (schedule != null && !schedule.isDone()) {
            Log.d(TAG,"--> 驱动服务器已经再重连,将不会执行此次重连请求");
            return;
        }

        schedule = executorService.schedule(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"--> 开始重新连接驱动服务");
                if (contextWeakReference.get() != null) {
                    boolean isBind;
                    int tryCount = 0;
                    do {
                        try {
                            Thread.sleep(tryCount * 30 * 1000);
                        } catch (InterruptedException ignored) {
                        }
                        isBind = contextWeakReference.get().bindService(driverIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                    } while (!isBind || tryCount++ < 10);
                }
            }
        }, 3, TimeUnit.SECONDS);
    }
    /**
     * 绑定东城驱动服务
     * @param context
     */
    public boolean bind(Context context){
        driverIntent = new Intent("hzdongcheng.intent.action.DRIVER");
        driverIntent.setPackage("com.hzdongcheng.drivers");
        contextWeakReference = new WeakReference<>(context);
        return context.bindService(driverIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 获取副柜
     * @return
     */
    public static ISlaveController getSlaveController()  {
        if (slaveController != null&&slaveController.asBinder().isBinderAlive())
            return slaveController;
        if (driverManager != null) {
            try {
                slaveController = ISlaveController.Stub.asInterface(driverManager.getSlaveService());
            } catch (RemoteException e) {
                Log.e(TAG,">>副柜控制器获取失败>>" + e.getMessage());
            }
        }
        if (slaveController == null) {
            Log.e(TAG,"未获取到服务模块");
        }
        return slaveController;
    }


    /**
     * 获取扫描枪服务
     * @return
     * @throws
     */
    public IScannerController getScannerController() throws Exception {
        if (scannerController != null&&scannerController.asBinder().isBinderAlive())
            return scannerController;
        if (driverManager != null) {
            try {
                scannerController = IScannerController.Stub.asInterface(driverManager.getScannerService());
                if (scannerController == null) {
                    throw new Exception("未获取到服务模块");
                }
                scannerController.start();
                scannerController.addObserver(HAL.scannerObserver);
            } catch (RemoteException e) {
                Log.e(TAG,">>扫描枪控制器获取失败>>" + e.getMessage());
            }
        }
        if (scannerController == null) {
            throw new Exception("未获取到服务模块");
        }
        return scannerController;
    }

    //解绑
    public void unBind(){
        if (contextWeakReference.get() != null){
            contextWeakReference.get().unbindService(serviceConnection);
        }

    }
}
