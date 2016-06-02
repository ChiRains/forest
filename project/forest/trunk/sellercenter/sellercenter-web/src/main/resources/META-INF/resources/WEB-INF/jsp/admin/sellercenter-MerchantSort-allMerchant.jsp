<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>
<!-- ajax layout which only needs content area -->
<!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
                <div class="row">
                    <div class="col-xs-6" style="text-align: right;">
                        <label>
                            <form id="query-form" action="/admin/merchant/allMerchantlist.do" >
                                <div class="input-group">
                                	<input hidden name="pageNum" value="${pageNum}"/>
                                    <input type="search" maxlength="11" class="form-control search-query"
                                           placeholder="请输入搜索内容" name="name" value="${query.name}">
                                    <span class="input-group-btn">
                                        <button type="button" class="btn btn-purple btn-sm search-button">
		                                    <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
		                                    搜索
		                                </button>
                                    </span>
                                </div>
                            </form>
                        </label>
                    </div>
                </div>
                <div class="dataTables_wrapper form-inline no-footer">
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">
                        <th>名称</th>
                        <th>分类</th>
                        <th>地址</th>
                        <th>登记时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${result}" var="item" varStatus="current">
                        <tr>
                            <td>${item.name}</td>
                            <td>${item.classifyStr}</td>
                            <td>${item.province} ${item.city} ${item.district} ${item.address}</td>
                            <td><fmt:formatDate value="${item.registTime}" pattern="yyyy-MM-dd"/></td>
                           <!--<td><a href="#admin/merchandise/list4UnAudit.do?pageNum=1&merchantId=${item.id}">添加</a></td>
                        	-->
                        	<td>
                        	<div class="hidden-sm hidden-xs action-buttons">
                                <a title="选择" class="green add-btn"
                                   data-id="${item.id}" data-name="${item.name}" data-logo="${item.logo}"
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
