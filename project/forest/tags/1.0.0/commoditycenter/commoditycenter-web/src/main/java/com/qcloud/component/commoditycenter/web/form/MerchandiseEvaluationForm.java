package com.qcloud.component.commoditycenter.web.form;

public class MerchandiseEvaluationForm {

    //
    private Long   toEvaluationId;

    // 评价内容
    private String content;

    // 星级(1-5星，星值=(1-5)*10)
    private int    star;

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
}
