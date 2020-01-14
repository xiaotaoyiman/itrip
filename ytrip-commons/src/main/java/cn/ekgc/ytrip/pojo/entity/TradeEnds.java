package cn.ekgc.ytrip.pojo.entity;

import java.io.Serializable;

/**
 * <b>库存中间表实体类</b>
 */
public class TradeEnds implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;                                        // 主键
	private String orderNo;                                 // 订单编号
	private boolean flag;                                   // 标识字段(默认false：未处理；true：处理中)

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
