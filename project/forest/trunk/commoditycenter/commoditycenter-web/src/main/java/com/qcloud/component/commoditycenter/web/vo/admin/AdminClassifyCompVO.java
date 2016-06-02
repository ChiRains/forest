package com.qcloud.component.commoditycenter.web.vo.admin;

import java.util.ArrayList;
import java.util.List;

public class AdminClassifyCompVO {	
	
	//分类定义
	private long classifyId;
	
	private String classifyStr;	
	
    private String path;
	
	private List<AdminClassifySpecificationsVO> detailList = new ArrayList<AdminClassifySpecificationsVO>();

	public AdminClassifyCompVO(){
	
	}
			
	public void setClassifyId(long classifyId) {
		this.classifyId = classifyId;
	}

	public long getClassifyId() {
		return classifyId;
	}

    public String getClassifyStr() {
        return classifyStr;
    }

    public void setClassifyStr(String classifyStr) {
        this.classifyStr = classifyStr;
    }
           
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<AdminClassifySpecificationsVO> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<AdminClassifySpecificationsVO> detailList) {
        this.detailList = detailList;
    }

    public int getSize(){
        return detailList.size() + 1;
    }
}
