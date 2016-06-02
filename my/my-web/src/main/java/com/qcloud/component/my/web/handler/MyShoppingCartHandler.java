package com.qcloud.component.my.web.handler;

import java.util.List;
import com.qcloud.component.my.model.MyShoppingCart;
import com.qcloud.component.my.web.vo.MyShoppingCartClassifyVO;
import com.qcloud.component.my.web.vo.MyShoppingCartMerchantVO;
import com.qcloud.component.my.web.vo.MyShoppingCartVO;
import com.qcloud.component.my.web.vo.admin.AdminMyShoppingCartVO;

public interface MyShoppingCartHandler {

    List<MyShoppingCartVO> toVOList(List<MyShoppingCart> list);

    MyShoppingCartVO toVO(MyShoppingCart myShoppingCart);

    List<MyShoppingCartClassifyVO> toVOList4Classify(List<MyShoppingCart> list);

    List<MyShoppingCartMerchantVO> toVOList4Merchant(List<MyShoppingCart> list);

    List<AdminMyShoppingCartVO> toVOList4Admin(List<MyShoppingCart> list);

    AdminMyShoppingCartVO toVO4Admin(MyShoppingCart myShoppingCart);
}
