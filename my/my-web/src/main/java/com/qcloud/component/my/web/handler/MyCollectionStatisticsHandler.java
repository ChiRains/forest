package com.qcloud.component.my.web.handler;

import java.util.List;
import com.qcloud.component.my.model.MyCollectionStatistics;
import com.qcloud.component.my.web.vo.MyCollectionStatisticsVO;

public interface MyCollectionStatisticsHandler {

    List<MyCollectionStatisticsVO> toVOList(List<MyCollectionStatistics> list);

    MyCollectionStatisticsVO toVO(MyCollectionStatistics myCollectionStatistics);
}
