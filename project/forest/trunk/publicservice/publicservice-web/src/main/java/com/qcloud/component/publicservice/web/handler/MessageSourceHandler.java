package com.qcloud.component.publicservice.web.handler;

import java.util.List;
import com.qcloud.component.publicservice.model.MessageSource;
import com.qcloud.component.publicservice.web.vo.admin.AdminMessageSourceVO;

public interface MessageSourceHandler {

    public List<AdminMessageSourceVO> toVOList4Admin(List<MessageSource> list);

    public AdminMessageSourceVO toVO4Admin(MessageSource messageSource);
}
