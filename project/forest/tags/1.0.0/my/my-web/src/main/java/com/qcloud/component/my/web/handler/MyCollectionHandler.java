package com.qcloud.component.my.web.handler;

import java.util.List;
import com.qcloud.component.my.model.MyCollection;
import com.qcloud.component.my.web.vo.MyMerchandiseCollectionVO;
import com.qcloud.component.my.web.vo.MyMerchantCollectionVO;
import com.qcloud.component.my.web.vo.MyStoreCollectionVO;
import com.qcloud.component.my.web.vo.admin.AdminMyCollectionVO;

public interface MyCollectionHandler {

    List<MyMerchandiseCollectionVO> toMerchandiseMyCollectionVOList(List<MyCollection> list);

    MyMerchandiseCollectionVO toMerchandiseMyCollectionVO(MyCollection myCollection);

    List<MyMerchantCollectionVO> toMerchantMyCollectionVOList(List<MyCollection> list);

    MyMerchantCollectionVO toMerchantMyCollectionVO(MyCollection myCollection);

    // 我的店铺收藏
    List<MyStoreCollectionVO> toStoreMyCollectionVOList(List<MyCollection> list);

    MyStoreCollectionVO toStoreMyCollectionVO(MyCollection myCollection);

    List<AdminMyCollectionVO> toVOList4Admin(List<MyCollection> list);

    AdminMyCollectionVO toVO4Admin(MyCollection myCollection);
}
