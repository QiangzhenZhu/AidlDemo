package com.hzdongcheng.drivers.peripheral.biomini;
import com.hzdongcheng.drivers.bean.Result;

interface IBiominiController {

    /**
     * 获取设备版本
     */
    Result getVersion();

    /**
     * 获取指纹仪图像
     */
    Result getImage();

    /**
     * 获取特征码
     */
    Result getFeature();
    /**
     * 获取模板
     */
    Result getFPTempalte();

    /**
     * 指纹匹配
     */
    Result matching(String featureOne, String featureTwo);
}
