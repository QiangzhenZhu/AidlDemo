/**
 * All rights Reserved, Designed By Android_Robot   
 * @Title:  Box.java   
 * @Package com.hzdongcheng.components.driver.elocker.model   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: Jxing     
 * @date:   2017年4月12日 下午3:14:00   
 * @version V1.0     
 */
package com.hzdongcheng.aidldemo.bean;

/**
 * @ClassName: Box
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Jxing
 * @date 2017年4月12日 下午3:14:00
 * @version 1.0
 */
public class BoxStatus {
	public static final byte UNKNOWN_STATUS = 9;//未知状态
	public BoxStatus() {
		setGoodsStatus(UNKNOWN_STATUS);
		setOpenStatus(UNKNOWN_STATUS);
		setHeatStatus(UNKNOWN_STATUS);
		setLedLanternStatus(UNKNOWN_STATUS);
		setRedLanternStatus(UNKNOWN_STATUS);
		setDisinfectStatus(UNKNOWN_STATUS);
		setSaleReturnStatus(0);
		setTemp(0);
	}

	/**
	 * 物品状态. 0:无物 1:有物 9:未知
	 */
	private byte goodsStatus;

	/**
	 * 箱门开关状态. 0:关闭 1:打开 9:未知
	 */
	private byte openStatus;

	/**
	 * 加热状态. 0:停止加热 1:正在加热 9:未知
	 */
	private byte heatStatus;

	/**
	 * LED灯状态. 0:关闭 1:开启 9:未知
	 */
	private int ledLanternStatus;
	/**
	 * 红灯状态. 0:关闭 1:开启 9:未知
	 */
	private int redLanternStatus;
	/**
	 * 消毒状态. 0:关闭 1:开启 9:未知
	 */
	private int disinfectStatus;

	/**
	 * 箱内温度
	 */
	private int temp;

	/**
	 * 售卖柜开箱返回状态 返回类型 0:成功 1：失败 2：超出 3：应答
	 */
	private int saleReturnStatus;

	/**
	 * @return the goodsStatus
	 */
	public byte getGoodsStatus() {
		return goodsStatus;
	}

	/**
	 * @param goodsStatus
	 *            the goodsStatus to set
	 */
	public void setGoodsStatus(byte goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	/**
	 * @return the openStatus
	 */
	public byte getOpenStatus() {
		return openStatus;
	}

	/**
	 * @param openStatus
	 *            the openStatus to set
	 */
	public void setOpenStatus(byte openStatus) {
		this.openStatus = openStatus;
	}

	/**
	 * @return the heatStatus
	 */
	public byte getHeatStatus() {
		return heatStatus;
	}

	/**
	 * @param heatStatus
	 *            the heatStatus to set
	 */
	public void setHeatStatus(byte heatStatus) {
		this.heatStatus = heatStatus;
	}

	/**
	 * @return the wendu
	 */
	public int getTemp() {
		return temp;
	}

	/**
	 * @param wendu
	 *            the wendu to set
	 */
	public void setTemp(int value) {
		this.temp = value;
	}

	/**
	 * @return the saleReturnStatus
	 */
	public int getSaleReturnStatus() {
		return saleReturnStatus;
	}

	/**
	 * @param saleReturnStatus
	 *            the saleReturnStatus to set
	 */
	public void setSaleReturnStatus(int saleReturnStatus) {
		this.saleReturnStatus = saleReturnStatus;
	}


	/**
	 * @return the ledLanternStatus
	 */
	public int getLedLanternStatus() {
		return ledLanternStatus;
	}

	/**
	 * @param ledLanternStatus
	 *            the ledLanternStatus to set
	 */
	public void setLedLanternStatus(int ledLanternStatus) {
		this.ledLanternStatus = ledLanternStatus;
	}

	/**
	 * @return the redLanternStatus
	 */
	public int getRedLanternStatus() {
		return redLanternStatus;
	}

	/**
	 * @param redLanternStatus
	 *            the redLanternStatus to set
	 */
	public void setRedLanternStatus(int redLanternStatus) {
		this.redLanternStatus = redLanternStatus;
	}

	/**
	 * @return the disinfectStatus
	 */
	public int getDisinfectStatus() {
		return disinfectStatus;
	}

	/**
	 * @param disinfectStatus
	 *            the disinfectStatus to set
	 */
	public void setDisinfectStatus(int disinfectStatus) {
		this.disinfectStatus = disinfectStatus;
	}
}
