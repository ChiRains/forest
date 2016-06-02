package com.qcloud.component.personalcenter.web.handler;

import java.util.List;

import com.qcloud.component.personalcenter.model.MySignInRecord;
import com.qcloud.component.personalcenter.web.vo.MySignInRecordVO;

public interface MySignInRecordHandler {

	List<MySignInRecordVO> toVOList(List<MySignInRecord> list);

	MySignInRecordVO toVO(MySignInRecord mySignInRecord);

}
