//package com.qcloud.component.mall.web.eximpl;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import org.apache.commons.collections.CollectionUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import com.qcloud.component.filesdk.FileSDKClient;
//import com.qcloud.component.orderform.OrderformClient;
//import com.qcloud.component.orderform.QAfterSaleOrder;
//import com.qcloud.component.orderform.QMerchantOrder;
//import com.qcloud.component.orderform.QOrder;
//import com.qcloud.component.orderform.QOrderItem;
//import com.qcloud.component.personalcenter.web.handler.OrderFormGetter;
//import com.qcloud.component.sellercenter.QMerchant;
//import com.qcloud.component.sellercenter.SellercenterClient;
//import com.qcloud.pirates.util.AssertUtil;
//
//@Component
//public class OrderFormGetterImpl implements OrderFormGetter {
//
//    @Autowired
//    private SellercenterClient sellercenterClient;
//
//    @Autowired
//    private OrderformClient    orderformClient;
//
//    @Autowired
//    private FileSDKClient      fileSDKClient;
//
//    @Override
//    public OrderFormModel get(Long orderId, Date orderDate) {
//
//        final QOrder order = orderformClient.getOrder(orderId, orderDate);
//        AssertUtil.assertNotNull(order, "订单不存在." + orderId);
//        return new OrderFormModel() {
//
//            @Override
//            public String getOrderNumber() {
//
//                return order.getOrderNumber();
//            }
//
//            @Override
//            public double getSum() {
//
//                return order.getSum();
//            }
//
//            @Override
//            public Date getOrderDate() {
//
//                return order.getOrderDate();
//            }
//
//            @Override
//            public Date getExpectedArrivalDate() {
//
//                // TODO
//                return new Date();
//            }
//
//            @Override
//            public Date getArrivalDate() {
//
//                // TODO
//                return new Date();
//            }
//
//            @Override
//            public String getFirstImage() {
//
//                if (order.getMerchantOrderList().get(0).getOrderItemList().get(0).getOrderItemDetailList().size() > 0) {
//                    return order.getMerchantOrderList().get(0).getOrderItemList().get(0).getOrderItemDetailList().get(0).getImage();
//                } else {
//                    return null;
//                }
//            }
//
//            @Override
//            public double getCash() {
//
//                return order.getCash();
//            }
//
//            @Override
//            public double calculateTotalPrice() {
//
//                return order.getTotalPrice();
//            }
//
//            @Override
//            public List<MerchantOrderModel> getMerchantOrderList() {
//
//                List<MerchantOrderModel> list = new ArrayList<MerchantOrderModel>();
//                List<QMerchantOrder> merchantOrders = order.getMerchantOrderList();
//                for (final QMerchantOrder qMerchantOrder : merchantOrders) {
//                    final QMerchant merchant = sellercenterClient.getMerchant(qMerchantOrder.getMerchantId());
//                    MerchantOrderModel merchantOrderModel = new MerchantOrderModel() {
//
//                        @Override
//                        public long getMerchantId() {
//
//                            return merchant.getId();
//                        }
//
//                        @Override
//                        public String getMerchantName() {
//
//                            return merchant.getName();
//                        }
//
//                        @Override
//                        public int getNumber() {
//
//                            int number = 0;
//                            List<QOrderItem> qOrderItems = qMerchantOrder.getOrderItemList();
//                            for (QOrderItem orderItem : qOrderItems) {
//                                number += orderItem.getNumber();
//                            }
//                            return number;
//                        }
//
//                        @Override
//                        public double getSum() {
//
//                            return qMerchantOrder.getSum();
//                        }
//
//                        @Override
//                        public List<OrderItemModel> getOrderItemList() {
//
//                            List<OrderItemModel> itemList = new ArrayList<OrderItemModel>();
//                            List<QOrderItem> qOrderItems = qMerchantOrder.getOrderItemList();
//                            for (final QOrderItem qOrderItem : qOrderItems) {
//                                OrderItemModel orderItemModel = new OrderItemModel() {
//
//                                    @Override
//                                    public long getUnifiedMerchandiseId() {
//
//                                        return qOrderItem.getUnifiedMerchandiseId();
//                                    }
//
//                                    @Override
//                                    public double getDiscount() {
//
//                                        return qOrderItem.getDiscount();
//                                    }
//
//                                    @Override
//                                    public int getNumber() {
//
//                                        return qOrderItem.getNumber();
//                                    }
//
//                                    @Override
//                                    public String getFirstImage() {
//
//                                        return fileSDKClient.getFileServerUrl() + qOrderItem.getOrderItemDetailList().get(0).getImage();
//                                    }
//
//                                    @Override
//                                    public String getFirstName() {
//
//                                        // TODO 组合需要考虑
//                                        return qOrderItem.getOrderItemDetailList().get(0).getName();
//                                    }
//
//                                    @Override
//                                    public String getSpecifications() {
//
//                                        // TODO 组合需要考虑
//                                        return qOrderItem.getOrderItemDetailList().get(0).getSpecifications();
//                                    }
//                                };
//                                itemList.add(orderItemModel);
//                            }
//                            return itemList;
//                        }
//                    };
//                    list.add(merchantOrderModel);
//                }
//                return list;
//            }
//
//            @Override
//            public String getAfterSaleStateName() {
//
//                List<QAfterSaleOrder> list = order.getAfterSaleOrderList();
//                if (CollectionUtils.isEmpty(list)) {
//                    return "";
//                }
//                boolean finish = true;
//                for (QAfterSaleOrder qAfterSaleOrder : list) {
//                    if (!qAfterSaleOrder.isFinish()) {
//                        finish = false;
//                        break;
//                    }
//                }
//                if (finish) {
//                    return "售后完成";
//                } else {
//                    return "售后中";
//                }
//            }
//        };
//    }
//}
