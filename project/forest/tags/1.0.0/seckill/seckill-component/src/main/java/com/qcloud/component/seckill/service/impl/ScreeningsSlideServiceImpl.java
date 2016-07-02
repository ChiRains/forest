package com.qcloud.component.seckill.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.seckill.dao.ScreeningsSlideDao;
import com.qcloud.component.seckill.model.ScreeningsSlide;
import com.qcloud.component.seckill.service.ScreeningsSlideService;
import com.qcloud.component.seckill.model.query.ScreeningsSlideQuery;
		
@Service
public class ScreeningsSlideServiceImpl implements ScreeningsSlideService{
	
	@Autowired
	private ScreeningsSlideDao screeningsSlideDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "seckill_screenings_slide";

	@Override
	public boolean add(ScreeningsSlide screeningsSlide) {
		long id = autoIdGenerator.get(ID_KEY);
		screeningsSlide.setId(id);
		
		return screeningsSlideDao.add(screeningsSlide);
	}

	@Override
	public ScreeningsSlide get(Long id) {	
		return screeningsSlideDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return screeningsSlideDao.delete(id);
	}
	
	@Override
	public boolean update(ScreeningsSlide screeningsSlide) {
		return screeningsSlideDao.update(screeningsSlide);
	}
	
	@Override
	public Page<ScreeningsSlide> page(ScreeningsSlideQuery query, int start, int count) {
		return screeningsSlideDao.page(query, start, count);
	}
	
	public List<ScreeningsSlide> listAll(){
		return screeningsSlideDao.listAll();
	}

    @Override
    public List<ScreeningsSlide> listByScreenings(long screeningsId) {
        return screeningsSlideDao.listByScreenings(screeningsId);
    }
}

