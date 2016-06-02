package com.qcloud.component.personalcenter.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.dao.GradeDao;
import com.qcloud.component.personalcenter.model.Grade;
import com.qcloud.component.personalcenter.service.GradeService;
import com.qcloud.component.personalcenter.model.query.GradeQuery;
		
@Service
public class GradeServiceImpl implements GradeService{
	
	@Autowired
	private GradeDao gradeDao;
	
	@Autowired
	private FileSDKClient fileSDKClient;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "personalcenter_grade";

	@Override
	public boolean add(Grade grade) {
		long id = autoIdGenerator.get(ID_KEY);
		grade.setId(id);
		if(StringUtils.isNotEmpty(grade.getImage())){
		    grade.setImage(fileSDKClient.uidToUrl(grade.getImage()));
		}
		return gradeDao.add(grade);
	}

	@Override
	public Grade get(Long id) {	
		return gradeDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return gradeDao.delete(id);
	}
	
	@Override
	public boolean update(Grade grade) {
	    if(StringUtils.isNotEmpty(grade.getImage())){
            grade.setImage(fileSDKClient.uidToUrl(grade.getImage()));
        }
		return gradeDao.update(grade);
	}
	
	@Override
	public Page<Grade> page(GradeQuery query, int start, int count) {
		return gradeDao.page(start, count);
	}
	
	public List<Grade> listAll(){
		return gradeDao.listAll();
	}
}

