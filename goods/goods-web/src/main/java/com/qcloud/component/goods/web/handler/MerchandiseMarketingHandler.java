package com.qcloud.component.goods.web.handler;

import java.util.List;

import com.qcloud.component.goods.model.MerchandiseMarketing;
import com.qcloud.component.goods.web.vo.MerchandiseMarketingVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseMarketingVO;

public interface MerchandiseMarketingHandler {

	List<MerchandiseMarketingVO> toVOList(List<MerchandiseMarketing> list);

	MerchandiseMarketingVO toVO(MerchandiseMarketing merchandiseMarketing);

	List<AdminMerchandiseMarketingVO> toVOList4Admin(List<MerchandiseMarketing> list);

	AdminMerchandiseMarketingVO toVO4Admin(MerchandiseMarketing merchandiseMarketing);
}
