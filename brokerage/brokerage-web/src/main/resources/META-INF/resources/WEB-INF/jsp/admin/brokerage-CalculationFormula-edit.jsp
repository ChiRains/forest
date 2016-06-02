<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>编辑佣金公式</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        佣金公式管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            编辑
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="model-form" class="form-horizontal"  role="form" action="/admin/calculationFormula/edit.do">
            <!-- #section:elements.form -->
			<input type="hidden" name="id" value="${calculationFormula.id}">
			<input type="hidden" name="queryStr" value="${queryStr}">
			
						<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name"> 公式名称 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="name" name="name" placeholder="公式名称" value="${calculationFormula.name}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="formula"> 公式 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<textarea style="height:200px;" class="width-100" id="formula" name="formula">${calculationFormula.formula}</textarea>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="tradeUserDistribution"> 交易用户是否参加分佣 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<select class="form-control" id="tradeUserDistribution" name="tradeUserDistribution" >
							<c:if test="${calculationFormula.tradeUserDistribution==1}">
								<option value="1" selected="selected">参加</option>
								<option value="2">不参加</option>
							</c:if>
							<c:if test="${calculationFormula.tradeUserDistribution==2}">
								<option value="1">参加</option>
								<option value="2" selected="selected">不参加</option>
							</c:if>
						</select>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="proportion"> 分配比例 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" <c:if test="${calculationFormula.tradeUserDistribution==2}">readonly</c:if> maxlength="20" id="proportion" name="proportion" placeholder="分配比例,如50%,则存50" value="${calculationFormula.proportion}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            			<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="poundageRate"> 手续费率 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" <c:if test="${calculationFormula.tradeUserDistribution==2}">readonly</c:if> maxlength="20" id="poundageRate" name="poundageRate" placeholder="手续费率,如50%,则存50" value="${calculationFormula.poundageRate}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
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
            $(window)
            .off('resize.chosen')
            .on('resize.chosen', function() {
                $('.chosen-select').each(function() {
                     var $this = $(this);
                     $this.next().css({'width': $this.parent().width()});
                })
            }).trigger('resize.chosen');
            
            $("#tradeUserDistribution").change(function(){
            	var tradeUserDistribution=$(this).val();
            	if(tradeUserDistribution==2){
            		$("#proportion").attr("readonly",true);
            		$("#proportion").val("0");		
            		$("#poundageRate").attr("readonly",true);
            		$("#poundageRate").val("0");
            	}else{
            		$("#proportion").attr("readonly",false);	
            		$("#poundageRate").attr("readonly",false);
            	}
            });       
            //表单验证
            $("#model-form").validate({
                errorElement: 'div',
                errorClass: 'help-block col-xs-12 col-sm-reset inline',
                focusInvalid: false,
                rules: {
                    name: {
                        required: true
                    },
                    formula: {
                        required: true
                    },

                    proportion: {
                        required: true,
                        range: [0, 100],
                        digits: true
                    },
                    poundageRate: {
                        required: true,
                        range: [0, 100],
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
</script>
