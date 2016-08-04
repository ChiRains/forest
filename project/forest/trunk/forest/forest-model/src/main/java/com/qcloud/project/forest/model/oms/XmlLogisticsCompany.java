package com.qcloud.project.forest.model.oms;

import java.util.List;

public class XmlLogisticsCompany extends XmlResult {

    private List<Logistics_Company> logistics_companies;

    public List<Logistics_Company> getLogistics_companies() {

        return logistics_companies;
    }

    public void setLogistics_companies(List<Logistics_Company> logistics_companies) {

        this.logistics_companies = logistics_companies;
    }
}
