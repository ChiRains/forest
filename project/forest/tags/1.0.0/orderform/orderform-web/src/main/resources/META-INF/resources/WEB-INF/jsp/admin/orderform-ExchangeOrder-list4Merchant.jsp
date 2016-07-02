<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>换货订单管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
    换货订单管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            换货订单列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
           换货订单列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                        </div>
                    </div>
                    <div class="col-xs-6">
	                    <div class="text-right">
	                        <label>
	                            <form action="#admin/exchangeOrder/list4Merchant" onsubmit="listFormSearch(this); return false">
	                                <div class="dataTables_length">          
	                                	<input type="search" class="form-control search-query"
                                           placeholder="换货单号" name="exchangeNumber" value="${query.exchangeNumber}">                                	                            	
		                                   	 状态
		                                   	 <select class="form-control" id="state" name="state">
	                                        	<option value="0">请选择</option>
			                                   		 <option <c:if test="${state==510}"> selected </c:if> value="1">申请换货</option>			                                   		
			                                   		 <option <c:if test="${state==520}"> selected </c:if> value="2">卖家不同意换货</option>			                                   		
			                                   		 <option <c:if test="${state==530}"> selected </c:if> value="3">换货失败</option>			                                   		
			                                   		 <option <c:if test="${state==540}"> selected </c:if> value="4">卖家同意换货</option>			                                   		
			                                   		 <option <c:if test="${state==550}"> selected </c:if> value="5">买家发货</option>			                                   		
			                                   		 <option <c:if test="${state==560}"> selected </c:if> value="6">卖家签收</option>			                                   		
			                                   		 <option <c:if test="${state==570}"> selected </c:if> value="7">卖家发货</option>			                                   		
			                               			 <option <c:if test="${state==580}"> selected </c:if> value="8">买家签收</option>			                                   		
			                                   		 <option <c:if test="${state==590}"> selected </c:if> value="9">换货成功</option>			                                   		
			                                </select>
				                      		&nbsp;&nbsp;
	                                   		<button type="submit" class="btn btn-purple btn-sm">
	                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
	                                            查询
	                                        </button>                                        
	                                 </div>
	                            </form>
	                        </label>
	                    </div>
	                </div>                
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                                                <th>总单ID</th>  
                                                <th>子单ID</th>           
                                                <th>用户</th>           
                                                <th>换货单号</th>      
                                                <th>申请换货时间</th>        
                                                <th>状态</th>                
                                                <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                <td>${item.orderId}</td>  
                                <td>${item.subOrderId}</td>                      
                                 <td>${item.userName}</td>                         
                                <td>${item.exchangeNumber}</td> 
                                <td><fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>                         
                                <td>
                                 <c:forEach items="${orderStateType}" var="orderStateType" varStatus="current">
                                 	<c:if test="${orderStateType.key eq item.state}">${orderStateType.name}</c:if> 
                                 </c:forEach>
                                </td>                          
                                <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                	<a title="查看明细" class="green" 
                                       href="#admin/exchangeOrder/getDetails4Merchant?id=${item.id}">
                                        <i class="ace-icon fa fa-eye bigger-130"></i>
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
         
    });
</script>
