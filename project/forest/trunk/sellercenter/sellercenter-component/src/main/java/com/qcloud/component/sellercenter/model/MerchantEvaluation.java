package com.qcloud.component.sellercenter.model;

import java.util.Date;
import java.math.BigDecimal;

public class MerchantEvaluation {

    private long   id;

    // 评价表id
    private long   evaluationId;

    // 商家id
    private long   merchantId;

    // 商品id
    private long   merchandiseId;

    // 评价时间
    private Date   evaluationTime;

    private String content;

    public MerchantEvaluation() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setEvaluationId(long evaluationId) {

        this.evaluationId = evaluationId;
    }

    public long getEvaluationId() {

        return evaluationId;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setMerchandiseId(long merchandiseId) {

        this.merchandiseId = merchandiseId;
    }

    public long getMerchandiseId() {

        return merchandiseId;
    }

    public void setEvaluationTime(Date evaluationTime) {

        this.evaluationTime = evaluationTime;
    }

    public Date getEvaluationTime() {

        return evaluationTime;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }
}
