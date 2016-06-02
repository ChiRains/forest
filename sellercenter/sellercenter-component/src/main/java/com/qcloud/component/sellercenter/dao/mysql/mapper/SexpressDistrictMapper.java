package com.qcloud.component.sellercenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.sellercenter.model.SexpressDistrict;

public interface SexpressDistrictMapper {

	@Insert("insert into `sellercenter_sexpress_district`(`id`,`expressId`,`firstPrice`,`continuedPrice`,`province`,`city`,`district`)"
			+ " values(#{id},#{expressId},#{firstPrice},#{continuedPrice},#{province},#{city},#{district})")
	public void insert(SexpressDistrict sexpressDistrict);

	@Select("select * from `sellercenter_sexpress_district` where `id`=#{id}")
	public SexpressDistrict get(Long id);

	@Update("update `sellercenter_sexpress_district` set `expressId`=#{expressId},`firstPrice`=#{firstPrice},`continuedPrice`=#{continuedPrice},`province`=#{province},`city`=#{city},`district`=#{district} where `id`=#{id}")
	public void update(SexpressDistrict sexpressDistrict);

	@Delete("delete from `sellercenter_sexpress_district` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `sellercenter_sexpress_district` limit #{start},#{count}")
	public List<SexpressDistrict> list4page(Map<String,Object> param);

	@Select("select count(*) from `sellercenter_sexpress_district`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `sellercenter_sexpress_district`")
	public List<SexpressDistrict> listAll();
	@Select("select * from `sellercenter_sexpress_district` where `expressId`=#{expressId}")
	public List<SexpressDistrict > listByExpressId(Long expressId);
}