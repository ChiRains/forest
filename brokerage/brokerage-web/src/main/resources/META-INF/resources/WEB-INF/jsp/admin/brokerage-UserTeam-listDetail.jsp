<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>组织成员</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        组织关系管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
           组织成员列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">             
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
    							<th>组长</th>  
    							<th>组织成员</th>   
                    </tr>
                    </thead>

                    <tbody>
                       <c:forEach items="${result}" var="item" varStatus="current"> 
                        <tr>                                                                                       
                            <td>${item.leaderStr}</td>                       
                            <td>${item.userStr}</td> 
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
