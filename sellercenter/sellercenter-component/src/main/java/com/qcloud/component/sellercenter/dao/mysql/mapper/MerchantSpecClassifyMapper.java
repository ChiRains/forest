package com.qcloud.component.sellercenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.sellercenter.model.MerchantSpecClassify;
import com.qcloud.component.sellercenter.model.query.MerchantSpecClassifyQuery;

public interface MerchantSpecClassifyMapper {

	@Insert("insert into `sellercenter_merchant_spec_classify`(`id`,`merchantId`,`classifyId`)"
			+ " values(#{id},#{merchantId},#{classifyId})")
	public void insert(MerchantSpecClassify merchantSpecClassify);

	@Select("select * from `sellercenter_merchant_spec_classify` where `id`=#{id}")
	public MerchantSpecClassify get(Long id);

	@Update("update `sellercenter_merchant_spec_classify` set `merchantId`=#{merchantId},`classifyId`=#{classifyId} where `id`=#{id}")
	public void update(MerchantSpecClassify merchantSpecClassify);

	@Delete("delete from `sellercenter_merchant_spec_classify` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `sellercenter_merchant_spec_classify` limit #{start},#{count}")
	public List<MerchantSpecClassify> list4page(Map<String,Object> param);

	@Select("select count(*) from `sellercenter_merchant_spec_classify`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `sellercenter_merchant_spec_classify`")
	public List<MerchantSpecClassify> listAll();
}