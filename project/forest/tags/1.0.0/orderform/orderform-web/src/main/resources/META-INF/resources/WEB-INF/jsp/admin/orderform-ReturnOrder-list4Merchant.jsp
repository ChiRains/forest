<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>退货订单管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
      退货订单管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            退货订单列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
           退货订单列表
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
	                            <form action="#admin/returnOrder/list4Merchant" onsubmit="listFormSearch(this); return false">
	                                <div class="dataTables_length">  
	                                		<input type="search" class="form-control search-query"
                                          	 placeholder="退货单号" name="returnNumber" value="${query.returnNumber}">                                                   	                            	
		                                   	 状态
		                                   	 <select class="form-control" id="state" name="state">
	                                        	<option value="0">请选择</option>
			                                   		 <option <c:if test="${state==310}"> selected </c:if> value="1">申请退货</option>			                                   		
			                                   		 <option <c:if test="${state==320}"> selected </c:if> value="2">门店不同意退货</option>			                                   		
			                                   		 <option <c:if test="${state==330}"> selected </c:if> value="3">退货失败</option>			                                   		
			                                   		 <option <c:if test="${state==340}"> selected </c:if> value="4">门店同意退货</option>			                                   		
			                                   		 <option <c:if test="${state==350}"> selected </c:if> value="5">买家发货</option>			                                   		
			                                   		 <option <c:if test="${state==360}"> selected </c:if> value="6">门店签收</option>			                                   		
			                                   		 <option <c:if test="${state==370}"> selected </c:if> value="7">公司退款</option>			                                   		
			                               			 <option <c:if test="${state==380}"> selected </c:if> value="8">买家确认退款</option>			                                   		
			                                   		 <option <c:if test="${state==390}"> selected </c:if> value="9">退货成功</option>			                                   		
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
                                                <th>退货单号</th>      
                                                <th>申请退货时间</th>        
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
                                <td>${item.returnNumber}</td> 
                                <td><fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>                         
                                <td>
                                	  <c:forEach items="${orderStateType}" var="orderStateType" varStatus="current">
                                 		<c:if test="${orderStateType.key eq item.state}">${orderStateType.name}</c:if> 
                                 	</c:forEach>
								</td>                          
                                <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                	<a title="查看明细" class="green" 
                                       href="#admin/returnOrder/getDetails4Merchant?id=${item.id}">
                                        <i class="ace-icon fa fa-eye bigger-130"></i>
                                    </a>
                                    <c:if test="${state==360}">
                                     	退款
	                                    <a title="卖家退款" class="green payReturn-item" href="javascript:;"
	                                    api-path="/afterSaleState/payReturn.do?returnId=${item.id}">
	                                         <i class="ace-icon glyphicon glyphicon-ok"></i>
	                                    </a>
	                                    &nbsp;
                                    </c:if>
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
          $('.payReturn-item').on('click', function () {
            var delUrl = $(this).attr('api-path');
            BootstrapDialog.show({
                title: '退款',
                message: '公司确定退款？',
                buttons: [{
                    id: 'btn-1',
                    label: '确定',
                    cssClass: 'btn btn-primary',
                    action: function (dialogItself) {
                        $.get(delUrl, {},
                                function (data) {
                                    data = JSON.parse(data);
                                    if (parseInt(data["status"]) === -599) {
                                        dialogItself.setTitle('执行失败');
                                        dialogItself.setMessage(data["message"]);
                                        //dialogItself.setType(BootstrapDialog.TYPE_DANGER);
                                        dialogItself.getButton('btn-1').remove();
                                    } else {
                                        dialogItself.setTitle('成功');
                                        dialogItself.setMessage("操作成功!");
                                        dialogItself.setType(BootstrapDialog.TYPE_SUCCESS);
                                         dialogItself.getButton('btn-1').remove();
                                        setTimeout(function () {
                                            dialogItself.close();
                                        }, 1000);
                                        setTimeout(function () {
                                            location.reload();
                                        }, 1500);
                                    }
                                });
                    }
                }, {
                    label: '取消',
                    action: function (dialogItself) {
                        dialogItself.close();
                    }
                }]
            });
        });
    });
</script>
