package com.qcloud.component.goods.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.goods.model.MerchandiseVipDiscountHistory;
import com.qcloud.component.goods.model.query.MerchandiseVipDiscountHistoryQuery;

public interface MerchandiseVipDiscountHistoryMapper {

	@Insert("insert into `goods_merchandise_vip_discount_history`(`id`,`userId`,`merchandiseItemId`,`price`,`discount`,`paymentType`,`priceHistory`,`discountHistory`,`paymentTypeHistory`,`updateTime`)"
			+ " values(#{id},#{userId},#{merchandiseItemId},#{price},#{discount},#{paymentType},#{priceHistory},#{discountHistory},#{paymentTypeHistory},#{updateTime})")
	public void insert(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory);

	@Select("select * from `goods_merchandise_vip_discount_history` where `id`=#{id}")
	public MerchandiseVipDiscountHistory get(Long id);

	@Update("update `goods_merchandise_vip_discount_history` set `userId`=#{userId},`merchandiseItemId`=#{merchandiseItemId},`price`=#{price},`discount`=#{discount},`paymentType`=#{paymentType},`priceHistory`=#{priceHistory},`discountHistory`=#{discountHistory},`paymentTypeHistory`=#{paymentTypeHistory},`updateTime`=#{updateTime} where `id`=#{id}")
	public void update(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory);

	@Delete("delete from `goods_merchandise_vip_discount_history` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `goods_merchandise_vip_discount_history` limit #{start},#{count}")
	public List<MerchandiseVipDiscountHistory> list4page(Map<String,Object> param);

	@Select("select count(*) from `goods_merchandise_vip_discount_history`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `goods_merchandise_vip_discount_history`")
	public List<MerchandiseVipDiscountHistory> listAll();
}