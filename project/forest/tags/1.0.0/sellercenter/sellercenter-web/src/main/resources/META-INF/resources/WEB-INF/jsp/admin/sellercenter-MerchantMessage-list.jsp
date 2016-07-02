<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>

<title>消息中心管理</title>
<style>
    .select-product-dialog {
        top: 50px;
    }

    .select-product-dialog tr {

        word-break: break-all;
    }

    .select-product-dialog .modal-content {
        min-height: 300px;
        min-width: 500px;
    }

    /*.select-dialog .modal-body{*/
    /*padding: 0;*/
    /*}*/
</style>
<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        消息中心管理
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
            消息列表列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            
                        </div>
                    </div>
                     <div class="col-xs-6">
	                    <div class="text-right">
	                        <label>
	                            <form action="#admin/merchantMessage/list" onsubmit="listFormSearch(this); return false">
	                                <div class="dataTables_length">
	                                	  <select class="form-control" id="clasify" name="clasify" style="width:50">	  
                                    			<option value="-1">请选择分类</option>                                      	
	                                         	<c:forEach items="${classifyList}" var="item" varStatus="current"> 
			                                   		 <option value="${item.key}" ${item.message}>${item.value}</option>			                                   		
			                                   	</c:forEach> 
			                              </select>
				                      		&nbsp;&nbsp;
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
                        <th>标题</th>
                        <th>发送时间</th>
                        <th>状态(未读，已读)</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${result}" var="item" varStatus="current">
                        <tr>
                            <td><div class="col-xs-6" style="text-align: right;">
                                  <div class="dataTables_length">
                             <a class="btn btn-sm btn-info get-message" data-id="${item.id}">${item.title}</a>                         
                            </div>
                    </div>   </td>
                            <td>${item.time}</td>
                            <td>
                            <span>
                            <c:if test='${item.read eq true}'>已读</c:if>
                           <c:if test='${item.read eq false}'>未读</c:if>
                           </span>
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
         $(".get-message").on("click", function () {
     		// 务必添加解绑事件，要不然会重复提交数据
     		$(document).off('click');
     		 var id = $(this).attr("data-id");
            BootstrapDialog.show({   
                title:"查看消息",                             
                message: $('<div></div>').load('/admin/merchantMessage/toShow.do?id='+id),
                cssClass: "select-product-dialog",
                onshow: function (dialog) {
                
                 }
	                });
	              });  
               
        });
</script>
