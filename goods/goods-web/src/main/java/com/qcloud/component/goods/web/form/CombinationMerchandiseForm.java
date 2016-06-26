package com.qcloud.component.goods.web.form;

import com.qcloud.component.goods.model.CombinationMerchandise;
import com.qcloud.component.goods.model.CombinationMerchandiseItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brook on 2015/6/24.
 */
public class CombinationMerchandiseForm {
    private CombinationMerchandise combinationMerchandise=new  CombinationMerchandise();
    private List<CombinationMerchandiseItem> combinationMerchandiseItems = new ArrayList<CombinationMerchandiseItem>();

    public CombinationMerchandiseForm() {
    }

    public CombinationMerchandiseForm(CombinationMerchandise combinationMerchandise, List<CombinationMerchandiseItem> combinationMerchandiseItems) {
        this.combinationMerchandise = combinationMerchandise;
        this.combinationMerchandiseItems = combinationMerchandiseItems;
    }

    public CombinationMerchandise getCombinationMerchandise() {
        return combinationMerchandise;
    }

    public void setCombinationMerchandise(CombinationMerchandise combinationMerchandise) {
        this.combinationMerchandise = combinationMerchandise;
    }

    public List<CombinationMerchandiseItem> getCombinationMerchandiseItems() {
        return combinationMerchandiseItems;
    }

    public void setCombinationMerchandiseItems(List<CombinationMerchandiseItem> combinationMerchandiseItems) {
        this.combinationMerchandiseItems = combinationMerchandiseItems;
    }
}
