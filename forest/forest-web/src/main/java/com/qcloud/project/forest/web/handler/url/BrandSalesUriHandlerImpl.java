package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class BrandSalesUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/brandSales/listBrand.do");
        list.add("/admin/brandSales/listClassify.do");
        list.add("/admin/brandSales/enable.do");
        list.add("/admin/brandSales/toAdd.do");
        list.add("/admin/brandSales/add.do");
        list.add("/admin/brandSales/toEdit.do");
        list.add("/admin/brandSales/edit.do");
        list.add("/admin/brandSales/listMerchandise.do");
        list.add("/admin/brandSales/toAddMerchandise.do");
        list.add("/admin/brandSales/addMerchandise.do");
        list.add("/admin/brandSales/deleteMerchandise.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/userdb/list.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/brandSales/brandOnSaleBrands.do");
        list.add("/brandSales/getBrandSalesClassify.do");
        list.add("/brandSales/getBrandSaleslist.do");
        list.add("/app/brandSales/brandOnSaleBrands.do");
        list.add("/app/brandSales/getBrandSalesClassify.do");
        list.add("/app/brandSales/getBrandSaleslist.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/brandSales/brandOnSaleBrands.do");
        list.add("/app/brandSales/getBrandSalesClassify.do");
        list.add("/app/brandSales/getBrandSaleslist.do");
        return list;
    }
}
