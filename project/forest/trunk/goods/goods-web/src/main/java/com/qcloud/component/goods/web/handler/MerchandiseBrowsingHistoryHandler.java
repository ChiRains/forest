package com.qcloud.component.goods.web.handler;

import java.util.List;
import com.qcloud.component.goods.model.MerchandiseBrowsingHistory;
import com.qcloud.component.goods.web.vo.MerchandiseBrowsingHistoryVO;

public interface MerchandiseBrowsingHistoryHandler {

    List<MerchandiseBrowsingHistoryVO> toVOList(List<MerchandiseBrowsingHistory> list);

    MerchandiseBrowsingHistoryVO toVO(MerchandiseBrowsingHistory merchandiseBrowsingHistory);
}
