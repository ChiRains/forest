package com.qcloud.component.goods.web.handler;

import java.util.List;

import com.qcloud.component.goods.model.MerchandiseImage;
import com.qcloud.component.goods.web.vo.MerchandiseImageVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseImageVO;

public interface MerchandiseImageHandler {

	List<MerchandiseImageVO> toVOList(List<MerchandiseImage> list);

	MerchandiseImageVO toVO(MerchandiseImage merchandiseImage);

	List<AdminMerchandiseImageVO> toVOList4Admin(List<MerchandiseImage> list);

	AdminMerchandiseImageVO toVO4Admin(MerchandiseImage merchandiseImage);
}
