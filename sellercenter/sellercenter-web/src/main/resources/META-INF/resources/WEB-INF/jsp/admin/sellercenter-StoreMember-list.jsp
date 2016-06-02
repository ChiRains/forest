<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>

<title>门店职员管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        门店职员管理
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
            门店职员列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/storeMember/toAdd?storeId=${query.storeId}">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>
                        </div>
                    </div>
                    <div class="col-xs-6" style="text-align: right;">
                        <a href="#admin/store/list?storeId=${query.storeId}" class="btn btn-sm btn-info">&ensp;返&emsp;回&ensp;</a>
                    </div>
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">
                        <th>账号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>昵称</th>
                        <th>手机号</th>
                        <th>QQ</th>
                        <th>状态</th>
                        <th>重置密码</th>
                        <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${result}" var="item" varStatus="current">
                        <tr>
                            <td>${item.account}</td>
                            <td>${item.name}</td>
                            <td>${item.sexStr}</td>
                            <td>${item.nickname}</td>
                            <td>${item.mobile}</td>
                            <td>${item.qq}</td>
                            <td>${item.enable > 0 ?"在职":"离职"}</td>
                            <td>
                                <a class="btn btn-xs btn-info reset-pwd" api-path="/admin/storeMember/resetPwd.do?id=${item.id}">重置密码</a>
                            </td>
                            <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="修改" class="green"
                                       href="#admin/storeMember/toEdit?id=${item.id}">
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
        $('.reset-pwd').on('click', function () {
            var delUrl = $(this).attr('api-path');
            BootstrapDialog.show({
                title: '确认重置密码？',
                message: '重置以后密码将会恢复默认，请尽快登陆修改！',
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
//                                        setTimeout(function () {
//                                            location.reload();
//                                        }, 1500);
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
