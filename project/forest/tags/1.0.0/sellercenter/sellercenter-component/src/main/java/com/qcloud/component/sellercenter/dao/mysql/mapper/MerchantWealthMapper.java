package com.qcloud.component.sellercenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.sellercenter.model.MerchantWealth;
import com.qcloud.component.sellercenter.model.query.MerchantWealthQuery;

public interface MerchantWealthMapper {

	@Insert("insert into `sellercenter_merchant_wealth`(`id`,`merchantId`,`integral`,`commission`,`consumptionCurrency`,`integralSummation`,`commissionSummation`,`consumptionCurrencySummation`,`time`,`version`)"
			+ " values(#{id},#{merchantId},#{integral},#{commission},#{consumptionCurrency},#{integralSummation},#{commissionSummation},#{consumptionCurrencySummation},#{time},#{version})")
	public void insert(MerchantWealth merchantWealth);

	@Select("select * from `sellercenter_merchant_wealth` where `id`=#{id}")
	public MerchantWealth get(Long id);

	@Update("update `sellercenter_merchant_wealth` set `merchantId`=#{merchantId},`integral`=#{integral},`commission`=#{commission},`consumptionCurrency`=#{consumptionCurrency},`integralSummation`=#{integralSummation},`commissionSummation`=#{commissionSummation},`consumptionCurrencySummation`=#{consumptionCurrencySummation},`time`=#{time},`version`=#{version} where `id`=#{id}")
	public void update(MerchantWealth merchantWealth);

	@Delete("delete from `sellercenter_merchant_wealth` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `sellercenter_merchant_wealth` limit #{start},#{count}")
	public List<MerchantWealth> list4page(Map<String,Object> param);

	@Select("select count(*) from `sellercenter_merchant_wealth`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `sellercenter_merchant_wealth`")
	public List<MerchantWealth> listAll();
}