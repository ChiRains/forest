package com.qcloud.component.commoditycenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.commoditycenter.model.MonthHotSale;
import com.qcloud.component.commoditycenter.model.query.MonthHotSaleQuery;

public interface MonthHotSaleMapper {

	@Insert("insert into `commoditycenter_month_hot_sale`(`id`,`mallClassifyId`,`mallBsid`,`unifiedMerchandiseId`,`number`,`year`,`month`,`merchantClassifyId`,`merchantBsid`)"
			+ " values(#{id},#{mallClassifyId},#{mallBsid},#{unifiedMerchandiseId},#{number},#{year},#{month},#{merchantClassifyId},#{merchantBsid})")
	public void insert(MonthHotSale monthHotSale);

	@Select("select * from `commoditycenter_month_hot_sale` where `id`=#{id}")
	public MonthHotSale get(Long id);

	@Update("update `commoditycenter_month_hot_sale` set `mallClassifyId`=#{mallClassifyId},`mallBsid`=#{mallBsid},`unifiedMerchandiseId`=#{unifiedMerchandiseId},`number`=#{number},`year`=#{year},`month`=#{month},`merchantClassifyId`=#{merchantClassifyId},`merchantBsid`=#{merchantBsid} where `id`=#{id}")
	public void update(MonthHotSale monthHotSale);

	@Delete("delete from `commoditycenter_month_hot_sale` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `commoditycenter_month_hot_sale` limit #{start},#{count}")
	public List<MonthHotSale> list4page(Map<String,Object> param);

	@Select("select count(*) from `commoditycenter_month_hot_sale`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `commoditycenter_month_hot_sale`")
	public List<MonthHotSale> listAll();
}