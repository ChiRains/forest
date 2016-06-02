package com.qcloud.component.my.web.handler;

import java.util.List;
import com.qcloud.component.my.model.MyAfterSale;
import com.qcloud.component.my.web.vo.MyAfterSaleVO;

public interface MyAfterSaleHandler {

    List<MyAfterSaleVO> toVOList(List<MyAfterSale> list);

    MyAfterSaleVO toVO(MyAfterSale myAfterSale);
}
