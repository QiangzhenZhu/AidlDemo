/**
 * All rights Reserved, Designed By Android_Robot   
 * @Title:  ViceMachine.java   
 * @Package com.hzdongcheng.components.driver.elocker.model   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Jxing     
 * @date:   2017年4月12日 下午3:13:52   
 * @version V1.0     
 */
package com.hzdongcheng.aidldemo.bean;

/**
 * @ClassName: ViceMachine
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Jxing
 * @date 2017年4月12日 下午3:13:52
 * @version 1.0
 */
public class SlaveStatus {
	public static final byte UNKNOWN_STATUS = 9;//未知状态

	/**
	 * 箱数量
	 */
	private byte boxNums;
	/**
	 * 箱状态数组
	 */
	private BoxStatus[] boxStatusArray;

	/**
	 * 震动状态
	 */
	private byte ShockStatus = UNKNOWN_STATUS;

	/**
	 * 防撬状态
	 */
	private byte TamperStatus = UNKNOWN_STATUS;

	/**
	 * @return the boxNums
	 */
	public byte getBoxNums() {
		return boxNums;
	}

	/**
	 * @param boxNums
	 *            the boxNums to set
	 */
	public void setBoxNums(byte boxNums) {
		this.boxNums = boxNums;
	}

	/**
	 * @return the shockStatus
	 */
	public int getShockStatus() {
		return ShockStatus;
	}

	/**
	 * @param shockStatus
	 *            the shockStatus to set
	 */
	public void setShockStatus(byte shockStatus) {
		ShockStatus = shockStatus;
	}

	/**
	 * @return the tamperStatus
	 */
	public int getTamperStatus() {
		return TamperStatus;
	}

	/**
	 * @param tamperStatus
	 *            the tamperStatus to set
	 */
	public void setTamperStatus(byte tamperStatus) {
		TamperStatus = tamperStatus;
	}

	/**
	 * @return the boxStatusArray
	 */
	public BoxStatus[] getBoxStatusArray() {
		return boxStatusArray;
	}

	/**
	 * @param boxStatusArray
	 *            the boxStatusArray to set
	 */
	public void setBoxStatusArray(BoxStatus[] boxStatusArray) {
		this.boxStatusArray = boxStatusArray;
	}

	/**
	 * @return the boxStatusArray
	 */
	public BoxStatus getBoxStatusArray(int boxID) {
		return boxStatusArray[boxID];
	}

}
