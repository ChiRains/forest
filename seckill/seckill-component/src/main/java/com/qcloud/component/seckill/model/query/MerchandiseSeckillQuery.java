package com.qcloud.component.seckill.model.query;

public class MerchandiseSeckillQuery {

    private long screeningsId;
    
    private String name;

    public MerchandiseSeckillQuery() {

    }

    public long getScreeningsId() {
        return screeningsId;
    }

    public void setScreeningsId(long screeningsId) {
        this.screeningsId = screeningsId;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
}
