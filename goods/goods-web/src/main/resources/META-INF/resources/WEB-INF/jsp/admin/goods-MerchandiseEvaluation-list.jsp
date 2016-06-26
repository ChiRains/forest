<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>评价管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        评价管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            评价列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/merchandiseEvaluation/toAdd?merchandiseId=${query.merchandiseId}">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>                           
                        </div>
                    </div>  
                    <div class="col-xs-6" style="text-align: right;">
                        <a href="#admin/merchandise/list" class="btn btn-sm btn-info">&ensp;返&emsp;回&ensp;</a>
                    </div>                  
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                        <th>商品名称</th>           
                        <th>评价内容</th>           
                        <th>星级</th>           
                        <th>评价时间</th>           
                        <th>审核状态</th>           
                        <th>规格</th>           
                        <th>评价人</th>           
                       <!--  <th class="sorting_disabled">操作</th> -->
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                <td>${item.merchandiseName}</td>                         
                                <td>
                                	 <label class="showcontent" content="${item.content}">
	                                 <c:choose>
		                                <c:when test="${fn:length(item.content)>20}">
		                               		<a>${fn:substring(item.content,0,20)} .....</a>
		                                </c:when>
		                                <c:otherwise><a>${item.content}</a></c:otherwise>
	                                </c:choose> 
                                </td>                         
                                <td>
                            		<c:forEach items="${item.stars}" var="star" varStatus="current2"> 
	                                	<c:if test="${star eq 0}">
		                                	<i class="ace-icon red bigger-130 fa fa-star"></i>
	                                	</c:if>
	                                	<c:if test="${star eq 1}">
		                                	<i class="ace-icon red bigger-130 fa fa-star-half-o"></i>
	                                	</c:if>
                                    </c:forEach>
								</td>                         
                                <td><fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>                         
                                <td><c:if test="${item.status eq statusType[0].key}">
                                		未审核
                                	</c:if>
                                	<c:if test="${item.status eq statusType[1].key}">
                                		未通过
                                		<!--  <i class="ace-icon red bigger-130 fa fa-times"></i>  -->
                                	</c:if>
                                	<c:if test="${item.status eq statusType[2].key}">
                                		已通过
                                		<!--  <i class="ace-icon green bigger-130 fa fa-check"></i>  -->
                                	</c:if>
                                </td>                         
                                <td>${item.specifications}</td> 
                                <td>${item.userName}</td>                         
                                <!--  
                                <td>
	                                <div class="hidden-sm hidden-xs action-buttons">
	                                    <a title="修改基本信息" class="green" 
	                                       href="#admin/merchandiseEvaluation/toEdit?id=${item.id}">
	                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
	                                    </a>							                                 
	                                </div>
                                 	<a title="删除" class="red del-item-new" href="javascript:;"
                                       api-path="/admin/merchandiseEvaluation/delete.do?id=${item.id}&merchandiseId=${item.merchandiseId}">
                                        <i class="ace-icon fa fa-trash-o bigger-130"></i>
                                    </a>
                            	</td>
                                -->
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
          $('.showcontent').on('click', function () {
	    	var content=$(this).attr('content');
	        BootstrapDialog.show({
	            title: '内容',
	            message:content,
	            buttons: [{
	                id: 'btn-1',
	                label: '确定',
	                cssClass: 'btn btn-primary',
	                action: function (dialogItself) {
	                    dialogItself.close();
	                }
	            }]
	        });
        });
        
         $('.del-item-new').on('click', function () {
            var delUrl = $(this).attr('api-path');
            BootstrapDialog.show({
                title: '确认删除？',
                message: '删除后将无法恢复！',
                buttons: [{
                    id: 'btn-1',
                    label: '确定',
                    cssClass: 'btn btn-primary',
                    action: function (dialogItself) {
                        $.get(delUrl, {},
                                function (data) {
                                    data = JSON.parse(data);
                                    if (parseInt(data["status"]) === 0) {
                                        dialogItself.setTitle('删除失败');
                                        dialogItself.setMessage(data["message"]);
                                        dialogItself.setType(BootstrapDialog.TYPE_DANGER);
                                        dialogItself.getButton('btn-1').remove();
                                    } else {
                                        dialogItself.setTitle('成功');
                                        dialogItself.setMessage("删除成功！");
                                        dialogItself.setType(BootstrapDialog.TYPE_SUCCESS);
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
