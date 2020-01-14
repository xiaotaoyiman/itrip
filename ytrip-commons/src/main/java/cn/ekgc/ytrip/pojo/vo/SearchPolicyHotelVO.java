package cn.ekgc.ytrip.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <b>酒店政策VO（酒店详情页）</b>
 */
@ApiModel(value = "SearchPolicyHotelVO",description = "查询酒店的政策")
public class SearchPolicyHotelVO implements Serializable  {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("[必填] 酒店政策")
    private String hotelPolicy;

    public String getHotelPolicy() {
        return hotelPolicy;
    }

    public void setHotelPolicy(String hotelPolicy) {
        this.hotelPolicy = hotelPolicy;
    }
}
