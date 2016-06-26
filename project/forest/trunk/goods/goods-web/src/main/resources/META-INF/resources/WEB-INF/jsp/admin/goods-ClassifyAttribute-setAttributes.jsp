<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>

<title>设置商品分类与商品属性关联</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        商品分类与属性管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            给${classify.name}设置属性
        </small>
    </h1>
</div>
<!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            属性列表
        </div>

        <!-- <div class="table-responsive"> -->
		
        <!-- <div class="dataTables_borderWrap"> -->
        <div>
        
            <div class="dataTables_wrapper form-inline no-footer">
            	
            
                <div class="row">
		          <!--    <div class="col-xs-6">
		                <div class="dataTables_length">
		                    <a title="新增" class="btn btn-sm btn-info"
		                       href="#admin/classifyAttribute/toAddClassifyAttributes?classifyId=${classify.id}">
		                        <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
		                        新&nbsp;增
		                    </a>
		                </div>
		            </div>  -->
		            
		             <div class="col-xs-6">
		                <div class="dataTables_length">
		                    <a title="新增" class="btn btn-sm btn-info"
		                       href="#admin/classifyAttribute/toAddClassifyAttr?classifyId=${classify.id}">
		                        <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
		                        新&nbsp;增
		                    </a>
		                </div>
		            </div>
                    
                    <div class="col-xs-6" style="text-align: right;">
                        <a href="#admin/classifyAttribute/list" class="btn btn-sm btn-info">&ensp;返&emsp;回&ensp;</a>
                    </div>
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">
                    	<th>序号</th>
                        <th>属性</th>
                        <th>排序</th>
                        <th>操作</th>
                        
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${result}" var="item" varStatus="current">
                        <tr>
                        	<td>${current.index+1}</td>
                            <td>${item.attributeStr}</td>
                            <td>${item.sort}</td>
                            <td>	
                            	<!-- <a class="green" title="修改属性" href="#admin/classifyAttribute/toEditClassifyAttributes?id=${item.id}">
                            	 <i class="ace-icon fa fa-pencil bigger-130"></i>
                            	 </a> -->   
                            	 <c:if test="${item.isParent!=1}">
                            	 	<a class="green" title="修改属性" href="#admin/classifyAttribute/toEditClassifyAttr?id=${item.id}">
	                            	 <i class="ace-icon fa fa-pencil bigger-130"></i>
	                            	 </a>
                            	 </c:if>
                            	 <c:if test="${item.isParent==1}">
                            	 	父类[${item.classify}]属性,只读
                            	 </c:if> 	
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

        $(".ajax_switch").on('change', function () {
            var el = $(this);
            var data = {
                classifyId: el.attr('data-classify'),
                attributeId: el.attr('data-attribute'),
                value: el[0].checked ? '1' : '0'
            };
            $.ajax({
                url: '/admin/classifyAttribute/setAttribute.do',
                type: 'POST',
                data: data,
                dataType: 'json',
                cache: false,
                async: false,
                error: function () {
                    BootstrapDialog.alert({
                        title: '错误',
                        message: '网络错误，请稍后再尝试！',
                        type: BootstrapDialog.TYPE_DANGER,
                        callback: function () {
                            setTimeout(function () {
                                el[0].checked = !el[0].checked;
                            }, 500)
                        }
                    });
                },
                success: function (rd) {
                    if (rd['status'] != 200) {
                        BootstrapDialog.alert({
                            title: '错误',
                            message: rd.message,
                            type: BootstrapDialog.TYPE_DANGER,
                            callback: function () {
                                setTimeout(function () {
                                    el[0].checked = !el[0].checked;
                                }, 500)
                            }
                        });
                    }
                }
            })
        })
    });
</script>
