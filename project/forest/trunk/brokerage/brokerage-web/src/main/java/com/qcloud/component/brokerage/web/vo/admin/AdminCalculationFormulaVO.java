package com.qcloud.component.brokerage.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminCalculationFormulaVO {
	
	private long id;		
	
	//公式
	private String formula;		
	
	//1 启用 2关停
	private int state;		
	
	private String name;		
	
	//交易用户是否参加分佣1 是 2否
	private int tradeUserDistribution;		
	
	private String tradeUserDistributionStr;
	
	//分配比例,如50%,则存50
	private int proportion;		
	
	//手续费率
    private int poundageRate;

	public AdminCalculationFormulaVO(){
	
	}

	public AdminCalculationFormulaVO(long id,String formula,int state,String name,int tradeUserDistribution,int proportion){
		this.id = id;		
		this.formula = formula;		
		this.state = state;		
		this.name = name;		
		this.tradeUserDistribution = tradeUserDistribution;		
		this.proportion = proportion;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getFormula() {
		return formula;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}	
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
		
	public void setTradeUserDistribution(int tradeUserDistribution) {
		this.tradeUserDistribution = tradeUserDistribution;
	}

	public int getTradeUserDistribution() {
		return tradeUserDistribution;
	}	
		
	public void setProportion(int proportion) {
		this.proportion = proportion;
	}

	public int getProportion() {
		return proportion;
	}

    
    public String getTradeUserDistributionStr() {
    
        return tradeUserDistributionStr;
    }

    
    public void setTradeUserDistributionStr(String tradeUserDistributionStr) {
    
        this.tradeUserDistributionStr = tradeUserDistributionStr;
    }

    
    public int getPoundageRate() {
    
        return poundageRate;
    }

    
    public void setPoundageRate(int poundageRate) {
    
        this.poundageRate = poundageRate;
    }	
		
}
