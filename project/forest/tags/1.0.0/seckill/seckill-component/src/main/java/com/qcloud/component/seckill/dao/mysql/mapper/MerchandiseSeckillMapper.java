package com.qcloud.component.seckill.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.seckill.model.MerchandiseSeckill;
import com.qcloud.component.seckill.model.query.MerchandiseSeckillQuery;

public interface MerchandiseSeckillMapper {

	@Insert("insert into `seckill_merchandise_seckill`(`id`,`screeningsId`,`unifiedMerchandiseId`,`qUnifiedMerchandiseId`,`merchandiseName`,`discountPrice`,`mallClassifyId`,`sort`,`salesVolume`,`originalStock`,`enable`)"
			+ " values(#{id},#{screeningsId},#{unifiedMerchandiseId},#{qUnifiedMerchandiseId},#{merchandiseName},#{discountPrice},#{mallClassifyId},#{sort},#{salesVolume},#{originalStock},#{enable})")
	public void insert(MerchandiseSeckill merchandiseSeckill);

	@Select("select * from `seckill_merchandise_seckill` where `id`=#{id}")
	public MerchandiseSeckill get(Long id);

	@Update("update `seckill_merchandise_seckill` set `screeningsId`=#{screeningsId},`unifiedMerchandiseId`=#{unifiedMerchandiseId},`qUnifiedMerchandiseId`=#{qUnifiedMerchandiseId},`merchandiseName`=#{merchandiseName},`discountPrice`=#{discountPrice},`mallClassifyId`=#{mallClassifyId},`sort`=#{sort},`salesVolume`=#{salesVolume},`originalStock`=#{originalStock},`enable`=#{enable} where `id`=#{id}")
	public void update(MerchandiseSeckill merchandiseSeckill);

	@Delete("delete from `seckill_merchandise_seckill` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `seckill_merchandise_seckill` limit #{start},#{count}")
	public List<MerchandiseSeckill> list4page(Map<String,Object> param);

	@Select("select count(*) from `seckill_merchandise_seckill`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `seckill_merchandise_seckill`")
	public List<MerchandiseSeckill> listAll();
}