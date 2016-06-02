<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>大客户商品折扣价管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        大客户商品折扣价管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            大客户商品折扣价列表 &nbsp; &nbsp; <!--<span style="color: red;">支付方式：折扣和价格二选一</span>-->
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
            	<form id="groupbuys-form" class="form-horizontal" role="form" action="/admin/merchandiseVipDiscount/add.do">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <button class="btn btn-sm btn-info" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>&nbsp;保&nbsp;存&nbsp;</button>                      
                        </div>
                    </div>   
                    <div class="col-xs-6" style="text-align: right;">
                        <a href="#admin/merchantUser/vipList" class="btn btn-sm btn-info"> 返 回 </a>
                    </div>
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
	                    <tr role="row">     
		                    <th>客户名称</th>
		                    <th>商品</th>
		                    <!-- <th>结算方式</th> -->
		                    <!-- <th>折扣</th> -->
		                    <th>市场价</th>
		                    <th>价格</th>
		                    <th class="sorting_disabled">操作</th>
	                    </tr>
                    </thead>

                    <tbody>
                    	<c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>          
                            	<td>${item.companyName}<input type="hidden" name="merchandiseVipDiscounts[${current.index}].userId" value="${item.userId}" class="config-value"></td>     
                                <td>${item.merchandiseItemName}<input type="hidden" name="merchandiseVipDiscounts[${current.index}].merchandiseItemId" value="${item.merchandiseItemId}" class="config-value"></td>                         
                                <%-- <td>
                                	<input type="radio" name="paymentType" value="1" <c:if test="${item.paymentType == 1}">checked</c:if> />按折扣
                    				<input type="radio" name="paymentType" value="2" <c:if test="${item.paymentType == 1}">checked</c:if> />按价格
                                </td> --%>
                                
                                
                                <%--<td>
                                	<input type="hidden" name="merchandiseVipDiscounts[${current.index}].paymentType" value="${item.paymentType}" class="config-value"> 
                                	<input type="number" myIndex="${current.index}" name="merchandiseVipDiscounts[${current.index}].discount" value="${item.discount}" class="config-value paymentByDiscount">折
                                </td>--%>
                                <input type="hidden" myIndex="${current.index}" name="merchandiseVipDiscounts[${current.index}].discount" value="0" class="config-value paymentByDiscount">
                                
                                
                                <td>
                                	${item.marketDiscount}元
                                </td>
                                <td>
                                	<input type="number" myIndex="${current.index}" name="merchandiseVipDiscounts[${current.index}].price" value="${item.price}" class="config-value paymentByPrice">元
                                </td>
                                <td>
                                	<div class="hidden-sm hidden-xs action-buttons">
	                                    <a href="#admin/merchandiseVipDiscountHistory/list?userId=${query.userId}&companyName=${query.companyName}&merchandiseItemId=${item.merchandiseItemId}" class="btn btn-primary btn-xs"><i class="ace-icon fa fa-usd bigger-130"></i>历史价格</a>					                                 
	                                </div>
	                        	</td>
	                        </tr>
						</c:forEach>
                    </tbody>
                </table>   
                </form>
                <div class="row">
                    <div class="col-xs-12">                  
                       	<%@include file="../page.inc.jsp" %>
                    </div>
                </div>          
            </div>
        </div>
    </div>
</div>

<!-- page specific plugin scripts -->
<script type="text/javascript">
    var scripts = [null, null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page

        $(".paymentByDiscount").change(function(){
      		var myIndex = $(this).attr("myIndex");
          	var targetName = "merchandiseVipDiscounts["+myIndex+"].price";
          	//var targetName2 = "merchandiseVipDiscounts["+myIndex+"].paymentType";
          	$("[name='"+targetName+"']").val(0);
          	//$("[name='"+targetName2+"']").val(2);
      	});

        $(".paymentByPrice").change(function(){
      		var myIndex = $(this).attr("myIndex");
          	var targetName = "merchandiseVipDiscounts["+myIndex+"].discount";
          	//var targetName2 = "merchandiseVipDiscounts["+myIndex+"].paymentType";
          	$("[name='"+targetName+"']").val(0);
          	//$("[name='"+targetName2+"']").val(1);
      	});
        

    	//表单验证
        $("#groupbuys-form").validate({
            errorElement: 'div',
            errorClass: 'help-block col-xs-12 col-sm-reset inline',
            focusInvalid: false,
            rules: {
            	discount: {
                    required: true,
                    range: [0, 9],
                    digits: true
                },
            	price: {
                    required: true,
                    range: [0, 99999999],
                    number: true
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
                postForm('groupbuys-form');
            },
            invalidHandler: function (form) {
            }
        });
        
    });
</script>
