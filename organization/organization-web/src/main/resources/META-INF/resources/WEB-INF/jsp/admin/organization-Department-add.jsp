<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>新增管理员</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        管理里管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="model-form" class="form-horizontal"  role="form" action="/admin/department/add.do">
            <!-- #section:elements.form -->
      					<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name"> 名称 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="name" name="name" placeholder="名称" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
      					<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="displayName"> 父节点 </label>
                <div class="col-sm-9">
                	<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
	                	<select class="chosen-select" id="parentId" name="parentId" data-placeholder="请选择父节点">
	                		<c:if test="${root eq 1}">
	                			<option value="-1">默认根节点</option>
	                		</c:if>
	                		<c:forEach items="${departmentList}" var="item" varStatus="current">
	                			<option value="${item.id}">${item.displayName}</option>
	                		</c:forEach>
	                	</select>
                	</span>
                </div>
            </div>
            
            
            
      					<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="type"> 类型 </label>
                <div class="col-sm-9">
                	<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
	                	<select  class="form-control" id="type" name="type" >
	                		<c:forEach items="${typeList}" var="item" varStatus="current">
	                			<option value="${item.type}">${item.name}</option>
	                		</c:forEach>
	                	</select>
                	</span>
                </div>
            </div>
            
            
            			<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="image"> 缩略图 </label>
                <div class="col-sm-9">
		    	<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
					<input type="hidden" id="image" name="image" value=""/>
                    	<button type="button" mult="false" sid="image" vid="pic2-pic-view" class="btn btn-sm btn-purple btn-upload-pic1" upfrom="0">
                        <i class="ace-icon fa fa-upload"></i> 上 传
                    	</button>
                    <ul sid="image" id="pic2-pic-view" class="ace-thumbnails clearfix">
                    	
                    </ul>
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
                <label class="col-sm-3 control-label no-padding-right" for="address"> 详细地址 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="address" name="address" placeholder="详细地址" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
           				<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right"> 经纬度 </label>

                <div class="col-sm-9">
                    <span class="">
                        <input type="text" readonly name="longitude" id="longitude" value="">
                    </span>
                    <span class="">
                        <input type="text" readonly name="latitude" id="latitude" value="">
                    </span>
                    <span class="btn btn-sm btn-info" id="getPoint">
                         <i class="ace-icon glyphicon glyphicon-map-marker"></i>
                    </span>
                </div>
            </div>
            
            			<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="phone"> 联系方式 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="phone" name="phone" placeholder="联系方式" value=""/>
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
    var scripts = ["http://api.map.baidu.com/api?v=2.0&ak=RsKB8DSnWTqfeICstegXWCRG&callback=init", "/qcloud-admin/assets/js/jquery.colorbox-min.js","/qcloud-admin/assets/js/upload-img.js","/qcloud-admin/assets/js/chosen.jquery.min.js", null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
        jQuery(function ($) {
        	$('.chosen-select').chosen({allow_single_deselect:true});
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

            var btnUpload = $(".btn-upload-pic1");
            delEvent(getButtonSetting(btnUpload));
            //绑定事件
            btnUpload.on('click', function () {
                var bs = getButtonSetting($(this));
                uploadDialog(bs);
            });
            
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
            
             var setPoint = function (lng, lat) {
                if (lng && lat) {
                    $("#longitude").val(parseFloat(lng));
                    $("#latitude").val(parseFloat(lat));
                }
            };

            var lng = 0;
            var lat = 0;

            $("#getPoint").on("click", function () {
                BootstrapDialog.show({
                    title: '坐标设置',
                    message: '<div>经纬度：<span><input type="text" readonly class="lng" value="' + lng + '"></span><span><input type="text" readonly class="lat" value="' + lat + '"></span></div>' +
                    '<div id="bmap" style="height: 300px;"></div>',
                    onshown: function (dialog) {
                        var map = new BMap.Map(bmap);
                        if (lng && lat) {
                            map.centerAndZoom(new BMap.Point(lng, lat), 13);
                        } else {
                            map.centerAndZoom("珠海", 13);
                        }
                        var menu = new BMap.ContextMenu();
                        var txtMenuItem = [
                            {
                                text: '放大',
                                callback: function () {
                                    map.zoomIn()
                                }
                            },
                            {
                                text: '缩小',
                                callback: function () {
                                    map.zoomOut()
                                }
                            }
                        ];
                        for (var i = 0; i < txtMenuItem.length; i++) {
                            menu.addItem(new BMap.MenuItem(txtMenuItem[i].text, txtMenuItem[i].callback, 100));
                        }
                        map.addContextMenu(menu);
                        map.addEventListener("click", function (e) {
                            dialog.getModalBody().find(".lng").val(e.point.lng).end().find(".lat").val(e.point.lat);
                        });
                    },
                    buttons: [{
                        label: '确定',
                        cssClass: 'btn-primary',
                        action: function (dialog) {
                            lng = dialog.getModalBody().find(".lng").val();
                            lat = dialog.getModalBody().find(".lat").val();
                            setPoint(lng, lat);
                            dialog.close();
                        }
                    }]
                });
            });
              
              
            $("#parentId").change(function(){
            	var key=$(this).val();
            	$.ajax({
					url:'/admin/department/platformTypeList.do',
					data:{parentId:key},
					dataType:'json',
					type:'post',
					success:function(result){
						var html ="";
						var data=result.data;
						for(var i=0;i<data.list.length;i++){
							//alert(data.list[i].name);		
							html+="<option value='"+data.list[i].type+"'>"+data.list[i].name+"</option>";	
						}
						$("#type").html(html);
						
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
                    phone: {
                        required: true
                    },
					 address: {
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
                	var phone=$("#phone").val();
                	var phoneReg=/^1[3,4,5,7,8,9]\d{9}$/;
                	if(!phoneReg.test(phone)){
                		 BootstrapDialog.show({
                		 	message:'请输入正确的电话号码'
                		 });
                		return false;
                	}
                    postForm('model-form');
                },
                invalidHandler: function (form) {
                }
            });
        });
    })
</script>
