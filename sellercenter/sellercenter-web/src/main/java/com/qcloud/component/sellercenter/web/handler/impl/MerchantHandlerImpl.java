//package com.qcloud.component.sellercenter.web.handler.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import com.qcloud.component.filesdk.FileSDKClient;
//import com.qcloud.component.publicdata.PublicdataClient;
//import com.qcloud.component.publicdata.model.Classify;
//import com.qcloud.component.publicservice.ShareClient;
//import com.qcloud.component.sellercenter.model.Merchant;
//import com.qcloud.component.sellercenter.model.key.TypeEnum.CertifiedType;
//import com.qcloud.component.sellercenter.model.key.TypeEnum.CommodityAuditingType;
//import com.qcloud.component.sellercenter.model.key.TypeEnum.DistributionType;
//import com.qcloud.component.sellercenter.model.key.TypeEnum.ExternalUrlType;
//import com.qcloud.component.sellercenter.model.key.TypeEnum.MerchantIsIncludePost;
//import com.qcloud.component.sellercenter.model.key.TypeEnum.MerchantStatelType;
//import com.qcloud.component.sellercenter.model.key.TypeEnum.NoReasonType;
//import com.qcloud.component.sellercenter.model.key.TypeEnum.NotifyType;
//import com.qcloud.component.sellercenter.model.key.TypeEnum.SpecialServiceType;
//import com.qcloud.component.sellercenter.service.MemberService;
//import com.qcloud.component.sellercenter.web.handler.MerchantHandler;
//import com.qcloud.component.sellercenter.web.vo.MerchantVO;
//import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantVO;
//import com.qcloud.pirates.core.json.Json;
//import com.qcloud.pirates.util.DateUtil;
//
//@Component
//public class MerchantHandlerImpl implements MerchantHandler {
//
//    @Autowired
//    private PublicdataClient publicdataClient;
//
//    @Autowired
//    private FileSDKClient    fileSDKClient;
//
//    @Autowired
//    private MemberService    memberService;
//
//    @Autowired
//    private ShareClient      shareClient;
//
//    @Override
//    public List<MerchantVO> toVOList(List<Merchant> list) {
//
//        List<MerchantVO> voList = new ArrayList<MerchantVO>();
//        for (Merchant merchant : list) {
//            voList.add(toVO(merchant));
//        }
//        return voList;
//    }
//
//    @Override
//    public MerchantVO toVO(Merchant merchant) {
//
//        String json = Json.toJson(merchant);
//        MerchantVO vo = Json.toObject(json, MerchantVO.class, true);
//        vo.setAddress(merchant.getProvince() + merchant.getCity() + merchant.getDistrict() + merchant.getAddress());
//        if (StringUtils.isNotEmpty(vo.getLogo())) {
//            vo.setLogo(fileSDKClient.getFileServerUrl() + vo.getLogo());
//        } else {
//            vo.setLogo("");
//        }
//        if (StringUtils.isNotEmpty(vo.getImage())) {
//            vo.setImage(fileSDKClient.getFileServerUrl() + vo.getImage());
//        } else {
//            vo.setImage("");
//        }
//        vo.setShareUrl(shareClient.getShareDomain() + "/merchantShare.html?merchantId=" + merchant.getId());
//        return vo;
//    }
//
//    @Override
//    public List<AdminMerchantVO> toVOList4Admin(List<Merchant> list) {
//
//        List<AdminMerchantVO> voList = new ArrayList<AdminMerchantVO>();
//        for (Merchant adminMerchant : list) {
//            voList.add(toVO4Admin(adminMerchant));
//        }
//        return voList;
//    }
//
//    @Override
//    public AdminMerchantVO toVO4Admin(Merchant merchant) {
//
//        String json = Json.toJson(merchant);
//        AdminMerchantVO vo = Json.toObject(json, AdminMerchantVO.class, true);
//        String commodityAuditingStr = CommodityAuditingType.UNNEED.getValue();
//        if (CommodityAuditingType.NEED.getKey() == merchant.getCommodityAuditing()) {
//            commodityAuditingStr = CommodityAuditingType.NEED.getValue();
//        }
//        vo.setCommodityAuditingStr(commodityAuditingStr);
//        String distributionStr = DistributionType.NO.getValue();
//        if (DistributionType.YES.getKey() == merchant.getDistribution()) {
//            distributionStr = DistributionType.YES.getValue();
//        }
//        vo.setDistributionStr(distributionStr);
//        String notifyStr = NotifyType.No.getName();
//        if (NotifyType.Yes.getKey() == merchant.getNotify()) {
//            notifyStr = NotifyType.Yes.getName();
//        }
//        vo.setNotifyStr(notifyStr);
//        String isIncludePostStr= MerchantIsIncludePost.NO.getName();
//        if(MerchantIsIncludePost.YES.getKey()==merchant.getIsIncludePost()){
//            isIncludePostStr=MerchantIsIncludePost.YES.getName();
//        }
//        vo.setIsIncludePostStr(isIncludePostStr);
//        String isCertifiedStr=CertifiedType.NO.getName();
//        if(CertifiedType.YES.getKey()==merchant.getIsCertified()){
//            isCertifiedStr=CertifiedType.YES.getName();
//        }
//        vo.setIsCertifiedStr(isCertifiedStr);
//        String isSpecialServiceStr= SpecialServiceType.SPECIAL.getName();
//        if(SpecialServiceType.NUSPECIAL.getKey()==merchant.getIsSpecialService()){
//            isSpecialServiceStr=SpecialServiceType.NUSPECIAL.getName();
//        }
//        vo.setIsSpecialServiceStr(isSpecialServiceStr);
//        
//        String isExternalUrlStr=ExternalUrlType.NOREASON.getName();
//        if(ExternalUrlType.REASON.getKey()==merchant.getIsExternalUrl()){
//            isExternalUrlStr=ExternalUrlType.REASON.getName();
//        }
//        vo.setIsExternalUrlStr(isExternalUrlStr);
//        
//        String stateStr=MerchantStatelType.NO.getName();
//        if(MerchantStatelType.YES.getKey()==merchant.getState()){
//            stateStr=MerchantStatelType.YES.getName();
//        }
//        vo.setStateStr(stateStr);
//        String isNoReasonStr=NoReasonType.NOREASON.getName();
//        if(NoReasonType.REASON.getKey()==merchant.getIsNoReason()){
//            isNoReasonStr=NoReasonType.REASON.getName();
//        }
//        vo.setIsNoReasonStr(isNoReasonStr);
//        
//        Classify classify = publicdataClient.getClassify(merchant.getClassifyId());
//        vo.setClassifyStr(classify == null ? "" : classify.getName());
//        String logoUid = fileSDKClient.urlToUid(merchant.getLogo());
//        vo.setLogoUid(logoUid);
//        vo.setLogo(fileSDKClient.getFileServerUrl() + vo.getLogo());
//        if (memberService.get(merchant.getUserId()) != null) {
//            vo.setUserMobile(memberService.get(merchant.getUserId()).getMobile());
//        }
//        vo.setImageUid(fileSDKClient.urlToUid(merchant.getImage()));
//        vo.setValidDateStr(DateUtil.date2String(vo.getValidDate(), "yyyy-MM-dd"));
//        return vo;
//    }
//}
