package com.qcloud.component.goods.web.handler;

import java.util.List;

import com.qcloud.component.goods.model.MerchandiseDealRecord;
import com.qcloud.component.goods.web.vo.MerchandiseDealRecordVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseDealRecordVO;

public interface MerchandiseDealRecordHandler {

	List<MerchandiseDealRecordVO> toVOList(List<MerchandiseDealRecord> list);

	MerchandiseDealRecordVO toVO(MerchandiseDealRecord merchandiseDealRecord);

	List<AdminMerchandiseDealRecordVO> toVOList4Admin(List<MerchandiseDealRecord> list);

	AdminMerchandiseDealRecordVO toVO4Admin(MerchandiseDealRecord merchandiseDealRecord);
}
