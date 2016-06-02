<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>
<style>
	.select-product-dialog {
        top: 50px;
    }

    .select-product-dialog tr {
        word-break: break-all;
    }

    .select-product-dialog .modal-content {
        min-height: 500px;
        min-width: 800px;
    }
</style>
<div class="row">
    <div class="col-xs-12">
<div class="row">
            <form action="/admin/user/listBySelect.do">
                <div class="col-xs-6">
                    <label>
                        <div class="input-group">
                            <input type="search" maxlength="25" class="width-100" placeholder="姓名/手机/昵称" name="name" value="${query.name}">
                            <span class="input-group-btn">
                                <button type="button" class="btn btn-purple btn-sm search-button">
                                    <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>搜索
                                </button>
                            </span>
                        </div>
                    </label>
                </div>
            </form>
        </div>
            <div class="dataTables_wrapper form-inline no-footer">
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">         
                        <th>姓名</th>        
                        <th>手机号</th> 
                        <th>性别</th>  
                        <th>昵称</th>            
                        <th>Email</th>           
                        <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>                                 
                                                       
                                <td>${item.name}</td>    
                                <td>${item.mobile}</td>
                                <td>${item.sexStr}</td>   
                                <td>${item.nickname}</td>                       
                                <td>${item.email}</td>                         
                                <td>
		                            <div class="hidden-sm hidden-xs action-buttons">
		                                <a title="选择" class="green add-btn" 
		                                   data-id="${item.id}" 
		                                    data-name="${item.name}" 
		                                     data-mobile="${item.mobile}" 
		                                      data-nickname="${item.nickname}" 
		                                       data-email="${item.email}" 
		                                        data-sexStr="${item.sexStr}" 
		                                   href="">
		                                    <i class="ace-icon glyphicon glyphicon-ok bigger-130"></i>
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
<!-- <script type="text/javascript">
    var scripts = [null, "/qcloud-admin/assets/js/jquery.colorbox-min.js","/qcloud-admin/assets/js/upload-img.js","/qcloud-admin/assets/js/chosen.jquery.min.js", null];
    ace.load_ajax_scripts(scripts, function () {
    	$('.chosen-select').chosen({allow_single_deselect:true});
    	$(document).ready(function(){
    	
    	});
    })
</script> -->
