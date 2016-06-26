<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>大客户历史价格管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        大客户历史价格管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            大客户历史价格列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-12" style="text-align: right;">
                        <c:if test="${query.merchandiseItemId==0}"><a href="#admin/merchantUser/vipList" class="btn btn-sm btn-info"> 返 回 </a></c:if>
                        <c:if test="${query.merchandiseItemId!=0}"><a href="#admin/merchandiseVipDiscount/list?userId=${query.userId}&companyName=${query.companyName}" class="btn btn-sm btn-info"> 返 回 </a></c:if>
                    </div>                
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
	                    <tr role="row">     
		                    <th>客户</th>           
		                    <c:if test="${query.merchandiseItemId!=0}">
			                    <th>商品</th>
			                    <th>价格</th>
		                    </c:if>
		                    <th>折扣</th>
		                    <c:if test="${query.merchandiseItemId!=0}">
			                    <th>支付方式</th>
			                    <th>历史价格</th>
		                    </c:if>
		                    <th>历史折扣</th>
		                    <c:if test="${query.merchandiseItemId!=0}">
		                    	<th>历史支付方式</th>
		                    </c:if>
		                    <th>修改时间</th>
		                    <!-- <th class="sorting_disabled">操作</th> -->
	                    </tr>
                    </thead>

                    <tbody>
                    	<c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                            	<td>${item.companyName}</td>                         
                            	<c:if test="${query.merchandiseItemId!=0}">
	                            	<td>${item.merchandiseItemName}</td>
	                                <td>${item.price}</td>
                                </c:if>
                                <td>${item.discount}</td>
                                <c:if test="${query.merchandiseItemId!=0}">
	                                <td>${item.paymentType}</td>
	                                <td>${item.priceHistory}</td>
                                </c:if>
                                <td>${item.discountHistory}</td>
                                <c:if test="${query.merchandiseItemId!=0}">
                                	<td>${item.paymentTypeHistory}</td>
                                </c:if>
                                <td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                                <%-- <td>
	                                <div class="hidden-sm hidden-xs action-buttons">
	                                    <a title="修改基本信息" class="green" 
	                                       href="#admin/merchandiseVipDiscountHistory/toEdit?id=${item.id}&queryStr=${queryStr}">
	                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
	                                    </a>
	                                </div>
                           		</td> --%>
                        	</tr>
						</c:forEach>
                    </tbody>
                </table>    
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
         
    });
</script>
