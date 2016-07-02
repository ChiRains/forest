package com.qcloud.component.personalcenter.web.handler;

import java.util.Date;
import java.util.List;
import com.qcloud.component.personalcenter.model.MyWealthDetail;
import com.qcloud.component.personalcenter.web.vo.MyWealthDetailVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMyWealthDetailVO;

public interface MyWealthDetailHandler {

    List<MyWealthDetailVO> toVOList(List<MyWealthDetail> list);

    List<MyWealthDetailVO> toVOList4Stat(List<MyWealthDetail> list, Date begin, Date end);

    MyWealthDetailVO toVO(MyWealthDetail myWealthDetail);

    List<AdminMyWealthDetailVO> toVOList4Admin(List<MyWealthDetail> list);

    AdminMyWealthDetailVO toVO4Admin(MyWealthDetail myWealthDetail);
}
