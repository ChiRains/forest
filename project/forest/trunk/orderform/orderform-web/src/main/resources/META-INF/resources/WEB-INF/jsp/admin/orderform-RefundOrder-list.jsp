<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>退款订单管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        退款订单管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            退款订单列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            退款订单列表
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
	                            <form action="#admin/refundOrder/list" onsubmit="listFormSearch(this); return false">
	                                <div class="dataTables_length">
	                                	<input type="search" class="form-control search-query"
                                           placeholder="退款单号" name="refundNumber" value="${query.refundNumber}">                                        	                            	
		                                   	 状态
		                                   	 <select class="form-control" id="state" name="state">
	                                        	<option value="0">请选择</option>
	                                        	<c:forEach var="state" items="${orderStateType}" >
	                                        		 <option <c:if test="${state.key==query.state}"> selected </c:if> value="${state.key}">${state.name}</option>			                         
	                                        	</c:forEach>
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
                        <th>退款单号</th>      
                        <th>申请退款时间</th>  
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
                                <td>${item.refundNumber}</td>                         
                                <td>
                                <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.time}" />
                                </td>
                                <td>
                                	 <c:forEach items="${orderStateType}" var="type" varStatus="typeStatus"> 
                                		<c:if test="${item.state eq type.key}" >
                                			${type.name}
                                		</c:if>
                                	</c:forEach>
                               </td>        
                                <td>
	                                <div class="hidden-sm hidden-xs action-buttons">
	                                    <div class="hidden-sm hidden-xs action-buttons">
	                                	<a title="查看明细" class="green" 
	                                       href="#admin/refundOrder/getDetails?id=${item.id}">
	                                        <i class="ace-icon fa fa-eye bigger-130"></i>
	                                    </a>
                               			 </div>						                                 
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
