<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>用户财务兑兑券列表</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        用户财务兑兑券
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            用户财务兑兑券列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    
                    <div class="col-xs-6">
                    	<div class="dataTables_length">
                            <a title="注册送兑兑券" class="btn btn-sm btn-info"
                               href="#admin/pointConfig/getRegistrationCurrency">
                                <i class="ace-icon fa fa-gift align-bottom bigger-125"></i>
                               注册&nbsp;送兑兑券
                            </a>                           
                        </div>
                    </div> 
                    
                    <form action="#admin/myWealth/couponbondList" id="query-form" onsubmit="listFormSearch(this); return false">
                    <div class="col-xs-6">
                    	<div class="dataTables_length" style="float:right;">
	                   		 <select class="chosen-select" style="width:200px!important;" id="userId" name="userId" >
	                            <option value="0" selected>请选择用户</option>
	                            <c:forEach var="user" items="${userList}">
	                            	<option value="${user.id}">${user.name} </option>
	                            </c:forEach>
	                         </select>
	                         <button type="submit" class="btn btn-sm">
	                         	<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            查询
	                         </button> 
                     	</div>
                    </div> 
                    </form>  
                                       
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                        <th>用户名称</th>           
                        <th>兑兑券</th>           
                        <th>最后更新时间</th>           
                        <th class="sorting_disabled">操作</th>
                    </tr>
                  	</thead>

                    <tbody>
                       <c:forEach items="${result}" var="item" varStatus="current"> 
                        <tr>            
                            <td>${item.userName}</td>                         
                            <td>${item.consumptionCurrency} &nbsp;<a title="查看明细" class="blue" href="#admin/myWealthDetail/getWealthDetails?wealthId=${item.id}&userId=${item.userId}&type=3&pageNum=1">
								<i class="ace-icon fa fa-eye bigger-130"></i></a>
                            </td>                         
                            <td>
                            <fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd HH:mm:ss"/> 
                            </td>                         
                            <td>
	                            <div class="hidden-sm hidden-xs action-buttons">
	                                <a title="修改基本信息" class="green" 
	                                   href="#admin/myWealth/toEditCouponbond?id=${item.id}">
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
    var scripts = [null, "/qcloud-admin/assets/js/jquery.colorbox-min.js", "/qcloud-admin/assets/js/bootstrap-tag.min.js", "/qcloud-admin/assets/js/upload-img.js", "/qcloud-admin/assets/js/chosen.jquery.min.js",null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
        $('.chosen-select').chosen({allow_single_deselect:true}); 
        
        
    });
</script>
