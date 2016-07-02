<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>商城自定义</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        商城自定义
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            商城自定义列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/merchandiseCustomClassification/toAddClassifyForMallCustom">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>                           
                        </div>
                    </div>
                    <div class="col-xs-6">
	                    <div class="text-right">
	                        <label>
	                            <form action="#admin/merchandiseCustomClassification/mallList" onsubmit="listFormSearch(this); return false">
	                               <div class="dataTables_length">
	                               			分类
	                               			<select id="classifyId" name="classifyId" style="width:140px;">
	                               				<option value="">请选择分类</option>
	                               				<c:forEach items="${classifyMap}" var="map" varStatus="current">
	                               					<option value="${map.key}"
	                               					<c:if test="${map.key eq query.classifyId}">
	                               						selected
					                				</c:if>>${map.value}</option>
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
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                        <th>分类定义</th>
                        <th>商品编号</th>
                        <th>商品名称</th>           
                        <th>商品规格</th>           
                        <th>商品库存</th>
                        <th>排序</th>
                        <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                <td>${classifyMap[item.customClassifyId]}</td>                         
                                <td>${item.unifiedMerchandiseId}</td>
                                <td>${item.qUnifiedMerchandise.list[0].name}</td>                    
                                <td>${item.qUnifiedMerchandise.list[0].specifications}</td>                         
                                <td>${item.qUnifiedMerchandise.stock}</td>
                                <td>${item.orderNum}</td>
                                <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="删除" class="red del-item-new" href="javascript:;"
                                       api-path="/admin/merchandiseCustomClassification/deleteMall.do?id=${item.id}">
                                        <i class="ace-icon fa fa-trash-o bigger-130"></i>
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
    });
</script>
