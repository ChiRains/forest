//package com.qcloud.component.goods.web.handler.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.stereotype.Component;
//
//import com.qcloud.pirates.core.json.Json;
//import com.qcloud.component.goods.web.handler.MerchandiseItemHandler;
//import com.qcloud.component.goods.model.MerchandiseItem;
//import com.qcloud.component.goods.web.vo.MerchandiseItemVO;
//import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseItemVO;
//
//@Component
//public class MerchandiseItemHandlerImpl implements MerchandiseItemHandler {
//
//	@Override
//	public List<MerchandiseItemVO> toVOList(List<MerchandiseItem> list){
//		List<MerchandiseItemVO> voList = new ArrayList<MerchandiseItemVO>();
//		for (MerchandiseItem merchandiseItem : list) {
//			voList.add(toVO(merchandiseItem));
//		}
//		return voList;
//	}
//
//	@Override
//	public MerchandiseItemVO toVO(MerchandiseItem merchandiseItem){
//		String json = Json.toJson(merchandiseItem);
//		return Json.toObject(json, MerchandiseItemVO.class, true);
//
//	}
//
//	@Override
//	public List<AdminMerchandiseItemVO> toVOList4Admin(List<MerchandiseItem> list){
//		List<AdminMerchandiseItemVO> voList = new ArrayList<AdminMerchandiseItemVO>();
//		for (MerchandiseItem adminMerchandiseItem : list) {
//			voList.add(toVO4Admin(adminMerchandiseItem));
//		}
//		return voList;
//	}
//
//	@Override
//	public AdminMerchandiseItemVO toVO4Admin(MerchandiseItem merchandiseItem){
//		String json = Json.toJson(merchandiseItem);
//		return Json.toObject(json, AdminMerchandiseItemVO.class, true);
//	}
//}
