<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>评价中心</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        评价管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            评价管理列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-2">
                    </div>   
                    <div class="col-xs-12">
                        <div class="text-right">
                            <label>
                                <form id="query-form" action="#admin/merchantEvaluation/list" onsubmit="listFormSearch(this); return false">
                                    <div class="dataTables_length">
		                                   	<select class="form-control" id="status" name="status">
													<option value="0" <c:if test="${query.status eq 0}">selected</c:if>>全部</option>
													<option value="1"<c:if test="${query.status eq 1}">selected</c:if>>未通过</option>
													<option value="2"<c:if test="${query.status eq 2}">selected</c:if>>通过</option>
											</select>	
											                                    	 
				                             日期
		                                    <span class="input-daterange input-group date">	
					                             <input type="text" id="time" name="time" maxlength="20" readonly class="input-sm form-control"
					                             value='<fmt:formatDate value="${query.time}" pattern="yyyy-MM-dd"/>' style="width:100px;margin:0px;">
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
                </div>
                <form id="model-form"  class="form-horizontal"  role="form" action="">
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                        <th>商品名称</th>           
                        <th>评价内容</th>
                        <th>星级</th> 
                        <th>审核状态</th>           
                        <th>规格</th>
                        <th>评价时间</th>
                        <th>评价人</th>
                        <th class="sorting_disabled">&nbsp;   
                        	<input type="checkbox" style="width:20px;height:20px;" name="selectAll"/>全选
                        	<input type="button" class="AuditBtn" api-path="/admin/merchandiseEvaluation/agree.do" style="color:green;" value="审核通过" />
							<input type="button" class="UnAuditBtn" api-path="/admin/merchandiseEvaluation/disagree.do" style="color:red;" value="审核不通过" />
						</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                <td>${item.merchandiseName}</td>                         
                                <td>
                                <label class="showcontent" content="${item.content}">
                                 <c:choose>
	                                <c:when test="${fn:length(item.content)>20}">
	                               		<a>${fn:substring(item.content,0,20)} .....</a>
	                                </c:when>
	                                <c:otherwise><a>${item.content}</a></c:otherwise>
                                </c:choose> 
                                </td>                         
                                <td>
                                	<c:forEach items="${item.stars}" var="star" varStatus="current2"> 
	                                	<c:if test="${star eq 0}">
		                                	<i class="ace-icon red bigger-130 fa fa-star"></i>
	                                	</c:if>
	                                	<c:if test="${star eq 1}">
		                                	<i class="ace-icon red bigger-130 fa fa-star-half-o"></i>
	                                	</c:if>
                                    </c:forEach>
                                </td>
                                <td>
                                	<c:if test="${item.status eq statusType[0].key}">
                                		未审核
                                	</c:if>
                                	<c:if test="${item.status eq statusType[1].key}">
                                		未通过
                                		<!--  <i class="ace-icon red bigger-130 fa fa-times"></i>  -->
                                	</c:if>
                                	<c:if test="${item.status eq statusType[2].key}">
                                		已通过
                                		<!--  <i class="ace-icon green bigger-130 fa fa-check"></i>  -->
                                	</c:if>
								</td>
								<td>${item.specifications}</td>
								<td><fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${item.userName}</td>
                                <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <span class="col-sm-10 no-padding block input-icon input-icon-right mr10">
										<label>
											<c:if test="${item.status eq statusType[1].key}">
												审核不通过
											</c:if>
											<c:if test="${item.status eq statusType[2].key}">
												审核通过
											</c:if>
											<c:if test="${item.status eq statusType[0].key}">
												<input type="hidden" id="status_${item.id}" name="merchandises[${current.index}]" value="0" />
												<input type="hidden" id="status_${item.id}" name="evaluations[${current.index}]" value="0" />
												<input type="checkbox" data-id="${item.id}" data-merchandiseId="${item.merchandiseId}" style="width:20px;height:20px;" name="statusItems[${current.index}].status" value="${statusType[2].key}" />
												<i class="ace-icon green bigger-130 fa fa-check"></i>
											</c:if>
										</label>	
									</span>
                                </div>
                            </td>
                        </tr>
                   </c:forEach>  
                    </tbody>
                </table>    
                </form>
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
        //全选
        $("input[name=selectAll]").click(function(){
    		if($(this).is(":checked")){
    			$("input[name^=statusItems]").prop("checked",true);
    		}else{
    			$("input[name^=statusItems]").prop("checked",false);
    		}
    		$("input[name^=statusItems]").each(function(){
    			var id=$(this).attr("data-id");
    			var merchandiseId=$(this).attr("data-merchandiseId");
    			if($(this).is(":checked")){
    				$(this).prev().val(id);
    				$(this).prev().prev().val(merchandiseId);
    			}else{
    				$(this).prev().val(0);
    				$(this).prev().prev().val(0);
    			}
    		});
        });
        // √
    	$("input[name^=statusItems]").click(function(){
    		var flag=0;
    		$("input[name^=statusItems]").each(function(){
    			var id=$(this).attr("data-id");
    			var merchandiseId=$(this).attr("data-merchandiseId");
    			if($(this).is(":checked")){
    				$(this).prev().val(id);
    				$(this).prev().prev().val(merchandiseId);
    			}else{
    				$(this).prev().val(0);
    				$(this).prev().prev().val(0);
    				flag++;
    			}
    		});
    		if(flag>0){
    			$("input[name=selectAll]").prop("checked",false);
    		}else{
    			$("input[name=selectAll]").prop("checked",true);
    		}
    	});
    	
        
	      $(".ace-switch-calculatePrice").on('change',function(){
	            var el = $(this);
	            $("#calculatePrice").val(el[0].checked?'1':'2');
	        });
	        
	       $('.date').datepicker({
	                format:'yyyy-mm-dd',
	                autoclose:true,
	                startDate: '-1y'
	        });
	        
	     $(".AuditBtn").click(function(){
	     	 var url =$(this).attr("api-path");
			 $("#model-form").attr("action",url);
	     	 var flag=0;
	     	 $("input[name^=statusItems]").each(function(){
    			if($(this).is(":checked")){
    				flag++;
    			}
    		});
    		if(flag==0){
    			BootstrapDialog.show({
	            	title: '非法操作',
	            	message :'请至少勾选一个勾选框'
	            });
    			return ;
    		}
    		BootstrapDialog.show({
	            title: '确定批量审核通过？',
	            message:'该操作不可恢复,确定批量审核通过？',
	            buttons: [{
	                id: 'btn-1',
	                label: '确定',
	                cssClass: 'btn btn-primary',
	                action: function (dialogItself) {
	                	 dialogItself.close();
				     	 postForm('model-form');
				     	 setTimeout(function () {
				     	 	window.location.reload();
                        }, 1000);
	                }
	            }]
	          });
	     	
	     }); 
	     
	     
	     $(".UnAuditBtn").click(function(){
	     	 var url =$(this).attr("api-path");
			 $("#model-form").attr("action",url);
	     	 var flag=0;
	     	 $("input[name^=statusItems]").each(function(){
    			if($(this).is(":checked")){
    				flag++;
    			}
    		});
    		if(flag==0){
    			BootstrapDialog.show({
	            	title: '非法操作',
	            	message :'请至少勾选一个勾选框'
	            });
    			return ;
    		}
    		BootstrapDialog.show({
	            title: '确定批量审核不通过？',
	            message:'该操作不可恢复,确定批量审核不通过？',
	            buttons: [{
	                id: 'btn-1',
	                label: '确定',
	                cssClass: 'btn btn-primary',
	                action: function (dialogItself) {
	                	 dialogItself.close();
				     	 postForm('model-form');
				     	  setTimeout(function () {
				     	 	window.location.reload();
                        }, 1000);
	                }
	            }]
	          });
	     }); 
	     
	     $("#model-form").validate({
                errorElement: 'div',
                errorClass: 'help-block col-xs-12 col-sm-reset inline',
                focusInvalid: false,
                rules: {
                    name: {
                        required: true
                    },
                    mobile: {
                        required: true
                    },

                    sort: {
                        required: true,
                        range: [0, 99999999],
                        digits: true
                    }
                },
                messages: {},
                highlight: function (e) {
                    $(e).closest('.form-group').removeClass('has-success').addClass('has-error');
                },

                success: function (e) {
                    $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                    $(e).remove();
                },

                errorPlacement: function (error, element) {
                    if (element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
                        var controls = element.closest('div[class*="col-"]');
                        if (controls.find(':checkbox,:radio').length > 1) controls.append(error);
                        else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
                    }
                    else if (element.is('.select2')) {
                        error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
                    }
                    else if (element.is('.chosen-select')) {
                        error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
                    }
                    else error.insertAfter(element.parent());
                },

                submitHandler: function (form) {
                    postForm('model-form');
                },
                invalidHandler: function (form) {
                }
            });
	        
	     $('.showcontent').on('click', function () {
	    	var content=$(this).attr('content');
	        BootstrapDialog.show({
	            title: '内容',
	            message:content,
	            buttons: [{
	                id: 'btn-1',
	                label: '确定',
	                cssClass: 'btn btn-primary',
	                action: function (dialogItself) {
	                    dialogItself.close();
	                }
	            }]
	        });
        });
    });
    
</script>
