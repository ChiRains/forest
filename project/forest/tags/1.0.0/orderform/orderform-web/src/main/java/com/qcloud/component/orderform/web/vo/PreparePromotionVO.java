package com.qcloud.component.orderform.web.vo;
import java.util.ArrayList;
import java.util.List;
// 促销相关
public class PreparePromotionVO {
    // 促销token
    private String                         promotionTokenId;
    // 促销名称
    private String                         name;
    // 可选促销方案列表
    private List<PreparePromotionDetailVO> list = new ArrayList<PreparePromotionDetailVO>();
    
}
