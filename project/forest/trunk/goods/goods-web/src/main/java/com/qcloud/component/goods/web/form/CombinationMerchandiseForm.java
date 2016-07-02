package com.qcloud.component.goods.web.form;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.component.goods.model.CombinationMerchandiseItem;
import com.qcloud.component.goods.model.UnifiedMerchandise;

/**
 * Created by brook on 2015/6/24.
 */
public class CombinationMerchandiseForm {

    private UnifiedMerchandise               combinationMerchandise      = new UnifiedMerchandise();

    private List<CombinationMerchandiseItem> combinationMerchandiseItems = new ArrayList<CombinationMerchandiseItem>();

    public CombinationMerchandiseForm() {

    }

    public CombinationMerchandiseForm(UnifiedMerchandise combinationMerchandise, List<CombinationMerchandiseItem> combinationMerchandiseItems) {

        this.combinationMerchandise = combinationMerchandise;
        this.combinationMerchandiseItems = combinationMerchandiseItems;
    }

    public UnifiedMerchandise getCombinationMerchandise() {

        return combinationMerchandise;
    }

    public void setCombinationMerchandise(UnifiedMerchandise combinationMerchandise) {

        this.combinationMerchandise = combinationMerchandise;
    }

    public List<CombinationMerchandiseItem> getCombinationMerchandiseItems() {

        return combinationMerchandiseItems;
    }

    public void setCombinationMerchandiseItems(List<CombinationMerchandiseItem> combinationMerchandiseItems) {

        this.combinationMerchandiseItems = combinationMerchandiseItems;
    }
}
