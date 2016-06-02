package com.qcloud.component.sellercenter.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.model.SexpressDistrict;
import com.qcloud.component.sellercenter.model.query.SexpressDistrictQuery;

public interface SexpressDistrictService {

    public boolean add(SexpressDistrict sexpressDistrict);

    public SexpressDistrict get(Long id);

    public boolean delete(Long id);

    public boolean update(SexpressDistrict sexpressDistrict);

    public Page<SexpressDistrict> page(SexpressDistrictQuery query, int start, int count);

    public List<SexpressDistrict> listAll();

    public List<SexpressDistrict> listByExpressId(Long id);
}
