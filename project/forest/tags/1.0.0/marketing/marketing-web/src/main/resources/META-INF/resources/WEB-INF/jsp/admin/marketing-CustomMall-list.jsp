<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>商品自定义</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        商品自定义
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            商品自定义列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/merchandiseCustomClassification/toAddCustomMall?merchantId=${merchantId}&classifyId=${classifyId}">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a> 
                             <a title="一键" class="btn btn-sm btn-info multipleSort" >
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                               		一键 排序
                            </a>                            
                        </div>
                    </div>
                          
                        <div class="col-xs-6" style="text-align: right;">
                        <label>
                            <form id="query-form" action="#admin/merchandiseCustomClassification/customMallList" onsubmit="listFormSearch(this); return false">
                            	<div class="dataTables_length">
                            			<input type="hidden" name="merchantId" value="${merchantId}"/>
                            			<input type="hidden" name="classifyId" value="${classifyId}"/>
                               			<input type="search" maxlength="11" class="form-control search-query"
                                           placeholder="商品名称/系统编号" name="name" value="${name}">
                                   		<button type="submit" class="btn btn-purple btn-sm">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                          	  查&nbsp;&nbsp;询
                                        </button>                                        
                                 </div>
                            </form>
                        </label>
                    </div>    
                </div>
                <form id="model-form" action="/admin/merchandiseCustomClassification/multipleSort.do" class="form-horizontal"  role="form" >
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                        <th>系统编号</th>
                        <th>商品名称</th>    
                        <th>商品图片</th>       
                        <th>商品库存</th>
                        <th>排序</th>
                        <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>
					<input name="merchantId" value="${merchantId}" type="hidden" >
					<input name="classifyId" value="${classifyId}" type="hidden" >
                    <tbody>
                           <c:forEach items="${list}" var="item" varStatus="current"> 
                            <tr>            
                                <td>${item.sysCode}</td>
                                <td>${item.name}</td>      
                                <td><img src="${item.image}" width="100" height="100"/></td>              
                                <td>${item.stock}</td>
                                <td>
                                	<input type="hidden" value="${item.id}" name="customList[${current.index}].id" />
	                            	<input type="text" value="${item.orderNum}" name="customList[${current.index}].orderNum" />
                                </td>
                                <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="删除" class="red del-item-new" href="javascript:;"
                                       api-path="/admin/merchandiseCustomClassification/delete.do?id=${item.id}">
                                        <i class="ace-icon fa fa-trash-o bigger-130"></i>
                                    </a>						                                 
                                </div>
	                            </td>
	                        </tr>
							</c:forEach>
                    </tbody>
                </table>
                <div class="space-4"></div>
	            <div class="clearfix form-actions" style="display:none;">
	                <div class="col-md-offset-3 col-md-9">
	                    <button class="btn btn-info hideBtn" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>&nbsp;保&nbsp;存&nbsp;</button>
	                    &nbsp; &nbsp; &nbsp;
	                    <button class="btn" type="button" onclick="window.location.href='#admin/currencyExchange/exchangeList'">
	                    <i class="ace-icon fa fa-undo bigger-110"></i>&nbsp;返&nbsp;回&nbsp;</button>
	                </div>
	            </div> 
                </form>   
                 
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
                                    if(parseInt(data["status"]) === 0){
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
         //表单验证
        $("#model-form").validate({
            errorElement: 'div',
            errorClass: 'help-block col-xs-12 col-sm-reset inline',
            focusInvalid: false,
            rules: {
                stock: {
                    required: true,
                    range: [0, 99999999],
                    digits: true
                },
                orderNums: {
                    required: true,
                    range: [0, 99999999],
                    digits: true
                }
            },

            messages: {},

            highlight: function (e) {
                $(e).closest('.form-group').removeClass('has-success').addClass('has-error');
            },

            success: function (e) {
                $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                $(e).remove();
            },

            errorPlacement: function (error, element) {
                if (element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
                    var controls = element.closest('div[class*="col-"]');
                    if (controls.find(':checkbox,:radio').length > 1) controls.append(error);
                    else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
                }
                else if (element.is('.select2')) {
                    error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
                }
                else if (element.is('.chosen-select')) {
                    error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
                }
                else error.insertAfter(element.parent());
            },

            submitHandler: function (form) {
                postForm('model-form');
            },
            invalidHandler: function (form) {
            }
        });
        $(".multipleSort").click(function(){
        	$(".hideBtn").click();
        });
    });
</script>
