<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>

<title>商品管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        商品管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
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
                        <c:if test="${query.merchantId > 0}">
                            <div class="dataTables_length">
                                <a title="新增" class="btn btn-sm btn-info"
                                   href="#admin/merchandise/toAdd?merchantId=${query.merchantId}">
                                    <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                    新&nbsp;增
                                </a>
                            </div>
                        </c:if>
                    </div>
                    <div class="col-xs-6" style="text-align: right;">
                        <label>
                            <form id="query-form" action="#admin/merchandise/list" onsubmit="listFormSearch(this); return false">
                            	<div class="dataTables_length">
                               			<input type="search" maxlength="40" class="form-control search-query"
                                           placeholder="名称/系统编号/编号" name="name" value="${query.name}">
                                        <select class="form-control" id="specClassifyId" name="specClassifyId" style="width:140px">
				                            <option value="-1" selected>请选择规格</option>
				                            <c:forEach items="${specClassifyId}" var="item" varStatus="current">
				                                <option value="${item.key}" 
				                                <c:if test="${item.key eq query.specClassifyId}">selected</c:if>>${item.value}</option>
				                            </c:forEach>
				                        </select>
	                                    <select class="form-control" id="merchantClassifyId" name="merchantClassifyId" style="width:140px">
				                            <option value="-1" selected>请选择分类</option>
				                            <c:forEach items="${merchantClassifyList}" var="item" varStatus="current">
				                                <option value="${item.key}"
				                                <c:if test="${query.merchantClassifyId eq item.key}">
					                                selected
				                                </c:if>>${item.value}</option>
				                            </c:forEach>
				                        </select>
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
                    	<th>系统编码</th>
                        <th>商品编号</th>
                        <th>名称</th>
                        <th>缩略图</th>
                        <th>关键字</th>
                        <th width="120px">状态</th>
                        <th width="60px">单位</th>
                        <th>规格</th>
                        <th>商城分类</th>                        
                        <th>分类</th>
                        <th>规格设置</th>
                        <th>属性设置</th>
                        <th class="sorting_disabled" width="100">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${result}" var="item" varStatus="current">
                        <tr>
                        	<td>${item.sysCode}</td>
                            <td>
                            <c:choose>
                            	<c:when test="${fn:length(item.code) > 15}">
                            		${fn:substring(item.code, 0, 15)}...
                            	</c:when>
                            	<c:otherwise>
                            		${item.code}
                            	</c:otherwise>
                            </c:choose>
                            </td>
                            <td>${item.name}</td>
                            <td><img height="50" width="80" src="${item.image}"/></td>
                            <td>${item.keywords}</td>
                            <td>
                            <c:if test="${item.state==2}">新增 &nbsp;<a class="btn btn-sm btn-info toAudit" api-path="/admin/merchandise/toAuditid.do?id=${item.id}">提交审核</a></c:if>
                            <c:if test="${item.state==3}">待审核&nbsp;</c:if>
                            <c:if test="${item.state==4}"><span class="label label-success arrowed">上线</span>&nbsp;<a class="btn btn-sm btn-danger offline" api-path="/admin/merchandise/offline.do?id=${item.id}">下线</a></c:if>
                            <c:if test="${item.state==5}"><span class="label label-danger arrowed-in">下线</span>&nbsp;<a class="btn btn-sm btn-success online" api-path="/admin/merchandise/online.do?id=${item.id}">上线</a></c:if>
                          
                            </td>
                            <td>${item.unit}</td>
                            <td>${item.specClassifyStr}</td>
                            <td>${item.mallClassifyStr}</td>
                            <td>${item.merchantClassifyStr}</td>
                            <td>
                                <a class="btn btn-xs btn-info" href="#admin/merchandise/toEditSpec?id=${item.id}">
                                    规格设置
                                </a>
                            </td>
                            <td>
                                 <a title="属性设置" class="btn btn-xs btn-info"
                                       href="#admin/merchandise/toEditAttributes?id=${item.id}">
                                       属性设置
                                    </a>
                            </td>
                            <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="编辑" class="green"
                                       href="#admin/merchandise/toEdit?id=${item.id}&pageNum=${pageNum}">
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
		$('.toAudit').on('click', function () {
            var auditURL = $(this).attr('api-path');
            BootstrapDialog.show({
                title: '提交审核？',
                message: '确认提交审核！',
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
        $('.offline').on('click', function () {
            var auditURL = $(this).attr('api-path');
            BootstrapDialog.show({
                title: '确认下线？',
                message: '确认商品下线！',
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
        $('.online').on('click', function () {
            var auditURL = $(this).attr('api-path');
            BootstrapDialog.show({
                title: '确认上线？',
                message: '确认商品上线！',
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
