<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>卖家中心</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        卖家中心
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            快递列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
           快递列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/sexpress/toAdd4Admin">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>                           
                        </div>
                    </div>                    
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                        <th>快递公司名称</th>           
                        <th>快递公司编码</th>           
                        <th>快递公司图片</th>           
                        <th>排序</th>           
                        <th>首重重量</th>           
                        <th>首重费用</th>           
                        <th>续重重量</th>           
                        <th>续重费用</th>           
                        <th>是否启用 </th>           
                        <th>类型</th>           
                        <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
    							<tr>            
                                    <td>${item.name}</td>                         
                                    <td>${item.code}</td>                         
                                    <td><img src="${item.logo}" width="80%" height="80px" /></td>                         
                                    <td>${item.sort}</td>                         
                                    <td>${item.firstWeight}</td>                         
                                    <td>${item.firstPrice}</td>                         
                                    <td>${item.continuedWeight}</td>                         
                                    <td>${item.continuedPrice}</td>                         
                                    <td>
                                    	<c:if test="${item.enable==1}">是</c:if>
                                    	<c:if test="${item.enable!=1}">否</c:if>
                                    </td>                         
                                    <td>
	                                    <c:if test="${item.type==1}">包邮</c:if>
	                                    <c:if test="${item.type==2}">固定邮费</c:if>
	                                    <c:if test="${item.type==3}">区域收费</c:if>
                                    </td>                         
                                    <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="修改基本信息" class="green" 
                                       href="#admin/sexpress/toEdit4Admin?id=${item.id}">
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
         $(document).ready(function(){
         
         });
    });
</script>
