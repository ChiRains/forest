package com.qcloud.component.my.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.my.model.InvoiceMode;
import com.qcloud.component.my.model.query.InvoiceModeQuery;

public interface InvoiceModeMapper {

	@Insert("insert into `my_invoice_mode`(`id`,`userId`,`mode`,`type`,`head`,`content`,`isDefault`)"
			+ " values(#{id},#{userId},#{mode},#{type},#{head},#{content},#{isDefault})")
	public void insert(InvoiceMode invoiceMode);

	@Select("select * from `my_invoice_mode` where `id`=#{id}")
	public InvoiceMode get(Long id);

	@Update("update `my_invoice_mode` set `userId`=#{userId},`mode`=#{mode},`type`=#{type},`head`=#{head},`content`=#{content} where `id`=#{id}")
	public void update(InvoiceMode invoiceMode);

	@Delete("delete from `my_invoice_mode` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `my_invoice_mode` limit #{start},#{count}")
	public List<InvoiceMode> list4page(Map<String,Object> param);

	@Select("select count(*) from `my_invoice_mode`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `my_invoice_mode`")
	public List<InvoiceMode> listAll();
}