<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>优惠券管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
      优惠券管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            优惠券列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
           优惠券列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/coupon/toAdd4Merchant">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>                           
                        </div>
                    </div>    
                </div>
                                    
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     		<th>图片</th> 
                                                <th>开始时间</th>           
                                                <th>结束时间</th>           
                                                <th>开始使用时间</th> 
                                                <th>截止使用时间</th>              
                                                <th>总共领取张数/人</th>           
                                                <th>总共领取金额数/人</th>    
                                                <th>领取间隔时间(天)</th>
                                                <th>描述</th>           
                                                <th>是否启用</th>           
                                                <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
							<td><img src="${item.image}" width="100" height="80"/>  </td> 
                            <td><fmt:formatDate value="${item.startDate}" pattern="yyyy-MM-dd"/></td>                         
                            <td><fmt:formatDate value="${item.endDate}" pattern="yyyy-MM-dd"/></td>                         
                            <td><fmt:formatDate value="${item.validDate}" pattern="yyyy-MM-dd"/></td>      
                            <td><fmt:formatDate value="${item.invalidDate}" pattern="yyyy-MM-dd"/></td>                         
                            <td>${item.totalOfPerson}</td>                         
                            <td>${item.priceOfPerson}</td>     
                            <td>${item.interval}天</td>                      
                            <td width="240">
                            	<c:choose>
                            		<c:when test="${fn:length(item.description) > 10}">
		                            	<span title="${item.description}">${fn:substring(item.description,0,20)}...</span>
                            		</c:when>
                            		<c:otherwise>
                            			<span title="${item.description}">${item.description}</span>
                            		</c:otherwise>
                            	</c:choose>
                            </td>                         
                            <td>
                            <c:choose>
                            	<c:when test="${item.enable==1}">
		                            	启用
                            		</c:when>
                            		<c:otherwise>
                            			禁用
                            		</c:otherwise>
                            	</c:choose>
                            </td>                         
                            <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="修改" class="green" 
                                       href="#admin/coupon/toEdit4Merchant?id=${item.id}">
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                    </a>
                                    <a title="查看详情" 
                                       href="#admin/couponItems/list4Merchant?couponID=${item.id}">
                                        <i class="ace-icon fa fa-eye bigger-130"></i>
                                    </a>	
                                    <a title="删除" class="red del-item-new" href="javascript:;"
                                       api-path="/admin/coupon/delete.do?id=${item.id}">
                                        <i class="ace-icon fa fa-trash-o bigger-130"></i>
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
         $('.del-item-new').on('click', function () {
            var delUrl = $(this).attr('api-path');
            BootstrapDialog.show({
                title: '确认删除？',
                message: '删除后将无法恢复！',
                buttons: [{
                    label: '确定',
                    cssClass: 'btn btn-primary',
                    action: function (dialogItself) {
                        $.get(delUrl, {},
                                function (data) {
                                    data = JSON.parse(data);
                                    if(data["status"] != 200){
                                        dialogItself.setTitle('删除失败');
                                        dialogItself.setMessage(data["message"]);
                                        dialogItself.setType(BootstrapDialog.TYPE_WARNING);
                                    }else{
                                        dialogItself.setTitle('成功');
                                        dialogItself.setMessage("删除成功！");
                                        dialogItself.setType(BootstrapDialog.TYPE_SUCCESS);
                                        setTimeout(function(){
                                            dialogRef.close();
                                        }, 1000);
                                        setTimeout(function(){
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
