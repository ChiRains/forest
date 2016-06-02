<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>大参林资讯管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        客户评价管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
             评价列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
           <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                       
                           </div> 
                           
                             <div class="col-xs-6" style="text-align: right;">
                                  <div class="dataTables_length">
                             <a href="#admin/article/classifyList" class="btn btn-sm btn-info"  >&ensp;返&emsp;回&ensp;</a>                         
                       </div>
                    </div>                     
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                                                <th>资讯名称</th>           
                                                <th>内容</th>           
                                                <th>用户</th>           
                                                <th>评价时间</th>           
                                                <th class="sorting_disabled">审核状态</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                                        <td>${item.articleName}</td>                         
                                                        <td>${item.content}</td>                         
                                                        <td>${item.userName}</td>                         
                                                        <td><fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>                         
                                                        <td>
                                  <div class="hidden-sm hidden-xs action-buttons">
                                     <span class="col-sm-10 no-padding block input-icon input-icon-right mr10">
                                    	<label><input type="radio" name="state_${item.id}" value="${stateType[0].key}" onchange="changeState('${item.id}');"
                                    	<c:if test="${item.state eq stateType[0].key}">
                                    		checked
                                    	</c:if>/>未审核</label>
                                    	&nbsp;
										<label>
											<input type="radio" name="state_${item.id}" value="${stateType[2].key}" onchange="changeState('${item.id}');"
											<c:if test="${item.state eq stateType[2].key}">
											checked
                                    		</c:if>/><i class="ace-icon red bigger-130 fa fa-times"></i>
										</label>
										&nbsp;
										<label>
											<input type="radio" name="state_${item.id}" value="${stateType[1].key}" onchange="changeState('${item.id}');"
											<c:if test="${item.state eq stateType[1].key}">
											checked
											</c:if>/><i class="ace-icon green bigger-130 fa fa-check"></i>
										</label>	
									</span>						                                 
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
   var scripts = [null, "/qcloud-admin/assets/js/date-time/bootstrap-datepicker.min.js", null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
          $(".ace-switch-calculatePrice").on('change',function(){
	            var el = $(this);
	            $("#calculatePrice").val(el[0].checked?'1':'2');
	        });
	        
	       $('.date').datepicker(
                {
	                format:'yyyy-mm-dd',
	                autoclose:true,
	                startDate: '-1y'
                }
            );
    });
    
    function changeState(id) {
    	var state= $('input:radio[name="state_'+id+'"]:checked').val();
    	$.post("/admin/articleEvaluation/edit.do", {id:id,state:state},
			   function(data){
			   	var obj = JSON.parse(data);
			   	console.log(obj.state);
			   	if(obj.state == -599) {
			   		alert(obj.message);
			   	}
		   });
    }
</script>
