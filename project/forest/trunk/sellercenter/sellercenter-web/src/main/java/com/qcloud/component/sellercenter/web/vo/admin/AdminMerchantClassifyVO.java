package com.qcloud.component.sellercenter.web.vo.admin;
import java.util.Date;
import java.math.BigDecimal;
public class AdminMerchantClassifyVO {
    private long   id;
    // 商家
    private long   merchantId;
    // 商品分类
    private long   classifyId;
    private String classify;
    private String checked;
    private String path;

    public AdminMerchantClassifyVO() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setClassifyId(long classifyId) {
        this.classifyId = classifyId;
    }

    public long getClassifyId() {
        return classifyId;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
