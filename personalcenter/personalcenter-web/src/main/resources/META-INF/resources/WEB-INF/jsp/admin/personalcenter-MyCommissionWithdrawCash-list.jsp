<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>管理员管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        管理员管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            管理员列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">

                              <a title="导出" class="btn btn-sm btn-info" target="blank"
                               href="${exportUrl}">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                                                导&nbsp;出
                           </a>                          
                        </div>
                        
                    </div> 
                         
                        <div class="col-xs-6" style="text-align: right;">
                        <label>
                            <form id="query-form" action="#admin/myCommissionWithdrawCash/list" onsubmit="listFormSearch(this); return false">
                            	<div class="dataTables_length">
                            	<select name="state" id="state"
											class="input-sm form-control">
											<option value="0">---请选择状态类型---</option>
											<option value="1" 
											<c:if test="${query.state eq 1}">
												selected="selected"
											</c:if>>申请</option>
											<option value="2"						
											<c:if test="${query.state eq 2}">
												selected="selected"
											</c:if>>审核通过</option>
											<option value="3"
											<c:if test="${query.state eq 3}">
												selected="selected"
											</c:if>>审不核通过</option>
										</select>
                               		 <span class="input-daterange input-group date"> <input
											type="text" id="date" name="date" maxlength="20"
											class="input-sm form-control"
											value='<fmt:formatDate value="${query.date}" pattern="yyyy-MM-dd"/>'
											style="width: 100px; margin: 0px;">
										</span>
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
                                                <!-- <th>ID</th>         -->   
                                           <!--      <th>财富账号</th>           
                                                <th>财富明细</th>     -->  
                                                <th>账号</th>      
                                                <th>用户名</th> 
                                                <th>用户</th>          
                                                <th>当次提现值</th>           
                                                <th>持卡人</th>           
                                                <th>银行</th>           
                                                <th>卡</th>           
                                                <th>发生时间</th>           
                                                <th>完成时间</th>           
                                                <!-- <th>类型1. 申请 2.审核  3.成功</th>    --> 
                                                <th>审核状态</th>       
                                                <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                                    <%--     <td>${id}</td>                         
                                                        <td>${wealthId}</td>                         
                                                        <td>${wealthDetailId}</td>  --%>   
                                                        <td>${item.account}</td> 
                                                        <td>${item.username}</td>                    
                                                        <td>${item.userId}</td>                         
                                                        <td>${item.commissionCash}</td>                         
                                                        <td>${item.cardholder}</td>                         
                                                        <td>${item.bank}</td>                         
                                                        <td>${item.card}</td>                         
                                                        <td><fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>                         
                                                        <td><fmt:formatDate value="${item.completeTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>                         
                                                        <td>${item.stateName}</td>                         
                                                        <td>
                                <div class="hidden-sm hidden-xs action-buttons">
<%--                                     <a title="修改基本信息" class="green" 
                                       href="#admin/myCommissionWithdrawCash/toEdit?id=${item.id}">
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                    </a>	 --%>
                                    <c:if  test="${item.state==1}">
									<a class="btn btn-xs btn-primary passcheck"
											api-path="/admin/myCommissionWithdrawCash/check.do?id=${item.id}&ischeck=2">通过
									</a>
									</c:if>
									<c:if  test="${item.state==1}">
									<a class="btn btn-xs btn-primary notpasscheck"
											api-path="/admin/myCommissionWithdrawCash/check.do?id=${item.id}&ischeck=4">未通过
									</a>
									</c:if>	 							                                 
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
    var scripts = [null,"/qcloud-admin/assets/js/date-time/bootstrap-datepicker.min.js", null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
        $('.date').datepicker({
				format : 'yyyy-mm-dd',
				autoclose : true,
				startDate : '-1y'
			}); 
    	 $('.passcheck').on('click', function () {
             var delUrl = $(this).attr('api-path');
             BootstrapDialog.show({
                 title: '确认',
                 message: '确认审核通过？',
                 buttons: [{
                     id: 'btn-1',
                     label: '确定',
                     cssClass: 'btn btn-primary',
                     action: function (dialogItself) {
                         $.get(delUrl, {},
                                 function (data) {
                                     data = JSON.parse(data);
                                     if (parseInt(data["status"]) === 0) {
                                         dialogItself.setTitle('审核操作失败');
                                         dialogItself.setMessage(data["message"]);
                                         dialogItself.setType(BootstrapDialog.TYPE_DANGER);
                                         dialogItself.getButton('btn-1').remove();
                                     } else {
                                         dialogItself.setTitle('成功');
                                         dialogItself.setMessage("审核操作成功！");
                                         dialogItself.setType(BootstrapDialog.TYPE_SUCCESS);
                                         setTimeout(function () {
                                             dialogItself.close();
                                         }, 1000);
                                         setTimeout(function () {
                                             location.reload(true);
                                         }, 1500);
                                     }
                                 });
                     }
                 }, {
                     label: '取消',
                     action: function (dialogItself) {
                         dialogItself.close();
                         setTimeout(function () {
                             dialogItself.close();
                         }, 100);
                         setTimeout(function () {
                             location.reload(true);
                         }, 150);
                     }
                 }]
             });
         });
     });
     ace.load_ajax_scripts(scripts, function () {
         //inline scripts related to this page
         $('.notpasscheck').on('click', function () {
             var delUrl = $(this).attr('api-path');
             BootstrapDialog.show({
                 title: '确认',
                 message: '确认审核不通过？',
                 buttons: [{
                     id: 'btn-1',
                     label: '确定',
                     cssClass: 'btn btn-primary',
                     action: function (dialogItself) {
                         $.get(delUrl, {},
                                 function (data) {
                                     data = JSON.parse(data);
                                     if (parseInt(data["status"]) === 0) {
                                         dialogItself.setTitle('审核操作失败');
                                         dialogItself.setMessage(data["message"]);
                                         dialogItself.setType(BootstrapDialog.TYPE_DANGER);
                                         dialogItself.getButton('btn-1').remove();
                                     } else {
                                         dialogItself.setTitle('成功');
                                         dialogItself.setMessage("审核操作成功！");
                                         dialogItself.setType(BootstrapDialog.TYPE_SUCCESS);
                                         setTimeout(function () {
                                             dialogItself.close();
                                         }, 1000);
                                         setTimeout(function () {
                                             location.reload(true);
                                         }, 1500);
                                     }
                                 });
                     }
                 }, {
                     label: '取消',
                     action: function (dialogItself) {
                         dialogItself.close();
                         setTimeout(function () {
                             dialogItself.close();
                         }, 100);
                         setTimeout(function () {
                             location.reload(true);
                         }, 150);
                     }
                 }]
             });
         });
    });
</script>
