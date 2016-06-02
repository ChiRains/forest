package com.qcloud.component.brokerage.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.brokerage.model.CalculationFormula;
import com.qcloud.component.brokerage.model.query.CalculationFormulaQuery;

public interface CalculationFormulaMapper {

	@Insert("insert into `brokerage_calculation_formula`(`id`,`formula`,`state`,`name`,`tradeUserDistribution`,`proportion`,`poundageRate`)"
			+ " values(#{id},#{formula},#{state},#{name},#{tradeUserDistribution},#{proportion},#{poundageRate})")
	public void insert(CalculationFormula calculationFormula);

	@Select("select * from `brokerage_calculation_formula` where `id`=#{id}")
	public CalculationFormula get(Long id);

	@Update("update `brokerage_calculation_formula` set `formula`=#{formula},`state`=#{state},`name`=#{name},`tradeUserDistribution`=#{tradeUserDistribution},`proportion`=#{proportion},`poundageRate`=#{poundageRate} where `id`=#{id}")
	public void update(CalculationFormula calculationFormula);

	@Delete("delete from `brokerage_calculation_formula` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `brokerage_calculation_formula` limit #{start},#{count}")
	public List<CalculationFormula> list4page(Map<String,Object> param);

	@Select("select count(*) from `brokerage_calculation_formula`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `brokerage_calculation_formula`")
	public List<CalculationFormula> listAll();
}