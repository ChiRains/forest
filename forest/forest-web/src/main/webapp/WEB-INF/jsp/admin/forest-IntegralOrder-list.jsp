<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>积分订单管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        积分订单管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            积分订单列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
<!--                             <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/integralOrder/toAdd">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>    -->                        
                        </div>
                    </div>                    
                    <div class="col-xs-6 " style="text-align: right;">
                        <div class="dataTables_length">
                        	<form action="#admin/integralOrder/list" onsubmit="listFormSearch(this); return false">
			                    <input type="search" maxlength="11" class="form-control search-query" name="keyword" value="${query.keyword }" placeholder="订单号">
			                 	<button id="search-button" type="submit" class="btn btn-purple btn-sm">
			                    <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>搜索</button>
		                    </form>
                        </div>
                    </div>                    
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                                                <th>订单号</th>           
                                                <th>用户名</th>           
                                                <th>金额</th>           
                                                <th>积分</th>           
                                                <th>图片</th>           
                                                <th>商品名称</th>           
                                                <th>规格</th>           
                                                <th>发货状态</th>           
                                                <th>催促状态</th>           
                                                <th>下单时间</th>           
                                                <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                                        <td>${item.orderNumber}</td>                         
                                                        <td>${item.userName}</td>                         
                                                        <td>${item.cash}</td>                         
                                                        <td>${item.integral}</td>                         
                                                        <td>${item.name}</td>                         
                                                        <td><img style="max-height: 150px; max-width: 150px;"
																src="${item.image}" />
														</td>                         
                                                        <td>${item.specifications}</td>                         
                                                        <td>
                                                        <c:if test="${item.state eq 10}">待付款</c:if>
                                                        <c:if test="${item.state eq 20}">待发货</c:if>
                                                        <c:if test="${item.state eq 30}">待收货</c:if>
                                                        <c:if test="${item.state eq 40}">已完成</c:if>
                                                        </td>                         
                                                        <td>
                                                        <c:if test="${item.remind eq 0}">没催促</c:if>
                                                        <c:if test="${item.remind eq 1}">已催促</c:if>
                                                        </td>                         
                                                        <td>
					                                	 <fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd HH:mm:ss" />
					                                   </td>                             
                                                        <td>
                                <div class="hidden-sm hidden-xs action-buttons">
<%--                                     <a title="修改基本信息" class="green" 
                                       href="#admin/integralOrder/toEdit?id=${item.id}&queryStr=${queryStr}">
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                    </a> --%>
                                 <c:if test="${item.state eq 20}">
                                    <button class="btn btn-warning shipOrder" data-orderId="${item.orderNumber}">发货</button>
                                 </c:if>
									<button class="btn btn-success edit" api-path="#admin/integralOrder/toEdit?id=${item.id}&queryStr=${queryStr}">查看</button>							                                 
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
    	 //发布
      $('.shipOrder').on('click',
        function() {
          var orderId=$(this).attr("data-orderId");
    	    BootstrapDialog.show({
                title: "快递清单",
                message: $('<div></div>').load('/admin/integralOrder/shipOrderForm.do?orderId='+orderId),
                 buttons: [{
                    label: '发货',
                    cssClass: 'btn btn-primary',
                    action: function(dialog) {
                        var orderIdForm = $("#orderId").val();
                        var expressCodeForm = $("#expressCode").val();
                        var expressNameForm = $("#expressName").val();
                        var expressNumberForm = $("#expressNumber").val();
                        var data = {
                        	orderId:orderIdForm,
                        	expressCode:expressCodeForm,
                        	expressName:expressNameForm,
                        	expressNumber:expressNumberForm
                        };
                        if($("#expressNumber").val()!=""&&$("#expressName").val()!="")
                        {
	                        $.ajax({
	                            url:'/admin/integralOrder/shipOrder.do',
	                            type:'POST',
	                            data:data,
	                            dataType: 'json',
	                            cache: false,
	                            async: false,
	                            error: function(){
	                                BootstrapDialog.alert({
	                                    title: '错误',
	                                    message:'网络错误，请稍后再尝试！',
	                                    type: BootstrapDialog.TYPE_DANGER,
	                                    callback: function(){setTimeout(function(){el[0].checked = !el[0].checked;},500)}
	                                });
	                            },
	                            success:function(data){
	                                if(data['status'] == 200){
	                                dialog.close();
	                                location.reload();
	                                }
	                                if(data['status'] != 200){
	                                    BootstrapDialog.show({
	                                        title:'错误',
	                                        message :'网络异常',
	                                               buttons: [{
	                                                   label: '确定',
	                                                   action: function (dialogItself) {
	                                                       dialogItself.close();
	                                                   }
	                                               }]
	                                         }); 
	                                
	                                }
	                            }
	                        })
	
	                   }
                        else if($("#expressNumber").val()=="" || $("#expressName").val()=="")
                        {
                            BootstrapDialog.show({
                                title:'错误',
                                message :'请填写好表单在提交',
                                       buttons: [{
                                           label: '确定',
                                           action: function (dialogItself) {
                                               dialogItself.close();
                                           }
                                       }]
                                 }); 
                        }
                    }
                }, {
                    label: '取消',
                    action: function(dialog) {
                        dialog.close();
                    }
                }]               
						
                    });
            });

		//修改
		$('.edit').on('click',
		function() {
		    var editUrl = $(this).attr('api-path');
		    window.location.href=editUrl;
		});
    });
</script>
