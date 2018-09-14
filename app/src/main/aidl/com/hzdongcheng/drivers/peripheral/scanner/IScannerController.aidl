// IScannerController.aidl
package com.hzdongcheng.drivers.peripheral.scanner;
import com.hzdongcheng.drivers.bean.Result;
// 扫描枪控制
import com.hzdongcheng.drivers.peripheral.IObserver;

interface IScannerController {
   /**
    * 获取扫描枪厂家
    */
    Result getVendor();

    /**
     * 获取扫描枪版本
     */
     Result getVersion();

   /**
    *切换工作模式 常亮/指令
    */
    Result switchMode(boolean always);

    /**
     * 开启/禁用条码识别
     */
    Result toggleBarcode(boolean enabled);

    /**
     * 开启/禁用二维码识别
     */
    Result toggleQRCode(boolean enabled);

    /**
     * 指令模式下启动扫描
     */
    Result start();
    /*
     * 指令模式下停止扫描
     */
    Result stop();

   /**
    * 添加观察者
    */
    boolean addObserver(in IObserver observer);

    /**
     * 删除观察者
     */
    boolean removeObserver(in IObserver observer);
}
