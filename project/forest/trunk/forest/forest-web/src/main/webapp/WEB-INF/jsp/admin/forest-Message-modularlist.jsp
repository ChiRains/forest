<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>消息通知</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        消息通知
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
              消息通知
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">   
                    	<th>#</th>   
	                    <th>消息类型</th>           
	                    <th>描述</th>           
	                    <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                        <tr>      
                        	<td>1</td><td>站内信</td><td>主题活动、优惠信息</td>                         
                            <td>
	                            <div class="hidden-sm hidden-xs action-buttons">
	                                <a class="btn btn-xs btn-info" href="#admin/messageSource/list">
	                                	发送消息
	                                </a>
	                            </div>
                            </td>
                        </tr>
                        <tr>      
                        	<td>2</td><td>赠送优惠券</td><td>赠送优惠券活动</td>                         
                            <td>
	                            <div class="hidden-sm hidden-xs action-buttons">
	                                <a class="btn btn-xs btn-info" href="#">
	                                	赠送优惠券
	                                </a>
	                            </div>
                            </td>
                        </tr>
                        <tr>      
                        	<td>3</td><td>赠送积分</td><td>赠送积分活动</td>                         
                            <td>
	                            <div class="hidden-sm hidden-xs action-buttons">
	                                <a class="btn btn-xs btn-info" href="#">
	                                	赠送积分
	                                </a>
	                            </div>
                            </td>
                        </tr>
                    </tbody>
                </table>    
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
       
</script>
