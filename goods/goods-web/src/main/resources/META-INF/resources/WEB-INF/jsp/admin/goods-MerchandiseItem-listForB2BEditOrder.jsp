<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <div class="row">
            <form action="/admin/merchandiseItem/listForAdmin.do">
                <div class="col-xs-12">
                        <div class="dataTables_length">
                        	<select class="chosen-select width-40"  
                        		id="mallClassifyId" name="mallClassifyId" >
	                            <option value="0" selected>请选择分类</option>
	                            <c:forEach items="${mallClassifyList}" var="item" varStatus="current">
	                                <option value="${item.key}" ${item.message}>${item.value}</option>
	                            </c:forEach>
	                        </select>
                        	&nbsp;
                            <input type="search" maxlength="30" class="width-40" placeholder="请输入商品名称或关键字"
                                   name="name" value="${query.name}">
                            <input type="hidden" name="listIds" value="${listIds}"/>
                            <button type="button" class="btn btn-purple btn-sm search-button">
                                <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                		搜索
                            </button>
                        </div>
                </div>
            </form>
        </div>
        <div class="dataTables_wrapper form-inline no-footer">
            <table class="table table-striped table-bordered table-hover dataTable no-footer">
                <thead>
                <tr role="row">
                    <th>商品ID</th>
                    <th>名称</th>
                    <th>库存</th>
                    <th>原价</th>
                    <th>折扣价</th>
                    <th class="sorting_disabled">操作</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${result}" var="item" varStatus="current">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <td>${item.stock}</td>
                        <td>${item.price}</td>
                        <td>${item.discount}</td>
                        <td>
                            <div class="hidden-sm hidden-xs action-buttons">
                                <a title="选择" 
                                <c:if test="${item.isCheck==1}" > class="grey" </c:if>
                                <c:if test="${item.isCheck==0}" > class="green add-btn" </c:if>  
                                   data-id="${item.id}" 
                                   data-name="${item.name}" 
                                   data-stock="${item.stock}" 
                                   data-price="${item.price}"
                                   data-purchase="${item.purchase}"
                                   data-discount="${item.discount}"
                                   data-merchandiseId="${item.merchandiseId}"
                                   data-unifiedMerchandiseId="${item.unifiedMerchandiseId}"
                                   data-merchantId="${item.merchantId}"
                                   data-mallClassifyId="${item.mallClassifyId}"
                                   data-merchantClassifyId="${item.merchantClassifyId}"
                                   data-keywords="${item.keywords}"
                                   href="">
                                    <i class="ace-icon glyphicon glyphicon-ok bigger-130"></i>
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

<script type="text/javascript">
    var scripts = [null, "/qcloud-admin/assets/js/jquery.colorbox-min.js","/qcloud-admin/assets/js/upload-img.js","/qcloud-admin/assets/js/chosen.jquery.min.js", null];
    ace.load_ajax_scripts(scripts, function () {
    	$('.chosen-select').chosen({
    		allow_single_deselect:true,
    		no_results_text:'无搜索结果'
    	});
    	$(window)
        .off('resize.chosen')
        .on('resize.chosen', function() {
            $('.chosen-select').each(function() {
                 var $this = $(this);
                 $this.next().css({'width': '300'});
            })
        }).trigger('resize.chosen');
    })
</script>