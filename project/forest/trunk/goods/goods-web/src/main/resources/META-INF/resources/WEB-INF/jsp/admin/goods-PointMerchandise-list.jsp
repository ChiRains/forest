<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>管理员管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        管理员管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            管理员列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/pointMerchandise/toAdd">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>                           
                        </div>
                    </div>                    
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
	                        <th>名称</th>           
	                        <th>唯一编码</th>           
	                        <th>规格</th>        
	                        <th>状态</th>
	                        <th>积分兑换价</th>
	                        <th>额外现金价</th>
	                        <th>进货价</th>
	                        <th>原价</th>
	                        <th>库存</th>
	                        <th>合计</th>
	                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                            	<td>${item.name}</td>
                                <td>${item.code}</td>
                                <td>${item.specifications}</td>                 
                                <td>  
                                	<c:if test="${item.state==4}"><span class="label label-success arrowed">上线</span></c:if>
	                            	<c:if test="${item.state==5}"><span class="label label-danger arrowed-in">下线</span></c:if>
                                </td>  
                                <td>${item.integral}</td>
                                <td>${item.discount}</td>
                                <td>${item.purchase}</td>
                                <td>${item.price}</td>
                                <td>${item.stock}</td> 
                                <td>${item.integral}积分+ ￥ ${item.discount} 元</td>
                                <td>
                                	<c:if test="${item.state==4}">
	                            		<a class="btn btn-sm btn-danger offline" api-path="/admin/merchandise/offShelf.do?unifiedMerchandiseId=${item.id}">下线
	                            		</a>
		                             </c:if>
		                             <c:if test="${item.state==5}">
	                            		<a class="btn btn-sm btn-success online" api-path="/admin/merchandise/shelves.do?unifiedMerchandiseId=${item.id}">上线
	                            		</a>
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
                                    if (parseInt(data["status"]) !=200 ) {
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
