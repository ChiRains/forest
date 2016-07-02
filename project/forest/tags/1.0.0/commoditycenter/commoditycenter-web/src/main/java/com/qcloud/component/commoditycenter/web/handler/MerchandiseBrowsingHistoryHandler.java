package com.qcloud.component.commoditycenter.web.handler;

import java.util.List;
import com.qcloud.component.commoditycenter.model.MerchandiseBrowsingHistory;
import com.qcloud.component.commoditycenter.web.vo.MerchandiseBrowsingHistoryVO;

public interface MerchandiseBrowsingHistoryHandler {

    List<MerchandiseBrowsingHistoryVO> toVOList(List<MerchandiseBrowsingHistory> list);

    MerchandiseBrowsingHistoryVO toVO(MerchandiseBrowsingHistory merchandiseBrowsingHistory);
}
