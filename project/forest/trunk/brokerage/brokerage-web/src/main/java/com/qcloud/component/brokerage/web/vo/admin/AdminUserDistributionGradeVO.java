package com.qcloud.component.brokerage.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminUserDistributionGradeVO {

    // ID
    private long id;

    // 用户ID
    private long userId;

    // 级别ID
    private long gradeId;

    // 注册时间
    private Date createTime;

    // 升级时间
    private Date upgradeTime;

    // 有效时间
    private Date effectiveTime;

    public AdminUserDistributionGradeVO() {

    }

    public AdminUserDistributionGradeVO(long id, long userId, long gradeId, Date createTime, Date upgradeTime, Date effectiveTime) {

        this.id = id;
        this.userId = userId;
        this.gradeId = gradeId;
        this.createTime = createTime;
        this.upgradeTime = upgradeTime;
        this.effectiveTime = effectiveTime;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public long getUserId() {

        return userId;
    }

    public void setGradeId(long gradeId) {

        this.gradeId = gradeId;
    }

    public long getGradeId() {

        return gradeId;
    }

    public void setCreateTime(Date createTime) {

        this.createTime = createTime;
    }

    public Date getCreateTime() {

        return createTime;
    }

    public void setUpgradeTime(Date upgradeTime) {

        this.upgradeTime = upgradeTime;
    }

    public Date getUpgradeTime() {

        return upgradeTime;
    }

    public void setEffectiveTime(Date effectiveTime) {

        this.effectiveTime = effectiveTime;
    }

    public Date getEffectiveTime() {

        return effectiveTime;
    }
}
