// ICardreaderController.aidl
package com.hzdongcheng.drivers.peripheral.cardreader;
import com.hzdongcheng.drivers.bean.Result;

import com.hzdongcheng.drivers.peripheral.IObserver;

interface ICardReaderController {

   /**
    * 写入数据
    */
    Result writeToBlock(byte sectorNo, byte blockNo, String keyA, String keyB, String data);
   /**
    * 读取块数据
    */
    Result readFromBlock(byte sectorNo, byte blockNo, String keyA, String keyB);
   /**
    * 读取扇区
    */
    Result readFromSector(byte sectorNo, String keyA, String keyB);

   /**
    *开始读卡
    */
    boolean start();

   /**
    *停止读卡
    */
    boolean stop();

   /**
    * 添加观察者
    */
    boolean addObserver(IObserver observer);

   /**
    * 删除观察者
    */
    boolean removeObserver(IObserver observer);
}
