package com.qcloud.component.goods.web.form;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.component.goods.model.UnifiedMerchandise;

public class UnifiedMerchandiseListForm {

    List<UnifiedMerchandise> merchandiseList = new ArrayList<UnifiedMerchandise>();

    public List<UnifiedMerchandise> getMerchandiseList() {

        return merchandiseList;
    }

    public void setMerchandiseList(List<UnifiedMerchandise> merchandiseList) {

        this.merchandiseList = merchandiseList;
    }
}
