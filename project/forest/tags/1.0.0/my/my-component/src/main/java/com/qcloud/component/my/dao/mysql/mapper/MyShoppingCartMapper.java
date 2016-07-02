package com.qcloud.component.my.dao.mysql.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.qcloud.component.my.model.MyShoppingCart;

public interface MyShoppingCartMapper {

	@Insert("insert into `${table_name}`(`id`,`userId`,`merchantId`,`merchantClassifyId`,`unifiedMerchandiseId`,`time`,`number`)"
			+ " values(#{id},#{userId},#{merchantId},#{merchantClassifyId},#{unifiedMerchandiseId},#{time},#{number}) on DUPLICATE key update number = number + #{number}")
	public void insert(MyShoppingCart myShoppingCart);

	@Select("select * from `${table_name}` where `id`=#{id}")
	public MyShoppingCart get(Long id);

	@Update("update `${table_name}` set `userId`=#{userId},`merchantId`=#{merchantId},`merchantClassifyId`=#{merchantClassifyId},`unifiedMerchandiseId`=#{unifiedMerchandiseId},`time`=#{time},`number`=#{number} where `id`=#{id}")
	public void update(MyShoppingCart myShoppingCart);

	@Delete("delete from `${table_name}` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `${table_name}` limit #{start},#{count}")
	public List<MyShoppingCart> list4page(Map<String,Object> param);

	@Select("select count(*) from `${table_name}`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `${table_name}`")
	public List<MyShoppingCart> listAll();
}