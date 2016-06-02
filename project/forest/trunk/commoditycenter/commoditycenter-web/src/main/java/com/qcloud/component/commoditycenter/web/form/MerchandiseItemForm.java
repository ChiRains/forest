package com.qcloud.component.commoditycenter.web.form;

import java.util.List;

public class MerchandiseItemForm {
	
	private List<Long> merchandiseItemsIds;
	
	private List<Double> purchase;
    
    private List<Double> discount;
    
    private List<Double> price;
    

	public List<Double> getPurchase() {
		return purchase;
	}

	public void setPurchase(List<Double> purchase) {
		this.purchase = purchase;
	}

	public List<Double> getDiscount() {
		return discount;
	}

	public void setDiscount(List<Double> discount) {
		this.discount = discount;
	}

	public List<Double> getPrice() {
		return price;
	}

	public void setPrice(List<Double> price) {
		this.price = price;
	}

	public List<Long> getMerchandiseItemsIds() {
		return merchandiseItemsIds;
	}

	public void setMerchandiseItemsIds(List<Long> merchandiseItemsIds) {
		this.merchandiseItemsIds = merchandiseItemsIds;
	}

    
}
