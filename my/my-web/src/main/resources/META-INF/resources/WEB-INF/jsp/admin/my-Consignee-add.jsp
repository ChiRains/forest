<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<style>
	.select-product-dialog {
        top: 50px;
    }

    .select-product-dialog tr {
        word-break: break-all;
    }

    .select-product-dialog .modal-content {
        min-height: 600px;
        min-width: 800px;
    }

</style>
<div class="row">
    <div class="col-xs-12">
    <div class="row">
        <!-- PAGE CONTENT BEGINS -->
        <form id="consignee-form" class="form-horizontal"  role="form" action="/admin/consignee/add.do">
            <!-- #section:elements.form -->
			<input type="hidden" class="width-100" maxlength="20" id="userId" name="userId" value="${userId}"/>
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="userName"> 收货人姓名 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="name" name="name" placeholder="收货人姓名" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="mobile"> 联系电话 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="mobile" name="mobile" placeholder="联系电话" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="province"> 省份 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<select class="form-control" id="province" name="province">
					 		  <option value="" selected>请选择一个省份</option>
	                          <c:forEach items="${provinceList}" var="item" varStatus="current"> 
			                  	 <option value="${item.key}" ${item.message}>${item.value}</option>
			                  </c:forEach> 
			             </select>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="city"> 市 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<select class="form-control" id="city" name="city">
					 		  <option value="" selected>请选择一个市</option>
	                          <c:forEach items="${cityList}" var="item" varStatus="current"> 
			                  	 <option value="${item.key}" ${item.message}>${item.value}</option>
			                  </c:forEach> 
			             </select>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="district"> 区 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<select class="form-control" id="district" name="district">
					 		  <option value="" selected>请选择一个市</option>
	                          <c:forEach items="${districtList}" var="item" varStatus="current"> 
			                  	 <option value="${item.key}" ${item.message}>${item.value}</option>
			                  </c:forEach> 
			             </select>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="address"> 详细地址 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="address" name="address" placeholder="详细地址（不包含省市区）" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="email"> Email </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="email" name="email" placeholder="邮箱" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="zipCode"> 邮编 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="zipCode" name="zipCode" placeholder="邮编" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            <div class="space-4"></div>
            <div class="clearfix">
                <div class="col-md-offset-3 col-md-9">
                	<input type="hidden" id="submit_flag" value="0"/>
                    <button class="btn btn-info btn-submit consignee_btn" style="display:none;" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>&nbsp;保&nbsp;存&nbsp;</button>
                </div>
            </div>
        </form>
        </div>
        <!-- PAGE CONTENT ENDS -->
    </div>
    <!-- /.col -->
</div><!-- /.row -->

<script type="text/javascript">
    var scripts = [null, "/qcloud-admin/assets/js/jquery.colorbox-min.js","/qcloud-admin/assets/js/upload-img.js","/qcloud-admin/assets/js/chosen.jquery.min.js", null];
    ace.load_ajax_scripts(scripts, function () {
     	
        jQuery(function ($) {
            $(window)
            .off('resize.chosen')
            .on('resize.chosen', function() {
                $('.chosen-select').each(function() {
                     var $this = $(this);
                     $this.next().css({'width': $this.parent().width()});
                })
            }).trigger('resize.chosen');
            
            $('#province').change(function(){
			 	var data = {
			 		province:$('#province').val()
            	};
			 	$.ajax({
					url:'/city/queryByProvince.do',
					type:'POST',
					data:data,
					dataType: 'json',
					cache: false,
					async: false,
					beforeSend: function(){
					
					},
					error: function(){
						BootstrapDialog.alert({
							title: '错误',
							message:'网络错误，请稍后再尝试！',
							type: BootstrapDialog.TYPE_DANGER
						});
					},
					success:function(rd){
						if(rd['status'] != 200){
							BootstrapDialog.alert({
								title: '错误',
								message:rd.message,
								type: BootstrapDialog.TYPE_DANGER
							});
						}else{
							$("#city").empty();		
							$("#district").empty();		
							var option0 = $("<option>").val(-1).text("请选择一个市"); 
							$("#city").append(option0);		
							
							var option1 = $("<option>").val(-1).text("请选择一个区"); 
							$("#district").append(option1);						
									
							var obj = rd.data.cityList;
							$.each(obj,function(n,value) {
       							var option = $("<option>").val(value.key).text(value.value); 
								$("#city").append(option);
       						});  	
						}
					}
				});
			});    
			
			$('#city').change(function(){
			 	var data = {
                	city:$('#city').val()
            	};			 	
			 	$.ajax({
					url:'/district/queryByCity.do',
					type:'POST',
					data:data,
					dataType: 'json',
					cache: false,
					async: false,
					beforeSend: function(){
					
					},
					error: function(){
						BootstrapDialog.alert({
							title: '错误',
							message:'网络错误，请稍后再尝试！',
							type: BootstrapDialog.TYPE_DANGER
						});
					},
					success:function(rd){
						if(rd['status'] != 200){
							BootstrapDialog.alert({
								title: '错误',
								message:rd.message,
								type: BootstrapDialog.TYPE_DANGER
							});
						}else{
							$("#district").empty();		
							var option0 = $("<option>").val(-1).text("请选择一个区"); 
							$("#district").append(option0);						
							var obj = rd.data.districtList;
							$.each(obj,function(n,value) {         
       							var option = $("<option>").val(value.key).text(value.value); 
								$("#district").append(option); 
       						});  	
						}
					}
				});
			});    

            //表单验证
            $("#consignee-form").validate({
                errorElement: 'div',
                errorClass: 'help-block col-xs-12 col-sm-reset inline',
                focusInvalid: false,
                rules: {
                    name: { //企业名
                        required: true
                    },
                    mobile: {
                        required: true
                    },
                    province: {
                    	required:true
                    },
                    city: {
                    	required:true
                    },
                    district: {
                    	required:true
                    },
                    address: {
                    	required:true
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
                    //postForm('consignee-form');
                	$("#submit_flag").val(0);
                },
                invalidHandler: function (form) {
                    $("#submit_flag").val(1);
                }
            });
        });
    });
</script>
