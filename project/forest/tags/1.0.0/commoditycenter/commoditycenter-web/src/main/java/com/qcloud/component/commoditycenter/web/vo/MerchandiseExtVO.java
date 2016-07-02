package com.qcloud.component.commoditycenter.web.vo;

import java.util.ArrayList;
import java.util.List;

public class MerchandiseExtVO {

    private long                     attributeId0 = -1L;

    // 值
    private String                   value0       = "";

    private long                     attributeId1 = -1L;

    // 值
    private String                   value1       = "";

    private long                     attributeId2 = -1L;

    // 值
    private String                   value2       = "";

    private long                     stock;

    private boolean                  enable;

    private List<String>             imageList    = new ArrayList<String>();

    private CombinationMerchandiseVO combinationMerchandiseVO;

    private long                     goodEvaluationNumber;

    private long                     middleEvaluationNumber;

    private long                     lowEvaluationNumber;

    private boolean                  certified;

    private boolean                  specialService;

    private boolean                  noReason;

    private String                   certifiedDesc;

    private String                   specialServiceDesc;

    private String                   noReasonDesc;

    private boolean                  freeShipping;

    private String                   shareUrl;

    public CombinationMerchandiseVO getCombinationMerchandiseVO() {

        return combinationMerchandiseVO;
    }

    public void setCombinationMerchandiseVO(CombinationMerchandiseVO combinationMerchandiseVO) {

        this.combinationMerchandiseVO = combinationMerchandiseVO;
    }

    public long getStock() {

        return stock;
    }

    public void setStock(long stock) {

        this.stock = stock;
    }

    public List<String> getImageList() {

        return imageList;
    }

    public void setImageList(List<String> imageList) {

        this.imageList = imageList;
    }

    public long getAttributeId0() {

        return attributeId0;
    }

    public void setAttributeId0(long attributeId0) {

        this.attributeId0 = attributeId0;
    }

    public String getValue0() {

        return value0;
    }

    public void setValue0(String value0) {

        this.value0 = value0;
    }

    public long getAttributeId1() {

        return attributeId1;
    }

    public void setAttributeId1(long attributeId1) {

        this.attributeId1 = attributeId1;
    }

    public String getValue1() {

        return value1;
    }

    public void setValue1(String value1) {

        this.value1 = value1;
    }

    public long getAttributeId2() {

        return attributeId2;
    }

    public void setAttributeId2(long attributeId2) {

        this.attributeId2 = attributeId2;
    }

    public String getValue2() {

        return value2;
    }

    public void setValue2(String value2) {

        this.value2 = value2;
    }

    public boolean isEnable() {

        return enable;
    }

    public void setEnable(boolean enable) {

        this.enable = enable;
    }

    public long getGoodEvaluationNumber() {

        return goodEvaluationNumber;
    }

    public void setGoodEvaluationNumber(long goodEvaluationNumber) {

        this.goodEvaluationNumber = goodEvaluationNumber;
    }

    public long getMiddleEvaluationNumber() {

        return middleEvaluationNumber;
    }

    public void setMiddleEvaluationNumber(long middleEvaluationNumber) {

        this.middleEvaluationNumber = middleEvaluationNumber;
    }

    public long getLowEvaluationNumber() {

        return lowEvaluationNumber;
    }

    public void setLowEvaluationNumber(long lowEvaluationNumber) {

        this.lowEvaluationNumber = lowEvaluationNumber;
    }

    public boolean isCertified() {

        return certified;
    }

    public void setCertified(boolean certified) {

        this.certified = certified;
    }

    public boolean isSpecialService() {

        return specialService;
    }

    public void setSpecialService(boolean specialService) {

        this.specialService = specialService;
    }

    public boolean isNoReason() {

        return noReason;
    }

    public void setNoReason(boolean noReason) {

        this.noReason = noReason;
    }

    public String getCertifiedDesc() {

        return certifiedDesc;
    }

    public void setCertifiedDesc(String certifiedDesc) {

        this.certifiedDesc = certifiedDesc;
    }

    public String getSpecialServiceDesc() {

        return specialServiceDesc;
    }

    public void setSpecialServiceDesc(String specialServiceDesc) {

        this.specialServiceDesc = specialServiceDesc;
    }

    public String getNoReasonDesc() {

        return noReasonDesc;
    }

    public void setNoReasonDesc(String noReasonDesc) {

        this.noReasonDesc = noReasonDesc;
    }

    public boolean isFreeShipping() {

        return freeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {

        this.freeShipping = freeShipping;
    }

    public String getShareUrl() {

        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {

        this.shareUrl = shareUrl;
    }
}
