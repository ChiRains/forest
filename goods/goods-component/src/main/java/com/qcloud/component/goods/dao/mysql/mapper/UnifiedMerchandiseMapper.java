package com.qcloud.component.goods.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.query.UnifiedMerchandiseQuery;

public interface UnifiedMerchandiseMapper {

	@Insert("insert into `goods_unified_merchandise`(`id`,`type`,`merchantId`,`merchandiseId`,`mallClassifyId`,`mallClassifyBsid`,`merchantClassifyId`,`merchantClassifyBsid`,`name`,`code`,`image`,`brandId`,`purchase`,`discount`,`price`,`integral`,`canUseCoupon`,`stock`,`keywords`,`state`,`salesVolume`,`virtualSalesVolume`,`clickRate`,`lowEvaluation`,`middleEvaluation`,`goodEvaluation`,`relaUnifiedMerchandiseId`,`order`,`activityId`,`recordTime`,`updateTime`)"
			+ " values(#{id},#{type},#{merchantId},#{merchandiseId},#{mallClassifyId},#{mallClassifyBsid},#{merchantClassifyId},#{merchantClassifyBsid},#{name},#{code},#{image},#{brandId},#{purchase},#{discount},#{price},#{integral},#{canUseCoupon},#{stock},#{keywords},#{state},#{salesVolume},#{virtualSalesVolume},#{clickRate},#{lowEvaluation},#{middleEvaluation},#{goodEvaluation},#{relaUnifiedMerchandiseId},#{order},#{activityId},#{recordTime},#{updateTime})")
	public void insert(UnifiedMerchandise unifiedMerchandise);

	@Select("select * from `goods_unified_merchandise` where `id`=#{id}")
	public UnifiedMerchandise get(Long id);

	@Update("update `goods_unified_merchandise` set `mallClassifyId`=#{mallClassifyId},`mallClassifyBsid`=#{mallClassifyBsid},`merchantClassifyId`=#{merchantClassifyId},`merchantClassifyBsid`=#{merchantClassifyBsid},`name`=#{name},`code`=#{code},`image`=#{image},`brandId`=#{brandId},`purchase`=#{purchase},`discount`=#{discount},`price`=#{price},`integral`=#{integral},`canUseCoupon`=#{canUseCoupon},`keywords`=#{keywords},`state`=#{state},`virtualSalesVolume`=#{virtualSalesVolume},`order`=#{order},`activityId`=#{activityId},`updateTime`=#{updateTime},relaUnifiedMerchandiseId=#{relaUnifiedMerchandiseId},`stock`=#{stock} where `id`=#{id}")
	public void update(UnifiedMerchandise unifiedMerchandise);

	@Delete("delete from `goods_unified_merchandise` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `goods_unified_merchandise` limit #{start},#{count}")
	public List<UnifiedMerchandise> list4page(Map<String,Object> param);

	@Select("select count(*) from `goods_unified_merchandise`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `goods_unified_merchandise`")
	public List<UnifiedMerchandise> listAll();
}