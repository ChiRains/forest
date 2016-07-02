package com.qcloud.component.brokerage.web.vo.admin;
import java.util.Date;
import java.math.BigDecimal;
public class AdminUserTeamVO {
    // 用户ID
    private long   userId;
    private String userStr;
    // 上级
    private long   leader;
    private String leaderStr;
    private String childrenStr;
    private String parentStr;

    public AdminUserTeamVO() {
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setLeader(long leader) {
        this.leader = leader;
    }

    public long getLeader() {
        return leader;
    }

    public String getUserStr() {
        return userStr;
    }

    public void setUserStr(String userStr) {
        this.userStr = userStr;
    }

    public String getLeaderStr() {
        return leaderStr;
    }

    public void setLeaderStr(String leaderStr) {
        this.leaderStr = leaderStr;
    }

    public String getChildrenStr() {
        return childrenStr;
    }

    public void setChildrenStr(String childrenStr) {
        this.childrenStr = childrenStr;
    }

    public String getParentStr() {
        return parentStr;
    }

    public void setParentStr(String parentStr) {
        this.parentStr = parentStr;
    }
}
