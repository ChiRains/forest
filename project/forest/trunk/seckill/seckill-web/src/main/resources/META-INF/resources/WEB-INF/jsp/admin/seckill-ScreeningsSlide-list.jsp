<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>秒杀活动轮播图管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        秒杀活动轮播图理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            秒杀活动轮播图列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/screeningsSlide/toAdd?screeningsId=${screeningsId}">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>                           
                        </div>
                    </div>                    
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                                                <th>场次</th>           
                                                <th>点击地址</th>           
                                                <th>图片</th>           
                                                <th>排序</th>           
                                                <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                                        <td>${item.screeningsId}</td>                         
                                                        <td>${item.clickUrl}</td>                         
														<td><img style="max-height: 150px; max-width: 150px;"
															src="${item.image}" />
															</td>                                             
														<td>${item.orderNum}</td>                         
                                                        <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="修改基本信息" class="green" 
                                       href="#admin/screeningsSlide/toEdit?id=${item.id}">
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                    </a>							                                 
                                    <a title="删除" class="red" 
                                     api-path="/admin/screeningsSlide/delete.do?id=${item.id}">
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
 	$('.red').on('click',
             function() {
                 var delUrl = $(this).attr('api-path');
                 BootstrapDialog.show({
                     title: '确认删除信息？',
                     message: '删除信息将不能恢复！',
                     buttons: [{
                         id: 'btn-1',
                         label: '确定',
                         cssClass: 'btn btn-primary',
                         action: function(dialogItself) {
                             $.get(delUrl, {},
                             function(data) {
                                 data = JSON.parse(data);
                                 if (parseInt(data["status"]) === 0) {
                                     dialogItself.setTitle('删除信息失败');
                                     dialogItself.setMessage(data["message"]);
                                     dialogItself.setType(BootstrapDialog.TYPE_DANGER);
                                     dialogItself.getButton('btn-1').remove();
                                 } else {
                                     dialogItself.setTitle('成功');
                                     dialogItself.setMessage("删除信息成功！");
                                     dialogItself.setType(BootstrapDialog.TYPE_SUCCESS);
                                     setTimeout(function() {
                                         dialogItself.close();
                                     },
                                     1000);
                                     setTimeout(function() {
                                         location.reload(true);
                                     },
                                     1500);
                                 }
                             });
                         }
                     },
                     {
                         label: '取消',
                         action: function(dialogItself) {
                             dialogItself.close();
                         }
                     }]
                 });
             });
    });
</script>
