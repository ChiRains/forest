package com.qcloud.component.my.model.query;

import java.util.Date;

public class MyCommissionWithdrawCashQuery {
    
    private Date date;
    private int state;
    private int mode;
	public MyCommissionWithdrawCashQuery(){
	
	}

    
    public Date getDate() {
    
        return date;
    }

    
    public void setDate(Date date) {
    
        this.date = date;
    }


    
    public int getState() {
    
        return state;
    }


    
    public void setState(int state) {
    
        this.state = state;
    }


    
    public int getMode() {
    
        return mode;
    }


    
    public void setMode(int mode) {
    
        this.mode = mode;
    }
}
