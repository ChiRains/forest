<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>秒杀管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        秒杀管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            秒杀列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/screenings/toAdd">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>                           
                        </div>
                    </div>                    
                    <div class="col-xs-6" style="text-align: right;">
                        <label>
                            <form id="query-form" action="#admin/screenings/list" onsubmit="listFormSearch(this); return false">
                            	<div class="dataTables_length">
                                        <input type="text" class="form-control search-query" maxlength="20" readonly id="beginTime" name="beginTime" placeholder="按秒杀时间查询" value="${query.beginTime}"/>
                                   		<button type="submit" class="btn btn-purple btn-sm">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                          	  查&nbsp;&nbsp;询
                                        </button>                                        
                                 </div>
                            </form>
                        </label>
                    </div>
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
	                    <tr role="row">     
		                    <th>场次</th>           
		                    <th>开始时间</th>           
		                    <th>结束时间</th>   
		                    <th>商品配置</th>        
		                    <th class="sorting_disabled">操作</th> 
	                    </tr>
                    </thead>

                    <tbody>
                    	<c:forEach items="${result}" var="item" varStatus="current"> 
                        	<tr>            
                                <td>${item.id}</td>
                                <td><fmt:formatDate value="${item.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>                         
                                <td><fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>                         
                                <td>
	                            	<a href="#admin/merchandiseSeckill/list?screeningsId=${item.id}" class="btn btn-primary btn-xs"><i class="ace-icon fa fa-usd bigger-130"></i>秒杀商品</a>
                                </td>
                                <td>
	                               <div class="hidden-sm hidden-xs action-buttons">
	                                    <a title="删除" class="red del-item-new" api-path="/admin/screenings/delete.do?id=${item.id}">
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
    var scripts = ["/qcloud-admin/assets/js/date-time/bootstrap-datepicker.min.js", null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
        jQuery(function ($) {
            $(window).off('resize.chosen').on('resize.chosen', function() {
                $('.chosen-select').each(function() {
                     var $this = $(this);
                     $this.next().css({'width': $this.parent().width()});
                })
            }).trigger('resize.chosen');
            
            $('#beginTime').datepicker({
				language: 'zh-cn',
				format: 'yyyy-mm-dd'
				//defaultDate:'+0d'
			});
			if (!$("#beginTime").val()) {
				$("#beginTime").datepicker('setDate', new Date());
			}
        });
        
    	$(".red").click(function(){
         	var delUrl=$(this).attr("api-path");
         	BootstrapDialog.show({
				title:'确认删除？',
				message:'删除后将无法恢复',
				buttons:[{
					label:'确定',
					cssClass :'btn btn-primary',
					action :function(dialogItself){
						$.get(delUrl, {},function(data){
							data = JSON.parse(data);
                                if(parseInt(data["status"]) === 0){
                                    dialogItself.setTitle('删除失败');
                                    dialogItself.setMessage(data["message"]);
                                    dialogItself.setType(BootstrapDialog.TYPE_WARNING);
                                    setTimeout(function(){
                                    	dialogItself.close();
                                    }, 1000);
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
					},{
	                    label: '取消',
	                    action: function (dialogItself) {
	                        dialogItself.close();
	                    }
	                }
				]
         	});
         });
    });
</script>
