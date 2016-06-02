<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />



<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="model-form" class="form-horizontal" role="form" action="/admin/sexpress/add.do" >
			<input type="hidden" name="extra" id="extra" value="1"/>
            <!-- /section:elements.form -->
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name"></label>

                <div class="col-sm-9">
				<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
					 <button id="search-button" type="button" class="btn btn-purple btn-sm">
                        <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                      		 选择快递公司
                    </button>
				</span>
            </div>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name"> 配送方式名称 </label>

                <div class="col-sm-9">
                    <span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
                        <input type="text" class="width-100" maxlength="100" id="name" name="name" value="">
                        <i class="ace-icon fa"></i>
                    </span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="code"> 快递公司编码 </label>

                <div class="col-sm-2">
                    <span class="col-sm-12 no-padding block input-icon input-icon-right mr10">
                        <input type="text" class="width-100" maxlength="20" id="code" placeholder="用于快递100查询物流" name="code" value="">
                        <i class="ace-icon fa"></i>
                    </span>
                </div>
                <label class="control-label"><a href="https://code.google.com/p/kuaidi-api/wiki/Open_API_API_URL" target="_blank">快递公司及参数说明</a></label>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="desc"> 描述 </label>

                <div class="col-sm-9">
                    <span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
                        <textarea class="width-100" maxlength="255" id="desc" name="desc"></textarea>
                    </span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="image"> 图片 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="hidden"  id="logo" name="logo" value=""/>
						<button id="logoBtn" type="button" mult="false" sid="logo" vid="pic-pic-view" class="btn btn-sm btn-purple btn-upload-pic"  upfrom="0"  >
						<i class="ace-icon fa fa-upload"></i> 上 传</button>
						<ul sid="pic" id="pic-pic-view" class="ace-thumbnails clearfix">	
							<img src=""  id="img">
						</ul>
					</span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name"> 重量设置 </label>

                <div class="col-sm-9 ">
                    <span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
                       首重重量&nbsp;&nbsp;&nbsp;<input type="text" id="firstWeight" class="input-mini" style="width: 80px;" name="firstWeight" value="">&nbsp;&nbsp;克&nbsp;<br><br>
                    </span>
                    <span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
                       首重费用&nbsp;&nbsp;&nbsp;<input type="text" id="firstPrice" class="input-mini" style="width: 80px;" name="firstPrice" value="">&nbsp;&nbsp;元<br><br>
                    </span>
                    <span class="col-sm-5 no-padding block input-icon input-icon-right mr10">  
                       续重重量&nbsp;&nbsp;&nbsp;<input type="text" id="continuedWeight" class="input-mini" style="width: 80px;" name="continuedWeight" value="">&nbsp;&nbsp;克&nbsp;<br><br>
                    </span>
                    <span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
                       续重费用&nbsp;&nbsp;&nbsp;<input type="text" id="continuedPrice" class="input-mini" style="width: 80px;" name="continuedPrice" value="">&nbsp;&nbsp;元
                	</span>
                </div>
            </div>
            
          	<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="image"> 包邮/固定邮费/区域收费 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<select name="type" class="col-sm-9">
							<option value="1">包邮</option>
							<option value="2">固定邮费</option>
							<option value="3">区域收费  (请填写配送地区)</option>
						</select>
					</span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="image"> 固定邮费： </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input value="0" name="fixedPrice" class="input-mini" style="width: 80px;"/> (若选择为固定邮费请务必填写)
					</span>
                </div>
            </div>
            
            <div id="areas_show">
            	<div class="space-4"></div>
	            <div class="form-group">
	                <label for="version" class="col-xs-12 col-sm-3 control-label no-padding-right">
	                    支持的配送地区
	                </label>
	                <div class="col-sm-9">
	                    <span class="col-sm-8 no-padding block input-icon input-icon-right mr10">
	                        <table id="sample-table-1" class="table table-striped table-bordered table-hover ">
	                            <thead>
	                                <tr>
	                                    <th>首重费用</th>
	                                    <th>续重费用</th>
	                                    <th>特殊地区</th>
	                                    <th></th>
	                                </tr>
	                            </thead>
	
	                            <tbody class="city-tbody">
									<tr>
	                                    <td><input type="text" class="input-mini" name="list[0].firstWeight"></td>
	                                    <td><input type="text" class="input-mini" name="list[0].continuedWeight"></td>
	                                    <td>
	                                      <select id="initProvince" class="tag-input-style province"  name="list[0].province">
	                                      	
                                    	</select>
                                    	<select class="tag-input-style city"  name="list[0].city">
                                    		<option value="">请选择城市</option>
                                    		
                                    	</select>
	                                    </td>
	                                    <td><a href="javascript:;" class="del-guige">X</a></td>
	                                </tr></tbody>
	
	                            <tfoot>                     
	                                <tr>                            
	                                    <td colspan="4">
	                                        <button type="button" class="btn-link add-row">增加</button>
	                                    </td>                       
	                                </tr>                   
	                            </tfoot>
	                        </table>
	                    </span>
                	</div>    
	            </div>
	            
	        </div>

            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="enable"> 是否启用 </label>

                <div class="col-sm-9">
                    <input id="enable" type="checkbox" value="1" class="ace ace-switch ace-switch-5" name="enable" checked="checked">
                    <span class="lbl middle"></span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="sort"> 排序号 </label>

                <div class="col-sm-9">
                    <span class="col-sm-2 no-padding block input-icon input-icon-right mr10">
                        <input type="text" class="width-100" id="sort" name="sort" value="0">
                        <i class="ace-icon fa"></i>
                    </span>
                </div>
            </div>

            <div class="space-4"></div>

            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>&nbsp;保&nbsp;存&nbsp;
                    </button>
                    &nbsp; &nbsp; &nbsp;
                    <button id="back" class="btn" type="button"><i class="ace-icon fa fa-undo bigger-110"></i>&nbsp;返&nbsp;回&nbsp;
                    </button>
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
        	$("#search-button").on("click", function () {
	                BootstrapDialog.show({
	                    title: "需要审核商品的商家列表",
	                    message: $('<div ></div>').load('/admin/express/toList.do?pageNum=1'),
	                    cssClass: "select-product-dialog",
	                    onshow: function (dialog) {
	                        $(document).off("click", ".select-product-dialog a,.search-button");
	                        $(document).on("click", ".select-product-dialog a,.search-button", function (e) {
	                            e.preventDefault();
	                            var obj = $(this);
	                            //console.log(obj);
	                            //查询按钮
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
	                                var firstWeight = obj.attr("data-firstWeight");
	                                var firstPrice = obj.attr("data-firstPrice");
	                                var continuedWeight = obj.attr("data-continuedWeight");
	                                var continuedPrice = obj.attr("data-continuedPrice");
	                                var code = obj.attr("data-code");
	                                var desc = obj.attr("data-desc");
	                                var logo = obj.attr("data-logo");   
	                                var logoUid= obj.attr("data-logoUid");
	                                $("#name").val(name);
	                                $("#firstWeight").val(firstWeight);
	                                $("#firstPrice").val(firstPrice);
	                                $("#continuedWeight").val(continuedWeight);
	                                $("#continuedPrice").val(continuedPrice);
	                                $("#code").val(code);  
	                                $("#desc").val(desc);  
	                                $("#logo").val(logoUid); 
	                                $("#img").attr("src",logo);  
	                                $("#img").css("width","100"); 
	                                $("#img").css("height","100");  
	                                $("#extra").val(0);
	                                
	                                $("#name").attr("readonly","true");
	                                $("#firstWeight").attr("readonly","true");
	                                $("#firstPrice").attr("readonly","true");
	                                $("#continuedWeight").attr("readonly","true");
	                                $("#continuedPrice").attr("readonly","true");
	                                $("#code").attr("readonly","true"); 
	                                $("#desc").attr("readonly","true");
	                                $("#logoBtn").hide();
	                                dialog.close();
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
	         ////
        	var rows=1;
            $(window)
            .off('resize.chosen')
            .on('resize.chosen', function() {
                $('.chosen-select').each(function() {
                     var $this = $(this);
                     $this.next().css({'width': $this.parent().width()});
                })
            }).trigger('resize.chosen');
            
            $("#enable").click(function(){
            	if($(this).attr("checked")){
            		$(this).removeAttr("checked");
            		$(this).val("0");
            	}else{
            		$(this).val("1");
            		$(this).attr("checked","true");
            	}
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
            //增加一行
	        $('.add-row').on('click',function(){
	        	rows++;
	        	var str="<tr><td><input type='text' class='input-mini' name='list["+rows+"].firstWeight'></td>"+
	        	"<td><input type='text' class='input-mini' name='list["+rows+"].continuedWeight''></td>"+
	        	"<td><select class='tag-input-style province' name='list["+rows+"].province'>"+provinceOption+"</select>  "+
	        	"<select class='tag-input-style city' name='list["+rows+"].city'><option> 请选择城市 </option></select>  </td>"+
	        	"<td><a href='javascript:;' class='del-guige'>X</a></td>";
	        	$(".city-tbody").append(str);
	        	$(".province").change(function(){
		        	 var obj=$(this);  
		        	 var name=obj.val();
		        	 cityOption="";
		        	 $.ajax({
			        	type:"GET",
			        	data :{provinceName:name},
			        	url: "/admin/express/cityList.do",
			        	dataType:"json",
			        	success:function(data){
			        		$.each(data.data.cityList,function(i,c){
			        			cityOption+="<option value='"+c+"'>"+c+"</option>"
			        		});
			        		obj.next().html(cityOption);
			        	}
			        });
			        
		        });
	        	
	        });
            //删除
            $(document).on('click','.del-guige',function(){
	            $(this).parent().parent().remove();
	        });    
	        
	       
	        var provinceOption="<option value=''>请选择省份</option>";
	        var cityOption="";
	        $.ajax({
	        	type:"GET",
	        	url: "/admin/express/provinceList.do",
	        	dataType:"json",
	        	success:function(data){
	        		$.each(data.data.provinceList,function(i,p){
	        			provinceOption+="<option value='"+p+"'>"+p+"</option>"
	        		});
	        		$("#initProvince").html(provinceOption);
	        	}
	        });
	        
	        $(".province").change(function(){
	        	 var obj=$(this);  
	        	 var name=obj.val();
	        	 cityOption="";
	        	 $.ajax({
		        	type:"GET",
		        	data :{provinceName:name},
		        	url: "/admin/express/cityList.do",
		        	dataType:"json",
		        	success:function(data){
		        		$.each(data.data.cityList,function(i,c){
		        			cityOption+="<option value='"+c+"'>"+c+"</option>"
		        		});
		        		obj.next().html(cityOption);
		        	}
		        });
		        
		        $.post('url',{data},function(data){
		        
		        });
		        
	        });
	           
            //表单验证
            $("#model-form").validate({
                errorElement: 'span',
                errorClass: 'help-block col-xs-12 col-sm-reset inline',
                focusInvalid: false,
                rules: {
                    name: {
                        required: true
                    },
                    firstWeight: {
                        required: true,
                        range: [0, 99999999]
                    },
					firstPrice: {
                        required: true,
                        range: [0, 99999999]
                    },
                    continuedWeight: {
                        required: true,
                        range: [0, 99999999]
                    },
                    continuedPrice: {
                        required: true,
                        range: [0, 99999999]
                    },
                    sort: {
                        required: true,
                        range: [0, 99999999],
                        digits: true
                    },
                    fixedPrice:{
                    	range: [0,999999999]
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
                    else error.insertAfter(element.html());
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
