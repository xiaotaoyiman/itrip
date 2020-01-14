package cn.ekgc.ytrip.pojo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>区域字典实体类</b>
 * @version 3.0.0 2019-12-17
 * @since 3.0.0
 */
public class AreaDic implements Serializable {
	private static final long serialVersionUID = 1;
	private Long id;                         // 主键
	private String name;                     // 区域名称
	private String areaNo;                   // 区域编号
	private AreaDic parent;                  // 父级区域
	private Integer isActivated;             // 是否激活：0-未激活，1-已激活
	private Integer isTradingArea;           // 是否是商圈(0:不是 1:是)
	private Integer isHot;                   // 是否是热门城市(0:不是 1:是)
	private Integer level;                   // 区域级别(0:国家级 1:省级 2:市级 3:区/县)
	private Integer isChina;                 // 1:国内 2:国外
	private String pinyin;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	public AreaDic getParent() {
		return parent;
	}

	public void setParent(AreaDic parent) {
		this.parent = parent;
	}

	public Integer getIsActivated() {
		return isActivated;
	}

	public void setIsActivated(Integer isActivated) {
		this.isActivated = isActivated;
	}

	public Integer getIsTradingArea() {
		return isTradingArea;
	}

	public void setIsTradingArea(Integer isTradingArea) {
		this.isTradingArea = isTradingArea;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getIsChina() {
		return isChina;
	}

	public void setIsChina(Integer isChina) {
		this.isChina = isChina;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
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
