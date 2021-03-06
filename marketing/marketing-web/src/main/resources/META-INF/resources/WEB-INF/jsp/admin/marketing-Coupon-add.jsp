<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>新增管理员</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        优惠券管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增优惠券
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="model-form" class="form-horizontal" onSubmit="checkTime();"  role="form" action="/admin/coupon/add.do">
            <!-- #section:elements.form -->

                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="startDate"> 开始时间 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" readonly maxlength="20" id="startDate" name="startDate" placeholder="开始时间" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="endDate"> 结束时间 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" readonly maxlength="20" id="endDate" name="endDate" placeholder="结束时间" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="validDate"> 使用开始时间 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" readonly maxlength="20" id="validDate" name="validDate" placeholder="有效时间" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
                   		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="validDate"> 使用截止时间 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" readonly maxlength="20" id="invalidDate" name="invalidDate" placeholder="使用截止时间" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="totalOfPerson"> 总共领取张数/人 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="totalOfPerson" name="totalOfPerson" placeholder="总共领取张数/人" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="priceOfPerson"> 总共领取金额数/人 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="priceOfPerson" name="priceOfPerson" placeholder="总共领取金额数/人" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="interval"> 领取间隔时间 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-90" maxlength="20" id="interval" name="interval" placeholder="领取间隔时间 (分钟)" value="0"/>
						(天)
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="description"> 名称 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" id="description" name="description" placeholder="描述" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="image"> 图片 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="hidden"  id="image" name="image" value=""/>
						<button type="button" mult="false" sid="image" vid="pic-pic-view"   class="btn btn-sm btn-purple btn-upload-pic"  upfrom="0"  ><i class="ace-icon fa fa-upload"></i>
						 上 传</button>&nbsp;&nbsp;<span style="color:red">建议上传300*300图片！</span>
						<ul sid="pic" id="pic-pic-view" class="ace-thumbnails clearfix">	</ul>
					</span>
                </div>
            </div>
            
                   <div class="space-4"></div>
              <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="">  </label>
               <div class="col-sm-9">
					<span style="color:red;" class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						${fileSize}
					</span>
                </div>
            </div>
            
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="usedNum"> 使用范围 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<select id="type" name="type" class="width-100">
							<option value="1">线上</option>
							<option value="2">线下</option>
							<option value="3">线上+线下</option>
						</select>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>

            <div class="space-4"></div>
            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info"  type="submit"><i class="ace-icon fa fa-check bigger-110"></i>&nbsp;保&nbsp;存&nbsp;</button>
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
    var scripts = [null, "/qcloud-admin/assets/js/jquery.colorbox-min.js","/qcloud-admin/assets/js/date-time/bootstrap-datepicker.min.js","/qcloud-admin/assets/js/upload-img.js","/qcloud-admin/assets/js/chosen.jquery.min.js", null];
    
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
           
           //value是当前的值 ,params是传入的参数值
           $.validator.addMethod("vD",function(value,element,params){
           		var endDate=$("#endDate").val();
				var startDate=$("#startDate").val();
			   	if(value>=endDate){    //if(value>=startDate && value<=endDate){
			   		return true;
			   	}else{
			   	   	return false;
			   	}
			},"有效时间必须大于结束时间");       //},"有效时间必须在开始时间与结束时间之间");
			//
            $.validator.addMethod("eD",function(value,element,params){
				var startDate=$("#startDate").val();
			   	if(value>startDate){
			   		return true;
			   	}else{
			   	   	return false;
			   	}
			},"结束时间必须大于开始时间");
			//
			$.validator.addMethod("sD",function(value,element,params){
				var date = new Date();
				var y=date.getFullYear();
				var m=date.getMonth()+1;
				var d=date.getDate();
				if(m<10){
					m="0"+m;
				}
				if(d<10){
					d="0"+d;
				}
				var nowDate=y+"-"+m+"-"+d;
				var endDate=$("#endDate").val();
			   	if(value>nowDate){
			   		return true;
			   	}else{
			   	   	return false;
			   	}
			},"开始时间不能小于当前时间");
           
            //表单验证
            $("#model-form").validate({
                errorElement: 'div',
                errorClass: 'help-block col-xs-12 col-sm-reset inline',
                focusInvalid: false,
                rules: {
                	validDate:{
                		required: true,
                		vD:true
                	},
                	endDate:{
                		required: true,
                		eD:true
                	},
                	startDate:{
                		required: true,
                	},
                    priceOfPerson: {
                        required: true,
                        range: [0, 99999999],
                        digits: true
                    },
                    totalOfPerson: {
                        required: true,
                        range: [0, 99999999],
                        digits: true
                    } ,
                    interval: {
                        required: true,
                        range: [0, 99999999],
                        digits: true
                    },
                    name :{
                        required: true,
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
            
            $('#startDate').datepicker({
                format:'yyyy-mm-dd',
                autoclose:true,
                startDate: '-1y'
            });  
            $('#endDate').datepicker({
                format:'yyyy-mm-dd',
                autoclose:true,
                startDate: '-1y'
            });  
            $('#validDate').datepicker({
                format:'yyyy-mm-dd',
                autoclose:true,
                startDate: '-1y'
            });
            
            $('#invalidDate').datepicker({
                format:'yyyy-mm-dd',
                autoclose:true,
                startDate: '-1y'
            });
            
            
            
            
        });
        
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
    })
</script>
