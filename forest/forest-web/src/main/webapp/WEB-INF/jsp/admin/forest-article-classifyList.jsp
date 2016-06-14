<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>活动商品类别管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        活动商品类别管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            活动商品类别列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/salesPromotion/toAddSalesPromotionClassify">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>                           
                        </div>
                    </div>                    
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
        
                                                <th>活动类别</th>           
                                                <th>图片</th>           
                                                <th>描述</th>           
                                                <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                                        <td>${item.name}</td>   
														<td><img style="max-height: 150px;max-width: 150px;" src="${item.image}" /></td>
														<td>
														<label class="showcontent"     content="${item.remark}">
					                                <c:choose>
					                               <c:when test="${fn:length(item.remark)>20}">
					                               ${fn:substring(item.desc,0,20)} .....
					                                </c:when>
					                              <c:otherwise> ${item.remark}</c:otherwise>
					                                 </c:choose> 
					                                </label></td>                                                                              
                                                        <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="查看商品" class="green" 
                                       href="#admin/salesPromotion/mallList?classifyId=${item.id}&merchantId=1">
                                        查看商品
                                    </a>							                                 
                                    <a title="查看商品" class="green" 
                                       href="#admin/salesPromotion/toEditSalesPromotionClassify?classifyId=${item.id}">
                                      修改类别
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
