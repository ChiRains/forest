<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>新增管理员</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />
<link rel="stylesheet"
	href="/qcloud-admin/assets/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" href="/qcloud-admin/assets/css/datepicker.css" />
<link rel="stylesheet"
	href="/qcloud-admin/assets/css/bootstrap-timepicker.css" />
<link rel="stylesheet"
	href="/qcloud-admin/assets/css/daterangepicker.css" />
<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        满减活动管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="model-form" class="form-horizontal"  role="form" action="/admin/fullReduces/add.do">
            <!-- #section:elements.form -->

                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name">活动名称</label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="name" name="name" placeholder="活动名称" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="benefit"> 优惠金额 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="benefit" name="benefit" placeholder="优惠金额" value="" onkeyup="value=value.replace(/[\W]/g,'') "   
							onbeforepaste="this.setData('text', this.getData('text').replace(/[^\d]/g,''))"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="limitPrice"> 使用限额 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="limitPrice" name="limitPrice" placeholder="使用限额" value="" onkeyup="value=value.replace(/[\W]/g,'') "   
							onbeforepaste="this.setData('text', this.getData('text').replace(/[^\d]/g,''))"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="beginDate"> 活动开始时间 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="beginDate" name="beginDate" placeholder="活动开始时间" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="endDate"> 活动结束时间 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="endDate" name="endDate" placeholder="活动结束时间" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="canUseCoupon"> 与优惠券一起使用</label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<select class="width-100"  id="canUseCoupon" name="canUseCoupon"  > 
						<option value="2" selected="selected">否 </option>
						<option value="1" >是</option>
						</select> 
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="canUseSeckill"> 与秒杀一起使用 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<select class="width-100" id="canUseSeckill" name="canUseSeckill" > 
						<option value="2" selected="selected">否 </option>
						<option value="1" >是</option>
						</select> 
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
var scripts = [null,"/qcloud-admin/assets/js/moment.min.js","/qcloud-admin/assets/js/bootstrap-datetimepicker.js", 
               "/qcloud-admin/assets/js/jquery.colorbox-min.js","/qcloud-admin/assets/js/upload-img.js",
               "/qcloud-admin/assets/js/chosen.jquery.min.js",
               "/qcloud-admin/assets/js/date-time/bootstrap-datetimepicker.min.js", null];    
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

            //获取当前时间
	          var date = new Date();
	          var seperator1 = "-";
	          var seperator2 = ":";
	          var month = date.getMonth() + 1;
	          var strDate = date.getDate();
	          if (month >= 1 && month <= 9) {
	              month = "0" + month;
	          }
	          if (strDate >= 0 && strDate <= 9) {
	              strDate = "0" + strDate;
	          }
	          var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
	                  + " " + date.getHours() + seperator2 + date.getMinutes()
	                  + seperator2 + date.getSeconds();
          
            
			//开始时间
            $('#beginDate').datetimepicker({
    			language: 'zh-cn',
    			format: 'YYYY-MM-DD',
                minDate:currentdate
    		}).next().on(ace.click_event, function(){
				$(this).prev().focus();
			});
            
			//结束时间
		 	$('#endDate').datetimepicker({
    			language: 'zh-cn',
    			format: 'YYYY-MM-DD',
    			minDate:currentdate
    		}).next().on(ace.click_event, function(){
				$(this).prev().focus();
			});
			//验证时间
		 	$.validator.addMethod("isCheckTime",function(value, element,params){  
	               var isCheckTime=true;
	               var beginDate =$('#beginDate').val();
	               var endDate =$('#endDate').val();
	               if(endDate<=beginDate)
	               {
	            	   isCheckTime=false;
		           }
					
	               return isCheckTime;
	            },"结束时间不能小于开始时间");  
                   
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
                        number:true,
                        required: true
                    },
                    benefit: {
                        number:true,
                        required: true
                    },
                    limitPrice: {
                        required: true
                    },
                    endDate: {
                        required: true,
                        isCheckTime:true
                        
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
