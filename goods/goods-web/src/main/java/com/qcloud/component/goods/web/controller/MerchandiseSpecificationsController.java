package com.qcloud.component.goods.web.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.goods.UnifiedMerchandiseType;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.key.TypeEnum.MerchandiseStateType;
import com.qcloud.component.goods.service.MerchandiseService;
import com.qcloud.component.goods.service.MerchandiseSpecificationsRelationService;
import com.qcloud.component.goods.service.MerchandiseSpecificationsService;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.goods.web.handler.ClassifySpecificationsHandler;
import com.qcloud.component.goods.web.vo.AttributeSpecificationsVO;
import com.qcloud.component.goods.web.vo.UnifiedMerchandiseSpecificationVO;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;

@Controller
@RequestMapping(value = MerchandiseSpecificationsController.DIR)
public class MerchandiseSpecificationsController {

    public static final String                       DIR = "/merchandiseSpecifications";

    @Autowired
    private MerchandiseService                       merchandiseService;

    @Autowired
    private MerchandiseSpecificationsRelationService merchandiseSpecificationsRelationService;

    // @Autowired
    // private ClassifySpecificationsService classifySpecificationsService;
    @Autowired
    private ClassifySpecificationsHandler            classifySpecificationsHandler;

    @Autowired
    private UnifiedMerchandiseService                unifiedMerchandiseService;

    @Autowired
    private MerchandiseSpecificationsService         merchandiseSpecificationsService;

    @Autowired
    private FileSDKClient                            fileSDKClient;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listByMerchandise(Long merchandiseId) {

        Merchandise merchandise = merchandiseService.get(merchandiseId);
        AssertUtil.assertNotNull(merchandise, "商品不存在." + merchandiseId);
        List<MerchandiseSpecificationsRelation> list = merchandiseSpecificationsRelationService.listByMerchandiseId(merchandiseId);
        List<AttributeSpecificationsVO> attributeSpecificationsVOList = classifySpecificationsHandler.toAttributeSpecificationsVOList(list);
        // List<ClassifySpecifications> csList = classifySpecificationsService.listByClassify(merchandise.getMallClassifyId());
        // List<AttributeSpecificationsVO> attributeSpecificationsVOList = classifySpecificationsHandler.toAttributeSpecificationsVOList(csList);
        // List<SpecificationsForm> specificationsList = new ArrayList<SpecificationsForm>();
        // for (AttributeSpecificationsVO attributeSpecificationsVO : attributeSpecificationsVOList) {
        // SpecificationsForm sf = new SpecificationsForm();
        // sf.setAttributeId(attributeSpecificationsVO.getId());
        // sf.setValue(attributeSpecificationsVO.getSpecificationsList().get(0));
        // specificationsList.add(sf);
        // }
        List<UnifiedMerchandise> merchandiseList = unifiedMerchandiseService.listByMerchandise(merchandiseId, UnifiedMerchandiseType.SINGLE.getKey());
        List<UnifiedMerchandiseSpecificationVO> voList = new ArrayList<UnifiedMerchandiseSpecificationVO>();
        for (UnifiedMerchandise unifiedMerchandise : merchandiseList) {
            if (unifiedMerchandise.getState() == MerchandiseStateType.ONLINE.getKey()) {// 只拿上线的
                UnifiedMerchandiseSpecificationVO vo = new UnifiedMerchandiseSpecificationVO();
                vo.setDiscount(unifiedMerchandise.getDiscount());
                vo.setImage(fileSDKClient.getFileServerUrl() + unifiedMerchandise.getImage());
                vo.setName(unifiedMerchandise.getName());
                vo.setPrice(unifiedMerchandise.getPrice());
                vo.setStock(unifiedMerchandise.getStock());
                vo.setUnifiedMerchandiseId(unifiedMerchandise.getId());
                List<MerchandiseSpecifications> specificationList = merchandiseSpecificationsService.listByUnifiedMerchandise(unifiedMerchandise.getId());
                for (MerchandiseSpecifications merchandiseSpecifications : specificationList) {
                    vo.getSpecificationsList().add(merchandiseSpecifications.getValue());
                }
                //
                voList.add(vo);
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("specificationsList", attributeSpecificationsVOList);
        view.addObject("merchandiseList", voList);
        view.setMessage("查询商品可选规格成功.");
        return view;
    }
}
