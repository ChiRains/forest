package com.qcloud.component.goods.web.handler;

import java.util.List;

import com.qcloud.component.goods.model.MerchandiseAttribute;
import com.qcloud.component.goods.web.vo.MerchandiseAttributeVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseAttributeVO;

public interface MerchandiseAttributeHandler {

	List<MerchandiseAttributeVO> toVOList(List<MerchandiseAttribute> list);

	MerchandiseAttributeVO toVO(MerchandiseAttribute merchandiseAttribute);

	List<AdminMerchandiseAttributeVO> toVOList4Admin(List<MerchandiseAttribute> list);

	AdminMerchandiseAttributeVO toVO4Admin(MerchandiseAttribute merchandiseAttribute);
}
