package cn.ekgc.ytrip.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <b>酒店设施VO（酒店详情页）</b>
 */
@ApiModel(value = "SearchDetailsFacilitiesPolicyHotelVO",description = "查询酒店的设施")
public class SearchFacilitiesHotelVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("[必填] 酒店设施")
    private String facilities;

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }
}
