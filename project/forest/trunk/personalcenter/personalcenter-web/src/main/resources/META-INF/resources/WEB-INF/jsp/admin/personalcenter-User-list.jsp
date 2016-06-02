<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>用户管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        用户管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            	用户列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/user/toAdd">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a> 
                            &nbsp;&nbsp; &nbsp;
                           <a class="btn btn-sm btn-info send-massage0" style="width:75px;height:34px;">
                                <i class="ace-icon bigger-125"></i>发送消息</a>
                                	&nbsp;&nbsp;   
                        </div>
                     </div>
                     <div class="col-xs-6" style="text-align: right;">
                        <label>
                            <form id="query-form" action="#admin/user/list" onsubmit="listFormSearch(this); return false">
                            	<div class="dataTables_length">
                               			<input type="search" maxlength="11" class="form-control search-query"
                                           placeholder="姓名/昵称/手机号/邮箱" name="name" value="${query.name}">
                                   		<button type="submit" class="btn btn-purple btn-sm">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            查询
                                        </button>                                        
                                 </div>
                            </form>
                        </label>
                    </div>                      
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">         
                                                <th>昵称</th>           
                                                <th>姓名</th>        
                                                <th>手机号</th>           
                                                <th>Email</th>           
                                                <th>头像</th>           
                                                <th>用户类别</th>           
                                                <th>性别</th>           
                                                <th>等级</th>           
                                                <th>注册时间</th>   
                                                <th>是否启用</th>
                                                <th>状态</th>          
                                                <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>                                 
                                <td>${item.nickname}</td>                         
                                <td>${item.name}</td>    
                                <td>${item.mobile}</td>                         
                                <td>${item.email}</td>                         
                                <td><img width="50" height="50" src="${item.headImage}"/></td>                      
                                <td>${item.typeStr}</td>                         
                                <td>${item.sexStr}</td>                         
                                <td>${item.grade}</td>                         
                                <td><fmt:formatDate value="${item.registTime}" pattern="yyyy年MM月dd日"/></td>      
                                <td>
									<input value="${item.id}" type="checkbox" data-id="${item.id}" class="ace ace-switch ace-switch-5 ajax_switch"
            								${item.enableSelected}>
        							<span class="lbl middle"></span>
								</td>
								<td>
									<c:if test="${item.state eq 1}">
										<a title="激活" class="activate" data-id="${item.id}">
	                            			<i class="ace-icon fa fa-cog bigger-130">激活</i>
	                            		</a>
									</c:if>	
									<c:if test="${item.state eq 2}">
										已激活
									</c:if>	
								</td>                    
                                <td>
		                            <div class="hidden-sm hidden-xs action-buttons">
		                                <a title="修改基本信息" class="green" 
		                                   href="#admin/user/toEdit?id=${item.id}">
		                                    <i class="ace-icon fa fa-pencil bigger-130"></i>
		                                </a>
		                                
		                                <a title="发送消息" class="send-massage" data-id="${item.id}" data-name="${item.nickname}">
		                            		<i class="ace-icon fa fa-envelope bigger-130"></i>
		                            	</a>
		                            	
		                            	<a title="重置密码" class="pink reset-item-new"  
								           api-path="/admin/user/resetPwd.do?id=${item.id}">
								           <i class="ace-icon fa fa-wrench bigger-130"></i>
								        </a>
		                            	
		                            	&nbsp;&nbsp;
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
     
      $(".send-massage").on("click", function () {
     // 务必添加解绑事件，要不然会重复提交数据
     		$(document).off('click',".to-send-massage");
     		  var url ="/admin/myMessage/send.do";
     		  var id=$(this).attr("data-id");
              var name=$(this).attr("data-name");
     		
            BootstrapDialog.show({ 
                title:"发送消息",   
                message: $('<div></div>').load('/admin/myMessage/toSend.do?userId='+id+'&name='+name),
                cssClass: "select-product-dialog",
                onshow: function (dialog) {
                	
                },
                buttons: [{
	                label: '发送',
	                cssClass: 'btn btn-primary',
	                action: function (dialog) {
	                	 /* $.get(url + "?" + $("#delivery-form").serialize(),
	                        function (data) {
			                    var title = document.getElementById("title").value;
		     		            var content = document.getElementById("content").value;
		     		            if(title==""||content=="") 
		     		            { 
		     		              BootstrapDialog.alert({
		                            title: '提醒',
		                            message:"标题或者内容不能为空！！",
		                            type: BootstrapDialog.TYPE_DANGER,
		                        });
		     		            }else{
		     		                  dialog.close();
			                          location.reload();
		     		            }
	                        }
	                    ); */
                    }
                }]
            });
        });              
            $(".send-massage0").on("click", function () {
     // 务必添加解绑事件，要不然会重复提交数据
     		$(document).off('click',".to-send-massage");
     		var url ="/admin/myMessage/send.do";
     		
     		  var id=0;
              var name=null;
     		 
            BootstrapDialog.show({ 
                title:"发送消息",   
                message: $('<div></div>').load('/admin/myMessage/toSend.do?userId='+id+'&name='+name),
                cssClass: "select-product-dialog",
                onshow: function (dialog) {
                	
                },
                buttons: [{
	                label: '发送',
	                cssClass: 'btn btn-primary',
	                action: function (dialog) {
	                
	                	 $.get(url + "?" + $("#delivery-form").serialize(),
	                        function (data) {
	                    var title = document.getElementById("title").value;
     		            var content = document.getElementById("content").value;
     		            var userId_send = document.getElementById("userId_send").value;
     		            if(title==""||content==""||userId_send==0) 
     		            { 
     		              BootstrapDialog.alert({
                            title: '提醒',
                            message:"接受人或标题或内容不能为空！！",
                            type: BootstrapDialog.TYPE_DANGER,
                        });
     		            }else{
     		                  dialog.close();
	                          location.reload();
     		            }
	                        }
	                    );
                    }
                }]
            });
        });                      
        //inline scripts related to this page
         $(".ajax_switch").on('change',function(){
            var el = $(this);
            var data = {
                id:el.attr('data-id'),
                value:el[0].checked?'1':'0'
            };
            $.ajax({
                url:'/admin/user/enable.do',
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
        }) 
        
        	$('.reset-item-new').on('click', function () {
            var delUrl = $(this).attr('api-path');
            BootstrapDialog.show({
                title: '确认重置密码？',
                message: '重置密码后将无法恢复！',
                buttons: [{
                    id: 'btn-1', 
                    label: '确定',
                    cssClass: 'btn btn-primary',
                    action: function (dialogItself) {
                        $.get(delUrl, {},
                                function (data) {
                                    data = JSON.parse(data);
                                    if (parseInt(data["status"]) === 0) {
                                        dialogItself.setTitle('重置密码失败');
                                        dialogItself.setMessage(data["message"]);
                                        dialogItself.setType(BootstrapDialog.TYPE_DANGER);
                                        dialogItself.getButton('btn-1').remove();
                                    } else {
                                        dialogItself.setTitle('成功');
                                        dialogItself.setMessage("重置密码成功！");
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
		         
        
        $(".activate").on('click',function(){
        	var id=$(this).attr("data-id");
        	var url="/admin/user/activate.do?id="+id;
        	BootstrapDialog.show({ 
        		title:"是否激活用户",   
                message:"是否激活用户",
                cssClass: "select-product-dialog",
                onshow: function (dialog) {
                	
                },
                buttons: [{
	                label: '激活',
	                cssClass: 'btn btn-primary',
	                action: function (dialogItself) {
	                	$.get(url, {},
                            function (data) {
                                data = JSON.parse(data);
                                if (parseInt(data["status"]) === 0) {
                                    dialogItself.setTitle('激活失败');
                                    dialogItself.setMessage(data["message"]);
                                    dialogItself.setType(BootstrapDialog.TYPE_DANGER);
                                    dialogItself.getButton('btn-1').remove();
                                } else {
                                    dialogItself.setTitle('成功');
                                    dialogItself.setMessage("激活成功 ！");
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
                }]
        	
        	});
        }); 
    });
</script>
