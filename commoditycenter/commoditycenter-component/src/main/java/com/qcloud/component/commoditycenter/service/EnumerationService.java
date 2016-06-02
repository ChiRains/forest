package com.qcloud.component.commoditycenter.service;
import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.commoditycenter.model.Enumeration;
import com.qcloud.component.commoditycenter.model.query.EnumerationQuery;
public interface EnumerationService {
    
    public boolean add(Enumeration enumeration);

    public Enumeration get(Long id);       

    public boolean delete(Long id);

    public boolean update(Enumeration enumeration);

    public Page<Enumeration> page(EnumerationQuery query, int start, int count);

    public List<Enumeration> listAll();    

    public List<Enumeration> listByName(String name);
    
    boolean existByName(String name);
    
    public List<String> listNames();
}
