<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>编辑商品属性</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        商品属性管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            编辑 
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="model-form" class="form-horizontal"  role="form" action="/admin/merchandise/editAttributes.do">
            <!-- #section:elements.form -->
            <input type="hidden" name="merchandiseId" value="${merchandiseId}">
			<input type="hidden" name="listSize" id="listSize" value="${fn:length(result)}">
		<c:forEach items="${result}" var="result" varStatus="status">
		  <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="catalogId">	${result.name}	</label>
              	<div class="col-sm-9">
                    <span class="col-sm-9 no-padding block input-icon input-icon-right mr10">
                    	<input type="hidden" name="list[${status.index}].key" value="${result.id}">
                    	<c:if test="${result.type eq 1}">
							<input name="list[${status.index}].value" id="list[${status.index}].value" class="width-100" value="${result.value}"/>
                		</c:if>
                        <c:if test="${result.type eq 2}">
	                        <select class="chosen-select" name="list[${status.index}].value" data-placeholder="请选择类型">
	                        	<c:forEach items="${result.list}" var="list">
	                            	<option value="${list.key}" ${list.message}>${list.value}</option>
	                            </c:forEach>
	                        </select>
           				</c:if>
           				<c:if test="${result.type eq 3}">
           					<c:forEach items="${result.list}" var="list">
           						<input type="radio" ${list.message} name="list[${status.index}].value" id="list[${status.index}].value" value="${list.value}"/>${list.value}
	                        </c:forEach>
                		</c:if>
                		<c:if test="${result.type eq 4}">
		                    <c:forEach items="${result.list}" var="list">
           						<input type="checkbox" ${list.message} name="list[${status.index}].value" id="list[${status.index}].value" value="${list.value}"/>${list.value}
	                        </c:forEach>
                		</c:if>
                		<c:if test="${result.type eq 5}">
		                    <textarea style="height:200px;resize:none;"  class="col-xs-10 no-float" id="list[${status.index}].value" name="list[${status.index}].value">${result.value}</textarea>
                		</c:if>
                		<c:if test="${result.type eq 6}">
		                    <textarea style="height:200px;" class="col-xs-10 no-float" id="details${status.index}" name="list[${status.index}].value">${result.value}</textarea>
                		</c:if>
                    </span>
                </div>
            </div>
         </c:forEach>
            
            <div class="space-4"></div>
            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>&nbsp;保&nbsp;存&nbsp;</button>
                    &nbsp; &nbsp; &nbsp;
                    <button id="back" class="btn" type="button"><i class="ace-icon fa fa-undo bigger-110"></i>&nbsp;返&nbsp;回&nbsp;</button>
                </div>
            </div>

        </form>
        <!-- PAGE CONTENT ENDS -->
    </div>
    <!-- /.col -->
</div><!-- /.row -->

<script type="text/javascript">
    var scripts = [null, "/qcloud-admin/assets/js/jquery.colorbox-min.js","/qcloud-admin/assets/js/upload-img.js","/qcloud-admin/assets/js/chosen.jquery.min.js", null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
        jQuery(function ($) {
        	$('.chosen-select').chosen({allow_single_deselect:true}); 
            $(window)
            .off('resize.chosen')
            .on('resize.chosen', function() {
                $('.chosen-select').each(function() {
                     var $this = $(this);
                     $this.next().css({'width': $this.parent().width()});
                })
            }).trigger('resize.chosen');
            
            //动态    --- >  初始化富文本编辑器
            var listSize=$("#listSize").val();
            for(var i=0;i<listSize;i++){
            	createEditor("details"+i);
            }
            
            //表单验证
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
        });
    })
    function createEditor(id){
    	window.UMEDITOR_HOME_URL = "/qcloud-admin/umeditor/";
        var serverPath = '/admin/file/';
    	var um0 = UM.getEditor(id, {
            imageUrl: serverPath + "addUMEditorFile.do",
            imagePath: "/file/",
            lang: /^zh/.test(navigator.language || navigator.browserLanguage || navigator.userLanguage) ? 'zh-cn' : 'en',
            langPath: UMEDITOR_CONFIG.UMEDITOR_HOME_URL + "lang/",
            focus: true
        });
        UM.clearCache(id);
    }
</script>
