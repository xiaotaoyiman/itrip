package cn.ekgc.ytrip.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <b>验证房间库存的传入参数VO</b>
 */
@ApiModel(value = "ValidateRoomStoreVO",description = "验证房屋库存是否存足的VO")
public class ValidateRoomStoreVO implements Serializable{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("[必填，注：接收数字类型 酒店ID")
    private Long hotelId;
    @ApiModelProperty("[必填，注：接收数字类型 房间ID")
    private Long roomId;
    @ApiModelProperty("[必填，注：接收日期类型 入住时间")
    private String checkInDate;
    @ApiModelProperty("[必填，注：接收日期类型 退房时间")
    private String checkOutDate;
    @ApiModelProperty("[必填，默认请传1")
    private Integer count;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
