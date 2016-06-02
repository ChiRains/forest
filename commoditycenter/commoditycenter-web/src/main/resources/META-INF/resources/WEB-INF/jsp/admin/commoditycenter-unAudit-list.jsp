<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>

<title>商品管理</title>
<style>
	.select-product-dialog {
        top: 50px;
    }

    .select-product-dialog tr {

        word-break: break-all;
    }

    .select-product-dialog .modal-content {
        min-height: 600px;
        min-width: 800px;
    }
</style>
<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        商品管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            未审核列表
        </small>
    </h1>
</div>
<!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            商品列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                   <div class="col-xs-6">
                            <div class="dataTables_length">
                                <a title="批量通过" id="selectAudit" class="btn btn-sm btn-info"
                                   api-path="/admin/merchandise/onlineSelected.do">
                                    <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                               批量通过
                                </a>
                            </div>
                    </div> 
                    <div class="col-xs-6" style="text-align: right;">
                        <label>
                                <div class="input-group">
                                    <input style="display:none;"type="search" maxlength="11" class="form-control search-query"
                                           placeholder="" name="merchantId" value="${merchantId}">
                                    <input type="search" readonly maxlength="11" class="form-control search-query"
                                           placeholder="" name="merchantName" value="${merchantName}">
                                    <span class="input-group-btn">
                                        <button id="search-button" type="submit" class="btn btn-purple btn-sm">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                          		 选择商家
                                        </button>
                                        <a href="#admin/merchandise/list4UnAudit">
                                        <button id="search-button2" type="button" class="btn btn-green btn-sm">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                          		 取消选择
                                        </button>
                                        </a>
                                    </span>
                                </div>
                        </label>
                    </div>
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">
                    	<th>勾选</th>
                        <th>商品编号</th>
                        <th>名称</th>
                        <th>关键字</th>
                        <th>状态</th>
                        <th>单位</th>
                        <th>商城分类</th>
                        <th>分类</th>
                        <%--<th>进货价</th>--%>
                        <%--<th>折扣价</th>--%>
                        <%--<th>原价</th>--%>
                        <%--<th>库存</th>--%>
                        <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${result}" var="item" varStatus="current">
                        <tr>
                        	<td><input type="checkbox" name="mIds" value="${item.id}"></td>
                            <td>${item.code}</td>
                            <td>${item.name}</td>
                            <td>${item.keywords}</td>
                            <td><!--${item.state}-->待审核</td>
                            <td>${item.unit}</td>
                            <td>${item.mallClassifyStr}</td>
                            <td>${item.merchantClassifyStr}</td>
                                <%--<td>${item.purchase}</td>--%>
                                <%--<td>${item.discount}</td>--%>
                                <%--<td>${item.price}</td>--%>
                                <%--<td>${item.stock}</td>--%>
                            <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="审核通过" class="green audit" 
                                    	api-path="/admin/merchandise/online.do?id=${item.id}">
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                    </a>
                                    
                                     <a title="审核不通过" class="red notAudit"
                                     	api-path="/admin/merchandise/notAudited.do?id=${item.id}">
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
		$('.audit').on('click', function () {
            var auditURL = $(this).attr('api-path');
            BootstrapDialog.show({
                title: '确认审核通过？',
                message: '确认审核通过！',
                buttons: [{
                    id: 'btn-1',
                    label: '确定',
                    cssClass: 'btn btn-primary',
                    action: function (dialogItself) {
                        $.get(auditURL, {},
                                function (data) {
                                    data = JSON.parse(data);
                                    if (parseInt(data["status"]) === 0) {
                                        dialogItself.setTitle('操作失败');
                                        dialogItself.setMessage(data["message"]);
                                        dialogItself.setType(BootstrapDialog.TYPE_DANGER);
                                        dialogItself.getButton('btn-1').remove();
                                    } else {
                                        dialogItself.setTitle('成功');
                                        dialogItself.setMessage("操作成功！");
                                        dialogItself.setType(BootstrapDialog.TYPE_SUCCESS);
                                        setTimeout(function () {
                                            dialogItself.close();
                                        }, 1000);
                                        setTimeout(function () {
                                            location.reload(true);
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
        $('.notAudit').on('click', function () {
            var auditURL = $(this).attr('api-path');
            BootstrapDialog.show({
                title: '确认拒绝审核通过？',
                message: '确认拒绝审核通过！',
                buttons: [{
                    id: 'btn-1',
                    label: '确定',
                    cssClass: 'btn btn-primary',
                    action: function (dialogItself) {
                        $.get(auditURL, {},
                                function (data) {
                                    data = JSON.parse(data);
                                    if (parseInt(data["status"]) === 0) {
                                        dialogItself.setTitle('操作失败');
                                        dialogItself.setMessage(data["message"]);
                                        dialogItself.setType(BootstrapDialog.TYPE_DANGER);
                                        dialogItself.getButton('btn-1').remove();
                                    } else {
                                        dialogItself.setTitle('成功');
                                        dialogItself.setMessage("操作成功！");
                                        dialogItself.setType(BootstrapDialog.TYPE_SUCCESS);
                                        setTimeout(function () {
                                            dialogItself.close();
                                        }, 1000);
                                        setTimeout(function () {
                                            location.reload(true);
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
    ace.load_ajax_scripts(scripts, function () {
	    jQuery(function ($) {
	            $("#search-button").on("click", function () {
	                BootstrapDialog.show({
	                    title: "需要审核商品的商家列表",
	                    message: $('<div ></div>').load('/admin/merchant/listNeedAudit.do?pageNum=1'),
	                    cssClass: "select-product-dialog",
	                    onshow: function (dialog) {
	                        $(document).off("click", ".select-product-dialog a,.search-button");
	                        $(document).on("click", ".select-product-dialog a,.search-button", function (e) {
	                            e.preventDefault();
	                            var obj = $(this);
	                            //console.log(obj);
	                            //查询按钮
	                            if (obj.hasClass("search-button")) {
	                                var form = obj.parents("form").eq(0);
	                                if (form) {
	                                    var url = form.prop("action");
	                                    var fieldArray = form.serializeArray();
	                                    var query_str = "";
	                                    $.each(fieldArray, function (i, data) {
	                                        if ($.trim(data['value'])) {
	                                            query_str += (query_str == '') ? '?' + data['name'] + '=' + data['value'] : '&' + data['name'] + '=' + data['value'];
	                                        }
	                                    });
	                                    url && dialog.getModalBody().html($('<div></div>').load(url + query_str));
	                                }
	                                return false;
	                            }
								
	                            if (obj.hasClass("add-btn")) {
	                                var id = obj.attr("data-id");
	                                var name = obj.attr("data-name");
	                                //var specifications = obj.attr("data-specifications");
	                                //var stock = obj.attr("data-stock");
	                                if (id ) {
	                                    dialog.close();
	                                    window.location.href="#admin/merchandise/list4UnAudit?pageNum=1&merchantId="+id+"&merchantName="+name;
	                                	//var sbURL="#admin/merchandise/list4UnAudit?pageNum=1&merchantId="+id+"&merchantName="+name;
	                                	//$("#fm1").attr("action",sbURL);
	                                	//$("#fm1").submit();
	                                }
	                                return false;
	                            }
	
	                            var url = obj.attr("href").replace(/#/, "/");
	                            if (url != "javascript:;" && url) {
	                                dialog.getModalBody().html($('<div></div>').load(url));
	                                return false;
	                            }
	
	                            return false;
	                        });
	                    }
	                });
	            });
	         });
	         //勾选之后出现删除按钮
	         $('#selectAudit').on('click', function () {
            var auditURL = $(this).attr('api-path');
            var ids="";
            $("input[name='mIds']:checkbox:checked").each(function(){ 
				//alert($(this).val()); 
				ids+=$(this).val()+","
			}) 
			if(ids==""){
				BootstrapDialog.show({
					 title: '非法操作',
               		 message: '请先进行勾选操作！',
				});
				return ;
			}
			auditURL+="?ids="+ids;
            BootstrapDialog.show({
                title: '确认批量审核通过？',
                message: '确认批量审核通过！',
                buttons: [{
                    id: 'btn-1',
                    label: '确定',
                    cssClass: 'btn btn-primary',
                    action: function (dialogItself) {
                        $.get(auditURL, {},
                                function (data) {
                                    data = JSON.parse(data);
                                    if (parseInt(data["status"]) === 0) {
                                        dialogItself.setTitle('操作失败');
                                        dialogItself.setMessage(data["message"]);
                                        dialogItself.setType(BootstrapDialog.TYPE_DANGER);
                                        dialogItself.getButton('btn-1').remove();
                                    } else {
                                        dialogItself.setTitle('成功');
                                        dialogItself.setMessage("操作成功！");
                                        dialogItself.setType(BootstrapDialog.TYPE_SUCCESS);
                                        setTimeout(function () {
                                            dialogItself.close();
                                        }, 1000);
                                        setTimeout(function () {
                                            location.reload(true);
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
