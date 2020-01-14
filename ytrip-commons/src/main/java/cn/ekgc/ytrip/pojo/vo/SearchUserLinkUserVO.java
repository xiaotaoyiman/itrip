package cn.ekgc.ytrip.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <b>查询常用联系人VO</b>
 */
@ApiModel(value = "SearchUserLinkUserVO",description = "查询常用联系人")
public class SearchUserLinkUserVO implements Serializable  {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("[必填] 常用刚联系人姓名")
    private String linkUserName;

    public String getLinkUserName() {
        return linkUserName;
    }

    public void setLinkUserName(String linkUserName) {
        this.linkUserName = linkUserName;
    }
}
