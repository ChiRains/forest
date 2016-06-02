package com.qcloud.component.brokerage.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.brokerage.model.FormulaCalculationRecord;
import com.qcloud.component.brokerage.model.query.FormulaCalculationRecordQuery;

public interface FormulaCalculationRecordMapper {

	@Insert("insert into `brokerage_formula_calculation_record`(`id`,`state`,`formulaId`,`calculateTime`,`beginTime`,`endTime`)"
			+ " values(#{id},#{state},#{formulaId},#{calculateTime},#{beginTime},#{endTime})")
	public void insert(FormulaCalculationRecord formulaCalculationRecord);

	@Select("select * from `brokerage_formula_calculation_record` where `id`=#{id}")
	public FormulaCalculationRecord get(Long id);

	@Update("update `brokerage_formula_calculation_record` set `state`=#{state},`formulaId`=#{formulaId},`calculateTime`=#{calculateTime},`beginTime`=#{beginTime},`endTime`=#{endTime} where `id`=#{id}")
	public void update(FormulaCalculationRecord formulaCalculationRecord);

	@Delete("delete from `brokerage_formula_calculation_record` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `brokerage_formula_calculation_record` limit #{start},#{count}")
	public List<FormulaCalculationRecord> list4page(Map<String,Object> param);

	@Select("select count(*) from `brokerage_formula_calculation_record`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `brokerage_formula_calculation_record`")
	public List<FormulaCalculationRecord> listAll();
}