package com.qcloud.component.sellercenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.sellercenter.model.Merchant;
import com.qcloud.component.sellercenter.model.query.MerchantQuery;

public interface MerchantMapper {

	@Insert("insert into `sellercenter_merchant`(`id`,`name`,`code`,`userId`,`province`,`city`,`district`,`address`,`longitude`,`latitude`,`logo`,`classifyId`,`flagship`,`introduction`,`detailIntroduction`,`linkman`,`phone`,`integralModeId`,`commodityAuditing`,`distribution`,`registTime`,`state`,`merchantType`,`consumptionCurrency`,`version`,`notify`,`image`,`receiveMobile`,`validDate`,`buckle`,`isCertified`,`isSpecialService`,`isNoReason`,`isExternalUrl`,`admin`,`isIncludePost`)"
			+ " values(#{id},#{name},#{code},#{userId},#{province},#{city},#{district},#{address},#{longitude},#{latitude},#{logo},#{classifyId},#{flagship},#{introduction},#{detailIntroduction},#{linkman},#{phone},#{integralModeId},#{commodityAuditing},#{distribution},#{registTime},#{state},#{merchantType},#{consumptionCurrency},#{version},#{notify},#{image},#{receiveMobile},#{validDate},#{buckle},#{isCertified},#{isSpecialService},#{isNoReason},#{isExternalUrl},#{admin},#{isIncludePost})")
	public void insert(Merchant merchant);

	@Select("select * from `sellercenter_merchant` where `id`=#{id}")
	public Merchant get(Long id);

	@Update("update `sellercenter_merchant` set `name`=#{name},`code`=#{code},`userId`=#{userId},`province`=#{province},`city`=#{city},`district`=#{district},`address`=#{address},`longitude`=#{longitude},`latitude`=#{latitude},`logo`=#{logo},`classifyId`=#{classifyId},`flagship`=#{flagship},`introduction`=#{introduction}," +
			"`detailIntroduction`=#{detailIntroduction},`linkman`=#{linkman},`phone`=#{phone},`integralModeId`=#{integralModeId},`commodityAuditing`=#{commodityAuditing},`distribution`=#{distribution},`registTime`=#{registTime},`state`=#{state},`merchantType`=#{merchantType},`consumptionCurrency`=#{consumptionCurrency}," +
			"`version`=#{version},`notify`=#{notify},`image`=#{image},`receiveMobile`=#{receiveMobile} ,`validDate`=#{validDate},`buckle`=#{buckle},`isExternalUrl`=#{isExternalUrl},`isNoReason`=#{isNoReason},`isSpecialService`=#{isSpecialService},`isCertified`=#{isCertified},`admin`=#{admin},`isIncludePost`=#{isIncludePost} where `id`=#{id}")
	public void update(Merchant merchant);

	@Delete("delete from `sellercenter_merchant` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `sellercenter_merchant` limit #{start},#{count}")
	public List<Merchant> list4page(Map<String,Object> param);

	@Select("select count(*) from `sellercenter_merchant`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `sellercenter_merchant`")
	public List<Merchant> listAll();
	
}