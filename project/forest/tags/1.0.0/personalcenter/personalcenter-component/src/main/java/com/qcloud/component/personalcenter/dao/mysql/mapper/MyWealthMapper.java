package com.qcloud.component.personalcenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.personalcenter.model.MyWealth;
import com.qcloud.component.personalcenter.model.query.MyWealthQuery;

public interface MyWealthMapper {

	@Insert("insert into `personalcenter_my_wealth`(`id`,`userId`,`integral`,`commission`,`consumptionCurrency`,`integralSummation`,`commissionSummation`,`consumptionCurrencySummation`,`time`,`version`)"
			+ " values(#{id},#{userId},#{integral},#{commission},#{consumptionCurrency},#{integralSummation},#{commissionSummation},#{consumptionCurrencySummation},#{time},#{version})")
	public void insert(MyWealth myWealth);

	@Select("select * from `personalcenter_my_wealth` where `id`=#{id}")
	public MyWealth get(Long id);

	@Update("update `personalcenter_my_wealth` set `userId`=#{userId},`integral`=#{integral},`commission`=#{commission},`consumptionCurrency`=#{consumptionCurrency},`integralSummation`=#{integralSummation},`commissionSummation`=#{commissionSummation},`consumptionCurrencySummation`=#{consumptionCurrencySummation},`time`=#{time},`version`=#{version} where `id`=#{id}")
	public void update(MyWealth myWealth);

	@Delete("delete from `personalcenter_my_wealth` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `personalcenter_my_wealth` limit #{start},#{count}")
	public List<MyWealth> list4page(Map<String,Object> param);

	@Select("select count(*) from `personalcenter_my_wealth`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `personalcenter_my_wealth`")
	public List<MyWealth> listAll();
}