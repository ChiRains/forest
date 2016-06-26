package com.qcloud.component.goods.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.goods.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.goods.model.query.MerchandiseSpecificationsRelationQuery;

public interface MerchandiseSpecificationsRelationMapper {

	@Insert("insert into `goods_merchandise_specifications_relation`(`id`,`merchandiseId`,`attributeId`,`value`,`alias`,`type`,`oldAlias`,`isCheck`)"
			+ " values(#{id},#{merchandiseId},#{attributeId},#{value},#{alias},#{type},#{oldAlias},#{isCheck})")
	public void insert(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation);

	@Select("select * from `goods_merchandise_specifications_relation` where `id`=#{id}")
	public MerchandiseSpecificationsRelation get(Long id);

	@Update("update `goods_merchandise_specifications_relation` set `merchandiseId`=#{merchandiseId},`attributeId`=#{attributeId},`value`=#{value}, `alias`=#{alias} ,`type`=#{type},`oldAlias`=#{oldAlias},`isCheck`=#{isCheck} where `id`=#{id}")
	public void update(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation);

	@Delete("delete from `goods_merchandise_specifications_relation` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `goods_merchandise_specifications_relation` limit #{start},#{count}")
	public List<MerchandiseSpecificationsRelation> list4page(Map<String,Object> param);

	@Select("select count(*) from `goods_merchandise_specifications_relation`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `goods_merchandise_specifications_relation`")
	public List<MerchandiseSpecificationsRelation> listAll();
	
	@Select("delete from `goods_merchandise_specifications_relation` where `merchandiseId`=#{merchandiseId}")
	public void deleteByMerchandiseId(Long merchandiseIds);
}