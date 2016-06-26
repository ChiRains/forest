<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>大客户价格管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        大客户价格管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            大客户折扣列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
            	<form id="groupbuys-form" class="form-horizontal" role="form" action="/admin/merchandiseVipDiscount/addVipDiscounts.do">
               <!-- <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <button class="btn btn-sm btn-info" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>&nbsp;保&nbsp;存&nbsp;</button>                      
                        </div>
                    </div>   
                </div>-->
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
	                    <tr role="row">     
		                    <th>客户名称</th>           
		                    <!--<th>折扣</th>-->
		                    <th class="sorting_disabled">操作</th>
	                    </tr>
                    </thead>

                    <tbody>
                    	<c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                <td>${item.companyName}<input type="hidden" name="merchandiseVipDiscounts[${current.index}].userId" value="${item.userId}" class="config-value"></td>                         
                                <!--<td>
                                	<input type="number" name="merchandiseVipDiscounts[${current.index}].discount" value="${item.discount}" class="config-value">折
                                </td>-->
                                <td>
	                                <div class="hidden-sm hidden-xs action-buttons">
	                                    <a href="#admin/merchandiseVipDiscount/list?userId=${item.userId}&companyName=${item.companyName}" class="btn btn-primary btn-xs"><i class="ace-icon fa fa-usd bigger-130"></i>价格设置</a>
	                                    <!--<a href="#admin/merchandiseVipDiscountHistory/list?userId=${item.userId}&companyName=${item.companyName}&merchandiseItemId=0" class="btn btn-primary btn-xs"><i class="ace-icon fa fa-usd bigger-130"></i>历史折扣</a>-->					                                 
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

    	//表单验证
        $("#groupbuys-form").validate({
            errorElement: 'div',
            errorClass: 'help-block col-xs-12 col-sm-reset inline',
            focusInvalid: false,
            rules: {
            	
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
