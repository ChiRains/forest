<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>团队组织关系</title>

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
           组织关系列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">             
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                    							<th>组长</th>  
                    							<th>上三代</th>       
                    							<th>成员</th>                                              
                                                <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>                                                                                       
                                                        <td>${item.leaderStr}</td>     
                                                        <td>${item.parentStr}</td>                   
                                                        <td>${item.childrenStr}</td>    
                                                        <td>
                                <div class="hidden-sm hidden-xs action-buttons">                                   
                                    <a title="查看团队成员" class="green" 
                                       href="#admin/userTeam/listDetail?leader=${item.leader}">
                                        <i class="ace-icon fa fa-eye bigger-130"></i>
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
