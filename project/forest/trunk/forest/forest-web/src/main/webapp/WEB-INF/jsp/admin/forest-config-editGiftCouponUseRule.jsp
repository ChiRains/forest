<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>编辑用户协议</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />
<link rel="stylesheet" href="/qcloud-admin/assets/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" href="/qcloud-admin/assets/css/datepicker.css" />
<link rel="stylesheet" href="/qcloud-admin/assets/css/bootstrap-timepicker.css" />
<link rel="stylesheet" href="/qcloud-admin/assets/css/daterangepicker.css" />
<link rel="stylesheet" href="/qcloud-admin/assets/css/bootstrap-datetimepicker.css" />
<script type="text/javascript" src="/qcloud-admin/assets/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="/qcloud-admin/assets/js/moment.min.js"></script>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        赠品券使用规则
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            编辑
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="model-form" class="form-horizontal"  role="form" action="/admin/config/editGiftCouponUseRule.do">
            <!-- #section:elements.form -->
			
                 <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="content">  </label>
                <div class="col-sm-3">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						 <textarea style="width: 800px;"  id="content" name="content" >${giftCouponUseRuleVO.content}</textarea>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>



            
            <div class="space-4"></div>
            <div class="clearfix form-actions">
                <div class="col-md-offset-5 col-md-5">
                    <button class="btn btn-info" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>&nbsp;保&nbsp;存&nbsp;</button>
                </div>
            </div>

        </form>
        <!-- PAGE CONTENT ENDS -->
    </div>
    <!-- /.col -->
</div><!-- /.row -->

<script type="text/javascript">
    var scripts = [null,"/qcloud-admin/assets/js/moment.min.js","/qcloud-admin/assets/js/bootstrap-datetimepicker.js", "/qcloud-admin/assets/js/jquery.colorbox-min.js","/qcloud-admin/assets/js/upload-img.js","/qcloud-admin/assets/js/chosen.jquery.min.js","/qcloud-admin/assets/js/chosen.jquery.min.js","/qcloud-admin/assets/js/date-time/bootstrap-datetimepicker.min.js", null];
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
            
/* 			$('#publishDate').datepicker({
				format : 'yyyy-mm-dd',
				autoclose : true,
				startDate : '-1y'
			}); */
			
	


			 $('#publishDate').datetimepicker({
    			language: 'zh-cn',
    			//format: 'MM/DD/YYYY'
    			format: 'YYYY-MM-DD HH:mm:SS'
    			
    		}).next().on(ace.click_event, function(){
				$(this).prev().focus();
			});
            
			setTimeout(function(){
				$(".edui-container").css("z-index","9");
			},500);
            
            
            window.UMEDITOR_HOME_URL = "/qcloud-admin/umeditor/";
            var serverPath = '/admin/file/';
            var um = UM.getEditor('content', {
                imageUrl: serverPath + "addUMEditorFile.do",
                imagePath: "/file/",
                lang: /^zh/.test(navigator.language || navigator.browserLanguage || navigator.userLanguage) ? 'zh-cn' : 'en',
                langPath: UMEDITOR_CONFIG.UMEDITOR_HOME_URL + "lang/",
                focus: true
            });
            UM.clearCache('content'); 
                   
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
                if($('#content').val()=="")
                {
                	BootstrapDialog.show({
            			 title:'错误',
            			 message :'请填写协议内容再提交',
            	                buttons: [{
            	                    label: '确定',
            	                    action: function (dialogItself) {
            	                        dialogItself.close();
            	                    }
            	                }]
            	            }); 
                    
                    }else if($('#content').val()!="")
                    {
                    	postForm('model-form');
                    }
                },
                invalidHandler: function (form) {
                }
            });
        });
    })
</script>
