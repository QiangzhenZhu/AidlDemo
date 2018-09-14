package com.hzdongcheng.aidldemo.bean;

public interface ICallBack {
    //type 0:扫描枪 1：读卡器 2 倒计时回调
    void onMessage(String data,Integer type);
}