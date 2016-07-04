//package com.qcloud.component.sellercenter.dao.mysql.mapper;
//
//import java.util.List;
//import java.util.Map;
//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
//import com.qcloud.component.sellercenter.model.MerchantSort;
//
//
//public interface MerchantSortMapper {
//    
//    @Insert("insert into `sellercenter_merchant_sort`(`id`,`merchantId`,`name`,`logo`,`sort`)"
//            + " values(#{id},#{merchantId},#{name},#{logo},#{sort})")
//    public void insert(MerchantSort merchantSort);
//
//    @Select("select * from `sellercenter_merchant_sort` where `id`=#{id}")
//    public MerchantSort get(Long id);
//
//    @Update("update `sellercenter_merchant_sort` set `merchantId`=#{merchantId},`name`=#{name},`logo`=#{logo},`sort`=#{sort} where `id`=#{id}")
//    public void update(MerchantSort merchantSort);
//
//    @Delete("delete from `sellercenter_merchant_sort` where `id`=#{id}")
//    public void delete(Long id);
//
//    @Select("select * from `sellercenter_merchant_sort` limit #{start},#{count}")
//    public List<MerchantSort> list4page(Map<String,Object> param);
//
//    @Select("select count(*) from `sellercenter_merchant_sort`")
//    public int count4page(Map<String,Object> param);
//    
//    @Select("select * from `sellercenter_merchant_sort`")
//    public List<MerchantSort> listAll();
//    
//    @Select("select * from `sellercenter_merchant_sort`  order by `sort` asc limit #{start},#{count} ")
//    public List<MerchantSort> list(int start,int count);
//    
//}
