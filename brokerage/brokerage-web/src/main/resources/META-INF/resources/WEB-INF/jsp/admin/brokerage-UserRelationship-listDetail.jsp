<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>推荐详情</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
          推荐人管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
           推荐详情列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">  
            	<div class="row">
                    <div class="col-xs-6">
                    </div>
                    <div class="col-xs-6" style="text-align: right;">
                        <a href="#admin/userRelationship/list" class="btn btn-sm btn-info">&ensp;返&emsp;回&ensp;</a>
                    </div>
                </div>              
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                                                <th>推荐人</th>    
                                                <th>被推荐人</th>    
                                                <th>关系</th>                                                 
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                                        <td>${item.recommed}</td>      
                                                        <td>${item.user}</td>   
                                                        <td>${item.path}</td>      
                        </tr>
						</c:forEach>
                    </tbody>
                </table>                         
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
