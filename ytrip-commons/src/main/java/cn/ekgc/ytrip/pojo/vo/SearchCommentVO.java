package cn.ekgc.ytrip.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <b>评论搜索条件VO</b>
 */
@ApiModel(value = "SearchCommentVO",description = "搜索评论VO")
public class SearchCommentVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("[必填] 酒店ID")
    private Long hotelId;

    @ApiModelProperty("[必填，注：接收数字类型] 是否有评论照片（0 无图片 1 有图片）")
    private Integer isHavingImg;//是否有评论图片（0 无图片 1 有图片）

    @ApiModelProperty("[必填，注：接收数字类型] 是否满意（0：有待改善 1：值得推荐）")
    private Integer isOk;//是否满意（0：有待改善 1：值得推荐）

    @ApiModelProperty("[必填] 页面容量")
    private Integer pageSize;

    @ApiModelProperty("[必填] 页码）")
    private Integer pageNo;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getIsHavingImg() {
        return isHavingImg;
    }

    public void setIsHavingImg(Integer isHavingImg) {
        this.isHavingImg = isHavingImg;
    }

    public Integer getIsOk() {
        return isOk;
    }

    public void setIsOk(Integer isOk) {
        this.isOk = isOk;
    }
}
