<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>佣金分配管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        佣金分配管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            佣金分配列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <c:forEach items="${stateType}" var="stateType" varStatus="current"> 
	                	   	 <c:if test="${query.state eq stateType.key}">
	                       		 <a type="button" class="btn btn-purple btn-sm"
	                       		     href="#admin/distributionBrokerage/list?state=${stateType.key}&type=${query.type}&startDate=<fmt:formatDate value="${query.startDate}" pattern="yyyy-MM-dd" />&endDate=<fmt:formatDate value="${query.endDate}" pattern="yyyy-MM-dd" />">
	                                 <i class="ace-icon  icon-on-right bigger-110"></i>
	                                    ${stateType.name}	
	                             </a> 
	                         </c:if>
	                         <c:if test="${query.state ne stateType.key}">
	                       		 <a type="button" class="btn btn-info btn-sm"
	                       		     href="#admin/distributionBrokerage/list?state=${stateType.key}&type=${query.type}&startDate=<fmt:formatDate value="${query.startDate}" pattern="yyyy-MM-dd" />&endDate=<fmt:formatDate value="${query.endDate}" pattern="yyyy-MM-dd" />">
	                                 <i class="ace-icon  icon-on-right bigger-110"></i>
	                                    ${stateType.name}	
	                             </a> 
	                         </c:if>
	                       </c:forEach>                      
                        </div>
                    </div>   
                    <div class="col-xs-6">
	                    <div class="text-right">
	                        <label>
	                            <form action="#admin/distributionBrokerage/list" onsubmit="listFormSearch(this); return false">
	                                <div class="dataTables_length">
	                                	  <input type="hidden" value="${query.state}" name="state">
	                                	  <select class="form-control search-query" style="width:163px;height:34px;" id="type" name="type">
										 	 <option value="0"> 请选择一个数据类型 </option>
											<c:forEach items="${dataPoolType}" varStatus="current" var="dateType">
										 	  <option <c:if test="${dateType.key eq query.type}">selected</c:if> value="${dateType.key}" > ${dateType.value}</option>
										 	</c:forEach>
							             </select> 
	                                	 <input type="text" placeholder="开始时间" class="startDate width-25" name="startDate" value="<fmt:formatDate value="${query.startDate}" pattern="yyyy-MM-dd" />" />-  
		                                 <input type="text" placeholder="结束时间" class="endDate width-25" name="endDate" value="<fmt:formatDate value="${query.endDate}" pattern="yyyy-MM-dd" />" />	 
                                   		 <button type="submit" class="btn btn-purple btn-sm">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            查询
                                         </button>                                        
	                                 </div>
	                            </form>
	                        </label>
	                    </div>
	                </div>                           
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    	<tr role="row">     
	                            <th>级别</th> 
	                            <th>名称</th>           
	                            <th>图</th>           
	                            <th>类别</th>           
	                            <th>佣金</th>           
	                            <th>购买时间</th>           
	                            <th class="sorting_disabled">操作</th>
                   	 </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                <td>${item.gradeName}</td>  
                                <td>${item.name}</td>  
                                <td><img height="50px" width="80px" src="${item.image}" /></td>                     
                                <td>${item.typeStr}</td>                         
                                <td>${item.brokerage}</td>                         
                                <td><fmt:formatDate value="${item.orderTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>                         
                                <td>
	                                <div class="hidden-sm hidden-xs action-buttons">
	                                    <a title="查看明细" class="bule" 
                                      		href="#admin/distributionBrokerage/getDetails?id=${item.id}">
                                        	<i class="ace-icon fa fa-eye bigger-130"></i>
                                    	</a>
	                            <%--        <a title="修改基本信息" class="green" 
	                                       href="#admin/distributionBrokerage/toEdit?id=${item.id}&queryStr=${queryStr}">
	                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
	                                    </a>	--%>						                                 
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
    var scripts = [null, "/qcloud-admin/assets/js/date-time/bootstrap-datepicker.min.js"];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
        
        $('.startDate').datepicker({
            format:'yyyy-mm-dd',
            autoclose:true,
            startDate: '-1y'
        }); 
        
        $('.endDate').datepicker({
            format:'yyyy-mm-dd',
            autoclose:true,
            startDate: '-1y'
        }); 
    });
</script>
