package com.hzdongcheng.drivers.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Result implements Parcelable {

    private int code;
    private String errorMsg;
    private String data;
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }



    protected Result(Parcel in) {
        setCode(in.readInt());
        setErrorMsg(in.readString());
        setData(in.readString());
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getCode());
        parcel.writeString(getErrorMsg());
        parcel.writeString(getData());
    }
}
