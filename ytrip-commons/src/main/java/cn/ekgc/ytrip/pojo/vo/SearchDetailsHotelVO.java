package cn.ekgc.ytrip.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <b>酒店特色和介绍VO</b>
 */
@ApiModel(value = "SearchDetailsHotelVO",description = "查询酒店的特色和介绍")

public class SearchDetailsHotelVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("[必填] 特色名称")
    private String name;
    @ApiModelProperty("[必填] 特色描述")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
