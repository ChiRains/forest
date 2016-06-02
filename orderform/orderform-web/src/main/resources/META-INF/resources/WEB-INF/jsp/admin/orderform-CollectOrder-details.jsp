<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>总单管理</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        总单管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            详情
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="model-form" class="form-horizontal"  role="form" >
            <!-- #section:elements.form -->
			      		<div class="space-4"></div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="orderNumber"> 订单号:  </label>
                <label style="text-align: left;" class="col-sm-1 control-label no-padding-right" for="orderNumber"> ${collectOrder.orderNumber}  </label>
            </div>
                  		
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="userId"> 用户ID: </label>
                <label style="text-align: left;" class="col-sm-1 control-label no-padding-right" for="userId"> ${collectOrder.userId} </label>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="time"> 下单时间: </label>
				<label style="width:13%;text-align:left;" class="col-sm-1 control-label no-padding-right" for="time"> <fmt:formatDate value="${collectOrder.time}" pattern="yyyy-MM-dd HH:mm:ss"/> </label>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="sum"> 订单金额 :</label>
                <label style="text-align: left;" class="col-sm-1 control-label no-padding-right" for="sum"> ${collectOrder.sum} </label>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="cash"> 支付金额 :</label>
                <label style="text-align: left;" class="col-sm-1 control-label no-padding-right" for="sum"> ${collectOrder.cash} </label>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="postage"> 邮费 :</label>
                <label style="text-align: left;" class="col-sm-1 control-label no-padding-right" for="sum"> ${collectOrder.postage} </label>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="consignee"> 收货人: </label>
                <label style="text-align: left;" class="col-sm-1 control-label no-padding-right" for="consignee"> ${collectOrder.consignee} </label>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="address"> 收货地址:</label>
				<label style="text-align: left;" class="col-sm-1 control-label no-padding-right" for="address"> ${collectOrder.address} </label>
            </div>
            
			<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="mobile"> 收货电话: </label>
                <label style="text-align: left;" class="col-sm-1 control-label no-padding-right" for="mobile">${collectOrder.mobile} </label>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="email"> 收货人邮箱: </label>
                <label style="text-align: left;" class="col-sm-1 control-label no-padding-right" for="email"> ${collectOrder.email} </label>
            </div>
            
      <%--    <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="deliveryTimeStr"> 送货时间说明: </label>
                  <label style="text-align: left;" class="col-sm-1 control-label no-padding-right" for="deliveryTimeStr"> ${collectOrder.deliveryTimeStr} </label>
            </div>  --%>
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="paymentMode"> 支付方式: </label>
       		    <c:forEach items="${paymentModeType}" var="type" varStatus="a"> 
                	<c:if test="${type.key eq collectOrder.paymentMode}">
                		<label style="text-align: left;" class="col-sm-1 control-label no-padding-right" for="paymentMode"> ${type.name} </label>
                	</c:if>
                </c:forEach>
            </div>
            	
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="state"> 状态 </label>
                <c:forEach items="${orderStateType}" var="type" varStatus="a"> 
                	<c:if test="${type.key eq collectOrder.state}">
                		<label style="text-align: left;" class="col-sm-1 control-label no-padding-right" for="state">${type.name} </label>
                	</c:if>
                </c:forEach>
            </div>
            
        <%--     <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="explain"> 订单说明: </label>
                <label style="text-align: left;" class="col-sm-1 control-label no-padding-right" for="explain"> ${collectOrder.explain} </label>
            </div> --%>
            
            <table class="table table-striped table-bordered table-hover dataTable no-footer">
                <thead>
                <tr role="row">     
                        <th>商家名称</th>           
                        <th>名称</th>           
                        <th>关键字</th> 
 						<th>购买数量</th>             
                        <th>进货价</th>
                        <th>原价</th>           
                        <th>折扣价</th>           
                        <th>成交价</th>           
                        <th>库存</th> 
                        <th>小结</th>         
                </tr>
                </thead>

                <tbody>
                       <c:forEach items="${itemVOs}" var="item" varStatus="current"> 
                        <tr>            
                                <td>${item.merchant.name}</td>  
                                <td>${item.item.name}</td>                         
                                <td>${item.item.keywords}</td> 
                                <td>${item.number}</td>                            
                                <td>${item.item.purchase}</td>    
                                <td>${item.item.price}</td>                         
                                <td>${item.item.discount}</td>                         
                                <td>${item.discount}</td>                 
                                <td>${item.item.stock}</td>  
                                <td>${item.sum}</td>                        
                        </td>
                    </tr>
					</c:forEach>
                </tbody>
            </table>
            
            
            <div class="space-4"></div>
            <div class="clearfix form-actions">
                <div style="text-align: center;">
                    <button id="back" class="btn" type="button"><i class="ace-icon fa fa-undo bigger-110"></i>&nbsp;返&nbsp;回&nbsp;</button>
                </div>
            </div>

        </form>
        <!-- PAGE CONTENT ENDS -->
    </div>
    <!-- /.col -->
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
                   
        });
    })
</script>
