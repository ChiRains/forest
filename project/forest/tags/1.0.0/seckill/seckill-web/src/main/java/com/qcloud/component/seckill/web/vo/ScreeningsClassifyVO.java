package com.qcloud.component.seckill.web.vo;

import java.util.ArrayList;
import java.util.List;

public class ScreeningsClassifyVO extends ScreeningsVO {

    private List<MerchandiseClassifyVO> classifyList = new ArrayList<MerchandiseClassifyVO>();

    private String                      shareUrl;

    public List<MerchandiseClassifyVO> getClassifyList() {

        return classifyList;
    }

    public void setClassifyList(List<MerchandiseClassifyVO> classifyList) {

        this.classifyList = classifyList;
    }

    public String getShareUrl() {

        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {

        this.shareUrl = shareUrl;
    }
}
