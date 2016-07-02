<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>订单管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
       订单管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
          总单列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
     <div class="col-xs-12">

        <div class="table-header">
            总单列表
        </div>
           <div>
            <div class="dataTables_wrapper form-inline no-footer">
            	<div class="row">
                    <div class="col-xs-12">
						<div class="text-right">
						<!--	<form id="query-form" action="#admin/collectOrder/list" onsubmit="listFormSearch(this); return false">
                                <div class="input-group">
                                    <input type="search" maxlength="11" class="form-control search-query"
                                           placeholder="请输入搜索订单号" name="orderNumber" value="${query.orderNumber}">
                                    <span class="input-group-btn">
                                        <button id="search-button" type="submit" class="btn btn-purple btn-sm">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            搜索
                                        </button>
                                    </span>
                                </div>
                            </form>  -->
							<div class="dataTables_length">
								请选择查询时间段 &nbsp; :&nbsp;
								<select id="dateType" name="" style="width:140px;">
									<c:if test="${query.dateType==1}">
										<option selected="selected" value="1">本月</option>
										<option value="2">本季度</option>
										<option value="3">今年内</option>
										<option value="4">去年</option>
										<option value="5">前年</option>
									</c:if>
									<c:if test="${query.dateType==2}">
										<option value="1">本月</option>
										<option selected="selected" value="2">本季度</option>
										<option value="3">今年内</option>
										<option value="4">去年</option>
										<option value="5">前年</option>
									</c:if>
									<c:if test="${query.dateType==3}">
										<option value="1">本月</option>
										<option value="2">本季度</option>
										<option selected="selected" value="3">今年内</option>
										<option value="4">去年</option>
										<option value="5">前年</option>
									</c:if>
									<c:if test="${query.dateType==4}">
										<option value="1">本月</option>
										<option value="2">本季度</option>
										<option value="3">今年内</option>
										<option selected="selected" value="4">去年</option>
										<option value="5">前年</option>
									</c:if>
									<c:if test="${query.dateType==5}">
										<option value="1">本月</option>
										<option value="2">本季度</option>
										<option value="3">今年内</option>
										<option value="4">去年</option>
										<option selected="selected" value="5">前年</option>
									</c:if>
									
									
								</select>
								 <button type="button" id="search_btn" class="btn btn-purple btn-sm">
									<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
									 查询
								</button>
							</div>
						  
						</div>
                    </div>                    
                </div>
				
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                            <th>订单号</th> 
                            <th>商家编号</th>          
                            <th>用户名称</th>           
                            <th>下单时间</th>           
                            <th>订单金额</th>           
                            <th>收货人</th>           
                            <th>收货地址</th>           
                            <th>收货电话</th>           
                            <th>支付方式</th>           
                            <th>状态</th>           
                            <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                <td>${item.orderNumber}</td>  
                                <td>${item.codeString}</td>                         
                                <td>${item.customer}</td>                         
                                <td><fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>                         
                                <td>${item.sum}</td>                         
                                <td>${item.consignee}</td> 
                                <td class="">
	                            	<c:if test="${fn:length(item.address) >15}">
	                            		${fn:substring(item.address, 0, 15)}...
	                            	</c:if>
	                            	<c:if test="${fn:length(item.address)<=15}">
	                            		${item.address}
	                            	</c:if>
	                            </td>                         
                                <td>${item.mobile}</td>                         
                                <td>
                                 <c:forEach items="${paymentModeType}" var="type" varStatus="a"> 
                                	<c:if test="${type.key eq item.paymentMode}">
                                		${type.name}
                                	</c:if>
                                </c:forEach>
                                </td>                         
                                <td>
                                <c:forEach items="${orderStateType}" var="type" varStatus="a"> 
                                	<c:if test="${type.key eq item.state}">
                                		${type.name}
                                	</c:if>
                                </c:forEach>
                                </td>                         
                                <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                	<a title="查看详情" class="blue"href="#admin/collectOrder/getDetails?id=${item.id}&orderDate=<fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd HH:mm:ss"/>">
										<i class="ace-icon fa fa-search-plus bigger-130"></i>
									</a>
                                </div>
                            </td>
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
	    jQuery(function ($) {
	    	$("#search_btn").click(function(){
				var dateType=$("#dateType option:selected").val();
				window.location="#admin/collectOrder/list?dateType="+dateType;
	    	});
	    
		     $(".showValue").click(function(){
	        	var value=$(this).attr("title");
	        	BootstrapDialog.show({
	        		title:'订单说明',
	        		message:value,
	        		cssClass: 'showValueCss'
	        	});
	        });
	        
	    });
    });
</script>
