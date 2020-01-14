package cn.ekgc.ytrip.pojo.entity;

import org.springframework.format.number.AbstractNumberFormatter;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>酒店房间实体类</b>
 * @version 4.0.0
 * @since 4.0.0
 */
public class HotelRoom implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;                                    // 主键
	private Long hotelId;                               // 酒店ID
	private String roomTitle;                           // 酒店房间
	private Double roomPrice;                           // 酒店价格
	private Long roomBedTypeId;                         // 酒店床型
	private Integer isHavingBreakfast;                  // 是否包含早餐
	private Integer payType;                            // 1:在线付 2:到店付 3:不限
	private Double satisfaction;                        // 满意度（冗余字段，在用户评论后更新）
	private Integer isBook;                             // 是否可预订(0:不可以 1:可以)
	private Integer isCancel;                           // 是否可取消(0:不可 1:可以)
	private Integer isTimelyResponse;                   // 是否及时响应(0:不及时 1:及时)
	private Date creationDate;
	private Long createdBy;
	private Date modifyDate;
	private Long modifiedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getRoomTitle() {
		return roomTitle;
	}

	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}

	public Double getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(Double roomPrice) {
		this.roomPrice = roomPrice;
	}

	public Long getRoomBedTypeId() {
		return roomBedTypeId;
	}

	public void setRoomBedTypeId(Long roomBedTypeId) {
		this.roomBedTypeId = roomBedTypeId;
	}

	public Integer getIsHavingBreakfast() {
		return isHavingBreakfast;
	}

	public void setIsHavingBreakfast(Integer isHavingBreakfast) {
		this.isHavingBreakfast = isHavingBreakfast;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Double getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(Double satisfaction) {
		this.satisfaction = satisfaction;
	}

	public Integer getIsBook() {
		return isBook;
	}

	public void setIsBook(Integer isBook) {
		this.isBook = isBook;
	}

	public Integer getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(Integer isCancel) {
		this.isCancel = isCancel;
	}

	public Integer getIsTimelyResponse() {
		return isTimelyResponse;
	}

	public void setIsTimelyResponse(Integer isTimelyResponse) {
		this.isTimelyResponse = isTimelyResponse;
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
