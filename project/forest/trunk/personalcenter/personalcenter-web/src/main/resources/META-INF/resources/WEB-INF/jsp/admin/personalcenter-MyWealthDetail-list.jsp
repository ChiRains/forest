<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>用户财务明细列表</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
       用户财务明细
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            用户财务明细列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                          <%--  <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/myWealthDetail/toAdd">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>      --%>                     
                        </div>
                    </div>                    
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                                                <th>用户名称</th>           
                                                <th>当次进出额度</th>           
                                                <th>时间</th>           
                                                <th>描述</th>           
                                                <th>类型</th>           
                                           <!--     <th class="sorting_disabled">操作</th>-->  
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                                <td>${item.userName}</td>                         
                                                <td>${item.point}</td>          
                                                <td><fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>                         
                                                <td>${item.desc}</td>                         
                                                <td>
                                                	<c:if test="${item.type==1}">积分</c:if>
                                                	<c:if test="${item.type==2}">佣金</c:if>
                                                	<c:if test="${item.type==3}">兑兑券</c:if>
                                                	<c:if test="${item.type==4}">投资</c:if>
                                                ${type}</td>                         
                                 <%--                <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                   <a title="修改基本信息" class="green" 
                                       href="#admin/myWealthDetail/toEdit?id=${item.id}">
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                    </a>							                                 
                                </div>
                            </td>	--%>
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
