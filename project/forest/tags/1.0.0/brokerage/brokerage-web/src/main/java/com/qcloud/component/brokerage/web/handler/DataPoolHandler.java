package com.qcloud.component.brokerage.web.handler;

import java.util.List;

import com.qcloud.component.brokerage.model.DataPool;
import com.qcloud.component.brokerage.web.vo.DataPoolVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminDataPoolVO;

public interface DataPoolHandler {

	List<DataPoolVO> toVOList(List<DataPool> list);

	DataPoolVO toVO(DataPool dataPool);

	List<AdminDataPoolVO> toVOList4Admin(List<DataPool> list);

	AdminDataPoolVO toVO4Admin(DataPool dataPool);
}
