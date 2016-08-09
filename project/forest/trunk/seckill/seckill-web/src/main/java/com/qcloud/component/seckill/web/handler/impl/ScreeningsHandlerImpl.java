package com.qcloud.component.seckill.web.handler.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.publicservice.ShareClient;
import com.qcloud.component.seckill.model.MerchandiseSeckill;
import com.qcloud.component.seckill.model.Screenings;
import com.qcloud.component.seckill.model.ScreeningsSlide;
import com.qcloud.component.seckill.service.MerchandiseSeckillService;
import com.qcloud.component.seckill.service.ScreeningsSlideService;
import com.qcloud.component.seckill.service.SeckillConfigService;
import com.qcloud.component.seckill.web.handler.MerchandiseSeckillHandler;
import com.qcloud.component.seckill.web.handler.ScreeningsHandler;
import com.qcloud.component.seckill.web.vo.MerchandiseClassifyVO;
import com.qcloud.component.seckill.web.vo.MerchandiseSeckillVO;
import com.qcloud.component.seckill.web.vo.ScreeningsClassifyVO;
import com.qcloud.component.seckill.web.vo.ScreeningsIndexVO;
import com.qcloud.component.seckill.web.vo.ScreeningsListVO;
import com.qcloud.component.seckill.web.vo.ScreeningsMerchandiseVO;
import com.qcloud.component.seckill.web.vo.admin.AdminScreeningsVO;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.DateUtil;

@Component
public class ScreeningsHandlerImpl implements ScreeningsHandler {

    @Autowired
    MerchandiseSeckillService merchandiseSeckillService;

    @Autowired
    MerchandiseSeckillHandler merchandiseSeckillHandler;

    @Autowired
    PublicdataClient          publicdataClient;

    @Autowired
    FileSDKClient             fileSDKClient;

    @Autowired
    ScreeningsSlideService    screeningsSlideService;

    @Autowired
    private ShareClient       shareClient;

    @Override
    public List<ScreeningsListVO> toVOList(List<Screenings> list) {

        Date now = new Date();
        List<ScreeningsListVO> voList = new ArrayList<ScreeningsListVO>();
        for (Screenings screenings : list) {
            ScreeningsListVO screeningsListVO = new ScreeningsListVO();
            screeningsListVO.setId(screenings.getId());
            screeningsListVO.setBeginTimeStr(DateUtil.date2String(screenings.getBeginTime()));
            screeningsListVO.setEndTimeStr(DateUtil.date2String(screenings.getEndTime()));
            int state = calculateState(screenings, now);
            screeningsListVO.setState(state);
            screeningsListVO.setStateStr(calculateStateStr(state));
            voList.add(screeningsListVO);
        }
        return voList;
    }

    private int calculateState(Screenings screenings, Date now) {

        if (screenings.getEndTime().before(now)) {
            return 1;
        } else if (screenings.getBeginTime().before(now) && now.before(screenings.getEndTime())) {
            return 2;
        } else {
            return 3;
        }
    }

    @Autowired
    SeckillConfigService seckillConfigService;

    private String calculateStateStr(int state) {

        if (state == 1) {
            return seckillConfigService.get().getKilledString();
        } else if (state == 2) {
            return seckillConfigService.get().getKillingString();
        } else {
            return seckillConfigService.get().getToKillString();
        }
    }

    @Override
    public List<AdminScreeningsVO> toVOList4Admin(List<Screenings> list) {

        List<AdminScreeningsVO> voList = new ArrayList<AdminScreeningsVO>();
        for (Screenings adminScreenings : list) {
            voList.add(toVO4Admin(adminScreenings));
        }
        return voList;
    }

    @Override
    public AdminScreeningsVO toVO4Admin(Screenings screenings) {

        String json = Json.toJson(screenings);
        return Json.toObject(json, AdminScreeningsVO.class, true);
    }

    @Override
    public ScreeningsClassifyVO toVO4Classify(Screenings screenings) {

        Date now = new Date();
        ScreeningsClassifyVO screeningsVO = new ScreeningsClassifyVO();
        screeningsVO.setBeginTimeStr(DateUtil.date2String(screenings.getBeginTime()));
        screeningsVO.setEndTimeStr(DateUtil.date2String(screenings.getEndTime()));
        screeningsVO.setId(screenings.getId());
        screeningsVO.setState(calculateState(screenings, now));
        screeningsVO.setNowStr(DateUtil.date2String(now));
        List<MerchandiseSeckill> list = merchandiseSeckillService.listByScreenings(screenings.getId());
        Set<Long> classifyIdSet = new HashSet<Long>();
        for (MerchandiseSeckill merchandiseSeckill : list) {
            classifyIdSet.add(merchandiseSeckill.getMallClassifyId());
        }
        List<MerchandiseClassifyVO> mcList = new ArrayList<MerchandiseClassifyVO>();
        for (Long classifyId : classifyIdSet) {
            List<MerchandiseSeckill> merchandiseList = new ArrayList<MerchandiseSeckill>();
            for (MerchandiseSeckill merchandiseSeckill : list) {
                if (merchandiseSeckill.getMallClassifyId() == classifyId) {
                    merchandiseList.add(merchandiseSeckill);
                }
            }
            List<MerchandiseSeckillVO> merchandiseVOList = merchandiseSeckillHandler.toVOList(merchandiseList);
            Classify classify = publicdataClient.getClassify(classifyId);
            MerchandiseClassifyVO merchandiseClassifyVO = new MerchandiseClassifyVO();
            merchandiseClassifyVO.setMerchandiseList(merchandiseVOList);
            merchandiseClassifyVO.setImage(fileSDKClient.getFileServerUrl() + classify.getImage());
            merchandiseClassifyVO.setName(classify.getName());
            mcList.add(merchandiseClassifyVO);
        }
        screeningsVO.setClassifyList(mcList);
        //
        List<ScreeningsSlide> slideList = screeningsSlideService.listByScreenings(screenings.getId());
        List<String> screeningsSlideList = new ArrayList<String>();
        for (ScreeningsSlide screeningsSlide : slideList) {
            screeningsSlideList.add(fileSDKClient.getFileServerUrl() + screeningsSlide.getImage());
        }
        screeningsVO.setScreeningsSlideList(screeningsSlideList);
        screeningsVO.setShareUrl(shareClient.getShareDomain() + "/seckillShare.html?screeningsId=" + screenings.getId());
        return screeningsVO;
    }

    @Override
    public ScreeningsMerchandiseVO toVO4Merchandise(Screenings screenings) {

        Date now = new Date();
        ScreeningsMerchandiseVO screeningsVO = new ScreeningsMerchandiseVO();
        screeningsVO.setBeginTimeStr(DateUtil.date2String(screenings.getBeginTime(), "HH:mm:ss"));
        screeningsVO.setEndTimeStr(DateUtil.date2String(screenings.getEndTime(), "HH:mm:ss"));
        screeningsVO.setId(screenings.getId());
        screeningsVO.setState(calculateState(screenings, now));
        screeningsVO.setNowStr(DateUtil.date2String(now));
        List<MerchandiseSeckill> merchandiseList = merchandiseSeckillService.listByScreenings(screenings.getId());
        List<MerchandiseSeckillVO> merchandiseVOList = merchandiseSeckillHandler.toVOList(merchandiseList);
        screeningsVO.setList(merchandiseVOList);
        //
        List<ScreeningsSlide> slideList = screeningsSlideService.listByScreenings(screenings.getId());
        List<Map<String, Object>> screeningsSlideMapList = new ArrayList<Map<String, Object>>();
        for (ScreeningsSlide screeningsSlide : slideList) {
            if (StringUtils.isNotEmpty(screeningsSlide.getImage())) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("image", fileSDKClient.getFileServerUrl() + screeningsSlide.getImage());
                screeningsSlideMapList.add(map);
            }
        }
        screeningsVO.setScreeningsSlideMapList(screeningsSlideMapList);
        return screeningsVO;
    }

    @Override
    public ScreeningsIndexVO toVO4Index(Screenings screenings) {

        Date now = new Date();
        ScreeningsIndexVO screeningsVO = new ScreeningsIndexVO();
        screeningsVO.setBeginTimeStr(DateUtil.date2String(screenings.getBeginTime()));
        screeningsVO.setEndTimeStr(DateUtil.date2String(screenings.getEndTime()));
        screeningsVO.setId(screenings.getId());
        screeningsVO.setState(calculateState(screenings, now));
        screeningsVO.setNowStr(DateUtil.date2String(now));
        List<MerchandiseSeckill> list = merchandiseSeckillService.listByScreenings(screenings.getId());
        Set<Long> classifyIdSet = new HashSet<Long>();
        for (MerchandiseSeckill merchandiseSeckill : list) {
            classifyIdSet.add(merchandiseSeckill.getMallClassifyId());
        }
        for (Long classifyId : classifyIdSet) {
            List<MerchandiseSeckill> merchandiseList = new ArrayList<MerchandiseSeckill>();
            for (MerchandiseSeckill merchandiseSeckill : list) {
                if (merchandiseSeckill.getMallClassifyId() == classifyId) {
                    merchandiseList.add(merchandiseSeckill);
                }
            }
            List<MerchandiseSeckillVO> merchandiseVOList = merchandiseSeckillHandler.toVOList(merchandiseList);
            if (CollectionUtils.isNotEmpty(merchandiseVOList)) {
                screeningsVO.setMerchandiseSeckillVO(merchandiseVOList.get(0));
            }
        }
        //
        List<ScreeningsSlide> slideList = screeningsSlideService.listByScreenings(screenings.getId());
        List<String> screeningsSlideList = new ArrayList<String>();
        for (ScreeningsSlide screeningsSlide : slideList) {
            screeningsSlideList.add(fileSDKClient.getFileServerUrl() + screeningsSlide.getImage());
        }
        screeningsVO.setScreeningsSlideList(screeningsSlideList);
        // screeningsVO.setShareUrl(shareClient.getShareDomain() + "/seckillShare.html?screeningsId=" + screenings.getId());
        Date last = screenings.getEndTime();
        long diff = last.getTime() - now.getTime();
        long hours = diff / 1000 / 60 / 60;
        long minutes = diff / 1000 / 60 % 60;
        long seconds = diff / 1000 % 60;
        screeningsVO.setMillsMinutes(diff);
        screeningsVO.setHours(hours);
        screeningsVO.setMinutes(minutes);
        screeningsVO.setSeconds(seconds);
        return screeningsVO;
    }

    public static void main(String[] args) {

        Date last = DateUtil.str2Date("2016-06-29 10:00:00", "yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        long diff = now.getTime() - last.getTime();
        System.out.println(diff);
        System.out.println(diff / 1000 / 60 / 60 + "时");
        System.out.println(diff / 1000 / 60 % 60 + "分");
        System.out.println(diff / 1000 % 60 + "秒");
    }

    @Override
    public List<MerchandiseSeckillVO> getCrazySeckill(Screenings screenings) {

        if (screenings != null) {
            List<MerchandiseSeckill> merchandiseList = merchandiseSeckillService.listByScreenings(screenings.getId());
            List<MerchandiseSeckillVO> merchandiseVOList = merchandiseSeckillHandler.toVOList(merchandiseList);
            if (CollectionUtils.isNotEmpty(merchandiseVOList)) {
                Collections.sort(merchandiseVOList, new Comparator<MerchandiseSeckillVO>() {

                    @Override
                    public int compare(MerchandiseSeckillVO arg0, MerchandiseSeckillVO arg1) {

                        return String.valueOf(arg1.getSalesVolume()).compareTo(String.valueOf(arg0.getSalesVolume()));
                    }
                });
                return merchandiseVOList;
            } else {
                return new ArrayList<MerchandiseSeckillVO>();
            }
        } else {
            return new ArrayList<MerchandiseSeckillVO>();
        }
    }
}
