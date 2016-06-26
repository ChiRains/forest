<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>编辑管理员</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        管理里管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            编辑
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="model-form" class="form-horizontal"  role="form" action="/admin/merchandise/edit.do">
            <!-- #section:elements.form -->
			<input type="hidden" name="id" value="${merchandise.id}">
			<input type="hidden" name="queryStr" value="${queryStr}">
			
			      		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="id"> ID </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="id" name="id" placeholder="ID" value="${merchandise.id}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="merchantClassifyId"> 商家分类ID </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="merchantClassifyId" name="merchantClassifyId" placeholder="商家分类ID" value="${merchandise.merchantClassifyId}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="mallClassifyId"> 商城分类ID </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="mallClassifyId" name="mallClassifyId" placeholder="商城分类ID" value="${merchandise.mallClassifyId}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="specClassifyId"> 规格分类ID </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="specClassifyId" name="specClassifyId" placeholder="规格分类ID" value="${merchandise.specClassifyId}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="merchantId"> 商家ID </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="merchantId" name="merchantId" placeholder="商家ID" value="${merchandise.merchantId}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name"> 名称 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="name" name="name" placeholder="名称" value="${merchandise.name}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="sysCode"> 系统编号 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="sysCode" name="sysCode" placeholder="系统编号" value="${merchandise.sysCode}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="code"> 商品编号 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="code" name="code" placeholder="商品编号" value="${merchandise.code}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="image"> 图片,缩略图 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="image" name="image" placeholder="图片,缩略图" value="${merchandise.image}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="keywords"> 关键字 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="keywords" name="keywords" placeholder="关键字" value="${merchandise.keywords}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="weight"> 重量 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="weight" name="weight" placeholder="重量" value="${merchandise.weight}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="state"> 状态 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="state" name="state" placeholder="状态" value="${merchandise.state}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="unit"> 单位 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="unit" name="unit" placeholder="单位" value="${merchandise.unit}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="details"> 详情 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="details" name="details" placeholder="详情" value="${merchandise.details}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="desc"> 描述 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="desc" name="desc" placeholder="描述" value="${merchandise.desc}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="isCertified">  </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="isCertified" name="isCertified" placeholder="" value="${merchandise.isCertified}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="isSpecialService">  </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="isSpecialService" name="isSpecialService" placeholder="" value="${merchandise.isSpecialService}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="isNoReason">  </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="isNoReason" name="isNoReason" placeholder="" value="${merchandise.isNoReason}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="isExternalUrl">  </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="isExternalUrl" name="isExternalUrl" placeholder="" value="${merchandise.isExternalUrl}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="certified">  </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="certified" name="certified" placeholder="" value="${merchandise.certified}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="specialService">  </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="specialService" name="specialService" placeholder="" value="${merchandise.specialService}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="noReason">  </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="noReason" name="noReason" placeholder="" value="${merchandise.noReason}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="externalUrl">  </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="externalUrl" name="externalUrl" placeholder="" value="${merchandise.externalUrl}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="isIncludePost">  </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="isIncludePost" name="isIncludePost" placeholder="" value="${merchandise.isIncludePost}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="brandId">  </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="brandId" name="brandId" placeholder="" value="${merchandise.brandId}"/>
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
</script>
