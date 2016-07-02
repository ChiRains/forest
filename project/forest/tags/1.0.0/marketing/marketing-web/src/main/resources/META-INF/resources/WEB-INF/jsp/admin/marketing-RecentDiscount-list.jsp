<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>管理员管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        管理员管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            管理员列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/recentDiscount/toAdd">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>                           
                        </div>
                    </div>                    
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                        <th>名称</th> 
                        <th>图片</th>            
                        <th>开始时间</th>           
                        <th>结束时间</th> 
                        <th>是否启用</th>
                        <th>查看活动商品</th>             
                        <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                <td>${item.name}</td>                         
                                <td><img src="${item.image}" width="120" height="80"/></td>                         
                                <td><fmt:formatDate value="${item.startDate}" pattern="yyyy-MM-dd"/></td>                         
                                <td><fmt:formatDate value="${item.endDate}" pattern="yyyy-MM-dd"/></td>                         
                                <td>
                                	<input value="${item.enable}" <c:if test="${item.enable==1}">checked </c:if> type="checkbox" data-id="${item.id}" class="ace ace-switch ace-switch-6 ajax_switch">
                                	<span class="lbl middle"></span>
                                </td>  
                                <td>
                                <a href="#admin/unionMall/recentDiscount?activityId=${item.id}"><button class="btn btn-info" type="button">
                                  <i class="ace-icon fa fa-eye bigger-80"> 
                                  		查看活动商品 </i>
                                </button></a>
                                </td>     
                                <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="修改基本信息" class="green" 
                                       href="#admin/recentDiscount/toEdit?id=${item.id}">
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
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
         $(".ajax_switch").on('change',function(){
            var el = $(this);
            var data = {
                id:el.attr('data-id'),
                value:el[0].checked?'1':'0'
            };
            var url=el[0].checked?"/admin/recentDiscount/enable.do":"/admin/recentDiscount/disable.do";
            $.ajax({
                url:url,
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
                success:function(rd){
                    if(rd['status'] != 200){
                        BootstrapDialog.alert({
                            title: '错误',
                            message:rd.message,
                            type: BootstrapDialog.TYPE_DANGER,
                            callback: function(){setTimeout(function(){el[0].checked = !el[0].checked;},500)}
                        });
                    }else{
                    	 /*setTimeout(function () {
                            location.reload();
                        }, 1500);*/
                    }
                }
	        });
	    });  
    });
</script>
