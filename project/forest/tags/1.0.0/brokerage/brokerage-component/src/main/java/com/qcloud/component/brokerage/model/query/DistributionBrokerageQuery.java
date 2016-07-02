package com.qcloud.component.brokerage.model.query;

import java.util.Date;

public class DistributionBrokerageQuery {
    
    private Date startDate;
    
    private Date endDate;
    
    private int type;
    
    private int state;

	public DistributionBrokerageQuery(){
	
	}

    public int getType() {
    
        return type;
    }

    
    public void setType(int type) {
    
        this.type = type;
    }

    
    public int getState() {
    
        return state;
    }

    
    public void setState(int state) {
    
        this.state = state;
    }

    
    public Date getStartDate() {
    
        return startDate;
    }

    
    public void setStartDate(Date startDate) {
    
        this.startDate = startDate;
    }

    
    public Date getEndDate() {
    
        return endDate;
    }

    
    public void setEndDate(Date endDate) {
    
        this.endDate = endDate;
    }
}
