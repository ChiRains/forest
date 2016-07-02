package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.project.forest.service.AnalysisresultService;
import com.qcloud.project.forest.web.handler.AnalysisresultHandler;
import com.qcloud.project.forest.model.Analysisresult;
import com.qcloud.project.forest.web.vo.AnalysisresultVO;
import com.qcloud.project.forest.web.vo.BMIVO;
import com.qcloud.project.forest.web.vo.RangeList;
import com.qcloud.project.forest.web.vo.admin.AdminAnalysisresultVO;

@Component
public class AnalysisresultHandlerImpl implements AnalysisresultHandler {

    @Autowired
    private AnalysisresultService analysisresultService;

    @Override
    public List<AnalysisresultVO> toVOList(List<Analysisresult> list) {

        List<AnalysisresultVO> voList = new ArrayList<AnalysisresultVO>();
        for (Analysisresult analysisresult : list) {
            voList.add(toVO(analysisresult));
        }
        return voList;
    }

    @Override
    public AnalysisresultVO toVO(Analysisresult analysisresult) {

        String json = Json.toJson(analysisresult);
        return Json.toObject(json, AnalysisresultVO.class, true);
    }

    @Override
    public List<AdminAnalysisresultVO> toVOList4Admin(List<Analysisresult> list) {

        List<AdminAnalysisresultVO> voList = new ArrayList<AdminAnalysisresultVO>();
        for (Analysisresult adminAnalysisresult : list) {
            voList.add(toVO4Admin(adminAnalysisresult));
        }
        return voList;
    }

    @Override
    public AdminAnalysisresultVO toVO4Admin(Analysisresult analysisresult) {

        String json = Json.toJson(analysisresult);
        return Json.toObject(json, AdminAnalysisresultVO.class, true);
    }

    @Override
    public List<BMIVO> toBMIVO(Analysisresult analysisResult, double bmi) {

        List<BMIVO> list = new ArrayList<BMIVO>();
        AssertUtil.assertNotNull(analysisResult, "暂无记录");
        BMIVO vo = new BMIVO();
        vo.setId(analysisResult.getId());
        vo.setDescription(analysisResult.getDescription());
        vo.setName(analysisResult.getName());
        vo.setBmi(bmi);
        List<Analysisresult> aList = analysisresultService.listAll();
        List<RangeList> rangeList = new ArrayList<RangeList>();
        for (Analysisresult a : aList) {
            RangeList range = new RangeList();
            range.setAfterData(a.getAfterData());
            range.setPreviousData(a.getPreviousData());
            rangeList.add(range);
        }
        vo.setRangeData(rangeList);
        list.add(vo);
        return list;
    }
}
