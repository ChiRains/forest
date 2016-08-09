package com.qcloud.component.seckill.web.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.seckill.model.Screenings;
import com.qcloud.component.seckill.model.SeckillConfig;
import com.qcloud.component.seckill.service.ScreeningsService;
import com.qcloud.component.seckill.service.SeckillConfigService;
import com.qcloud.component.seckill.web.handler.ScreeningsHandler;
import com.qcloud.component.seckill.web.vo.MerchandiseSeckillVO;
import com.qcloud.component.seckill.web.vo.ScreeningsListVO;
import com.qcloud.component.seckill.web.vo.ScreeningsMerchandiseVO;
import com.qcloud.component.seckill.web.vo.ScreeningsVO;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;

@Controller
@RequestMapping(value = SeckillController.DIR)
public class SeckillController {

    public static final String   DIR = "/seckill";

    @Autowired
    private ScreeningsService    screeningsService;

    @Autowired
    private ScreeningsHandler    screeningsHandler;

    @Autowired
    private SeckillConfigService seckillConfigService;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listByScreenings4Classify(Long screeningsId, Integer size) {

        AssertUtil.assertNotNull(screeningsId, "场次ID不能为空.");
        SeckillConfig seckillConfig = seckillConfigService.get();
        int screeningsMinSize = seckillConfig.getScreeningsMinSize();
        size = size == null || size <= 0 || size > screeningsMinSize ? screeningsMinSize : size;
        List<Screenings> list = screeningsService.listToday(size);
        List<ScreeningsListVO> voList = screeningsHandler.toVOList(list);
        Screenings current = null;
        for (Screenings screenings : list) {
            if (screenings.getId() == screeningsId) {
                current = screenings;
                break;
            }
        }
        if (current == null) {
            current = screeningsService.calculate(list);
        }
        for (ScreeningsListVO screeningsListVO : voList) {
            screeningsListVO.setCurrent(current.getId() == screeningsListVO.getId());
            screeningsListVO.setTitle(DateUtil.date2String(DateUtil.str2Date(screeningsListVO.getBeginTimeStr()), "HH") + "点场");
        }
        ScreeningsVO screeningsVO = current == null ? null : screeningsHandler.toVO4Classify(current);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage(screeningsVO == null ? "今天暂无秒杀数据" : "获取秒杀数据成功.");
        view.addObject("list", voList);
        view.addObject("current", screeningsVO);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listByScreenings(Long screeningsId, Integer size) {

        AssertUtil.assertNotNull(screeningsId, "场次ID不能为空.");
        SeckillConfig seckillConfig = seckillConfigService.get();
        int screeningsMinSize = seckillConfig.getScreeningsMinSize();
        size = size == null || size <= 0 || size > screeningsMinSize ? screeningsMinSize : size;
        List<Screenings> list = screeningsService.listToday(size);
        List<ScreeningsListVO> voList = screeningsHandler.toVOList(list);
        Screenings current = null;
        for (Screenings screenings : list) {
            if (screenings.getId() == screeningsId) {
                current = screenings;
                break;
            }
        }
        if (current == null) {
            current = screeningsService.calculate(list);
        }
        for (ScreeningsListVO screeningsListVO : voList) {
            screeningsListVO.setCurrent(current.getId() == screeningsListVO.getId());
            screeningsListVO.setTitle(DateUtil.date2String(DateUtil.str2Date(screeningsListVO.getBeginTimeStr()), "HH") + "点场");
            screeningsListVO.setBeginTimeStr(DateUtil.date2String(DateUtil.str2Date(screeningsListVO.getBeginTimeStr()), "HH:mm:ss"));
            screeningsListVO.setEndTimeStr(DateUtil.date2String(DateUtil.str2Date(screeningsListVO.getEndTimeStr()), "HH:mm:ss"));
        }
        // ScreeningsVO screeningsVO = current == null ? null : screeningsHandler.toVO4Classify(current);
        ScreeningsVO screeningsVO = current == null ? null : screeningsHandler.toVO4Merchandise(current);
        List<MerchandiseSeckillVO> carzySeckillList = screeningsHandler.getCrazySeckill(current);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage(screeningsVO == null ? "今天暂无秒杀数据" : "获取秒杀数据成功.");
        view.addObject("list", voList);
        view.addObject("current", screeningsVO);
        view.addObject("carzySeckillList", carzySeckillList);
        return view;
    }

    @RequestMapping
    public FrontAjaxView listByScreenings4List(Long screeningsId, Integer size) {

        throw new NotImplementedException();
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView current() {

        List<Screenings> list = screeningsService.listToday(4);
        Screenings current = screeningsService.calculate(list);
        ScreeningsVO screeningsVO = current == null ? null : screeningsHandler.toVO4Index(current);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage(screeningsVO == null ? "今天暂无秒杀数据" : "获取秒杀数据成功.");
        view.addObject("exist", !(screeningsVO == null));
        view.addObject("current", screeningsVO);
        return view;
    }
}
