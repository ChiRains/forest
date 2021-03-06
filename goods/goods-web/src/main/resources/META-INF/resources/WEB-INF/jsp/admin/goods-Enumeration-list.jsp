<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>

<title>商品规格管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        商品规格管理
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
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/enumeration/toAdd">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>
                        </div>
                    </div>
                    <div class="col-xs-6" style="text-align: right;">
                        <a href="#admin/attributeDefinition/list" class="btn btn-sm btn-info">&ensp;返&emsp;回&ensp;</a>
                    </div>
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">
                        <th>名称</th>
                        <th>值</th>
                        <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${result}" var="item" varStatus="current">
                        <tr>
                            <td>${item.name}</td>
                            <td>${item.value}</td>
                            <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="修改" class="green"
                                       href="#admin/enumeration/toEdit?name=${item.name}">
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

    });
</script>
