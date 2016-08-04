package com.qcloud.project.forest.model.oms;

import java.util.List;

public class XmlMerchandiseBatch extends XmlResult {

    private int           total_results;

    private int           total_page;

    private int           current_page;

    private List<Product> product_list;

    public int getTotal_results() {

        return total_results;
    }

    public void setTotal_results(int total_results) {

        this.total_results = total_results;
    }

    public int getTotal_page() {

        return total_page;
    }

    public void setTotal_page(int total_page) {

        this.total_page = total_page;
    }

    public int getCurrent_page() {

        return current_page;
    }

    public void setCurrent_page(int current_page) {

        this.current_page = current_page;
    }

    public List<Product> getProduct_list() {

        return product_list;
    }

    public void setProduct_list(List<Product> product_list) {

        this.product_list = product_list;
    }
}
