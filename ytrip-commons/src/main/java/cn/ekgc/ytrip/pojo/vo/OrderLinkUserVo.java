package cn.ekgc.ytrip.pojo.vo;

import java.io.Serializable;

/**
 * <b>根据订单查询联系人返回VO</b>
 */
public class OrderLinkUserVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long linkUserId;
    private String linkUserName;

    public Long getLinkUserId() {
        return linkUserId;
    }

    public void setLinkUserId(Long linkUserId) {
        this.linkUserId = linkUserId;
    }

    public String getLinkUserName() {
        return linkUserName;
    }

    public void setLinkUserName(String linkUserName) {
        this.linkUserName = linkUserName;
    }
}
