package com.qcloud.component.goods.service;

import java.util.List;
import com.qcloud.component.goods.model.UnifiedMerchandise;

public interface PointMerchandiseService {

    public static final int unifiedMerchandise_type = 5;

    public boolean createList(List<UnifiedMerchandise> list);
}
