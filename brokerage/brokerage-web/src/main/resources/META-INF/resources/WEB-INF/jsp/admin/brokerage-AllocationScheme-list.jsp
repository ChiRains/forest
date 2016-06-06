<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>佣金分配比例管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
      佣金分配比例管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            佣金分配比例列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/allocationScheme/toAdd?formulaId=${query.formulaId}">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>                           
                        </div>
                    </div>  
                     <div class="col-xs-6">
                        	<div class="dataTables_length">
                            	<a title="返回" class="btn btn-sm btn-back" style="float:right;"
	                                  href="#admin/calculationFormula/list">
	                                  <i class="ace-icon fa fa-undo align-bottom bigger-125"></i>
	                           		     返&nbsp;回
	                            </a> 
                        	</div>
                    </div>                      
                </div>
                
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    	<tr role="row">     
                                <th>分佣公式</th>           
                                <th>分配对象,等级类别</th>           
                                <th>分配比例</th>           
                                <th class="sorting_disabled">操作</th>
                    	</tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                <td>${item.formulaName}</td>                         
                                <td>${item.allocationGradeName}</td>                         
                                <td>${item.proportion}</td>                         
                                <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="修改基本信息" class="green" 
                                       href="#admin/allocationScheme/toEdit?id=${item.id}&queryStr=${queryStr}">
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