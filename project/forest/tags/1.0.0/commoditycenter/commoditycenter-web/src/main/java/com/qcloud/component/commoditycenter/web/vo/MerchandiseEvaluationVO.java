package com.qcloud.component.commoditycenter.web.vo;

public class MerchandiseEvaluationVO {

    // 商品id
    private long   merchandiseId;

    // 评价内容
    private String content;

    // 星级(1-5星，星值=(1-5)*10)
    private int    star;

    // 评价时间
    private String time;

    // 规格
    private String specifications;

    // 评价人
    private String userName;

    // 头像
    private String headImage;

    // 差评数量
    private long   lowEvaluation;

    // 中评数量
    private long   middleEvaluation;

    // 好评数量
    private long   goodEvaluation;

    public void setMerchandiseId(long merchandiseId) {

        this.merchandiseId = merchandiseId;
    }

    public long getMerchandiseId() {

        return merchandiseId;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public String getContent() {

        return content;
    }

    public void setStar(int star) {

        this.star = star;
    }

    public int getStar() {

        return star;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    public String getSpecifications() {

        return specifications;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {

        this.time = time;
    }

    public String getHeadImage() {

        return headImage;
    }

    public void setHeadImage(String headImage) {

        this.headImage = headImage;
    }

    public long getLowEvaluation() {

        return lowEvaluation;
    }

    public void setLowEvaluation(long lowEvaluation) {

        this.lowEvaluation = lowEvaluation;
    }

    public long getMiddleEvaluation() {

        return middleEvaluation;
    }

    public void setMiddleEvaluation(long middleEvaluation) {

        this.middleEvaluation = middleEvaluation;
    }

    public long getGoodEvaluation() {

        return goodEvaluation;
    }

    public void setGoodEvaluation(long goodEvaluation) {

        this.goodEvaluation = goodEvaluation;
    }
}
