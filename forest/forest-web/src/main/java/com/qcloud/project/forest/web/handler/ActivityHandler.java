package com.qcloud.project.forest.web.handler;

import java.util.List;

import com.qcloud.project.forest.model.Activity;
import com.qcloud.project.forest.web.vo.ActivityVO;
import com.qcloud.project.forest.web.vo.admin.AdminActivityVO;

public interface ActivityHandler {

	List<ActivityVO> toVOList(List<Activity> list);

	ActivityVO toVO(Activity activity);

	List<AdminActivityVO> toVOList4Admin(List<Activity> list);

	AdminActivityVO toVO4Admin(Activity activity);
}
