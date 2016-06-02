<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>管理员管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        用户财务管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            用户财务列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                         <!--   <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/myWealth/toAdd">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>  -->                          
                        </div>
                    </div>                    
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                              <!--              <th>ID</th>   -->         
                                                <th>用户</th>           
                                                <th>积分</th>           
                                                <th>佣金</th>           
                                                <th>消费币</th>           
                                                <th>投资</th>           
                                                <th>最后更新时间</th>           
                                                <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                       <c:forEach items="${result}" var="item" varStatus="current"> 
                        <tr>            
                                     <!--       <td>${item.id}</td>      -->                   
                                                <td>${item.userId}</td>                         
                                                <td>${item.integral} &nbsp;<a title="查看明细" class="blue" href="#admin/myWealthDetail/getWealthDetails?wealthId=${item.id}&userId=${item.userId}&type=1&pageNum=1">
                                                	<i class="ace-icon fa fa-eye bigger-130"></i></a>
                                                </td>                         
                                                <td>${item.commission} &nbsp;<a title="查看明细" class="blue" href="#admin/myWealthDetail/getWealthDetails?wealthId=${item.id}&userId=${item.userId}&type=2&pageNum=1">
													<i class="ace-icon fa fa-eye bigger-130"></i></a>
                                                </td>                         
                                                <td>${item.consumptionCurrency} &nbsp;<a title="查看明细" class="blue" href="#admin/myWealthDetail/getWealthDetails?wealthId=${item.id}&userId=${item.userId}&type=3&pageNum=1">
													<i class="ace-icon fa fa-eye bigger-130"></i></a>
                                                </td>                         
                                                <td>${item.investment} &nbsp;<a title="查看明细" class="blue" href="#admin/myWealthDetail/getWealthDetails?wealthId=${item.id}&userId=${item.userId}&type=4&pageNum=1">
													<i class="ace-icon fa fa-eye bigger-130"></i></a>
                                                </td>                         
                                                <td>
                                                <fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd HH:mm:ss"/> 
                                                </td>                         
                                                <td>
	                            <div class="hidden-sm hidden-xs action-buttons">
	                                <a title="修改基本信息" class="green" 
	                                   href="#admin/myWealth/toEdit?id=${item.id}">
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
