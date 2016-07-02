package com.qcloud.component.seckill.service;

import java.util.List;
import com.qcloud.component.seckill.model.ScreeningsSlide;
import com.qcloud.component.seckill.model.query.ScreeningsSlideQuery;
import com.qcloud.pirates.data.Page;

public interface ScreeningsSlideService {

    public boolean add(ScreeningsSlide screeningsSlide);

    public ScreeningsSlide get(Long id);

    public boolean delete(Long id);

    public boolean update(ScreeningsSlide screeningsSlide);

    public Page<ScreeningsSlide> page(ScreeningsSlideQuery query, int start, int count);

    public List<ScreeningsSlide> listAll();

    List<ScreeningsSlide> listByScreenings(long screeningsId);
}
