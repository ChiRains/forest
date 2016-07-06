<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>编辑广告图</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />
<link rel="stylesheet"
	href="/qcloud-admin/assets/css/bootstrap-datetimepicker.css" />

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        广告图管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            编辑
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="model-form" class="form-horizontal"  role="form" action="/admin/slide/edit.do">
            <!-- #section:elements.form -->
            <input type="hidden" id="enable"  name="enable" value="${slide.enable}">
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="title"> 标题 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="50" id="title" name="title" placeholder="标题" value="${slide.title}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div> 
            
			<input type="hidden" name="id" value="${slide.id}">		                  		
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="image"> 图片 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="hidden"  id="image" name="image" value="${slide.imageUid}"/>
						<button type="button" mult="false" sid="image" vid="pic-pic-view"   class="btn btn-sm btn-purple btn-upload-pic"  upfrom="0"  ><i class="ace-icon fa fa-upload"></i> 上 传</button><ul sid="pic" id="pic-pic-view" class="ace-thumbnails clearfix">
							<img style="max-width:150px;max-height:150px;" alt="" src="${slide.image}">
						</ul>
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
                <label class="col-sm-3 control-label no-padding-right" for="desc"> 描述 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="50" id="desc" name="desc" placeholder="描述" value="${slide.desc}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div> 
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="clickUrl"> 点击地址 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="200" id="clickUrl" name="clickUrl" placeholder="点击地址" value="${slide.clickUrl}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>            
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="sence"> 场景 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<select class="form-control" id="sence" name="sence">
							  <option value="-1" selected>请选择类别</option>
							  <c:forEach items="${senceTypeList}" var="item" varStatus="current"> 
			                	 <option value="${item.key}" ${item.message}>${item.value}</option>			                                   		
			                  </c:forEach> 
			            </select> 
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="orderNum"> 排序 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">											
						<select class="form-control" id="orderNum" name="orderNum">
							  <option value="-1" selected>请选择顺序,小在前</option>
							  <c:forEach items="${orderNumList}" var="item" varStatus="current"> 
			                	 <option value="${item.key}" ${item.message}>${item.value}</option>			                                   		
			                  </c:forEach> 
			            </select> 						
					</span>
                </div>
            </div>
            
  			<div class="space-4"></div>
			<div class="form-group"><label
				class="col-sm-3 control-label no-padding-right"
				for="activityStrDate"> 开始时间 </label>
				<div class="col-sm-9"><span
					class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" id="startDate"
						placeholder="开始时间 " name="startDate" value="<fmt:formatDate value="${slide.startDate}"  pattern="yyyy-MM-dd"/>" /> <i
						class="ace-icon fa"></i>
				</span></div></div>
			<div class="space-4"></div>
			<div class="form-group"><label
				class="col-sm-3 control-label no-padding-right"
				for="activityEndDate"> 结束时间 </label>
				<div class="col-sm-9"><span
					class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" id="endDate"
						placeholder="结束时间" name="endDate" value="<fmt:formatDate value="${slide.endDate}"  pattern="yyyy-MM-dd"/>" /> <i
						class="ace-icon fa"></i>
				</span></div></div>
            
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
    var scripts = [null, "/qcloud-admin/assets/js/jquery.colorbox-min.js",
                   "/qcloud-admin/assets/js/jquery.colorbox-min.js",
                   "/qcloud-admin/assets/js/chosen.jquery.min.js",
                   "/qcloud-admin/assets/js/upload-img.js",
                   "/qcloud-admin/assets/js/chosen.jquery.min.js", 
                   "/qcloud-admin/assets/js/moment.min.js",
                   "/qcloud-admin/assets/js/bootstrap-datetimepicker.js", 
                   "/qcloud-admin/assets/js/date-time/bootstrap-datetimepicker.min.js", , null];
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
			

			//开始时间
            $('#startDate').datetimepicker({
                
    			language: 'zh-cn',
    			//format: 'MM/DD/YYYY'
    			format: 'YYYY-MM-DD',
    		}).next().on(ace.click_event, function(){
				$(this).prev().focus();
			});
            
			//结束时间
		 $('#endDate').datetimepicker({
    			language: 'zh-cn',
    			//format: 'MM/DD/YYYY'
    			format: 'YYYY-MM-DD',
    		}).next().on(ace.click_event, function(){
				$(this).prev().focus();
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
