package cn.ekgc.ytrip.pojo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>酒店订单实体类</b>
 * @version 4.0.0
 * @since 4.0.0
 */
public class HotelOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;                                            // 主键
	private Long userId;                                        // 用户主键
	private Integer orderType;                                  // 订单类型(0:旅游产品 1:酒店产品 2：机票产品)
	private String orderNo;                                     // 订单号
	private String tradeNo;                                     // 交易单号
	private Long hotelId;                                       // 酒店Id
	private String hotelName;                                   // 酒店名称
	private Long roomId;                                        // 房间Id
	private Integer count;                                      // 消耗数量
	private Integer bookingDays;                                // 预订天数
	private Date checkInDate;                                   // 入住时间
	private Date checkOutDate;                                  // 退房时间
	private Integer orderStatus;                                // 订单状态（0：待支付 1:已取消 2:支付成功 3:已消费 4：已点评）
	private Double payAmount;                                   // 支付金额
	private Integer payType;                                    // 支付方式:1:支付宝 2:微信 3:到店付
	private String noticePhone;                                 // 联系手机号
	private String noticeEmail;                                 // 联系邮箱
	private String specialRequirement;                          // 特殊需求
	private Integer isNeedInvoice;                              // 是否需要发票（0：不需要 1：需要）
	private Integer invoiceType;                                // 发票类型（0：个人 1：公司）
	private String invoiceHead;                                 // 发票抬头
	private String linkUserName;                                // 入住人名称
	private Integer bookType;                                   // 0:WEB端 1:手机端 2:其他客户端
	private Date creationDate;                                  // 预订时间
	private Long createdBy;
	private Date modifyDate;                                    // 支付完成时间
	private Long modifiedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getBookingDays() {
		return bookingDays;
	}

	public void setBookingDays(Integer bookingDays) {
		this.bookingDays = bookingDays;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getNoticePhone() {
		return noticePhone;
	}

	public void setNoticePhone(String noticePhone) {
		this.noticePhone = noticePhone;
	}

	public String getNoticeEmail() {
		return noticeEmail;
	}

	public void setNoticeEmail(String noticeEmail) {
		this.noticeEmail = noticeEmail;
	}

	public String getSpecialRequirement() {
		return specialRequirement;
	}

	public void setSpecialRequirement(String specialRequirement) {
		this.specialRequirement = specialRequirement;
	}

	public Integer getIsNeedInvoice() {
		return isNeedInvoice;
	}

	public void setIsNeedInvoice(Integer isNeedInvoice) {
		this.isNeedInvoice = isNeedInvoice;
	}

	public Integer getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoiceHead() {
		return invoiceHead;
	}

	public void setInvoiceHead(String invoiceHead) {
		this.invoiceHead = invoiceHead;
	}

	public String getLinkUserName() {
		return linkUserName;
	}

	public void setLinkUserName(String linkUserName) {
		this.linkUserName = linkUserName;
	}

	public Integer getBookType() {
		return bookType;
	}

	public void setBookType(Integer bookType) {
		this.bookType = bookType;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
