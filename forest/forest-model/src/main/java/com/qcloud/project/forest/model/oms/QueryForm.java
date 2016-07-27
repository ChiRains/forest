package com.qcloud.project.forest.model.oms;

public class QueryForm extends AbstractForm {

    public String  starttime;

    public String  endtime;

    public String  status;

    public String  page;

    public String  pagesize;

    // 单个订单
    public String  tid;

    /**
     *  单个商品接口
     */
    public String  product_id;

    public String  shop_product_id;

    public String  sku_id;

    public String  shop_sku_id;

    /**
     *  库存更新接口
     */
    public Integer storage;

    /**
     *  物流公司接口
     */
    public Long    id;

    public String  code;

    public String  name;

    /**
     *  发货接口
     */
    public String  company_code;

    public String  out_sid;

    public String getStarttime() {

        return starttime;
    }

    public void setStarttime(String starttime) {

        this.starttime = starttime;
    }

    public String getEndtime() {

        return endtime;
    }

    public void setEndtime(String endtime) {

        this.endtime = endtime;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public String getPage() {

        return page;
    }

    public void setPage(String page) {

        this.page = page;
    }

    public String getPagesize() {

        return pagesize;
    }

    public void setPagesize(String pagesize) {

        this.pagesize = pagesize;
    }

    public String getTid() {

        return tid;
    }

    public void setTid(String tid) {

        this.tid = tid;
    }

    public String getProduct_id() {

        return product_id;
    }

    public void setProduct_id(String product_id) {

        this.product_id = product_id;
    }

    public String getShop_product_id() {

        return shop_product_id;
    }

    public void setShop_product_id(String shop_product_id) {

        this.shop_product_id = shop_product_id;
    }

    public String getSku_id() {

        return sku_id;
    }

    public void setSku_id(String sku_id) {

        this.sku_id = sku_id;
    }

    public String getShop_sku_id() {

        return shop_sku_id;
    }

    public void setShop_sku_id(String shop_sku_id) {

        this.shop_sku_id = shop_sku_id;
    }

    public Integer getStorage() {

        return storage;
    }

    public void setStorage(Integer storage) {

        this.storage = storage;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getCompany_code() {

        return company_code;
    }

    public void setCompany_code(String company_code) {

        this.company_code = company_code;
    }

    public String getOut_sid() {

        return out_sid;
    }

    public void setOut_sid(String out_sid) {

        this.out_sid = out_sid;
    }
}
