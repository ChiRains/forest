
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>

<title>商品价格管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        商品价格管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-lg-12">
     	<div class="table-header">
      	商品价格列表
        </div>
        <div>
			<div class="dataTables_wrapper form-inline no-footer">
           		
		        <div class="row">
                    <!-- <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/merchandiseItem/toAdd">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>                           
                        </div>
                    </div>  -->  
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="导入" class="btn btn-sm btn-info" 
                             href="#admin/merchandiseItem/toImport">
                                <i class="ace-icon fa fa-plane bigger-110">
                                	导&nbsp;入
                                </i>
                            </a> 
                            &nbsp;&nbsp;
                            <a title="导出" class="btn btn-sm btn-success	 export" 
                              api-path="/admin/merchandiseItem/merchandisePriceExport.do">
                                <i class="ace-icon fa fa-plane bigger-110">
                                	导&nbsp;出
                                </i>
                            </a>                        
                        </div>
                    </div>
                    <div class="col-xs-6" style="text-align: right;">
	                    <label>
	                        <form id="query-form" action="#admin/merchandiseItem/list" onsubmit="listFormSearch(this); return false">
                            	<div class="dataTables_length">
                            		<input type="search" maxlength="11" class="form-control search-query" placeholder="商品名称" name="name" value="${query.name}">
                                	<button type="submit" class="btn btn-purple btn-sm">
                                    	<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>查&nbsp;&nbsp;询
                                    </button>                                        
                                </div>
                            </form>
	                    </label>
	                </div>
                </div>
		        <table class="table table-striped table-bordered table-hover dataTable no-footer">
	                <thead>
	                <tr role="row">
	                    <th>商品名称</th>	                 
	                    <th>进货价</th>
	                    <th>原价</th>
	                    <th>折扣价</th>
	                    <th class="sorting_disabled">操作</th>
	                </tr>
	                </thead>
		
	                <tbody>
	                <c:forEach items="${result}" var="item" varStatus="current">
	                    <tr><input type="hidden" name="merchandiseItemsIds[]" value="${item.id}" readonly class="config-key">
	                        <td>${item.name}</td>	                      
	                        <td>${item.purchase}</td>
	                        <td>${item.price}</td>
	                        <td>${item.discount}</td>
	                        <td>
	                            <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="修改价格信息" class="green" href="#admin/merchandiseItem/toEdit?id=${item.id}">
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
    	 $(".import").click(function(){
    		var url= $(this).attr("api-path");
    		location.href=url;
    	}); 
    
       $(".export").click(function(){
    		var url= $(this).attr("api-path");
    		location.href=url;
    	}); 
    });
</script>