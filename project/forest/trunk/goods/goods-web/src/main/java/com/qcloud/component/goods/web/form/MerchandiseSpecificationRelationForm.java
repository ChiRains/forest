package com.qcloud.component.goods.web.form;

import java.util.List;


public class MerchandiseSpecificationRelationForm {
    
    private List<RelationForm> relationForms;
    
    private List<RelationForm> extraForms;

    
    public List<RelationForm> getRelationForms() {
    
        return relationForms;
    }

    
    public void setRelationForms(List<RelationForm> relationForms) {
    
        this.relationForms = relationForms;
    }


    
    public List<RelationForm> getExtraForms() {
    
        return extraForms;
    }


    
    public void setExtraForms(List<RelationForm> extraForms) {
    
        this.extraForms = extraForms;
    }
    
}
