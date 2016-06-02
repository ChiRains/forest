package com.qcloud.component.sellercenter.model.query;
import java.util.Date;
public class MerchantEvaluationQuery {
    private long merchantId;
    private Date time;

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
