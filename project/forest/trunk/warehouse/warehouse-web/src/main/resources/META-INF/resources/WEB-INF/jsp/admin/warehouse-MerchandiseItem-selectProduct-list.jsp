<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <div class="row">
            <form action="/admin/stockState/selectProductList.do">
                <div class="col-xs-6">
                    <label>
                        <div class="input-group">

                            <input type="search" maxlength="11" class="form-control search-query" placeholder="请输入搜索内容"
                                   name="name" value="${query.name}">
                            <span class="input-group-btn">
                                <button type="button" class="btn btn-purple btn-sm search-button">
                                    <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                    搜索
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
                    <th>商品编号</th>
                    <th>名称</th>
                    <th>规格</th>
                    <th>进货价</th>
                    <th>折扣价</th>
                    <th>原价</th>
                    <th class="sorting_disabled">操作</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${result}" var="item" varStatus="current">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.list[0].name}</td>
                        <td>${item.list[0].specifications}</td>
                        <td>${item.purchase}</td>
                        <td>${item.discount}</td>
                        <td>${item.price}</td>
                        <td>
                            <div class="hidden-sm hidden-xs action-buttons">
                                <a title="选择" class="green add-btn"
                                   data-id="${item.id}" data-name="${item.list[0].name}" data-specifications="${item.list[0].specifications}"
                                   data-stock="${item.stock}" data-purchase="${item.purchase}" data-discount="${item.discount}" data-price="${item.price}" 
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
