package com.qcloud.component.sellercenter.web.handler;

import java.util.List;
import com.qcloud.component.sellercenter.model.Sexpress;
import com.qcloud.component.sellercenter.web.vo.admin.AdminSexpressVO;

public interface SexpressHandler {

    List<AdminSexpressVO> toVOList4Admin(List<Sexpress> list);

    AdminSexpressVO toVO4Admin(Sexpress sexpress);
}
