package com.qcloud.component.seckill.web.handler;

import java.util.List;

import com.qcloud.component.seckill.model.MerchandiseSeckill;
import com.qcloud.component.seckill.web.vo.MerchandiseSeckillVO;
import com.qcloud.component.seckill.web.vo.admin.AdminMerchandiseSeckillVO;

public interface MerchandiseSeckillHandler {

	List<MerchandiseSeckillVO> toVOList(List<MerchandiseSeckill> list);

	MerchandiseSeckillVO toVO(MerchandiseSeckill merchandiseSeckill);

	List<AdminMerchandiseSeckillVO> toVOList4Admin(List<MerchandiseSeckill> list);

	AdminMerchandiseSeckillVO toVO4Admin(MerchandiseSeckill merchandiseSeckill);
}
