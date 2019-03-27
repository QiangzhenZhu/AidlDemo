// IDriverManager.aidl
package com.hzdongcheng.drivers;


interface IDriverManager {
    /**
     * 获取主控板控制
     */
    IBinder getMasterService();

    /**
     * 获取副柜控制服务
     */
    IBinder getSlaveService();

    /*
     * 获取读卡器控制服务
     */
    IBinder getCardReaderService();

    /**
     * 获取系统控制服务
     */
    IBinder getSystemService();

    /**
     * 获取扫描枪服务
     */
    IBinder getScannerService();

    /**
     * 通过服务名称获取服务
     */
    IBinder getService(String serviceName);
}
