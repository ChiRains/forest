package com.qcloud.project.forest.web.handler;

import java.util.List;

import com.qcloud.project.forest.model.Analysisresult;
import com.qcloud.project.forest.web.vo.AnalysisresultVO;
import com.qcloud.project.forest.web.vo.BMIVO;
import com.qcloud.project.forest.web.vo.admin.AdminAnalysisresultVO;

public interface AnalysisresultHandler {

	List<AnalysisresultVO> toVOList(List<Analysisresult> list);

	AnalysisresultVO toVO(Analysisresult analysisresult);

	List<AdminAnalysisresultVO> toVOList4Admin(List<Analysisresult> list);

	AdminAnalysisresultVO toVO4Admin(Analysisresult analysisresult);

	List<BMIVO> toBMIVO(Analysisresult analysisResult, double bmi);
}
