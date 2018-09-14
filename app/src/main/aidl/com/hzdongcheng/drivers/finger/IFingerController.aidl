// IFingerController.aidl
package com.hzdongcheng.drivers.finger;
import com.hzdongcheng.drivers.bean.Result;

// Declare any non-default types here with import statements

interface IFingerController {
    /**
    * 打开指纹设备
    */
    boolean open();

    /**
    *  关闭指纹仪设备
    */
    boolean close();
    /**
    * 获取指纹仪厂家
    */
    Result getVendor();

    /**
     * 获取指纹仪版本
     */
     Result getVersion();

    /**
     * 获取指纹特征码
     */
     Result getFeature();

     /**
      * 获取指纹模板
      */
      Result getTemplate();

      /**
      * 获取指纹图像
      */
      Result getImage();

     /**
      * 检测是否指纹按下
      */
      boolean pressDetect();

     /**
      * 指纹比对
      */
      Result match(String feature1, String feature2,int level);

}
