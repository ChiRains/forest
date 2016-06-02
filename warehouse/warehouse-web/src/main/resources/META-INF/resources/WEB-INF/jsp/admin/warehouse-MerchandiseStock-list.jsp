<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>

<title>库存管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        库存管理
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
            列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/merchandiseStock/edit">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>
                        </div>
                    </div>
                    <%--<div class="col-xs-6" style="text-align: right;">--%>
                        <%--<a href="#admin/allocationScheme/list?schemeId=${commissionScheme.id}"--%>
                           <%--class="btn btn-sm btn-info">&ensp;返&emsp;回&ensp;</a>--%>
                    <%--</div>--%>
                    <div class="col-xs-6" style="text-align: right;">
                        <label>
                            <form id="query-form" action="#admin/merchandiseStock/list" onsubmit="listFormSearch(this); return false">
                            	<div class="dataTables_length">
                               			<input type="search" class="form-control search-query"
                                           placeholder="请输入搜索内容" name="merchandiseName" value="${query.merchandiseName}">
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

                        <th>名称</th>
                        <th>进货价</th>
                        <th>折扣价</th>
                        <th>原价</th>
                        <th>库存</th>
                        <th>规格</th>
                        <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${result}" var="item" varStatus="current">
                        <tr>

                            <td>${item.merchandiseName}</td>
                            <td>${item.purchase}</td>
                            <td>${item.discount}</td>
                            <td>${item.price}</td>
                            <td>${item.stock}</td>
                            <td>${item.specifications}</td>
                            <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="修改" class="green"
                                       href="#admin/merchandiseStock/edit?id=${item.id}">
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                    </a>
                                    <a title="删除" class="red del-item-new" href="javascript:;"
                                       api-path="/admin/merchandiseStock/delete.do?id=${item.id}">
                                        <i class="ace-icon fa fa-trash-o bigger-130"></i>
                                    </a>
                                </div>
                                <div class="hidden-md hidden-lg">
                                    <div class="inline position-relative">
                                        <button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown"
                                                data-position="auto">
                                            <i class="ace-icon fa fa-caret-down icon-only bigger-120"></i>
                                        </button>
                                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                            <li>
                                                <a href="#admin/merchandiseStock/edit?id=${item.id}"
                                                   class="tooltip-success" data-rel="tooltip" title=""
                                                   data-original-title="Edit">
                                                <span class="green">
                                                <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                </span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;" class="tooltip-error del-item-new"
                                                   data-rel="tooltip" title="" data-original-title="Delete"
                                                   api-path="/admin/merchandiseStock/delete.do?id=${item.id}">
                                                <span class="red">
                                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                </span>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
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
                                    if (parseInt(data["status"]) === 0) {
                                        dialogItself.setTitle('删除失败');
                                        dialogItself.setMessage(data["message"]);
                                        dialogItself.setType(BootstrapDialog.TYPE_WARNING);
                                    } else {
                                        dialogItself.setTitle('成功');
                                        dialogItself.setMessage("删除成功！");
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
    });
</script>
