<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>要货单</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        要货单
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            要货单列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/stockState/toAdd">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                申请货单
                            </a>
                        </div>
                    </div>
                     <div class="col-xs-6" style="text-align: right;">
                        <label>
                            <form id="query-form" action="#admin/merchandiseStockState/list" onsubmit="listFormSearch(this); return false">
                                <div class="input-group">
                                		状态
                                    	<select id="state" name="state">
                                    		<option value="0">请选择一个状态</option>
                                    		<c:forEach items="${stockStateType}" var="stockStateType" varStatus="current">
	                                    		<option value="${stockStateType.key}"
	                                    		<c:if test="${query.state eq stockStateType.key}">
		                                    		selected
	                                    		</c:if>>${stockStateType.name}</option>
                                    		</c:forEach>
                                    	</select>
                                    	&nbsp;&nbsp;
                                        <button id="search-button" type="submit" class="btn btn-purple btn-sm">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            搜索
                                        </button>
                                    </span>
                                </div>
                            </form>
                        </label>
                    </div>
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">
                           	<th>序号</th>
                           	<th>申请时间</th>
                            <th>出货门店</th>
                            <th>商品名称</th>
                            <th>商品规格</th>
                            <th>申请数量</th>
                            <th>状态</th>
                            <th class="sorting_disabled">签收货单</th>
                            <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>
                                <td>${current.index}</td>
                            	<td><fmt:formatDate value="${item.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td>
                                	<c:forEach items="${storeList}" var="store" varStatus="current">
	                                	<c:if test="${store.id eq item.adminStockStateVO.formStoreId}">
	                                		${store.name}
	                                	</c:if>
                                	</c:forEach>
                                </td>                         
                                <td>${item.qUnifiedMerchandise.list.get(0).name}</td>
                                <td>${item.qUnifiedMerchandise.list.get(0).specifications}</td>
                                <td>${item.number}</td>
                                <td>
                                	<c:if test="${item.state eq 1}">
										<span class="label label-info arrowed-in-right arrowed">申请</span>
                                	</c:if>
                                	<c:if test="${item.state eq 2}">
										<span class="label label-danger arrowed-in">待签收</span>
                                	</c:if>
                                	<c:if test="${item.state eq 3}">
										<span class="label label-success arrowed">已签收</span>
                                	</c:if>
                                	&nbsp;&nbsp;
								</td>
                            	<td>
                            		<c:if test="${item.state eq 2}">
                            			<button id="sign" type="button" class="btn btn-white btn-success"
                            			path="/admin/merchandiseStockState/sign.do?id=${item.id}">
                            				签收
                            			</button>
                            		</c:if>
                            	</td>
                                <td>
	                                <c:if test="${item.state eq 1}">
		                                <div class="hidden-sm hidden-xs action-buttons">
		                                    <a title="修改基本信息" class="green" 
		                                       href="#admin/merchandiseStockState/toEdit?id=${item.id}">
		                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
		                                    </a>
		                                    <a title="删除" class="red del-item-new" href="javascript:;"
		                                       api-path="/admin/merchandiseStockState/delete.do?id=${item.id}">
		                                        <i class="ace-icon fa fa-trash-o bigger-130"></i>
		                                    </a>                                 
		                                </div>
	                                </c:if>
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
        
        $('#sign').on('click', function () {
       		var url = $(this).attr('path');
       		BootstrapDialog.show({
                title: '签收货单？',
                message: '确定执行此操作！',
                buttons: [{
                    label: '确定',
                    cssClass: 'btn btn-primary',
                    action: function (dialogItself) {
                        $.get(url, {},
                            function (data) {
                                data = JSON.parse(data);
                                if(parseInt(data["status"]) === 0){
                                    dialogItself.setTitle('操作失败');
                                    dialogItself.setMessage(data["message"]);
                                    dialogItself.setType(BootstrapDialog.TYPE_WARNING);
                                }else{
                                    dialogItself.setTitle('成功');
                                    dialogItself.setMessage("操作成功！");
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
    });
</script>
