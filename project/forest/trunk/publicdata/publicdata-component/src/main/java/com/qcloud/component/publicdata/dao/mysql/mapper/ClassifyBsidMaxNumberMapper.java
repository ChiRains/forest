package com.qcloud.component.publicdata.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.publicdata.model.ClassifyBsidMaxNumber;
import com.qcloud.component.publicdata.model.query.ClassifyBsidMaxNumberQuery;

public interface ClassifyBsidMaxNumberMapper {

	@Insert("insert into `publicdata_classify_bsid_max_number`(`id`,`parentClassifyId`,`type`,`maxNumber`)"
			+ " values(#{id},#{parentClassifyId},#{type},#{maxNumber})")
	public void insert(ClassifyBsidMaxNumber classifyBsidMaxNumber);

	@Select("select * from `publicdata_classify_bsid_max_number` where `id`=#{id}")
	public ClassifyBsidMaxNumber get(Long id);

	@Update("update `publicdata_classify_bsid_max_number` set `parentClassifyId`=#{parentClassifyId},`type`=#{type},`maxNumber`=#{maxNumber} where `id`=#{id}")
	public void update(ClassifyBsidMaxNumber classifyBsidMaxNumber);

	@Delete("delete from `publicdata_classify_bsid_max_number` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `publicdata_classify_bsid_max_number` limit #{start},#{count}")
	public List<ClassifyBsidMaxNumber> list4page(Map<String,Object> param);

	@Select("select count(*) from `publicdata_classify_bsid_max_number`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `publicdata_classify_bsid_max_number`")
	public List<ClassifyBsidMaxNumber> listAll();
}