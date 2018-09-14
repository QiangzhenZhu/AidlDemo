package com.hzdongcheng.aidldemo;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;

import com.google.gson.Gson;
import com.hzdongcheng.aidldemo.bean.BoxStatus;
import com.hzdongcheng.aidldemo.bean.ICallBack;
import com.hzdongcheng.aidldemo.bean.SlaveStatus;
import com.hzdongcheng.drivers.bean.Result;
import com.hzdongcheng.drivers.peripheral.IObserver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzq on 2018/9/13
 **/
public class HAL {
    private static final String TAG = "HAL";
    private static ICallBack scannerCallBack;
    public static boolean init(Context context){
        return ServiceProviderInstance.getInstance().bind(context);
    }

    //设置扫描枪回调
    public static void setScannerCallBack(ICallBack _callBack) {
        scannerCallBack = _callBack;
    }

    //#region 扫描枪
    public static IObserver scannerObserver = new IObserver.Stub() {
        @Override
        public void onMessage(String msg) throws RemoteException {
            if (scannerCallBack != null)
                scannerCallBack.onMessage(msg, 0);
        }
    };


    /**
     * 打开指定箱门
     *
     * @param boxName 格口名称
     * @return 打开结果
     */
    public static boolean openBox(String boxName) {
        try {
            Log.d(TAG, "打开箱门: ");
            Result result =ServiceProviderInstance.getInstance().getSlaveController().openBoxByName(boxName);
            if (result.getCode() == 0) {
                Log.d(TAG,"[HAL] 打开箱门成功：" + boxName);
                return true;
            }
            Log.e(TAG,"[HAL] 箱门打开失败：" + boxName + ",code " + result.getCode());
        } catch (RemoteException e) {
            Log.e(TAG,"[HAL] 开箱服务调用失败，箱门 " + boxName);
        }
        return false;
    }


    /**
     * 获取箱门状态
     * @param boxName
     * @return
     */
    public static BoxStatus getBoxStatus(String boxName)throws Exception {
        try {
            Result result = ServiceProviderInstance.getInstance().getSlaveController().queryBoxStatusByName(boxName);
            if (result.getCode() == 0) {
                Log.e(TAG,"[HAL] 获取格口状态成功 -->" + new Gson().toJson(result));
                return new Gson().fromJson(result.getData(), BoxStatus.class);
            }
            Log.e(TAG,"[HAL] 获取格口状态失败 boxName " + boxName);
            throw new Exception("获取格口状态失败");
        } catch (RemoteException e) {
            Log.e(TAG,"[HAL] 获取格口状态服务出错, boxName " + boxName);
            throw new Exception("获取格口状态服务出错");
        }
    }

    /**
     * 根据整组副柜，获取箱门状态
     */
    public static void getBoxStatue(){
        Map<Integer, SlaveStatus> boxStatusMap = new HashMap<>();
        //假设 快递柜一共有 4组 副柜
        for (int i = 0; i < 4; i++) {
            try {
                boxStatusMap.put(i,getSlaveStatus(i));
            } catch (Exception e) {
                Log.e(TAG,"获取箱门状态错误 ");
            }
        }

    }

    /**
     * 获取副柜状态
     *
     * @param boardId 副柜编码
     * @return 副柜状态
     */
    public static SlaveStatus getSlaveStatus(int boardId) throws Exception {
        try {
            Result result = ServiceProviderInstance.getInstance().getSlaveController().queryStatusById((byte) boardId);
            if (result.getCode() == 0) {
                return new Gson().fromJson(result.getData(), SlaveStatus.class);
            }
            Log.d(TAG,"[HAL] 获取副柜状态失败 board" + boardId);
            throw new Exception("获取副柜状态失败");
        } catch (RemoteException e) {
           Log.d(TAG,"[HAL] 获取副柜状态服务出错, board " + boardId);
            throw new Exception("获取副柜状态服务出错");
        }
    }



    /**
     * 添加扫描监听
     */
    public static void addObserver() throws Exception {
        try {
            Log.d(TAG,"[HAL] 添加扫描监听 ");
            ServiceProviderInstance.getInstance().getScannerController().addObserver(scannerObserver);
        } catch (RemoteException e) {
            Log.e(TAG,"[HAL] 添加扫描监听 " + e.getMessage());
        }
    }

    /**
     * 设置扫描枪是否可以扫描条码
     *
     * @param enabled true可以识别条码，false 禁止识别条码
     */
    public static void toggleBarcode(boolean enabled) throws Exception {
        try {
            Log.d(TAG,"[HAL] 扫描枪条码设置 " + enabled);
            ServiceProviderInstance.getInstance().getScannerController().toggleBarcode(enabled);
        } catch (RemoteException e) {
            Log.e(TAG,"[HAL] 扫描枪条码设置出错 " + e.getMessage());
        }
    }

    /**
     * 设置扫描枪是否可以扫描二维码
     *
     * @param enabled true可以识别，false 禁止识别
     */
    public static void toggleQRCode(boolean enabled) throws Exception {
        try {
            Log.d(TAG,"[HAL] 扫描枪二维码设置 " + enabled);
            ServiceProviderInstance.getInstance().getScannerController().toggleQRCode(enabled);
        } catch (RemoteException e) {
           Log.e(TAG,"[HAL] 扫描枪二维码设置出错 " + e.getMessage());
        }
    }

    /**
     * 开始扫描（扫描枪命令模式下生效）
     */
    public static void startScanning() throws Exception {
        try {
            Log.d(TAG,"[HAL] 扫描枪开始扫描 ");
            ServiceProviderInstance.getInstance().getScannerController().start();
        } catch (RemoteException e) {
            Log.e(TAG,"[HAL] 扫描枪开始扫描出错 " + e.getMessage());
        }
    }

    /**
     * 停止扫描（扫描枪命令模式下生效）
     */
    public static void stopScanning() throws Exception {
        try {
            Log.d(TAG,"[HAL] 扫描枪停止扫描 ");
            ServiceProviderInstance.getInstance().getScannerController().stop();
        } catch (RemoteException e) {
            Log.e(TAG,"[HAL] 扫描枪停止扫描出错 " + e.getMessage());
        }
    }

}
