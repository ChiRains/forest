package com.qcloud.component.personalcenter.web.handler;

import java.util.List;

import com.qcloud.component.personalcenter.model.MyCommissionWithdrawCash;
import com.qcloud.component.personalcenter.web.vo.MyCommissionWithdrawCashVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMyCommissionWithdrawCashVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMyCommissionWithdrawCashVOExport;

public interface MyCommissionWithdrawCashHandler {

	List<MyCommissionWithdrawCashVO> toVOList(List<MyCommissionWithdrawCash> list);

	MyCommissionWithdrawCashVO toVO(MyCommissionWithdrawCash myCommissionWithdrawCash);

	List<AdminMyCommissionWithdrawCashVO> toVOList4Admin(List<MyCommissionWithdrawCash> list);

	AdminMyCommissionWithdrawCashVO toVO4Admin(MyCommissionWithdrawCash myCommissionWithdrawCash);
	
	List<AdminMyCommissionWithdrawCashVOExport> toVOList4Export(List<MyCommissionWithdrawCash> list);
	
}
