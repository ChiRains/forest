<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>大参林资讯管理</title>

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
           大参林资讯列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/article/toAdd">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a> 
                             <a title="客户评价审核" class="btn btn-sm btn-info"
                               href="#admin/articleEvaluation/list">
                              </i>
                               客户评价审核
                            </a>  
                             </div>
                           </div> 
                             <div class="col-xs-6" style="text-align: right;">
                                  <div class="dataTables_length">
                             <a href="#admin/article/classifyList" class="btn btn-sm btn-info"  >&ensp;返&emsp;回&ensp;</a>                         
                       </div>
                    </div>                     
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                                                <th>标题</th>           
                                                <th>图标</th>           
                                                <th>关键词</th>           
                                                <th>标签</th>           
                                                <th>日期</th>           
                                                <th>排序号</th>           
                                                <th>是否启用</th>  
                                                <th>分类</th>         
                                                <th>查看次数</th>           
                                                <th>点赞次数</th>   
                                                <th>客户评价</th>        
                                                <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                                        <td>${item.name}</td>                         
                                                        <td><img width="50" height="50" src="${item.icon}"/></td>                         
                                                        <td>${item.keywords}</td>                         
                                                        <td>${item.label}</td>                         
                                                        <td><fmt:formatDate value="${item.date}" pattern="yyyy-MM-dd"/></td>                         
                                                        <td>${item.sort}</td>                         
                                                        <td>	
                                                             <input value="${item.id}" type="checkbox" data-id="${item.id}" class="ace ace-switch ace-switch-5 ajax_switch"
                                	                         <c:if test="${item.enable > 0}">
                                	                    	         checked
                                                             </c:if>/>
                                                        	<span class="lbl middle"></span>
                                	                    </td>
                                	                    <td>${item.classifyName}</td>                      
                                                        <td>${item.viewCount}</td>                         
                                                        <td>${item.likeCount}</td>
                                                        <td>
                                                         <div>
                                                           <a title="客户评价" class="blue" href="#admin/articleEvaluation/list?articleId=${item.id}">
	                                                           <i class="ace-icon fa fa-cog bigger-130"></i>
	                                                         </a> </div>
                                                          </td>                         
                                                        <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="修改基本信息" class="green" 
                                       href="#admin/article/toEdit?id=${item.id}">
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                    </a>
                                    	 <a class="del-item-new red" api-path="/admin/article/delete.do?id=${item.id}" href="javascript:;">
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
        
         
    });
   
     
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
                                    if(parseInt(data["status"]) === 0){
                                        dialogItself.setTitle('删除失败');
                                        dialogItself.setMessage(data["message"]);
                                        dialogItself.setType(BootstrapDialog.TYPE_WARNING);
                                    }else{
                                        dialogItself.setTitle('成功');
                                        dialogItself.setMessage("删除成功！");
                                        dialogItself.setType(BootstrapDialog.TYPE_SUCCESS);
                                        setTimeout(function(){
                                            dialogItself.close();
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
         $(".ajax_switch").on('change',function(){
            var el = $(this);
            var data = {
                id:el.attr('data-id'),
                value:el[0].checked?'1':'0'
            };
            
            $.ajax({
                url:'/admin/article/enable.do',
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
        
           
</script>
