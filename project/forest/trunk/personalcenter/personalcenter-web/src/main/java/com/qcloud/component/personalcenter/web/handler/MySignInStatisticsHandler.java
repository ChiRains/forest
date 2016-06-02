package com.qcloud.component.personalcenter.web.handler;

import java.util.List;

import com.qcloud.component.personalcenter.model.MySignInStatistics;
import com.qcloud.component.personalcenter.web.vo.MySignInStatisticsVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMySignInStatisticsVO;

public interface MySignInStatisticsHandler {

	List<MySignInStatisticsVO> toVOList(List<MySignInStatistics> list);

	MySignInStatisticsVO toVO(MySignInStatistics mySignInStatistics);

	List<AdminMySignInStatisticsVO> toVOList4Admin(List<MySignInStatistics> list);

	AdminMySignInStatisticsVO toVO4Admin(MySignInStatistics mySignInStatistics);
}
