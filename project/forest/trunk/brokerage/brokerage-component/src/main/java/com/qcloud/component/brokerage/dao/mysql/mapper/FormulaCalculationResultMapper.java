package com.qcloud.component.brokerage.dao.mysql.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.qcloud.component.brokerage.model.FormulaCalculationResult;

public interface FormulaCalculationResultMapper {

	@Insert("insert into `brokerage_formula_calculation_result`(`id`,`dataPoolId`,`type`,`name`,`image`,`brokerage`,`orderTime`,`userId`,`merchantId`,`state`,`formulaId`,`calculateTime`,`beginTime`,`endTime`)"
			+ " select #{id},#{dataPoolId},#{type},#{name},#{image},#{brokerage},#{orderTime},#{userId},#{merchantId},#{state},#{formulaId},#{calculateTime},#{beginTime},#{endTime} FROM DUAL WHERE NOT EXISTS" 
	        + "(SELECT `dataPoolId`,`formulaId` FROM brokerage_formula_calculation_result WHERE dataPoolId = #{dataPoolId} and formulaId = #{formulaId}) ")
	public void insert(FormulaCalculationResult formulaCalculationResult);

	@Select("select * from `brokerage_formula_calculation_result` where `id`=#{id}")
	public FormulaCalculationResult get(Long id);

	@Update("update `brokerage_formula_calculation_result` set `dataPoolId`=#{dataPoolId},`type`=#{type},`name`=#{name},`image`=#{image},`brokerage`=#{brokerage},`orderTime`=#{orderTime},`userId`=#{userId},`merchantId`=#{merchantId},`state`=#{state},`formulaId`=#{formulaId},`calculateTime`=#{calculateTime},`beginTime`=#{beginTime},`endTime`=#{endTime} where `id`=#{id}")
	public void update(FormulaCalculationResult formulaCalculationResult);

	@Delete("delete from `brokerage_formula_calculation_result` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `brokerage_formula_calculation_result` limit #{start},#{count}")
	public List<FormulaCalculationResult> list4page(Map<String,Object> param);

	@Select("select count(*) from `brokerage_formula_calculation_result`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `brokerage_formula_calculation_result`")
	public List<FormulaCalculationResult> listAll();
}