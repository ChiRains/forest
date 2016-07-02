package com.qcloud.component.goods.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.goods.model.MerchandiseVipDiscountDate;
import com.qcloud.component.goods.model.query.MerchandiseVipDiscountDateQuery;

public interface MerchandiseVipDiscountDateMapper {

	@Insert("insert into `goods_merchandise_vip_discount_date`(`id`,`userId`,`year`,`month`,`day`)"
			+ " values(#{id},#{userId},#{year},#{month},#{day})")
	public void insert(MerchandiseVipDiscountDate merchandiseVipDiscountDate);

	@Select("select * from `goods_merchandise_vip_discount_date` where `id`=#{id}")
	public MerchandiseVipDiscountDate get(Long id);

	@Update("update `goods_merchandise_vip_discount_date` set `userId`=#{userId},`year`=#{year},`month`=#{month},`day`=#{day} where `id`=#{id}")
	public void update(MerchandiseVipDiscountDate merchandiseVipDiscountDate);

	@Delete("delete from `goods_merchandise_vip_discount_date` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `goods_merchandise_vip_discount_date` limit #{start},#{count}")
	public List<MerchandiseVipDiscountDate> list4page(Map<String,Object> param);

	@Select("select count(*) from `goods_merchandise_vip_discount_date`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `goods_merchandise_vip_discount_date`")
	public List<MerchandiseVipDiscountDate> listAll();
}