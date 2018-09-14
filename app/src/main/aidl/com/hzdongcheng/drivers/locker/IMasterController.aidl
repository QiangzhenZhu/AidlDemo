// IMasterController.aidl
package com.hzdongcheng.drivers.locker;
import com.hzdongcheng.drivers.bean.Result;

interface IMasterController {
     /**
      * 查询主机所有状态
      */
    Result queryMainStatus();

    /**
     * 弱电控制
     */
    Result weakCurrentManage(in Map channelValues);

    /**
     * 强电控制
     */
    Result strongCurrentManage(in Map channelValues);

    /**
     * 控制主灯箱亮灭
     */
    Result toggleMasterLamp(boolean enabled);

    /**
     * 控制副灯箱亮灭
     */
    Result toggleSlaveLamp(boolean enabled);

    /**
     * 断电重启
     */
    Result reboot(int delayMillis);

    /**
     * 设置散热器工作温度范围
     */
    Result setCoolingScope(int min, int max);

    /**
     * 获取散热器工作温度范围
     */
    Result getCoolingScope();

    /**
     * 设置加热器工作温度范围
     */
    Result setWarmingScope(int min, int max);

    /**
     * 获取加热器工作温度范围
     */
    Result getWarmingScope();

    /**
     * 获取电表读数
     */
    Result getAmmeterReading();

    /**
     * 获取主机温度
     */
    Result getHostTemperature();

    /**
     * 启用硬件心跳功能
     */
     Result enableKeepAlive();

    /**
     * 关闭硬件心跳功能
     */
    Result disableKeepAlive();

    /**
     * 发送硬件心跳
     */
    Result keepAlive();
}
