package cn.ekgc.ytrip.pojo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>酒店商圈实体类</b>
 * @version 4.0.0
 * @since 4.0.0
 */
public class HotelTradingArea implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;                                    // 主键
	private Hotel hotel;                                // 酒店
	private AreaDic areaDic;                            // 商圈
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

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public AreaDic getAreaDic() {
		return areaDic;
	}

	public void setAreaDic(AreaDic areaDic) {
		this.areaDic = areaDic;
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
