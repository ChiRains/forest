//package com.qcloud.component.goods.dao.mysql.mapper;
//
//import java.util.List;
//import java.util.Map;
//
//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
//
//import com.qcloud.component.goods.model.MerchandiseItem;
//import com.qcloud.component.goods.model.query.MerchandiseItemQuery;
//
//public interface MerchandiseItemMapper {
//
//	@Insert("insert into `goods_merchandise_item`(`id`,`unifiedMerchandiseId`,`merchantId`,`merchandiseId`,`mallClassifyId`,`mallClassifyBsid`,`merchantClassifyId`,`merchantClassifyBsid`,`name`,`keywords`,`state`,`purchase`,`discount`,`price`,`stock`,`merchandiseSpecificationsId`,`salesVolume`,`virtualSalesVolume`,`clickRate`,`lowEvaluation`,`middleEvaluation`,`goodEvaluation`,`recordTime`,`updateTime`,`brandId`)"
//			+ " values(#{id},#{unifiedMerchandiseId},#{merchantId},#{merchandiseId},#{mallClassifyId},#{mallClassifyBsid},#{merchantClassifyId},#{merchantClassifyBsid},#{name},#{keywords},#{state},#{purchase},#{discount},#{price},#{stock},#{merchandiseSpecificationsId},#{salesVolume},#{virtualSalesVolume},#{clickRate},#{lowEvaluation},#{middleEvaluation},#{goodEvaluation},#{recordTime},#{updateTime},#{brandId})")
//	public void insert(MerchandiseItem merchandiseItem);
//
//	@Select("select * from `goods_merchandise_item` where `id`=#{id}")
//	public MerchandiseItem get(Long id);
//
//	@Update("update `goods_merchandise_item` set `unifiedMerchandiseId`=#{unifiedMerchandiseId},`merchantId`=#{merchantId},`merchandiseId`=#{merchandiseId},`mallClassifyId`=#{mallClassifyId},`mallClassifyBsid`=#{mallClassifyBsid},`merchantClassifyId`=#{merchantClassifyId},`merchantClassifyBsid`=#{merchantClassifyBsid},`name`=#{name},`keywords`=#{keywords},`state`=#{state},`purchase`=#{purchase},`discount`=#{discount},`price`=#{price},`stock`=#{stock},`merchandiseSpecificationsId`=#{merchandiseSpecificationsId},`salesVolume`=#{salesVolume},`virtualSalesVolume`=#{virtualSalesVolume},`clickRate`=#{clickRate},`lowEvaluation`=#{lowEvaluation},`middleEvaluation`=#{middleEvaluation},`goodEvaluation`=#{goodEvaluation},`recordTime`=#{recordTime},`updateTime`=#{updateTime},`brandId`=#{brandId}  where `id`=#{id}")
//	public void update(MerchandiseItem merchandiseItem);
//
//	@Delete("delete from `goods_merchandise_item` where `id`=#{id}")
//	public void delete(Long id);
//
//	@Select("select * from `goods_merchandise_item` limit #{start},#{count}")
//	public List<MerchandiseItem> list4page(Map<String,Object> param);
//
//	@Select("select count(*) from `goods_merchandise_item`")
//	public int count4page(Map<String,Object> param);
//	
//	@Select("select * from `goods_merchandise_item`")
//	public List<MerchandiseItem> listAll();
//}