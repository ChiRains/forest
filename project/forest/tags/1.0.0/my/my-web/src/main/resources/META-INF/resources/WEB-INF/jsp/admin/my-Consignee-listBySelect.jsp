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
		<div class="row"></div>
        <div class="dataTables_wrapper form-inline no-footer">
            <table class="table table-striped table-bordered table-hover dataTable no-footer">
                <thead>
                <tr role="row">         
                    <th>姓名</th>        
                    <th>手机号</th> 
                    <th>地址</th>
                    <th>区号</th>
                    <th>默认地址</th>
                    <th class="sorting_disabled">操作</th>
                </tr>
                </thead>
                <tbody>
                	<c:forEach items="${result}" var="item" varStatus="current"> 
                        <tr>                                 
                            <td>${item.name}</td>
                            <td>${item.mobile}</td>
                            <td>${item.province}${item.city}${item.district}${item.address}</td>
                            <td>${item.zipCode}</td>
                            <td>${item.acquiesce}</td>
                            <td>
                          <div class="hidden-sm hidden-xs action-buttons">
                              <a title="选择" class="green add-btn" href=""
                                 data-id="${item.id}" data-name="${item.name}" data-mobile="${item.mobile}" data-address="${item.province}${item.city}${item.district}${item.address}" data-zipCode="${item.zipCode}">
	                          		<i class="ace-icon glyphicon glyphicon-ok bigger-130"></i>
	                          </a>
	                          </div>
	                   		</td>		
	                    </tr>
					</c:forEach>
                </tbody>
            </table>    
            <%-- <div class="row">
                <div class="col-xs-12">                  
                   	<%@include file="../page.inc.jsp" %>
                </div>
            </div>  --%>         
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
