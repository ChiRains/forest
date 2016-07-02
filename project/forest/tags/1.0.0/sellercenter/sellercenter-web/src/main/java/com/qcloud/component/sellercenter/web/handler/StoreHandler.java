package com.qcloud.component.sellercenter.web.handler;

import java.util.List;

import com.qcloud.component.sellercenter.model.Store;
import com.qcloud.component.sellercenter.web.vo.StoreVO;
import com.qcloud.component.sellercenter.web.vo.admin.AdminStoreVO;

public interface StoreHandler {

	List<StoreVO> toVOList(List<Store> list);

	StoreVO toVO(Store store);

	List<AdminStoreVO> toVOList4Admin(List<Store> list);

	AdminStoreVO toVO4Admin(Store store);
}
