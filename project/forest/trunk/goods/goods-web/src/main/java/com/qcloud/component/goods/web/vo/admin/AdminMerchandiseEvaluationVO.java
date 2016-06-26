package com.qcloud.component.goods.web.vo.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminMerchandiseEvaluationVO {

    private long          id;

    // 商品名称
    private long          merchandiseId;

    // 商品名称
    private String        merchandiseName;

    // 评价内容
    private String        content;

    // 星级(1-5星，星值=(1-5)*10)
    private int           star;

    // 评价时间
    private Date          time;

    // 审核状态(0:未处理,1:未通过,2:已通过)
    private int           status;

    // 规格
    private String        specifications;

    // 评价人
    private String        userName;

    private Long          userId;

    private List<Integer> stars = new ArrayList<Integer>();

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
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

    public void setTime(Date time) {

        this.time = time;
    }

    public Date getTime() {

        return time;
    }

    public void setStatus(int status) {

        this.status = status;
    }

    public int getStatus() {

        return status;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    public String getSpecifications() {

        return specifications;
    }

    public String getMerchandiseName() {

        return merchandiseName;
    }

    public void setMerchandiseName(String merchandiseName) {

        this.merchandiseName = merchandiseName;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public List<Integer> getStars() {

        return stars;
    }

    public void setStars(List<Integer> stars) {

        this.stars = stars;
    }

    public long getMerchandiseId() {

        return merchandiseId;
    }

    public void setMerchandiseId(long merchandiseId) {

        this.merchandiseId = merchandiseId;
    }

    
    public Long getUserId() {
    
        return userId;
    }

    
    public void setUserId(Long userId) {
    
        this.userId = userId;
    }
}
