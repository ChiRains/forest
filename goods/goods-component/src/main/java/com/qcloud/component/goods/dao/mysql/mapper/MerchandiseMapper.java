package com.qcloud.component.goods.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.query.MerchandiseQuery;

public interface MerchandiseMapper {

	@Insert("insert into `goods_merchandise`(`id`,`merchantClassifyId`,`mallClassifyId`,`specClassifyId`,`merchantId`,`name`,`sysCode`,`image`,`keywords`,`weight`,`state`,`unit`,`details`,`desc`,`isCertified`,`isSpecialService`,`isNoReason`,`isExternalUrl`,`certified`,`specialService`,`noReason`,`externalUrl`,`isIncludePost`,`brandId`,`label`)"
			+ " values(#{id},#{merchantClassifyId},#{mallClassifyId},#{specClassifyId},#{merchantId},#{name},#{sysCode},#{image},#{keywords},#{weight},#{state},#{unit},#{details},#{desc},#{isCertified},#{isSpecialService},#{isNoReason},#{isExternalUrl},#{certified},#{specialService},#{noReason},#{externalUrl},#{isIncludePost},#{brandId},#{label})")
	public void insert(Merchandise merchandise);

	@Select("select * from `goods_merchandise` where `id`=#{id}")
	public Merchandise get(Long id);

	@Update("update `goods_merchandise` set `merchantClassifyId`=#{merchantClassifyId},`mallClassifyId`=#{mallClassifyId},`specClassifyId`=#{specClassifyId},`merchantId`=#{merchantId},`name`=#{name},`sysCode`=#{sysCode},`image`=#{image},`keywords`=#{keywords},`weight`=#{weight},`state`=#{state},`unit`=#{unit},`details`=#{details},`desc`=#{desc},`isCertified`=#{isCertified},`isSpecialService`=#{isSpecialService},`isNoReason`=#{isNoReason},`isExternalUrl`=#{isExternalUrl},`certified`=#{certified},`specialService`=#{specialService},`noReason`=#{noReason},`externalUrl`=#{externalUrl},`isIncludePost`=#{isIncludePost},`brandId`=#{brandId},`label`=#{label}  where `id`=#{id}")
	public void update(Merchandise merchandise);

	@Delete("delete from `goods_merchandise` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `goods_merchandise` limit #{start},#{count}")
	public List<Merchandise> list4page(Map<String,Object> param);

	@Select("select count(*) from `goods_merchandise`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `goods_merchandise`")
	public List<Merchandise> listAll();
}