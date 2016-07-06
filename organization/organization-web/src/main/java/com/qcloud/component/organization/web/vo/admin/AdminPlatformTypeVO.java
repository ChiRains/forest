package com.qcloud.component.organization.web.vo.admin;

import java.util.ArrayList;
import java.util.List;


public class AdminPlatformTypeVO {
    
    private int type;
    
    private String name;
    
    private int parent;
    
    private List<Integer> parents=new ArrayList<Integer>();

    
    public int getType() {
    
        return type;
    }

    
    public void setType(int type) {
    
        this.type = type;
    }

    
    public String getName() {
    
        return name;
    }

    
    public void setName(String name) {
    
        this.name = name;
    }

    
    public int getParent() {
    
        return parent;
    }

    
    public void setParent(int parent) {
    
        this.parent = parent;
    }

    
    public List<Integer> getParents() {
    
        return parents;
    }

    
    public void setParents(List<Integer> parents) {
    
        this.parents = parents;
    }
    
}
