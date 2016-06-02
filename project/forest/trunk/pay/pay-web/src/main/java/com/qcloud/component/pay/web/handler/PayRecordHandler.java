package com.qcloud.component.pay.web.handler;

import java.util.List;

import com.qcloud.component.pay.model.PayRecord;
import com.qcloud.component.pay.web.vo.PayRecordVO;
import com.qcloud.component.pay.web.vo.admin.AdminPayRecordVO;

public interface PayRecordHandler {

	List<PayRecordVO> toVOList(List<PayRecord> list);

	PayRecordVO toVO(PayRecord payRecord);

	List<AdminPayRecordVO> toVOList4Admin(List<PayRecord> list);

	AdminPayRecordVO toVO4Admin(PayRecord payRecord);
}
