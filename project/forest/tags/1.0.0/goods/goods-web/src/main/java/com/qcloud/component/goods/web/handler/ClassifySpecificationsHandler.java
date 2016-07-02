package com.qcloud.component.goods.web.handler;

import java.util.List;
import com.qcloud.component.goods.model.ClassifySpecifications;
import com.qcloud.component.goods.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.goods.web.vo.AttributeSpecificationsVO;
import com.qcloud.component.goods.web.vo.ClassifySpecificationsVO;
import com.qcloud.component.goods.web.vo.admin.AdminClassifyCompVO;
import com.qcloud.component.goods.web.vo.admin.AdminClassifySpecificationsVO;
import com.qcloud.component.publicdata.model.Classify;

public interface ClassifySpecificationsHandler {

    List<ClassifySpecificationsVO> toVOList(List<ClassifySpecifications> list);

    ClassifySpecificationsVO toVO(ClassifySpecifications classifySpecifications);

    // List<AttributeSpecificationsVO> toAttributeSpecificationsVOList(List<ClassifySpecifications> list);
    List<AttributeSpecificationsVO> toAttributeSpecificationsVOList(List<MerchandiseSpecificationsRelation> list);

//    AttributeSpecificationsVO toAttributeSpecificationsVO(ClassifySpecifications classifySpecifications);

    List<AdminClassifyCompVO> toVOList4Admin(List<ClassifySpecifications> list);

    List<AdminClassifyCompVO> toVOList4Admin(List<Classify> cList, List<ClassifySpecifications> csList);

    AdminClassifyCompVO toCompVO4Admin(ClassifySpecifications classifySpecifications);

    AdminClassifySpecificationsVO toVO4Admin(ClassifySpecifications classifySpecifications);

    public List<AdminClassifySpecificationsVO> toDetailVOList4Admin(List<ClassifySpecifications> classifySpecificationses);
}
