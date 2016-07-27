<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>意见反馈类型管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        意见反馈类型管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            意见反馈类型列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/feedback/toAddClassify?type=2">
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
                                                <th>备注</th>           
                                                <th>是否启用</th>           
                                                <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${list}" var="item" varStatus="current"> 
                            <tr>            
                                                        <td>${item.name}</td>                         
                                                        <td>${item.remark}</td>
	                                                    <td>                                                        
                                                        <input value="${item.id}" type="checkbox" data-id="${item.id}" class="ace ace-switch ace-switch-5 ajax_switch"
                                                        <c:if test="${item.enable > 0}">
                                                        checked
                                                        </c:if>>
                                    					<span class="lbl middle"></span>  
                                                        </td>                         
                                                        <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <button class="btn btn-success edit" api-path="#admin/feedback/toEditClassify?id=${item.id}&queryStr=${queryStr}">修改</button>					                                 
                                    
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
        //启用按钮
    	 $(".ajax_switch").on('change',function(){
             var el = $(this);
             var data = {
            	 classifyId:el.attr('data-id'),
                 enable:el[0].checked?'1':'0'
             };
             $.ajax({
                 url:'/admin/feedback/enableClassify.do',
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
                     }
                 }
             })
         });  
         //编辑
         $('.edit').on('click',
        	        function() {
        	            var editUrl = $(this).attr('api-path');
        	            window.location.href=editUrl;
        	});
         //查看商品
         $('.check').on('click',
        	        function() {
        	            var editUrl = $(this).attr('api-path');
        	            window.location.href=editUrl;
        	        });
    });
</script>
