package com.qcloud.component.marketing.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.marketing.model.Slide;
import com.qcloud.component.marketing.model.query.SlideQuery;
import com.qcloud.component.publicdata.IntKeyValue;

public interface SlideService {

    public boolean add(Slide slide);

    public Slide get(Long id);

    public boolean delete(Long id);

    public boolean update(Slide slide);

    public Page<Slide> page(SlideQuery query, int start, int count);

    public List<Slide> listAll();

    List<Slide> listBySence(int sence);

    List<IntKeyValue> getSenceTypes();
}
