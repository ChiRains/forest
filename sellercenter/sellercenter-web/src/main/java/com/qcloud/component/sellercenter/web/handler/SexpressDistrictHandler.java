package com.qcloud.component.sellercenter.web.handler;

import java.util.List;
import com.qcloud.component.sellercenter.model.SexpressDistrict;
import com.qcloud.component.sellercenter.web.vo.admin.AdminSexpressDistrictVO;

public interface SexpressDistrictHandler {

    List<AdminSexpressDistrictVO> toVOList4Admin(List<SexpressDistrict> list);

    AdminSexpressDistrictVO toVO4Admin(SexpressDistrict sexpressDistrict);
}
