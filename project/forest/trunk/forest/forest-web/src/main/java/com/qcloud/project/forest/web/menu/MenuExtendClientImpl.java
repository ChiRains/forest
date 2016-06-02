package com.qcloud.project.forest.web.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qcloud.component.admin.web.menu.MenuExtendClient;
import com.qcloud.component.permission.model.Catalog;
import com.qcloud.component.permission.model.Menu;
import com.qcloud.component.permission.vo.CatalogVO;

@Component
public class MenuExtendClientImpl implements MenuExtendClient {

	@Override
	public List<CatalogVO> list() {
		List<CatalogVO> list = new ArrayList<CatalogVO>();
	/*	
		CatalogVO set = new CatalogVO();
	       Catalog setCatalog = new Catalog();
	     setCatalog.setId(1010006000000001L);
	     setCatalog.setName("私人定制");
	      set.setCatalog(setCatalog);
	      List<Menu> setList = new ArrayList<Menu>();
	      
	       Menu bMenu = new Menu();
		      bMenu.setCatalogId(-1L);
		      bMenu.setId(-1L);
		      bMenu.setName("BMI计算");
		      bMenu.setUrl("admin/analysisresult/list");
		       setList.add(bMenu);
		       
		         Menu sMenu = new Menu();
			      sMenu.setCatalogId(-1L);
			       sMenu.setId(-1L);
			       sMenu.setName("大参林资讯");
			       sMenu.setUrl("admin/article/classifyList");
			       setList.add(sMenu);
			       
			       set.setMenuList(setList);
			       
	               list.add(set);*/
	     	return list;
	}
}
