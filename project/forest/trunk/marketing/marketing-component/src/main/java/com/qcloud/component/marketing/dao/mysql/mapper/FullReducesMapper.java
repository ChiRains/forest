package com.qcloud.component.marketing.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.marketing.model.FullReduces;
import com.qcloud.component.marketing.model.query.FullReducesQuery;

public interface FullReducesMapper {

	@Insert("insert into `marketing_full_reduces`(`id`,`merchantId`,`name`,`benefit`,`limitPrice`,`beginDate`,`endDate`,`canUseCoupon`,`canUseSeckill`,`state`)"
			+ " values(#{id},#{merchantId},#{name},#{benefit},#{limitPrice},#{beginDate},#{endDate},#{canUseCoupon},#{canUseSeckill},#{state})")
	public void insert(FullReduces fullReduces);

	@Select("select * from `marketing_full_reduces` where `id`=#{id}")
	public FullReduces get(Long id);

	@Update("update `marketing_full_reduces` set `merchantId`=#{merchantId},`name`=#{name},`benefit`=#{benefit},`limitPrice`=#{limitPrice},`beginDate`=#{beginDate},`endDate`=#{endDate},`canUseCoupon`=#{canUseCoupon},`canUseSeckill`=#{canUseSeckill},`state`=#{state} where `id`=#{id}")
	public void update(FullReduces fullReduces);

	@Delete("delete from `marketing_full_reduces` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `marketing_full_reduces` limit #{start},#{count}")
	public List<FullReduces> list4page(Map<String,Object> param);

	@Select("select count(*) from `marketing_full_reduces`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `marketing_full_reduces`")
	public List<FullReduces> listAll();
}