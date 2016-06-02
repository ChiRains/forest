<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>新增商家</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />
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
<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
       商家管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="model-form" class="form-horizontal"  role="form" action="/admin/merchant/add.do">                           
            <!-- #section:elements.form -->
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name"> 名称 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="30" id="name" name="name" placeholder="名称" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="classifyId"> 商城商家分类 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
					 	<select class="form-control" id="classifyId" name="classifyId">
					 		  <option value="-1" selected>请选择一个分类</option>
					 		  <c:forEach items="${classifyList}" var="item" varStatus="current"> 
			                  	 <option value="${item.key}" ${item.message}>${item.value}</option>
			                  </c:forEach> 
			             </select> 	
					</span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="image"> 商家展示图 </label>

                <div class="col-sm-9">
                    <span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
                    <input type="hidden" id="image" name="image" value=""/>
                    <button type="button" mult="false" sid="image" vid="pic-pic-view" class="btn btn-sm btn-purple btn-upload-pic" upfrom="0">
                        <i class="ace-icon fa fa-upload"></i> 上 传
                    </button>
                    <ul sid="image" id="pic-pic-view" class="ace-thumbnails clearfix">
                    </ul>
                </span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="flagship"> 主打产品 </label>

                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="100" id="flagship" name="flagship" placeholder="主打产品" value="${merchant.flagship}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="commodityAuditing"> 审核商品 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
					 	<select class="form-control" id="commodityAuditing" name="commodityAuditing">
					 		  <c:forEach items="${commodityAuditingTypeList}" var="item" varStatus="current"> 
			                  	 <option value="${item.key}" ${item.message}>${item.value}</option>
			                  </c:forEach> 
			             </select> 	
					</span>
                </div>
            </div>
            
           <!--   <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="distribution"> 是否分佣 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
					 	<select class="form-control" id="distribution" name="distribution">
					 		  <c:forEach items="${distributionTypeList}" var="item" varStatus="current"> 
			                  	 <option value="${item.key}" ${item.message}>${item.value}</option>
			                  </c:forEach> 
			             </select> 	
					</span>
                </div>
            </div>  -->
            
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
					</span>
                </div>
            </div>
            
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="address"> 地址 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="50" id="address" name="address" placeholder="地址" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>                  		
           <%--       		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="introduction"> 简介 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="50" id="introduction" name="introduction" placeholder="简介" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>  --%>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="merchantType"> 商家类别 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
					 	<select class="form-control" id="merchantType" name="merchantType">
					 		  <option value="1">普通商家</option>
					 		  <option value="2">房地产商家</option>
					 		  <option value="3">招商商家</option>
			             </select> 	
					</span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="validDate"> 入驻有效截止时间 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
					 	<input type="text" class="width-100" maxlength="20" readonly id="validDate" name="validDate" placeholder="入驻有效截止时间" value=""/>
					</span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="buckle"> 成交扣点 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
					 	<input type="text" class="width-100" maxlength="20" id="buckle" name="buckle" placeholder="成交扣点" value=""/>
					</span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="userId"> 招商用户 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-75" maxlength="20" readonly id="userMobile" name="userMobile" placeholder="招商用户电话号码" value=""/>
						<input type="hidden"  maxlength="20" id="userId" name="userId"  value="-1"/>
						<button id="search-button" type="button" class="btn btn-purple btn-sm">
                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                          		 选择用户
                        </button>
						<i class="ace-icon fa"></i>
						<div style="color:red;">
							招商用户只能在新增商家时添加,添加完毕后无法修改！
						</div>
					</span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="isCertified"> 正品保证 </label>

               <div class="col-sm-9">
                    <input type="radio" name="isCertified" value="1"/>是
                    <input type="radio"  checked name="isCertified" value="2"/>否
               </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="isSpecialService"> 特色服务</label>

               <div class="col-sm-9">
                    <input type="radio"  name="isSpecialService" value="1"/>有
                    <input type="radio" checked name="isSpecialService" value="2"/>无
               </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="isNoReason"> 无理由退货 </label>

               <div class="col-sm-9">
                    <input type="radio"  name="isNoReason" value="1"/>是
                    <input type="radio" checked name="isNoReason" value="2"/>否
               </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="isExternalUrl"> 商品外部链接 </label>

               <div class="col-sm-9">
                    <input type="radio"  name="isExternalUrl" value="1"/>有
                    <input type="radio" checked name="isExternalUrl" value="2"/>无
               </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="isIncludePost"> 商品是否包邮 </label>

               <div class="col-sm-9">
                    <input type="radio"  name="isIncludePost" value="1"/>是
                    <input type="radio" checked name="isIncludePost" value="2"/>否
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
    var scripts = [null,"/qcloud-admin/assets/js/date-time/bootstrap-datepicker.min.js", "/qcloud-admin/assets/js/jquery.colorbox-min.js","http://api.map.baidu.com/api?v=2.0&ak=RsKB8DSnWTqfeICstegXWCRG&callback=init", "/qcloud-admin/assets/js/upload-img.js", "/qcloud-admin/assets/js/chosen.jquery.min.js", null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
        jQuery(function ($) {
        	$('#validDate').datepicker({
                format:'yyyy-mm-dd',
                autoclose:true,
                startDate: '-1y'
            }); 
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
					userMobile: {
                        required: false
                    },
                    sort: {
                        required: true,
                        range: [0, 99999999],
                        digits: true
                    },
                    flagship:{
                    	required: true,
                    },
                    buckle :{
                    	required :true,
                    	range :[0,100],
                    	digits:true
                    },
                    validDate :{
                    	required :true
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
        $("#search-button").on("click", function () {
	                BootstrapDialog.show({
	                    title: "商家列表",
	                    message: $('<div></div>').load('/admin/merchant/selectUserList.do?pageNum=1'),
	                    cssClass: "select-product-dialog",
	                    onshow: function (dialog) {
	                        $(document).off("click", ".select-product-dialog a,.search-button");
	                        $(document).on("click", ".select-product-dialog a,.search-button", function (e) {
	                            e.preventDefault();
	                            var obj = $(this);
	                            //console.log(obj);
	                            if (obj.hasClass("search-button")) {
	                                var form = obj.parents("form").eq(0);
	                                if (form) {
	                                    var url = form.prop("action");
	                                    var fieldArray = form.serializeArray();
	                                    var query_str = "";
	                                    $.each(fieldArray, function (i, data) {
	                                        if ($.trim(data['value'])) {
	                                            query_str += (query_str == '') ? '?' + data['name'] + '=' + data['value'] : '&' + data['name'] + '=' + data['value'];
	                                        }
	                                    });
	                                    url && dialog.getModalBody().html($('<div></div>').load(url + query_str));
	                                }
	                                return false;
	                            }
	
	                            if (obj.hasClass("add-btn")) {
	                                var id = obj.attr("data-id");
	                                var name = obj.attr("data-name");
	                                var mobile = obj.attr("data-mobile");
	                                if (id ) {
	                                	$("#userId").val(id);
	                                	$("#userMobile").val(mobile);
	                                    dialog.close();
	                                }
	                                return false;
	                            }
	
	                            var url = obj.attr("href").replace(/#/, "/");
	                            if (url != "javascript:;" && url) {
	                                dialog.getModalBody().html($('<div></div>').load(url));
	                                return false;
	                            }
	
	                            return false;
	                        });
	                    }
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
