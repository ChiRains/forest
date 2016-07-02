<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>会员卡初始化管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        会员卡
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            会员卡列表
        </div>
              
        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="初始化" class="btn btn-sm btn-info"
                               href="#admin/membershipCardWarehouse/toInit">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                              	 初始化
                            </a>            
                            <a title="初始化" class="btn btn-sm btn-info"
                               href="#admin/distributeMembershipCard/stat">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                              	商家代发卡
                            </a>               
                        </div>
                    </div>
                    <div class="col-xs-6" style="text-align: right;">
                        <label>
                            <form id="query-form" action="#admin/membershipCardWarehouse/list" onsubmit="listFormSearch(this); return false">
                            	<div class="dataTables_length">
                               			<input type="search" maxlength="11" class="form-control search-query"
                                           placeholder="卡号" name="cardNumber" value="${query.cardNumber}">
                                   		<button type="submit" class="btn btn-purple btn-sm">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            查询
                                        </button>                                        
                                 </div>
                            </form>
                        </label>
                    </div>                      
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">                                                      
                                                <th>卡号</th>           
                                                <th>状态</th>          
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                                        <td>${item.cardNumber}</td>                         
                                                        <td>${item.stateStr}</td>   
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
