package com.qcloud.component.my.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = MyEvaluationController.DIR)
public class MyEvaluationController {

    public static final String           DIR = "/myEvaluation";

//    @Autowired
//    private UserFilterService            userFilterService;

//    @Autowired
//    private TokenClient                  tokenClient;
//
//    @Autowired
//    private UserService                  userService;

//    @Autowired
//    private UserEvaluationService        userEvaluationService;
//
//    @Autowired
//    private MerchandiseItemService       merchandiseItemService;
//
//    @Autowired
//    private MerchandiseEvaluationService merchandiseEvaluationService;
//
//    @Autowired
//    private MerchandiseEvaluationHandler merchandiseEvaluationHandler;
//
//    @Autowired
//    private OrderformClient              orderformClient;
//
//    @Autowired
//    private CommoditycenterClient        commoditycenterClient;

//    /**
//     * 评价商品
//     * @param vo  
//     * @return
//     */
//    @RequestMapping
//    @NoReferer
//    public FrontAjaxView evaluate(HttpServletRequest request, EvaluationVO evaluationVO) {
//
//        String tokenId = userFilterService.getTokenId(request);
//        FrontAjaxView view = new FrontAjaxView();
//        AssertUtil.assertNotEmpty(tokenId, "用户未登录.");
//        String idStr = tokenClient.get(tokenId);
//        // idStr = "1010005000003801";
//        AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
//        User user = userService.get(Long.parseLong(idStr));
//        AssertUtil.assertNotNull(user, "用户不存在.");
//        // ---------------------模拟数据---------------------------------
//        // meVO.setMerchandiseId(Long.valueOf("1010006000001201"));
//        // meVO.setContent("这是我的评价内容!");
//        // meVO.setSpecifications("颜色：机械战士");
//        // ---------------------模拟数据---------------------------------
//        List<Long> orderItemIds = evaluationVO.getOrderItemIds();
//        List<MerchandiseEvaluation> merchandiseEvaluations = evaluationVO.getMerchandiseEvaluations();
//        if (orderItemIds.size() != merchandiseEvaluations.size()) {
//            throw new EvaluationcenterException("数据不对应，请检查!");
//        }
//        if(orderItemIds.size() == 0) {
//            throw new EvaluationcenterException("请至少选择一个商品评价!");
//        }
//        for (int i = 0; i < orderItemIds.size(); i++) {
//            Long orderItemId = orderItemIds.get(i);
//            MerchandiseEvaluation meVO = evaluationVO.getMerchandiseEvaluations().get(i);
//            Date time = evaluationVO.getOrderTime();
//            List<OrderItemDetail> list = orderformClient.listItemDetailByItemId(orderItemId, time);
//            for (OrderItemDetail oid : list) {
//                long orderItemDetailId = oid.getId();
//                // 判断总单和子单对应的订单时间是否存在
//                if (time == null || orderItemDetailId == 0) {
//                    throw new EvaluationcenterException("非法操作!");
//                }
//                OrderItemDetail orderItemDetail = orderformClient.getOrderItemDetail(orderItemDetailId, time);
//                if (orderItemDetail == null) {
//                    throw new EvaluationcenterException("非法操作!");
//                }
//                MerchandiseItem merchandiseItem = commoditycenterClient.getMerchandiseItem(orderItemDetail.getMerchandiseItemId());
//                if (merchandiseItem == null) {
//                    throw new EvaluationcenterException("请管理员检查订单商品数据!");
//                }
//                meVO.setMerchandiseId(merchandiseItem.getMerchandiseId());
//                meVO.setSpecifications(orderItemDetail.getSpecifications());
//                meVO.setTime(new Date());
//                meVO.setStar(getRealStar(meVO.getStar()));
//                meVO.setStatus(0);
//                meVO.setUserId(user.getId());
//                long orderId = orderItemDetail.getOrderId();
//                CollectOrder collectOrder = orderformClient.getCollectOrder(orderId, time);
//                // 判断用户是否已评价订单
//                UserEvaluation userEvaluation = userEvaluationService.getByOrderItemDetailId(user.getId(), orderItemDetailId);
//                if (userEvaluation != null) {
//                    throw new EvaluationcenterException("用户已评价!");
//                }
//                // 用户订单判断
//                if (user.getId() != collectOrder.getUserId()) {
//                    throw new EvaluationcenterException("用户订单不存在!");
//                }
//                // 下单时间判断
//                if (!DateUtils.isSameInstant(time, collectOrder.getTime())) {
//                    throw new EvaluationcenterException("下单时间与实际不符，非法操作!");
//                }
//                if (!StringUtils.isEmpty(meVO.getContent())) {
//                    userEvaluationService.add(meVO, oid, time);
//                }
//            }
//        }
//        view.setMessage("评价成功!");
//        return view;
//    }

//    /**
//     * 我的评价管理
//     * @param pageNum
//     * @param merchandiseId
//     * @return
//     */
//    @RequestMapping
//    @NoReferer
//    public FrontPagingView list(HttpServletRequest request, Integer pageNum, UserEvaluationQuery query) {
//
//        String tokenId = userFilterService.getTokenId(request);
//        AssertUtil.assertNotEmpty(tokenId, "用户未登录.");
//        String idStr = tokenClient.get(tokenId);
//        AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
//        // idStr = "1010006000000001";
//        query.setUserId(Long.valueOf(idStr));
//        final int PAGE_SIZE = 10;
//        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
//        // 获取当前登录用户评价列表
//        Page<UserEvaluation> page = userEvaluationService.page(query, start, PAGE_SIZE);
//        List<MerchandiseEvaluation> meList = new ArrayList<MerchandiseEvaluation>();
//        // 获取评价表内容
//        for (UserEvaluation userEvaluation : page.getData()) {
//            OrderItemDetail orderItemDetail = orderformClient.getOrderItemDetail(userEvaluation.getOrderItemDetailId(), userEvaluation.getOrderTime());
//            // 单一商品，包含规格属性
//            MerchandiseItem item = merchandiseItemService.get(orderItemDetail.getMerchandiseItemId());
//            // 根据商品id获取评价表内容
//            MerchandiseEvaluation merchandiseEvaluation = merchandiseEvaluationService.get(userEvaluation.getEvaluationId(), item.getMerchandiseId());
//            meList.add(merchandiseEvaluation);
//        }
//        List<MerchandiseEvaluationVO> list = merchandiseEvaluationHandler.toVOPageList(meList);
//        FrontPagingView view = new FrontPagingView(pageNum, PAGE_SIZE, page.getCount());
//        view.addObject("result", list);
//        return view;
//    }
//
//    private int getRealStar(int star) {
//
//        switch (star) {
//        case 1:
//            star = star * 10;
//            break;
//        case 2:
//            star = star * 10;
//            break;
//        case 3:
//            star = star * 10;
//            break;
//        case 4:
//            star = star * 10;
//            break;
//        case 5:
//            star = star * 10;
//            break;
//        default:
//            star = 5 * 10;
//            // throw new EvaluationcenterException("请选择星数!");
//        }
//        return star;
//    }
}
