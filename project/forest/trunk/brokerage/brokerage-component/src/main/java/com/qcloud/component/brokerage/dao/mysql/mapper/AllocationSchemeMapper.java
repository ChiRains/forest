package com.qcloud.component.brokerage.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.brokerage.model.AllocationScheme;
import com.qcloud.component.brokerage.model.query.AllocationSchemeQuery;

public interface AllocationSchemeMapper {

	@Insert("insert into `brokerage_allocation_scheme`(`id`,`formulaId`,`allocationGradeId`,`proportion`)"
			+ " values(#{id},#{formulaId},#{allocationGradeId},#{proportion})")
	public void insert(AllocationScheme allocationScheme);

	@Select("select * from `brokerage_allocation_scheme` where `id`=#{id}")
	public AllocationScheme get(Long id);

	@Update("update `brokerage_allocation_scheme` set `formulaId`=#{formulaId},`allocationGradeId`=#{allocationGradeId},`proportion`=#{proportion} where `id`=#{id}")
	public void update(AllocationScheme allocationScheme);

	@Delete("delete from `brokerage_allocation_scheme` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `brokerage_allocation_scheme` limit #{start},#{count}")
	public List<AllocationScheme> list4page(Map<String,Object> param);

	@Select("select count(*) from `brokerage_allocation_scheme`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `brokerage_allocation_scheme`")
	public List<AllocationScheme> listAll();
}