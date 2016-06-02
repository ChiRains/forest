<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title> 用户--等级</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
         用户--等级
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            用户--等级
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="设置" class="btn btn-sm btn-info"
                               href="#admin/userDistributionGrade/toAdd?gradeId=${query.gradeId}">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                             设置
                            </a>                           
                        </div>
                    </div> 
                    <div class="col-xs-6">
                        <div class="dataTables_length" style="float:right;">
                            <a title="返回上一页 " class="btn btn-sm btn-info"
                               href="#admin/distributionGrade/list">
                                <i class="ace-icon fa fa-undo align-bottom bigger-125"></i>
                                返回上一页 
                            </a>                           
                        </div>
                    </div>                    
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                            <th>用户ID</th>           
                            <th>级别ID</th>           
                            <th>注册时间</th>           
                            <th>升级时间</th>           
                            <th>有效时间</th>           
                         <%--   <th class="sorting_disabled">操作</th> --%>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                <td>${item.userId}</td>                         
                                <td>${item.gradeId}</td>                         
                                <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>                         
                                <td><fmt:formatDate value="${item.upgradeTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>                         
                                <td><fmt:formatDate value="${item.effectiveTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>                         
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
         
    });
</script>
