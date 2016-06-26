package com.qcloud.component.goods.web.handler;

import java.util.List;

import com.qcloud.component.goods.model.MerchandiseBrowsingHistory;
import com.qcloud.component.goods.web.vo.MerchandiseBrowsingHistoryVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseBrowsingHistoryVO;

public interface MerchandiseBrowsingHistoryHandler {

	List<MerchandiseBrowsingHistoryVO> toVOList(List<MerchandiseBrowsingHistory> list);

	MerchandiseBrowsingHistoryVO toVO(MerchandiseBrowsingHistory merchandiseBrowsingHistory);

	List<AdminMerchandiseBrowsingHistoryVO> toVOList4Admin(List<MerchandiseBrowsingHistory> list);

	AdminMerchandiseBrowsingHistoryVO toVO4Admin(MerchandiseBrowsingHistory merchandiseBrowsingHistory);
}
