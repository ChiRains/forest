<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>编辑用户等级</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
     用户等级管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            编辑
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="delivery-form" class="form-horizontal"  role="form" action="/admin/grade/edit.do">
            <!-- #section:elements.form -->
			<input type="hidden" name="id" value="${grade.id}">
						      		
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name"> 名称 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="name" name="name" placeholder="名称" value="${grade.name}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="desc"> 等级描述 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="desc" name="desc" placeholder="等级描述" value="${grade.desc}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
            			<div class="space-4"></div>
            <div class="form-group">
            	<label class="col-sm-3 control-label no-padding-right" for="image"> 图标 </label>
                <div class="col-sm-9">
					<input type="hidden"  id="image" name="image" value="${grade.imageUid}"/>
					<button type="button" mult="false" sid="image" vid="pic-pic-view"   class="btn btn-sm btn-purple btn-upload-pic"  upfrom="0"  >
					<i class="ace-icon fa fa-upload"></i> 上 传</button>
					<ul sid="image" id="pic-pic-view" class="ace-thumbnails clearfix">
						<img style="max-width:150px;max-height:150px;" alt="" src="${grade.image}">
					</ul>					 
				</div>               
            </div>  
                              		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="point"> 积分 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="point" name="point" placeholder="积分" value="${grade.point}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                                     <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="discount"> 折扣 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="3" id="discount" name="discount" placeholder="折扣" value="${grade.discount}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
            
            <%--				<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="discount"> 价格类型  </label>
                <div class="col-sm-9">
					<span class="col-sm-12 no-padding block input-icon input-icon-right mr10">
						<select id="priceType" name="priceType" class="col-sm-5">
							<c:if test="${grade.priceType==1}">
								<option value="1" selected="selected">原价</option>
								<option value="2">折扣价</option>
							</c:if>
							<c:if test="${grade.priceType==2}">
								<option value="1">原价</option>
								<option value="2" selected="selected">折扣价</option>
							</c:if>
						</select>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
             --%>
            
            
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
                
                
                  //初始化图片浏览
            $('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
            $("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange'></i>");

            var btnUpload = $(".btn-upload-pic");
            delEvent(getButtonSetting(btnUpload));
            //绑定事件
            btnUpload.on('click', function () {
                var bs = getButtonSetting($(this));
                uploadDialog(bs);
            });
                   
            //表单验证
            $("#delivery-form").validate({
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
                    },
                    point: {
                        required: true,
                        range: [0, 99999999],
                        digits: true
                    },
                    discount: {
                        required: true,
                        range: [0, 100],
                        digits: true
                    },
                    image: {
                    	required: true
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
                    postForm('delivery-form');
                },
                invalidHandler: function (form) {
                }
            });
        });
    })
</script>
