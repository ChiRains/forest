package com.qcloud.component.commoditycenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.commoditycenter.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.commoditycenter.model.query.MerchandiseSpecificationsRelationQuery;

public interface MerchandiseSpecificationsRelationMapper {

	@Insert("insert into `commoditycenter_merchandise_specifications_relation`(`id`,`merchandiseId`,`attributeId`,`value`,`alias`,`type`,`oldAlias`,`isCheck`)"
			+ " values(#{id},#{merchandiseId},#{attributeId},#{value},#{alias},#{type},#{oldAlias},#{isCheck})")
	public void insert(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation);

	@Select("select * from `commoditycenter_merchandise_specifications_relation` where `id`=#{id}")
	public MerchandiseSpecificationsRelation get(Long id);

	@Update("update `commoditycenter_merchandise_specifications_relation` set `merchandiseId`=#{merchandiseId},`attributeId`=#{attributeId},`value`=#{value}, `alias`=#{alias} ,`type`=#{type},`oldAlias`=#{oldAlias},`isCheck`=#{isCheck} where `id`=#{id}")
	public void update(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation);

	@Delete("delete from `commoditycenter_merchandise_specifications_relation` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `commoditycenter_merchandise_specifications_relation` limit #{start},#{count}")
	public List<MerchandiseSpecificationsRelation> list4page(Map<String,Object> param);

	@Select("select count(*) from `commoditycenter_merchandise_specifications_relation`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `commoditycenter_merchandise_specifications_relation`")
	public List<MerchandiseSpecificationsRelation> listAll();
	
	@Select("delete from `commoditycenter_merchandise_specifications_relation` where `merchandiseId`=#{merchandiseId}")
	public void deleteByMerchandiseId(Long merchandiseIds);
}