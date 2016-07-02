<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>
<div class="row">
    <div class="col-xs-12">

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    	<tr role="row">     
								<th>公式名称</th>
	                            <th class="sorting_disabled">操作</th>
                    	</tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${list}" var="item" varStatus="current"> 
                            <tr>    
                            	<td>${item.name}</td>  
                            	<td class="center">
									<label>
										<input type="checkbox" class="ace select-departmentClerk-trigger" 
										id="clerkId_${item.id}" name="clerkId" value="${item.id}" data-id="${item.id}"
										 <c:forEach items="${result}" var="result" varStatus="current">
										 	<c:if test="${result.clerkId eq item.id}">
										 		checked
										 	</c:if>
										 </c:forEach>/>
										<span class="lbl"></span>
									</label>
								</td>
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
