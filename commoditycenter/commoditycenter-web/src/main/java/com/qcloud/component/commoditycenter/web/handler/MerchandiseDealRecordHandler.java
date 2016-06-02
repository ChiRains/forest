package com.qcloud.component.commoditycenter.web.handler;

import java.util.List;
import com.qcloud.component.commoditycenter.model.MerchandiseDealRecord;
import com.qcloud.component.commoditycenter.web.vo.MerchandiseDealRecordVO;

public interface MerchandiseDealRecordHandler {

    List<MerchandiseDealRecordVO> toVOList(List<MerchandiseDealRecord> list);

    MerchandiseDealRecordVO toVO(MerchandiseDealRecord merchandiseDealRecord);
}
