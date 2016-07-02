<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>

<title>门店管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        门店管理
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
            门店列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/store/toAdd">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>
                        </div>
                    </div>
                    <div class="col-xs-6" style="text-align: right;">
                        <label>
                            <form id="query-form" action="#admin/store/list" onsubmit="listFormSearch(this); return false">
                            	<div class="dataTables_length">
                               			<input type="search" maxlength="11" class="form-control search-query"
                                           placeholder="名称" name="name" value="${query.name}">
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
                        <th>地址</th>
                        <th>电话</th>
                        <th>店员管理</th>
                        <th>配送区域</th>      
                        <th>配送时间</th>          
                        <th>是否启用</th>       
                        <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${result}" var="item" varStatus="current">
                        <tr>
                            <td>${item.name}</td>
                            <td>${item.province} ${item.city} ${item.district} ${item.address}</td>
                            <td>${item.phone}</td>
                            <td>
                                <a href="#admin/storeMember/list?storeId=${item.id}" class="btn btn-primary btn-xs"><i
                                        class="ace-icon fa fa-users bigger-130"></i>
                                    店员管理</a>
                            </td>
                            <td>
                                <a href="#admin/storeDeliveryRange/toEditByStore?storeId=${item.id}" class="btn btn-primary btn-xs"><i
                                        class="ace-icon fa fa-users bigger-130"></i>
                                   		 配送区域</a>
                            </td>
                             <td>
                                <a href="#admin/storeDeliveryTime/toEditByStore?storeId=${item.id}" class="btn btn-primary btn-xs"><i
                                        class="ace-icon fa fa-users bigger-130"></i>
                                   		 配送时间</a>
                            </td>
                            <td>
                            	<input value="${item.enable}" type="checkbox" data-id="${item.id}" class="ace ace-switch ace-switch-5 ajax_switch" 
                            	<c:if test="${item.enable eq 1}">checked</c:if>>
								<span class="lbl middle"></span>
                            </td>
                            <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="修改基本信息" class="green"
                                       href="#admin/store/toEdit?id=${item.id}">
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
        $(".ajax_switch").on('change',function(){
            var el = $(this);
            var data = {
                id:el.attr('data-id'),
                value:el[0].checked?'1':'0'
            };
            $.ajax({
                url:'/admin/store/enable.do',
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
    });
</script>
