// ISlaveController.aidl
package com.hzdongcheng.drivers.locker;
import com.hzdongcheng.drivers.bean.Result;

// Declare any non-default types here with import statements

interface ISlaveController {

    /**
     * 打开副机所有箱门
     */
    Result openAllBox(byte boardId);

    /**
     * 根据ID打开箱门
     */
    Result openBoxById(byte boardId, byte boxId);

    /**
     * 根据格口名称打开箱门
     */
    Result openBoxByName(String boxName);

    /**
     * 查询副机状态
     */
    Result queryStatusById(byte boardId);

    /**
     * 查询副机状态
     */
    Result queryStatusByName(String boardName);

    /**
     * 查询指定箱门状态
     */
    Result queryBoxStatusById(byte boardId, byte boxId);

    /**
     * 查询指定箱门状态
     */
    Result queryBoxStatusByName(String boxName);

    /*
     * 获取温控器工作范围
     */
    Result queryFreezerScope(byte freezerId);

    /*
     * 获取温控器电源状态
     */
    Result queryFreezerPower(byte freezerId);

    /**
     * 设置温控器开机温度
     */
    Result setFreezerBoot(byte freezerId,int lower);
    /**
     设置温控器关机温度
    */
    Result setFreezerReboot(byte freezerId,int upper);

    /**
     * 格口加热
     */
    Result toggleBoxHeatingById(byte boardId,byte boxId,boolean active);

    /**
     * 格口加热
     */
    Result toggleBoxHeatingByName(String boxName,boolean active);

}
