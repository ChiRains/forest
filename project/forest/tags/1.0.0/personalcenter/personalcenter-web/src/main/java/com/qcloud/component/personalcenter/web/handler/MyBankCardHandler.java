package com.qcloud.component.personalcenter.web.handler;

import java.util.List;

import com.qcloud.component.personalcenter.model.MyBankCard;
import com.qcloud.component.personalcenter.web.vo.MyBankCardVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMyBankCardVO;

public interface MyBankCardHandler {

	List<MyBankCardVO> toVOList(List<MyBankCard> list);

	MyBankCardVO toVO(MyBankCard myBankCard);

	List<AdminMyBankCardVO> toVOList4Admin(List<MyBankCard> list);

	AdminMyBankCardVO toVO4Admin(MyBankCard myBankCard);
}
