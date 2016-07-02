package com.qcloud.component.publicservice.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.component.publicservice.model.MessageSource;
import com.qcloud.component.publicservice.web.handler.MessageSourceHandler;
import com.qcloud.component.publicservice.web.vo.admin.AdminMessageSourceVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class MessageSourceHandlerImpl implements MessageSourceHandler {


	@Override
	public List<AdminMessageSourceVO> toVOList4Admin(List<MessageSource> list){
		List<AdminMessageSourceVO> voList = new ArrayList<AdminMessageSourceVO>();
		for (MessageSource adminMessageSource : list) {
			voList.add(toVO4Admin(adminMessageSource));
		}
		return voList;
	}

	@Override
	public AdminMessageSourceVO toVO4Admin(MessageSource messageSource){
		String json = Json.toJson(messageSource);
		return Json.toObject(json, AdminMessageSourceVO.class, true);
	}
}
