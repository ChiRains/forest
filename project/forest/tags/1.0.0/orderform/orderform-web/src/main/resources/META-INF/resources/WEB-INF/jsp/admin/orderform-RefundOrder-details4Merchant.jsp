<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>查看退款订单子订单</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        退款订单子订单
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
					<tbody>
					<tr>
						<td style="width: 200px;">订单号：</td><td style="width: 300px;">${order.orderNumber}</td>
						<td style="width: 200px;">用户：</td><td style="width: 300px;">${order.userName}</td>
					</tr>
					<tr>
						<td>申请时间：</td><td><fmt:formatDate value="${order.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>订单状态：</td>
						<td>
							<span class="label
	                        	<c:if test="${order.state eq 40}">
	                        		label-success arrowed
	                        	</c:if>
	                        	<c:if test="${order.state eq 50}">
	                        		label-info arrowed-in-right arrowed
	                        	</c:if>
	                        	<c:if test="${order.state eq 60}">
	                        		label-inverse
	                        	</c:if>">
	                        	<c:forEach items="${orderStateType}" var="item" varStatus="current">
	                        		<c:if test="${item.key eq order.state}">
	                        			${item.name}
	                        		</c:if>
	                        	</c:forEach>
                        	</span>
						</td>
					</tr>
					<tr>
						<td>退款单号：</td>
						<td>
							${order.refundNumber}
						</td>
						<td>门店：</td><td>${store.name}</td>
					</tr>
					<tr>
						<td>退货单金额：</td><td><span style="color:red;">￥${returnCash}</span></td>
						<td>同意退货金额：</td><td><span style="color:red;">￥${order.sum}</span></td>
					</tr>
					<tr>
						<td>说明：</td><td><span style="color:green;">${order.explain}</span></td>
						<td>原因：</td><td><span style="color:green;">${order.reason}</span></td>
					</tr>
				</tbody></table>
				<div class="hr hr8 hr-dotted"></div>
				<table class="table table-striped table-bordered table-hover dataTable no-footer">
					<caption class="text-left table-header btn-info">退款商品列表&nbsp;&nbsp;&nbsp;&nbsp;
					</caption>
					<thead> 
						<tr role="row">
							<th class="center" style="width: 100px;">#</th>
							<th>商品名称</th>
							<th>金额合计</th>
							<th>缩略图</th>
							<th>数量</th>
							<th>规格说明</th>
						</tr>
					</thead>
					<tbody>
						<%-- 2015年11月21日14:08:42 --%>
						<c:forEach items="${orderItemsCombinationRecords}" var="item"  varStatus="current">
							<c:if test="${fn:length(item.itemDetails)!=1}">
								<tr>
									<td rowspan="${fn:length(item.itemDetails)}" class="center">${current.index}</td>
									<td rowspan="${fn:length(item.itemDetails)}"> ${item.itemName}</td>
									<td rowspan="${fn:length(item.itemDetails)}"> ${item.itemSum}</td>
								</tr>
								<c:forEach items="${item.itemDetails}" var="details"  varStatus="">
								<tr>	 
									<td><img width="50" height="50" src="${details.image}"></td>
									<td>${details.number}</td>
									<td>
									 	${details.specifications}
									</td>
								</tr>
								</c:forEach>	
							</c:if>
							<c:if test="${fn:length(item.itemDetails)==1}">
								<tr>
									<td class="center">${current.index}</td>
									<td> ${item.itemName}</td>
									<td> ${item.itemSum}</td>
									<c:forEach items="${item.itemDetails}" var="details"  varStatus="">
									<td><img width="50" height="50" src="${details.image}"></td>
									<td>${details.number}</td>
									<td>
									 	${details.specifications}
									</td>
									</c:forEach>	
								</tr>
							</c:if>
						</c:forEach>
						<%-- 2015年11月21日14:08:42 --%>
					</tbody>
				</table>
				
				<div class="hr hr8 hr-dotted"></div>
				<table class="table table-striped table-bordered table-hover dataTable no-footer">
					<caption class="text-left table-header btn-info">订单商品列表</caption>
					<thead> 
						<tr role="row">
							<th class="center" style="width: 100px;">#</th>
							<th>商品名称</th>
							<th>金额合计</th>
							<th>缩略图</th>
							<th>数量</th>
							<th>规格说明</th>
							<th><c:if test="${shipType eq merchantOrderForm.state}">
								<!-- <th class="sorting_disabled">操作</th> -->
							</c:if>
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orderItemsRecords}" var="item"  varStatus="current">
							<tr>
								<td class="center">${current.index}</td>
								<td>${item.name}</td>
								<td>${item.sum}</td>
								<td><img width="50" height="50" src="${item.image}"></td>
								<td>${item.number}</td>
								<td>
									${item.specifications}
								</td>
								<td>
								
								</td>
							</tr>
						</c:forEach>
					</tbody></table>
			    <div class="space-4"></div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="dataTables_length" align="right">
                        	 <c:if test="${order.state ==10}">
	                             <a title="同意退款" class="btn btn-sm btn-success agree" href="javascript:;"
	                             	api-path="/afterSaleState/confirmRefund.do?refundId=${order.id}">
	                            	<i class="ace-icon glyphicon glyphicon-send align-center bigger-125"></i>同意退款</a>
	                           	 
	                           	 <a title="拒绝退款" class="btn btn-sm btn-success disagree" href="javascript:;"
	                             	api-path="/afterSaleState/refuseRefund.do?refundId=${order.id}">
	                            	<i class="ace-icon glyphicon glyphicon-send align-center bigger-125"></i>拒绝退款</a>
                             </c:if>
                            <c:if test="${order.state ==20}"> 
	                             <a title="确认退款" class="btn btn-sm btn-success signed" href="javascript:;"
	                             	api-path="/afterSaleState/payRefund.do?refundId=${order.id}">
	                            	<i class="ace-icon glyphicon glyphicon-send align-center bigger-125"></i>确认退款</a>
                            </c:if>
                            
                             <a title="返回上一页" class="btn btn-sm send2" href="javascript:history.go(-1)">
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
        
        
        
         $(".agree").on("click", function () {
         	var url = $(this).attr('api-path');
         	var title=$(this).attr('title');
         	var money=${returnCash};
            BootstrapDialog.show({
                title: title,
                message: $('<div></div>').load('/admin/returnOrder/addMoney.do?cash='+money),
                cssClass: "select-product-dialog",
                buttons: [{
                    label: '保存',
                    cssClass: 'btn btn-primary',
                    action: function (dialogItself) {
                    	var valueArray=$("#myForm").serializeArray();
                    	var query_str = "";
                    	var flag=0;
                    	$.each(valueArray, function (i, data) {
                        	if(data['name']=="sum"){
                        		var price=Number(data['value']);
                        		if(isNaN(price)||price<=0||price>money){
                        			flag++;
                        		}
                        	}else{
	                        	if ($.trim(data['value'])) {
	                                query_str +=  '&' + data['name'] + '=' + data['value'];
	                            }else {
	                                flag++;
	                            }
                        	}
                            
                        });
                    	if(flag==0){
	                    	 $.get(url + "&" + $("#myForm").serialize(),
		                        function (data) {
		                        	dialogItself.close();
		                        	location.reload();
		                     });
	                     }else{
	                     	 BootstrapDialog.show({
                        	 	  message: "请输入正确的金额"
                        	 });
	                     }
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
        
        $(".disagree,.signed").on("click", function () {
         	var url = $(this).attr('api-path');
         	var title=$(this).attr('title');
            BootstrapDialog.show({
                title: title,
                message: title,
                cssClass: "select-product-dialog",
                buttons: [{
                    label: '保存',
                    cssClass: 'btn btn-primary',
                    action: function (dialogItself) {
                    	$.get(url ,{},
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
