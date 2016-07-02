package com.qcloud.component.seckill.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.seckill.model.ScreeningsSlide;
import com.qcloud.component.seckill.model.query.ScreeningsSlideQuery;

public interface ScreeningsSlideDao extends ISimpleDao<ScreeningsSlide, Long> {

    public boolean add(ScreeningsSlide screeningsSlide);

    public ScreeningsSlide get(Long id);

    public boolean delete(Long id);

    public boolean update(ScreeningsSlide screeningsSlide);

    public List<ScreeningsSlide> list(List<Long> idList);

    public Map<Long, ScreeningsSlide> map(Set<Long> idSet);

    public Page<ScreeningsSlide> page(ScreeningsSlideQuery query, int start, int size);

    public List<ScreeningsSlide> listAll();

    List<ScreeningsSlide> listByScreenings(long screeningsId);
}
