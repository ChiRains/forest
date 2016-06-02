<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>用户等级管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        用户等级管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
           用户等级列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/grade/toAdd">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>                           
                        </div>
                    </div>  
                     
                    <div class="col-xs-6" style="float:right;">
                         <div class="dataTables_length" style="float:right;">
                            <a title="积分设置" class="btn btn-sm btn-info"
                               href="#admin/pointConfig/getPointConfig">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                积分&nbsp;设置
                            </a>                           
                        </div>
                    </div>                     
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">                                                          
                                                <th>名称</th>           
                                                <th>等级描述</th>
                                                <th>图标</th>
                                                <th>积分</th>
                                                <th>折扣</th>
                                                <%-- <th>价格类型</th>  --%>          
                                                <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>                                                                                    
                                                <td>${item.name}</td>                         
                                                <td>${item.desc}</td>
                                                <td><img height="50" width="80" src="${item.image}" /></td>
                                                <td>${item.point}</td>
                                                <td>${item.discount}</td>
                                               <%--  <td>
                                                	<c:if test="${item.priceType==1}">原价</c:if>
                                                	<c:if test="${item.priceType==2}">折扣价</c:if>
                                                </td>    --%>                      
                                                <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="修改基本信息" class="green" 
                                       href="#admin/grade/toEdit?id=${item.id}">
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                    </a>							                                 
                                </div>
                            </td>
                        </tr>
						</c:forEach>
                    </tbody>
                </table>    
              <%--   <div class="row">
                    <div class="col-xs-12">                  
                       	<%@include file="../page.inc.jsp" %>
                    </div>
                </div>    --%>       
            </div>
        </div>
    </div>
</div>

<!-- page specific plugin scripts -->
<script type="text/javascript">
    var scripts = [null, null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
         
    });
</script>
