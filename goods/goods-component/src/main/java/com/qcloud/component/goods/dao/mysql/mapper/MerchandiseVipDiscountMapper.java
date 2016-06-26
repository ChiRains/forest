package com.qcloud.component.goods.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.goods.model.MerchandiseVipDiscount;
import com.qcloud.component.goods.model.query.MerchandiseVipDiscountQuery;

public interface MerchandiseVipDiscountMapper {

	@Insert("insert into `goods_merchandise_vip_discount`(`id`,`userId`,`merchandiseItemId`,`price`,`discount`,`paymentType`,`classifyId`,`classifyBSID`)"
			+ " values(#{id},#{userId},#{merchandiseItemId},#{price},#{discount},#{paymentType},#{classifyId},#{classifyBSID})")
	public void insert(MerchandiseVipDiscount merchandiseVipDiscount);

	@Select("select * from `goods_merchandise_vip_discount` where `id`=#{id}")
	public MerchandiseVipDiscount get(Long id);

	@Update("update `goods_merchandise_vip_discount` set `userId`=#{userId},`merchandiseItemId`=#{merchandiseItemId},`price`=#{price},`discount`=#{discount},`paymentType`=#{paymentType},`classifyId`=#{classifyId},`classifyBSID`=#{classifyBSID} where `id`=#{id}")
	public void update(MerchandiseVipDiscount merchandiseVipDiscount);

	@Delete("delete from `goods_merchandise_vip_discount` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `goods_merchandise_vip_discount` limit #{start},#{count}")
	public List<MerchandiseVipDiscount> list4page(Map<String,Object> param);

	@Select("select count(*) from `goods_merchandise_vip_discount`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `goods_merchandise_vip_discount`")
	public List<MerchandiseVipDiscount> listAll();
}