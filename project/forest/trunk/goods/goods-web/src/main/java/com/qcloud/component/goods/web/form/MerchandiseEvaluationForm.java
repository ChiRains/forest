package com.qcloud.component.goods.web.form;

import java.util.ArrayList;
import java.util.List;

public class MerchandiseEvaluationForm {

    // 待评价id
    private Long         toEvaluationId;

    // 评价内容
    private String       content;

    // 星级(1-5星，星值=(1-5)*10)
    private int          star;

    private List<String> images = new ArrayList<String>();

    // 待追评id
    private Long         appEvaluationId;

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public int getStar() {

        return star;
    }

    public void setStar(int star) {

        this.star = star;
    }

    public Long getToEvaluationId() {

        return toEvaluationId;
    }

    public void setToEvaluationId(Long toEvaluationId) {

        this.toEvaluationId = toEvaluationId;
    }

    public List<String> getImages() {

        return images;
    }

    public void setImages(List<String> images) {

        this.images = images;
    }

    public Long getAppEvaluationId() {

        return appEvaluationId;
    }

    public void setAppEvaluationId(Long appEvaluationId) {

        this.appEvaluationId = appEvaluationId;
    }
}
