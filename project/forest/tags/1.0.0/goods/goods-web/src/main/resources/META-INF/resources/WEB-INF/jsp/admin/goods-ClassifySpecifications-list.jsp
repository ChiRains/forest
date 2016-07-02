<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>

<title>规格管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        规格管理
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
            规格列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                        <!--    <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/classifySpecifications/toAdd?classifyId=${classifyId}">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>  -->
                            
                             <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/classifySpecifications/toAddClassifySpec?classifyId=${classifyId}">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>
                        </div>
                    </div>
                    <div class="col-xs-6" style="text-align: right;">
                        <a href="#admin/classifyAttribute/list" class="btn btn-sm btn-info">&ensp;返&emsp;回&ensp;</a>
                    </div>
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">
                        <th>序号</th>
                        <th>属性</th>
                        <th>显示图片</th>
                        <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${result}" var="item" varStatus="current">
                        <tr>
                            <td>${current.index+1}</td>
                            <td>${item.attributeStr}</td>
                            <td>${item.uploadImageStr}</td>
                            <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="修改基本信息" class="green"
                                       href="#admin/classifySpecifications/toEditClassifySpec?id=${item.id}">
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                    </a>
                                    
                               <%--      <a title="修改基本信息" class="green"
                                       href="#admin/classifySpecifications/toEdit?id=${item.id}">
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                    </a> --%>
                                    <%--<a title="删除" class="red del-item-new" href="javascript:;"--%>
                                       <%--api-path="/admin/gradeType/delete.do?id=${item.id}">--%>
                                        <%--<i class="ace-icon fa fa-trash-o bigger-130"></i>--%>
                                    <%--</a>--%>
                                </div>
                                <div class="hidden-md hidden-lg">
                                    <div class="inline position-relative">
                                        <button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown"
                                                data-position="auto">
                                            <i class="ace-icon fa fa-caret-down icon-only bigger-120"></i>
                                        </button>
                                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                            <li>
                                                <a href="#admin/classifySpecifications/toEdit?id=${item.id}"
                                                   class="tooltip-success" data-rel="tooltip" title=""
                                                   data-original-title="Edit">
                                                <span class="green">
                                                <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                </span>
                                                </a>
                                            </li>
                                            <%--<li>--%>
                                                <%--<a href="javascript:;" class="tooltip-error del-item-new"--%>
                                                   <%--data-rel="tooltip" title="" data-original-title="Delete"--%>
                                                   <%--api-path="/admin/gradeType/delete.do?id=${item.id}">--%>
                                                <%--<span class="red">--%>
                                                <%--<i class="ace-icon fa fa-trash-o bigger-120"></i>--%>
                                                <%--</span>--%>
                                                <%--</a>--%>
                                            <%--</li>--%>
                                        </ul>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <%--<div class="row">--%>
                    <%--<div class="col-xs-12">--%>
                        <%--<%@include file="../page.inc.jsp" %>--%>
                    <%--</div>--%>
                <%--</div>--%>
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
                    id: 'btn-1',
                    label: '确定',
                    cssClass: 'btn btn-primary',
                    action: function (dialogItself) {
                        $.get(delUrl, {},
                                function (data) {
                                    data = JSON.parse(data);
                                    if (parseInt(data["status"]) === 0) {
                                        dialogItself.setTitle('删除失败');
                                        dialogItself.setMessage(data["message"]);
                                        dialogItself.setType(BootstrapDialog.TYPE_DANGER);
                                        dialogItself.getButton('btn-1').remove();
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
