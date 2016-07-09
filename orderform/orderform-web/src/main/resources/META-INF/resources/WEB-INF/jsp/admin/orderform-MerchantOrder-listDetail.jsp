<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>查看子订单</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        子订单
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            管理
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
               	<table class="table table-striped table-bordered table-hover dataTable no-footer">
               		<fmt:formatDate value="${collectOrder.time}" pattern="yyyy-MM-dd" var="time"/>
					<caption class="text-left table-header btn-info">订单基本信息</caption>
					<tbody><tr>
						<td style="width: 200px;">订单号：</td><td style="width: 300px;">${subOrder.orderNumber}</td>
						<td>送货门店：</td><td>
						<c:forEach items="${storeList}" var="store" varStatus="current">
							<c:if test="${subOrder.storeId eq store.id}">
								<span class="label label-info label-white middle">${store.name}</span>
							</c:if>
						</c:forEach>
						</td>
					</tr>
					<tr>
						<td>下单时间：</td><td><fmt:formatDate value="${collectOrder.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>订单状态：</td>
						<td>
							<span class="label
	                        	<c:if test="${merchantOrderForm.state eq 7}">
	                        		label-success arrowed
	                        	</c:if>
	                        	<c:if test="${merchantOrderForm.state eq 8 || merchantOrderForm.state eq 9}">
	                        		label-warning
	                        	</c:if>
	                        	<c:if test="${merchantOrderForm.state eq 5}">
	                        		label-danger arrowed-in
	                        	</c:if>
	                        	<c:if test="${merchantOrderForm.state eq 6}">
	                        		label-info arrowed-in-right arrowed
	                        	</c:if>
	                        	<c:if test="${merchantOrderForm.state eq 2 || merchantOrderForm.state eq 3}">
	                        		label-inverse
	                        	</c:if>">
	                        	<c:forEach items="${merchantOrderStateType}" var="item" varStatus="current">
	                        		<c:if test="${item.key eq merchantOrderForm.state}">
	                        			${item.name}
	                        		</c:if>
	                        	</c:forEach>
                        	</span>
						</td>
					</tr>
					
					<tr>
						<td>支付方式：</td>
						<td>
							<c:forEach items="${paymentModeType}" var="item" varStatus="current">
								<c:if test="${item.key eq collectOrder.paymentMode}">${item.name}</c:if>
							</c:forEach>
						</td>
						<td>订单金额</td><td><span style="color:red;">￥${collectOrder.sum}</span></td>
					</tr>
					<tr>
						<td>支付金额：</td><td><span style="color:red;">￥${subOrder.cash}</span></td>
						<td>邮费：</td><td><span style="color:red;">￥${subOrder.postage}</span></td>
					</tr>
					<tr>
						<td>积分：</td><td colspan="3"><span style="color:red;">${subOrder.integral}</span></td>
						
					</tr>
					<tr>
						<td>留言板：</td><td colspan="3">${subOrder.explain}</td>
					</tr>
				</tbody></table>
				
				<div class="hr hr8 hr-dotted"></div>
				<table class="table table-striped table-bordered table-hover dataTable no-footer">
					<caption class="text-left table-header btn-info">使用优惠券情况 &nbsp;&nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;
						<c:if test="${collectOrder.coupon eq 0}">
							<span style="color:red";>没有使用任何优惠券</span>
						</c:if>
						<c:if test="${collectOrder.coupon ne 0}">
							￥${collectOrder.coupon}
						</c:if>
					</caption>
					<tbody>
					<c:forEach var="orderDiscount" items="${orderDiscountList}">
						<tr>
							<td style="width: 200px;">#</td>
							<td style="width: 300px;">${orderDiscount.price}</td>
						</tr>
					</c:forEach>
				</tbody></table>
				
				<div class="hr hr8 hr-dotted"></div>
				<table class="table table-striped table-bordered table-hover dataTable no-footer">
					<caption class="text-left table-header btn-info">配送方式</caption>
					<tbody><tr>
						<td style="width: 200px;">送货方式：</td><td style="width: 300px;">
						<c:forEach var="type" items="${deliveryModeType}">
							<c:if test="${subOrder.deliveryMode ne type.key}">
								${type.name}
							</c:if>	
						</c:forEach>
						</td>
						<td style="width: 200px;">自提地址：</td><td>${subOrder.pickupAddressStr}</td>
					</tr>
					<tr>
						<td>送货时间说明：</td><td colspan="3">${subOrder.deliveryTimeStr}</td>
					</tr>
				</tbody></table>
				
				<div class="hr hr8 hr-dotted"></div>
				<table class="table table-striped table-bordered table-hover dataTable no-footer">
					<caption class="text-left table-header btn-info">收货人信息 :</caption>
					<tbody><tr>
						<td style="width: 200px;">收货人姓名：</td><td style="width: 300px;">${collectOrder.consignee}</td>
						<td style="width: 200px;">收货人电话：</td><td>${collectOrder.mobile}</td>
					</tr>
					<tr>
						<td>收货人地址：</td><td colspan="3">${collectOrder.address}</td>
					</tr>
				</tbody></table>
				
			
				<div class="hr hr8 hr-dotted"></div>
				<table class="table table-striped table-bordered table-hover dataTable no-footer">
					<caption class="text-left table-header btn-info">物流信息 ：</caption>
					<tbody>
					<tr>
						<td style="width: 200px;">快递公司编码：</td><td style="width: 300px;">${subOrder.expressCode}</td>
						<td style="width: 200px;">物流公司：</td><td>${subOrder.expressName}</td>
					</tr>
					<tr>
						<td>物流查询号：</td><td colspan="3">${subOrder.expressNumber}</td>
					</tr>
				</tbody></table>
				
				<div class="hr hr8 hr-dotted"></div>
				<table class="table table-striped table-bordered table-hover dataTable no-footer">
					<caption class="text-left table-header btn-info">发票信息 &nbsp;&nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;
						<c:if test="${collectOrder.needInvoice==2}">
							<span style="color:red;">无</span>
						</c:if>
					</caption>
					<tbody>
					<c:if test="${collectOrder.needInvoice==1}">
					<tr>
						<td style="width: 200px;">发票类型:</td>
						<td style="width: 300px;">
							<c:forEach var="type" items="${invoiceType}">
								<c:if test="${type.key eq collectOrder.invoiceType}"> 
									${type.name}
								</c:if>
							</c:forEach>
						</td>
						<td style="width: 200px;">发票抬头：</td><td>${collectOrder.invoiceHead}</td>
					</tr>
					<tr>
						<td>发票内容：</td><td colspan="3">${collectOrder.invoiceContent}</td>
					</tr>
					</c:if>
				</tbody></table>
				
				<div class="hr hr8 hr-dotted"></div>
				<table class="table table-striped table-bordered table-hover dataTable no-footer">
					<caption class="text-left table-header btn-info">订单商品列表&nbsp;&nbsp;&nbsp;&nbsp;
					<span style="color:red;">备注：线下库存为当前选择门店的商品库存，线上库存包括所有门店库存总和</span></caption>
					<thead> 
						<tr role="row">
							<th class="center" style="width: 100px;">#</th>
							<th>商品名称</th>
							<th>缩略图</th>
							<th>物流查询号</th>
							<th>规格说明</th>
							<th>状态</th>
							<th>进货价</th>
							<th>成交价</th>
							<th>数量</th>
							<th>小计</th>
							<th>线上剩余库存</th>
							<th>线下剩余库存</th>
							<c:if test="${shipType eq merchantOrderForm.state}">
								<!-- <th class="sorting_disabled">操作</th> -->
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orderItemDetailList}" var="item"  varStatus="current">
							<tr>
								<td class="center">${current.index}</td>
								<td>${item.name}</td>
								<td><img width="50" height="50" src="${item.image}"></td>
								<td>${item.logisticsNumber}</td>
								<td>${item.specifications}</td>
								<td>
									<c:forEach items="${orderStateType}" var="orderStateType"  varStatus="current">
										<c:if test="${item.state eq orderStateType.key}">${orderStateType.name}</c:if>
									</c:forEach>
								</td>
								<td>￥${orderItemDetailMap[item.id].purchase}</td>
								<td>￥${orderItemDetailMap[item.id].discount}</td>
								<td>${orderItemDetailMap[item.id].number}</td>
								<td>￥${orderItemDetailMap[item.id].sum}</td>
								<td>${item.stock}</td>
								<td>${item.realStock}</td>
								<c:if test="${shipType eq merchantOrderForm.state}">
									<!--
									<td>
		                                <div class="hidden-sm hidden-xs action-buttons">
		                                	<a title="发货" class="green deliverOrder-itemDetail" href="javascript:;"
		                                    api-path="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()%>/orderState/deliverOrder.do?orderItemDetailId=${item.id}&orderDate=${time}">
		                                        <i class="ace-icon fa fa-truck bigger-130"></i>&nbsp;发货
		                                    </a>
		                                </div>
		                        	</td>
		                        	-->
	                        	</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			    <div class="space-4"></div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="dataTables_length" align="right">
                            <c:if test="${shipType eq merchantOrderForm.state}">
                             <a title="发货" class="btn btn-sm btn-success deliverOrder-item" href="javascript:;"
                             api-path="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()%>/orderState/deliverOrder.do?subOrderId=${subOrder.id}&orderDate=${time}">
                            	<i class="ace-icon glyphicon glyphicon-send align-center bigger-125"></i>一键发货</a>
                            </c:if>
                             <a title="返回上一页" class="btn btn-sm" href="javascript:history.go(-1);">
                                <i class="ace-icon fa fa-undo align-center bigger-125"></i>返回上一页</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div><!-- /.row -->

<script type="text/javascript">
    var scripts = [null, "/qcloud-admin/assets/js/jquery.colorbox-min.js","/qcloud-admin/assets/js/upload-img.js","/qcloud-admin/assets/js/chosen.jquery.min.js", null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
        jQuery(function ($) {
            $(window)
            .off('resize.chosen')
            .on('resize.chosen', function() {
                $('.chosen-select').each(function() {
                     var $this = $(this);
                     $this.next().css({'width': $this.parent().width()});
                })
            }).trigger('resize.chosen');
                   
            //表单验证
            $("#model-form").validate({
                errorElement: 'div',
                errorClass: 'help-block col-xs-12 col-sm-reset inline',
                focusInvalid: false,
                rules: {
                    name: {
                        required: true
                    },
                    mobile: {
                        required: true
                    },

                    sort: {
                        required: true,
                        range: [0, 99999999],
                        digits: true
                    }
                },

                messages: {},

                highlight: function (e) {
                    $(e).closest('.form-group').removeClass('has-success').addClass('has-error');
                },

                success: function (e) {
                    $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                    $(e).remove();
                },

                errorPlacement: function (error, element) {
                    if (element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
                        var controls = element.closest('div[class*="col-"]');
                        if (controls.find(':checkbox,:radio').length > 1) controls.append(error);
                        else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
                    }
                    else if (element.is('.select2')) {
                        error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
                    }
                    else if (element.is('.chosen-select')) {
                        error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
                    }
                    else error.insertAfter(element.parent());
                },

                submitHandler: function (form) {
                    postForm('model-form');
                },
                invalidHandler: function (form) {
                }
            });
        });
        
         $(".deliverOrder-item").on("click", function () {
         	var url = $(this).attr('api-path');
            BootstrapDialog.show({
                title: '确认发货',
                message: $('<div></div>').load('/admin/merchantOrderForm/toAddLogistics.do'),
                cssClass: "select-product-dialog",
                buttons: [{
                    label: '保存',
                    cssClass: 'btn btn-primary',
                    action: function (dialogItself) {
                        $.get(url + "&" + $("#myForm").serialize(),
	                        function (data) {
	                        	dialogItself.close();
	                        	window.location.href="#admin/merchantOrderForm/list";
	                        }
                        );
                    }
                }, {
                    label: '取消',
                    action: function (dialogItself) {
                        dialogItself.close();
                    }
                }],
                onshow: function (dialog) {
                	 
                }
            });
        });
        
        $(".deliverOrder-itemDetail").on("click", function () {
         	var url = $(this).attr('api-path');
            BootstrapDialog.show({
                title: '确认发货',
                message: $('<div></div>').load('/admin/merchantOrderForm/toAddLogistics.do'),
                cssClass: "select-product-dialog",
                buttons: [{
                    label: '保存',
                    cssClass: 'btn btn-primary',
                    action: function (dialogItself) {
                        $.get(url + "&" + $("#myForm").serialize(),
	                        function (data) {
	                        	dialogItself.close();
	                        	location.reload();
	                        }
                        );
                    }
                }, {
                    label: '取消',
                    action: function (dialogItself) {
                        dialogItself.close();
                    }
                }],
                onshow: function (dialog) {
                	 
                }
            });
        });
    })
</script>
