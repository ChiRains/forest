<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../taglib.inc.jsp" %>

<title>优惠券子项管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        优惠券子项管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            优惠券子项列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            优惠券子项列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/couponItems/toAdd?couponID=${couponID}">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>                           
                        </div>
                    </div>   
                     <div class="col-xs-6" style="text-align: right;">
                   	 <a href="#admin/coupon/list" class="btn btn-sm btn-info">&ensp;返&emsp;回&ensp;</a>
                	</div>                   
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                                                <th>优惠券名称</th>           
                                                <th>面额</th>           
                                                <th>总数量</th>           
                                                <th>发放数量</th>           
                                                <th>已使用数量</th>
                                                <th>使用限额</th> 
                                            <%--      <th>使用范围</th>        --%>  
                                                <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                                        <td>${item.name}</td>                         
                                                        <td>${item.price}</td>                         
                                                        <td>${item.totalNum}</td>                         
                                                        <td>${item.sendNum}</td>                         
                                                        <td>${item.usedNum}</td>   
                                                        <td>${item.limitPrice}</td>
                                                <%--        <td>
                                                        	<c:if test="${item.type==1}">
                                                        		线上
                                                        	</c:if>
                                                        	<c:if test="${item.type==2}">
                                                        		线下
                                                        	</c:if>
                                                        	<c:if test="${item.type==3}">
                                                        		线上+线下
                                                        	</c:if>
                                                        </td>      --%>                    
                                                        <td>  
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="修改" class="green" 
                                       href="#admin/couponItems/toEdit?id=${item.id}">
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
         
    });
</script>
