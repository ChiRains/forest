//package com.qcloud.component.mall.web.controller;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.commoditycenter.exception.CommoditycenterException;
//import com.qcloud.component.commoditycenter.model.MerchandiseEvaluation;
//import com.qcloud.component.commoditycenter.model.MerchandiseItem;
//import com.qcloud.component.commoditycenter.service.MerchandiseEvaluationService;
//import com.qcloud.component.commoditycenter.service.MerchandiseItemService;
//import com.qcloud.component.commoditycenter.web.form.EvaluationForm;
//import com.qcloud.component.commoditycenter.web.form.MerchandiseEvaluationForm;
//import com.qcloud.component.commoditycenter.web.handler.OrderItemToMerchandiseItemHandler;
//import com.qcloud.component.commoditycenter.web.handler.OrderItemToMerchandiseItemHandler.OrderItemInformation;
//import com.qcloud.component.filesdk.FileSDKClient;
//import com.qcloud.component.mall.web.vo.EvaluateOrderItemVO;
//import com.qcloud.component.orderform.OrderformClient;
//import com.qcloud.component.orderform.QMerchantOrder;
//import com.qcloud.component.orderform.QOrder;
//import com.qcloud.component.orderform.QOrderItem;
//import com.qcloud.component.personalcenter.PersonalcenterClient;
//import com.qcloud.component.personalcenter.QUser;
//import com.qcloud.component.personalcenter.web.helper.UserHelper;
//import com.qcloud.component.sellercenter.model.key.TypeEnum.StatusType;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//import com.qcloud.pirates.util.AssertUtil;
//import com.qcloud.pirates.web.security.annotation.NoReferer;
//
//// 评论功能重新做
//@Controller
//@RequestMapping(value = OrderEvaluationController.DIR)
//public class OrderEvaluationController {
//
//    public static final String                DIR = "/orderEvaluation";
//
////    @Autowired
////    private MerchandiseEvaluationService      merchandiseEvaluationService;
//
////    @Autowired
////    private UserHelper                        userHelper;
//
////    @Autowired
////    private MerchandiseItemService            merchandiseItemService;
////
////    @Autowired
////    private OrderItemToMerchandiseItemHandler orderItemToMerchandiseItemHandler;
//
////    @Autowired
////    private PersonalcenterClient              personalcenterClient;
////
////    @Autowired
////    private OrderformClient                   orderformClient;
////
////    @Autowired
////    private FileSDKClient                     fileSDKClient;
////
////    @RequestMapping
////    @NoReferer
////    public FrontAjaxView listToEvaluate(HttpServletRequest request, Long orderId, Date orderDate) {
////
////        QUser user = userHelper.getUserModel(request);
////        AssertUtil.assertNotNull(orderId, "订单ID不能为空.");
////        AssertUtil.assertNotNull(orderDate, "下单时间不能为空.");
////        QOrder order = orderformClient.getOrder(orderId, orderDate);
////        AssertUtil.assertNotNull(order, "订单不存在." + orderId);
////        List<EvaluateOrderItemVO> list = new ArrayList<EvaluateOrderItemVO>();
////        boolean evaluated = personalcenterClient.my().canEvaluate(user.getId(), orderId, -1L);
////        if (order.canEvaluation() && evaluated) {
////            for (QMerchantOrder merchantOrder : order.getMerchantOrderList()) {
////                for (QOrderItem orderItem : merchantOrder.getOrderItemList()) {
////                    if (orderItem.isEvaluation()) {
////                        EvaluateOrderItemVO evaluateOrderItemVO = new EvaluateOrderItemVO();
////                        evaluateOrderItemVO.setDiscount(orderItem.getDiscount());
////                        evaluateOrderItemVO.setImage(fileSDKClient.getFileServerUrl() + orderItem.getImage());
////                        evaluateOrderItemVO.setName(orderItem.getName());
////                        evaluateOrderItemVO.setOrderItemId(orderItem.getId());
////                        list.add(evaluateOrderItemVO);
////                    }
////                }
////            }
////        }
////        FrontAjaxView view = new FrontAjaxView();
////        view.setMessage("评价成功!");
////        view.addObject("list", list);
////        return view;
////    }
////
////    /**
////     * 评价商品
////     * @param vo  
////     * @return
////     */
////    @RequestMapping
////    @NoReferer
////    public FrontAjaxView evaluate(HttpServletRequest request, EvaluationForm evaluationForm) {
////
////        AssertUtil.assertNotNull(evaluationForm.getOrderId(), "订单标识不能为空.");
////        AssertUtil.assertNotNull(evaluationForm.getOrderDate(), "订单日期不能为空.");
////        AssertUtil.assertNotEmpty(evaluationForm.getMerchandiseEvaluations(), "评论内容不能为空.");
////        QUser user = userHelper.getUserModel(request);
////        boolean evaluated = personalcenterClient.my().canEvaluate(user.getId(), evaluationForm.getOrderId(), -1L);
////        AssertUtil.assertTrue(evaluated, "订单不能评论");
////        for (MerchandiseEvaluationForm merchandiseEvaluationForm : evaluationForm.getMerchandiseEvaluations()) {
////            List<OrderItemInformation> ids = orderItemToMerchandiseItemHandler.itemIdToInformation(evaluationForm.getOrderId(), evaluationForm.getOrderDate(), merchandiseEvaluationForm.getOrderItemId());
////            AssertUtil.assertNotEmpty(ids, "订单项不存在." + merchandiseEvaluationForm.getOrderItemId());
////            for (OrderItemInformation in : ids) {
////                AssertUtil.assertTrue(!personalcenterClient.my().isEvaluated(user.getId(), in.getOrderItemDetailId()), "订单项已经评论过：" + in.getOrderItemDetailId());
////                MerchandiseItem merchandiseItem = merchandiseItemService.get(in.getMerchandiseItemId());
////                if (merchandiseItem == null) {
////                    throw new CommoditycenterException("请管理员检查订单商品数据!" + in.getMerchandiseItemId());
////                }
////                MerchandiseEvaluation merchandiseEvaluation = new MerchandiseEvaluation();
////                merchandiseEvaluation.setSpecifications(in.getSpecifications());
////                merchandiseEvaluation.setMerchandiseId(merchandiseItem.getMerchandiseId());
////                merchandiseEvaluation.setTime(new Date());
////                merchandiseEvaluation.setStar(getRealStar(merchandiseEvaluationForm.getStar()));
////                merchandiseEvaluation.setStatus(StatusType.UNDO.getKey());
////                merchandiseEvaluation.setUserId(user.getId());
////                merchandiseEvaluation.setContent(merchandiseEvaluationForm.getContent());
////                merchandiseEvaluation.setAnonymous(evaluationForm.getAnonymous());
////                merchandiseEvaluationService.evaluate(merchandiseEvaluation, merchandiseItem.getMerchantId(), in.getOrderItemDetailId(), in.getOrderId(), evaluationForm.getOrderDate());
////            }
////        }
////        FrontAjaxView view = new FrontAjaxView();
////        view.setMessage("评价成功!");
////        return view;
////    }
////
////    private int getRealStar(int star) {
////
////        switch (star) {
////        case 1:
////            star = star * 10;
////            break;
////        case 2:
////            star = star * 10;
////            break;
////        case 3:
////            star = star * 10;
////            break;
////        case 4:
////            star = star * 10;
////            break;
////        case 5:
////            star = star * 10;
////            break;
////        default:
////            star = 5 * 10;
////            // throw new EvaluationcenterException("请选择星数!");
////        }
////        return star;
////    }
//}
