package com.qcloud.component.sellercenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.sellercenter.model.MerchantMerchandiseClassify;
import com.qcloud.component.sellercenter.model.query.MerchantMerchandiseClassifyQuery;

public interface MerchantMerchandiseClassifyMapper {

	@Insert("insert into `sellercenter_merchant_merchandise_classify`(`id`,`merchantId`,`classifyId`)"
			+ " values(#{id},#{merchantId},#{classifyId})")
	public void insert(MerchantMerchandiseClassify merchantMerchandiseClassify);

	@Select("select * from `sellercenter_merchant_merchandise_classify` where `id`=#{id}")
	public MerchantMerchandiseClassify get(Long id);

	@Update("update `sellercenter_merchant_merchandise_classify` set `merchantId`=#{merchantId},`classifyId`=#{classifyId} where `id`=#{id}")
	public void update(MerchantMerchandiseClassify merchantMerchandiseClassify);

	@Delete("delete from `sellercenter_merchant_merchandise_classify` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `sellercenter_merchant_merchandise_classify` limit #{start},#{count}")
	public List<MerchantMerchandiseClassify> list4page(Map<String,Object> param);

	@Select("select count(*) from `sellercenter_merchant_merchandise_classify`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `sellercenter_merchant_merchandise_classify`")
	public List<MerchantMerchandiseClassify> listAll();
	
	@Delete("delete from `sellercenter_merchant_merchandise_classify` where `merchantId`=#{merchantId}")
    public void deleteByMerchantId(Long merchantId);
	
}