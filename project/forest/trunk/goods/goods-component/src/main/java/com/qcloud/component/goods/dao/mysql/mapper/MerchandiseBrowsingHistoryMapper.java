package com.qcloud.component.goods.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.goods.model.MerchandiseBrowsingHistory;
import com.qcloud.component.goods.model.query.MerchandiseBrowsingHistoryQuery;

public interface MerchandiseBrowsingHistoryMapper {

	@Insert("insert into `goods_merchandise_browsing_history`(`id`,`unifiedMerchandiseId`,`userId`,`browsingTime`,`clientType`)"
			+ " values(#{id},#{unifiedMerchandiseId},#{userId},#{browsingTime},#{clientType})")
	public void insert(MerchandiseBrowsingHistory merchandiseBrowsingHistory);

	@Select("select * from `goods_merchandise_browsing_history` where `id`=#{id}")
	public MerchandiseBrowsingHistory get(Long id);

	@Update("update `goods_merchandise_browsing_history` set `unifiedMerchandiseId`=#{unifiedMerchandiseId},`userId`=#{userId},`browsingTime`=#{browsingTime},`clientType`=#{clientType} where `id`=#{id}")
	public void update(MerchandiseBrowsingHistory merchandiseBrowsingHistory);

	@Delete("delete from `goods_merchandise_browsing_history` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `goods_merchandise_browsing_history` limit #{start},#{count}")
	public List<MerchandiseBrowsingHistory> list4page(Map<String,Object> param);

	@Select("select count(*) from `goods_merchandise_browsing_history`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `goods_merchandise_browsing_history`")
	public List<MerchandiseBrowsingHistory> listAll();
}