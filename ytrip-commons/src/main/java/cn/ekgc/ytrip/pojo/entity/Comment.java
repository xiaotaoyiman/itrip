package cn.ekgc.ytrip.pojo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>评论实体类</b>
 * @version 4.0.0
 * @since 4.0.0
 */
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;                                        // 主键
	private Long hotelId;                                   // 酒店Id
	private Long productId;                                 // 商品Id
	private Long orderId;                                   // 订单Id
	private Integer productType;                            // 商品类型(0:旅游产品 1:酒店产品 2:机票产品)
	private String content;                                 // 评论内容
	private Long userId;                                    // 用户编号
	private Integer isHavingImg;                            // 是否包含图片(当用户上传评论时检测)0:无图片 1:有图片
	private Integer positionScore;                          // 位置评分
	private Integer facilitiesScore;                        // 设施评分
	private Integer serviceScore;                           // 服务评分
	private Integer hygieneScore;                           // 卫生评分
	private Integer score;                                  // 综合评分
	private Long tripMode;                                  // 出游类型
	private Integer isOk;                                   // 是否满意（0：有待改善 1：值得推荐）
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getIsHavingImg() {
		return isHavingImg;
	}

	public void setIsHavingImg(Integer isHavingImg) {
		this.isHavingImg = isHavingImg;
	}

	public Integer getPositionScore() {
		return positionScore;
	}

	public void setPositionScore(Integer positionScore) {
		this.positionScore = positionScore;
	}

	public Integer getFacilitiesScore() {
		return facilitiesScore;
	}

	public void setFacilitiesScore(Integer facilitiesScore) {
		this.facilitiesScore = facilitiesScore;
	}

	public Integer getServiceScore() {
		return serviceScore;
	}

	public void setServiceScore(Integer serviceScore) {
		this.serviceScore = serviceScore;
	}

	public Integer getHygieneScore() {
		return hygieneScore;
	}

	public void setHygieneScore(Integer hygieneScore) {
		this.hygieneScore = hygieneScore;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Long getTripMode() {
		return tripMode;
	}

	public void setTripMode(Long tripMode) {
		this.tripMode = tripMode;
	}

	public Integer getIsOk() {
		return isOk;
	}

	public void setIsOk(Integer isOk) {
		this.isOk = isOk;
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
