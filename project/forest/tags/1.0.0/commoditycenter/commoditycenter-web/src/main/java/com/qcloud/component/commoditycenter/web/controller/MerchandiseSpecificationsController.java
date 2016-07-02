package com.qcloud.component.commoditycenter.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.commoditycenter.model.Merchandise;
import com.qcloud.component.commoditycenter.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.commoditycenter.service.MerchandiseService;
import com.qcloud.component.commoditycenter.service.MerchandiseSpecificationsRelationService;
import com.qcloud.component.commoditycenter.web.handler.ClassifySpecificationsHandler;
import com.qcloud.component.commoditycenter.web.vo.AttributeSpecificationsVO;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;

@Controller
@RequestMapping(value = MerchandiseSpecificationsController.DIR)
public class MerchandiseSpecificationsController {

    public static final String                       DIR = "/merchandiseSpecifications";

    @Autowired
    private MerchandiseService                       merchandiseService;

    @Autowired
    private MerchandiseSpecificationsRelationService merchandiseSpecificationsRelationService;

//    @Autowired
//    private ClassifySpecificationsService            classifySpecificationsService;

    @Autowired
    private ClassifySpecificationsHandler            classifySpecificationsHandler;

    @RequestMapping
    public FrontAjaxView listByMerchandise(Long merchandiseId) {

        Merchandise merchandise = merchandiseService.get(merchandiseId);
        AssertUtil.assertNotNull(merchandise, "商品不存在." + merchandiseId);
        List<MerchandiseSpecificationsRelation> list = merchandiseSpecificationsRelationService.listByMerchandiseId(merchandiseId);
        List<AttributeSpecificationsVO> attributeSpecificationsVOList = classifySpecificationsHandler.toAttributeSpecificationsVOList(list);
        // List<ClassifySpecifications> csList = classifySpecificationsService.listByClassify(merchandise.getMallClassifyId());
        // List<AttributeSpecificationsVO> attributeSpecificationsVOList = classifySpecificationsHandler.toAttributeSpecificationsVOList(csList);
//        List<SpecificationsForm> specificationsList = new ArrayList<SpecificationsForm>();
//        for (AttributeSpecificationsVO attributeSpecificationsVO : attributeSpecificationsVOList) {
//            SpecificationsForm sf = new SpecificationsForm();
//            sf.setAttributeId(attributeSpecificationsVO.getId());
//            sf.setValue(attributeSpecificationsVO.getSpecificationsList().get(0));
//            specificationsList.add(sf);
//        }
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("specificationsList", attributeSpecificationsVOList);
        view.setMessage("查询商品可选规格成功.");
        return view;
    }
}
