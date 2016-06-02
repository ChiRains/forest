<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>

<title>商家管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        商家管理
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
            商家列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/merchant/toAdd">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>                       
                            <a title="导入" class="btn btn-sm btn-info"
                               href="#admin/merchant/toImport">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                导&nbsp;入
                            </a>
                        </div>
                    </div>
                    <div class="col-xs-6" style="text-align: right;">
                        <label>
                            <form id="query-form" action="#admin/merchant/list" onsubmit="listFormSearch(this); return false">
                                <div class="input-group">
                                    <input type="search" maxlength="11" class="form-control search-query"
                                           placeholder="商家名称/主打产品" name="name" value="${query.name}">
                                    <span class="input-group-btn">
                                        <button id="search-button" type="submit" class="btn btn-purple btn-sm">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            搜索
                                        </button>
                                    </span>
                                </div>
                                 <div class="input-group">
                                    <input type="search" maxlength="11" class="form-control search-query"
                                           placeholder="编码" name="code" value="${query.code}">
                                    <span class="input-group-btn">
                                        <button id="search-button" type="submit" class="btn btn-purple btn-sm">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            搜索编码
                                        </button>
                                    </span>
                                </div>
                            </form>
                        </label>
                    </div>
                </div>
                <div class="">
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">
                        <th>编码</th>
                        <th>名称</th>
                        <th>分类</th>
                        <th>主打产品</th>
                        <th>商品审核</th>
                        <th>参与分销</th>
                        <th>成交扣点</th>
                        <th>职员管理</th>
                        <th>分类管理</th>
                        <th>规格管理</th>
                        <th>是否禁用商家</th>
                        <th>是否需要短信通知</th>
                        <th>管理员</th>
                        <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${result}" var="item" varStatus="current">
                        <tr>
                            <td>${item.code}</td>
                            <td>${item.name}</td>
                            <td>${item.classifyStr}</td>
                            <td>${item.flagship}</td>
                            <td>${item.commodityAuditingStr}</td>
                            <td>${item.distributionStr}</td>
                            <td>${item.buckle}</td>
                            <td>
                                <a href="#admin/merchantMember/list?merchantId=${item.id}" class="btn btn-primary btn-xs">
                                <i class="ace-icon fa fa-users bigger-130"></i> 职员管理</a>
                            </td>
                            <td>
                                <a href="#admin/merchantMerchandiseClassify/list?merchantId=${item.id}" class="btn btn-primary btn-xs">
                                <i class="ace-icon fa fa-users bigger-130"></i> 分类管理</a>
                            </td>
                            <td>
                                <a href="#admin/merchantSpecClassify/list?merchantId=${item.id}" class="btn btn-primary btn-xs">
                                <i class="ace-icon fa fa-users bigger-130"></i> 规格管理</a>
                            </td>
                            <td>
                                <input value="${item.id}" <c:if test="${item.state==1}"> checked </c:if>  type="checkbox" data-id="${item.id}" class="ace ace-switch ace-switch-5 ajax_switch" >
                                <span class="lbl middle"></span>
                            </td>
                            <td>
                                <input value="${item.id}" <c:if test="${item.notify==1}"> checked </c:if>  type="checkbox" data-id="${item.id}" class="ace ace-switch ace-switch-5 ajax_switch1" >
                                <span class="lbl middle"></span>
                            </td>
                            <td>${item.admin}</td>
                             
                            <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="修改基本信息" class="green"
                                       href="#admin/merchant/toEdit?id=${item.id}&queryStr=${queryStr}">
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                    </a>
                                     <a title="详情" class="green"
                                       href="#admin/merchant/details4Admin?id=${item.id}">
                                        <i class="ace-icon fa fa-cog bigger-130"></i>
                                    </a>
                                    <%--<a title="设置商家店长" class="green"--%>
                                       <%--href="#admin/merchant/toAddMember4Admin?merchantId=${item.id}">--%>
                                        <%--<i class="ace-icon fa fa-cog bigger-130"></i>--%>
                                    <%--</a>--%>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </div>
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
            var url=el[0].checked?"/admin/merchant/enableMerchant.do":"/admin/merchant/disableMerchant.do";
            $.ajax({
                url:url,
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
                    }else{
                    	 setTimeout(function () {
                            location.reload();
                        }, 1500);
                    }
                }
            })
        })  
        
        $(".ajax_switch1").on('change',function(){
            var el = $(this);
            var data = {
                id:el.attr('data-id'),
                value:el[0].checked?'1':'0'
            };
            var url=el[0].checked?"/admin/merchant/notifyMerchant.do":"/admin/merchant/unNotifyMerchant.do";
            $.ajax({
                url:url,
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
                    }else{
                    	 setTimeout(function () {
                            location.reload();
                        }, 1500);
                    }
                }
            })
        })  

    });
</script>
