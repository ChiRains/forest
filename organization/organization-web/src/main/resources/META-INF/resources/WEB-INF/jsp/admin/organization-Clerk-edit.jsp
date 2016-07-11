<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>编辑成员</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        成员管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            编辑
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="model-form" class="form-horizontal"  role="form" action="/admin/clerk/edit.do">
            <!-- #section:elements.form -->
			<input type="hidden" name="id" value="${clerk.id}">
			<input type="hidden" name="departmentId" value="${clerk.departmentId}">
			
      <%--		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="account"> 账号 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" readonly maxlength="20" id="account" name="account" placeholder="账号" value="${clerk.account}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>   --%>
            
      		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name"> 姓名 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="name" name="name" placeholder="姓名" value="${clerk.name}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
              	
      		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="mobile"> 手机号 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="mobile" name="mobile" placeholder="手机号" value="${clerk.mobile}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="jobEmail"> 邮箱 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="jobEmail" name="jobEmail" placeholder="邮箱" value="${clerk.jobEmail}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="idCard"> 身份证 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="idCard" name="idCard" placeholder="身份证" value="${clerk.idCard}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="sex"> 性别 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<select class="form-control" id="sex" name="sex">						
			                <c:forEach items="${sexTypeList}" var="item" varStatus="current"> 
			               		<option <c:if test="${clerk.sex eq item.key}"> selected </c:if> value="${item.key}" ${item.message}>${item.value}</option>			                                   		
			            	</c:forEach> 
			            </select>
					</span>
                </div>
            </div>
             
             <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="headImage"> 头像 </label>
                <div class="col-sm-9">
		    <span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
			<input type="hidden" id="headImage" name="headImage" value="${clerk.headImageUid}"/>
                    	<button type="button" mult="false" sid="headImage" vid="pic1-pic-view" class="btn btn-sm btn-purple btn-upload-pic" upfrom="0">
                        <i class="ace-icon fa fa-upload"></i> 上 传
                    	</button>
                    <ul sid="headImage" id="pic1-pic-view" class="ace-thumbnails clearfix">
                    	<img src="${clerk.headImage}" width="120" height="80"/>
                    </ul>
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
            $("#model-form").validate({
                errorElement: 'div',
                errorClass: 'help-block col-xs-12 col-sm-reset inline',
                focusInvalid: false,
                rules: {
                    name: {
                        required: true
                    },
                    mobile: {
                        required: true,
                        digits: true
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
                	var mobile=$("#mobile").val();
                	var mobileReg=/^1[3,4,5,7,8,9]\d{9}$/;
                	var email=$("#jobEmail").val();
                	var emailReg=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
                	var idCard=$("#idCard").val();
                	var idCardReg=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/; 
                	if(!mobileReg.test(mobile)){
                		 BootstrapDialog.show({
                		 	message:'请输入正确的电话号码'
                		 });
                		return false;
                	}
                	if(email!=""){
                		if(!emailReg.test(email)){
	                		 BootstrapDialog.show({
	                		 	message:'邮箱格式不正确'
	                		 });
	                		return false;
	                	}
                	}
                	if(idCard!=""){
                		if(!idCardReg.test(idCard)){
	                		 BootstrapDialog.show({
	                		 	message:'身份证格式不正确'
	                		 });
	                		return false;
	                	}
                	}
                
                    postForm('model-form');
                },
                invalidHandler: function (form) {
                }
            });
        });
    })
</script>
