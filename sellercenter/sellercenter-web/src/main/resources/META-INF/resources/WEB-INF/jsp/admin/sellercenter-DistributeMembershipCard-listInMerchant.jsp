<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>商家代发会员卡列表</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        商家代发卡
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
         商家代发卡
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">  
            	 <div class="row">                    
                    <div class="col-xs-12" style="text-align: right;">
                        <a href="#admin/distributeMembershipCard/stat" class="btn btn-sm btn-info">&ensp;返&emsp;回&ensp;</a>
                    </div>
                </div>                         
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                                               
                                                <th>商家</th>           
                                                <th>职员</th>           
                                                <th>卡号</th>           
                                                <th>状态</th>           
                                                <th>发卡时间</th>                                                           
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                                                         
                                                        <td>${item.merchantName}</td>                         
                                                        <td>${item.memberMobile}</td>                         
                                                        <td>${item.cardNumber}</td>                         
                                                        <td>${item.stateStr}</td>                         
                                                        <td>${item.timeStr}</td>        
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
