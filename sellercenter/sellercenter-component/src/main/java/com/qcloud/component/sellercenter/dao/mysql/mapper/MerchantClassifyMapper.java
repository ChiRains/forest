package com.qcloud.component.sellercenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.sellercenter.model.MerchantClassify;
import com.qcloud.component.sellercenter.model.query.MerchantClassifyQuery;

public interface MerchantClassifyMapper {

	@Insert("insert into `sellercenter_merchant_classify`(`id`,`merchantId`,`classifyId`)"
			+ " values(#{id},#{merchantId},#{classifyId})")
	public void insert(MerchantClassify merchantClassify);

	@Select("select * from `sellercenter_merchant_classify` where `id`=#{id}")
	public MerchantClassify get(Long id);

	@Update("update `sellercenter_merchant_classify` set `merchantId`=#{merchantId},`classifyId`=#{classifyId} where `id`=#{id}")
	public void update(MerchantClassify merchantClassify);

	@Delete("delete from `sellercenter_merchant_classify` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `sellercenter_merchant_classify` limit #{start},#{count}")
	public List<MerchantClassify> list4page(Map<String,Object> param);

	@Select("select count(*) from `sellercenter_merchant_classify`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `sellercenter_merchant_classify`")
	public List<MerchantClassify> listAll();
}