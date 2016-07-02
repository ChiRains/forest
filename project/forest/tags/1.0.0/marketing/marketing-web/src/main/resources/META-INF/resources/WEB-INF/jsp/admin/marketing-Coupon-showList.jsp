<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>管理员管理</title>

<!-- ajax layout which only needs content area -->

<div class="row">
    <div class="col-xs-12">


        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                                                <th>ID</th>           
                                                <th>开始时间</th>           
                                                <th>结束时间</th>           
                                                <th>有效时间</th>           
                                                <th>总共领取张数/人</th>           
                                                <th>总共领取金额数/人</th>           
                                                <th>描述</th>           
                                                <th>是否启用</th>           
                                                <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                                        <td>${item.id}</td>                         
                                                        <td><fmt:formatDate value="${item.startDate}" pattern="yyyy-MM-dd"/></td>                         
                                                        <td><fmt:formatDate value="${item.endDate}" pattern="yyyy-MM-dd"/></td>                         
                                                        <td><fmt:formatDate value="${item.validDate}" pattern="yyyy-MM-dd"/></td>                         
                                                        <td>${item.totalOfPerson}</td>                         
                                                        <td>${item.priceOfPerson}</td>                         
                                                        <td>${item.description}</td>                         
                                                        <td>${item.enable}</td>                         
                                                        <td>
                                                        <div class="hidden-sm hidden-xs action-buttons">
                                <a title="选择" class="green add-btn"
                                   data-id="${item.id}"
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

<!-- page specific plugin scripts -->
<script type="text/javascript">
    var scripts = [null, null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
         
    });
</script>
