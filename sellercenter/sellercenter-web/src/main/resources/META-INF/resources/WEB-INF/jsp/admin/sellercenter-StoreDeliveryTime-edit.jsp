<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>编辑配送时间</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        配送时间管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            编辑
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="model-form" class="form-horizontal"  role="form" action="/admin/storeDeliveryTime/edit.do">
            <!-- #section:elements.form -->
			<input type="hidden" name="id" value="${storeDeliveryTime.id}">
			<input type="hidden" name="storeId" value="${storeDeliveryTime.storeId}">
						      		                  		
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="pickupStartTime"> 自提开始时间 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						 <select class="form-control" id="pickupStartTime" name="pickupStartTime">
					 		  <option value="-1" selected>请选择自提开始时间</option>
					 		  <c:forEach items="${pickupStartTimeList}" var="item" varStatus="current"> 
			                  	 <option value="${item.key}" ${item.message}>${item.value}</option>
			                  </c:forEach> 
			             </select>						
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="pickupEndTime"> 自提结束时间 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<select class="form-control" id="pickupEndTime" name="pickupEndTime">
					 		  <option value="-1" selected>请选择自提结束时间</option>
					 		  <c:forEach items="${pickupEndTimeList}" var="item" varStatus="current"> 
			                  	 <option value="${item.key}" ${item.message}>${item.value}</option>
			                  </c:forEach> 
			             </select>	
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="pickupDesc"> 自提描述 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" id="pickupDesc" name="pickupDesc" placeholder="自提描述" value="${storeDeliveryTime.pickupDesc}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="deliveryStartTime"> 送货上门开始时间 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<select class="form-control" id="deliveryStartTime" name="deliveryStartTime">
					 		  <option value="-1" selected>请选择送货上门开始时间</option>
					 		  <c:forEach items="${deliveryStartTimeList}" var="item" varStatus="current"> 
			                  	 <option value="${item.key}" ${item.message}>${item.value}</option>
			                  </c:forEach> 
			            </select>	
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="deliveryEndTime"> 送货上门结束时间 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<select class="form-control" id="deliveryEndTime" name="deliveryEndTime">
					 		  <option value="-1" selected>请选择送货上门结束时间</option>
					 		  <c:forEach items="${deliveryEndTimeList}" var="item" varStatus="current"> 
			                  	 <option value="${item.key}" ${item.message}>${item.value}</option>
			                  </c:forEach> 
			            </select>
			   		</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="deliveryFrequency"> 送货频率 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<select class="form-control" id="deliveryFrequency" name="deliveryFrequency">
					 		  <option value="-1" selected>请选择送货频率</option>
					 		  <c:forEach items="${deliveryFrequencyList}" var="item" varStatus="current"> 
			                  	 <option value="${item.key}" ${item.message}>${item.value}</option>
			                  </c:forEach> 
			            </select>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="deliveryDuration"> 送货时长 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<select class="form-control" id="deliveryDuration" name="deliveryDuration">
					 		  <option value="-1" selected>请选择送货频率</option>
					 		  <c:forEach items="${deliveryDurationList}" var="item" varStatus="current"> 
			                  	 <option value="${item.key}" ${item.message}>${item.value}</option>
			                  </c:forEach> 
			            </select>
					</span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>&nbsp;保&nbsp;存&nbsp;</button>
                    &nbsp; &nbsp; &nbsp;
                    <button class="btn" type="button" onclick="window.location.href='#admin/store/list'"><i class="ace-icon fa fa-undo bigger-110"></i>&nbsp;返&nbsp;回&nbsp;</button>
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
