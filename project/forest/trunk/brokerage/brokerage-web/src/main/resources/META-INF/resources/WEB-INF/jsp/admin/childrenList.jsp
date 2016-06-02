<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>


<div class="row">
    <div class="col-sm-12">
        <div class="dd" id="nestable">
            <ol class="dd-list">
                <c:forEach items="${result}" var="levelOne" varStatus="current">
                
                	<c:set var="index" value="${index + 1}" scope="request" />
                	
                    <li class="dd-item" data-id="${levelOne.id}">
                        <div class="dd-handle">
                        	<a class="red pplist " style="cursor:pointer;" data-index="${current.index}">
                        		<i class="ace-icon fa fa-angle-down bigger-110"></i>
                        	</a>
                        	 <span class="">${levelOne.name}------(充值金额${levelOne.cash},有效时间 ${levelOne.monthLimit} 个月)</span>
                            <div class="pull-right action-buttons">
								<c:if test="${levelOne.userResource==1 && levelOne.type==1}">
                            		<a class="red" title="选择用户" href="#admin/userDistributionGrade/list?gradeId=${levelOne.id}">
                            		<i class="ace-icon fa fa-user bigger-130"></i></a>
                            	</c:if>
                            	<c:if test="${levelOne.type==1}">
									<a class="blue" title="修改" href="#admin/distributionGrade/toEdit?id=${levelOne.id}">
									<i class="ace-icon fa fa-pencil bigger-130"></i></a>
								</c:if>
								<c:if test="${levelOne.type==1}">
                            		<a class="red" title="升级送礼" href="#admin/upgradeGift/list?gradeId=${levelOne.id}">
                            		<i class="ace-icon fa fa-gift bigger-130"></i></a>
                            	</c:if>
                            </div>
                        </div>
                        
                        <ol class="dd-list">
                         <c:if test="${fn:length(levelOne.childrenList) > 0}">       				<!-- 如果有childenList -->  
						    <c:set var="level" value="${level + 1}" scope="request" />				<!-- 循环一次子列表，level+1 -->  
						    <c:set var="result" value="${levelOne.childrenList}" scope="request" />	<!-- 注意此处，子列表覆盖treeList，在request作用域 -->  
						    <c:import url="childrenList.jsp" />
						  </c:if> 
                        </ol>
                        
                    </li>
                </c:forEach>
                <c:set var="level" value="${level - 1}" scope="request" /><!-- 退出时，level-1 --> 
            </ol>
        </div>
    </div>
</div>