<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>

<title>新增商品</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />
<style>
.chosen-container-single{
	width:800px!important;
}
.tags{
	width:800px!important;
	margin-left: 3px!important;
}
.qi-cloud-text{
	width:800px!important;
}
.help-block{
	color:red;
}
</style>
<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        商品管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增
        </small>
    </h1>
</div>
<!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="model-form" class="form-horizontal" role="form" action="/admin/merchandise/add.do">
        <div class="dataTables_wrapper form-inline no-footer">
        	
               	<table class="table table-striped table-bordered table-hover dataTable no-footer">
					<caption class="text-left table-header btn-info">商品档案信息</caption>
					<tbody>
						<tr>
							<td style="width: 200px;">商品名称：</td>
							<td style="width: 300px;"> 
								<input type="text" class="qi-cloud-text" maxlength="10" id="name" name="name" placeholder="名称" value=""/>
								<span  class="chosen-container"></span>
							</td>
						</tr>
						<tr>
							<td style="width: 200px;">商城商品分类:</td>
							<td style="width: 300px;"> 
								<select class="chosen-select" id="mallClassifyId" name="mallClassifyId" >
		                            <option value="-1" selected>请选择分类</option>
		                            <c:forEach items="${mallClassifyList}" var="item" varStatus="current">
		                                <option value="${item.key}" ${item.message}>${item.value}</option>
		                            </c:forEach>
		                        </select>
							</td>
						</tr>
						<tr>
							<td style="width: 200px;">自家商品分类：</td>
							<td style="width: 300px;"> 
								<select class="chosen-select" id="merchantClassifyId" name="merchantClassifyId">
		                            <option value="-1" selected>请选择分类</option>
		                            <c:forEach items="${merchantClassifyList}" var="item" varStatus="current">
		                                <option value="${item.key}" ${item.message}>${item.value}</option>
		                            </c:forEach>
		                        </select>
							</td>
						</tr>
					
						<tr>
							<td style="width: 200px;">规格类目：<br><strong><font color="red">请仔细确认商品规格,添加成功后不可修改(不选则视为无规格)</font></strong></td>
							<td style="width: 300px;"> 
								<select class="chosen-select" id="specClassifyId" name="specClassifyId">
		                            <option value="-1" selected>请选择规格</option>
		                            <c:forEach items="${specClassifyId}" var="item" varStatus="current">
		                                <option value="${item.key}" ${item.message}>${item.value}</option>
		                            </c:forEach>
		                        </select>
							</td>
						</tr>
						
						<tr id="specClassifyList" style="display:none;">
							<td style="width: 200px;"></td>
							<td style="width: 300px;" id="attrList"> 
								<%-- <span>111</span><br>
								<span><input type="checkbox"  style="width:20px!important;height:20px;"/>11</span> --%>
							</td>
						</tr>
						
						
						<c:if test="${hasBrand}">
							<tr>
								<td style="width: 200px;"> 品  牌：<br></td>
								<td style="width: 300px;"> 
									<select class="chosen-select" id="brandId" name="brandId">
			                            <option value="-1" selected>请选择品牌 </option>
			                            <c:forEach items="${brandList}" var="item" varStatus="current">
			                                <option value="${item.id}">${item.name}</option>
			                            </c:forEach>
			                        </select>
								</td>
							</tr>
			            </c:if>
			            <c:if test="${!hasBrand}">
			            	<input type="hidden"  value="-1" name="brandId" id="brandId" />
			            </c:if>
						
						<tr>
							<td style="width: 200px;">商品缩略图：<br><strong><font color="red">商品列表显示图</font></strong></td>
							<td style="width: 300px;"> 
								<input type="hidden" id="image" name="image" value=""/>
								<button type="button" mult="false" sid="image" vid="pic-pic-view" class="btn btn-sm btn-purple btn-upload-pic" upfrom="0">
								<i class="ace-icon fa fa-upload"></i> 上 传
								</button>
								<ul sid="pic" id="pic-pic-view" class="ace-thumbnails clearfix"></ul>
							</td>
						</tr>
						
						<tr>
							<td style="width: 200px;">搜索关键字：</td>
							<td style="width: 300px;"> 
								<input type="text" class="qi-cloud-text" maxlength="100" id="keywords" name="keywords" placeholder="关键字" value=""/>
							</td>
						</tr>
						
						
						<tr>
							<td style="width: 200px;">标签：</td>
							<td style="width: 300px;"> 
								<input type="text" class="width-100" maxlength="2" id="label" name="label" placeholder="热卖、促销、特价、好评..." value=""/>
							</td>
						</tr>
						
						<tr>
							<td style="width: 200px;">重量：</td>
							<td style="width: 300px;"> 
								<input type="text" class="qi-cloud-text" maxlength="10" id="weight" name="weight" placeholder="重量" value=""/>
								<span  class="chosen-container"></span>
							</td>
						</tr>
						
						<tr>
							<td style="width: 200px;">单位：</td>
							<td style="width: 300px;"> 
								<input type="text" class="qi-cloud-text" maxlength="100" id="unit" name="unit" placeholder="单位" value=""/>
							</td>
						</tr>
						
						<tr>
							<td style="width: 200px;">商品简介：</td>
							<td style="width: 300px;"> 
								<input type="text" class="qi-cloud-text" maxlength="200" id="desc" name="desc" placeholder="描述 " value=""/>
							</td>
						</tr>
						
						<c:if test="${merchantIsIncludePost==2}">
				            <tr>
								<td style="width: 200px;">是否包邮：</td>
								<td style="width: 300px;"> 
									 <input type="radio" style="width:20px!important;height:20px;" name="isIncludePost" value="1"/>是
					                    <input type="radio" style="width:20px!important;height:20px;" checked name="isIncludePost" value="2"/>否
								</td>
							</tr>
						</c:if>
						<c:if test="${merchantIsIncludePost==1}">
			                <input type="hidden"  name="isIncludePost" value="1"/>
						</c:if>  
						
						
						
						<c:if test="${merchantIsCertified==1}">
							<tr>
								<td style="width: 200px;">正品保证描述：</td>
								<td style="width: 300px;"> 
									<input type="checkbox" checked style="width:20px!important;height:20px;" id="isCertified" value="1" name="isCertified" />
				                    <input type="text" style="width:775px!important" id="certified" name="certified" value="正品保证">
								</td>
							</tr>
				        </c:if>  
				        
				        <c:if test="${merchantIsNoReason==1}">
							<tr>
								<td style="width: 200px;">无理由退货描述：</td>
								<td style="width: 300px;"> 
									<input type="checkbox" checked style="width:20px!important;height:20px;" id="isNoReason" value="1" name="isNoReason" />
                   					<input type="text" style="width:775px!important" id="noReason" name="noReason" value="7天无理由退货">
								</td>
							</tr>
				        </c:if>  
				        
				        
				        <c:if test="${merchantIsExternalUrl==1}">
							<tr>
								<td style="width: 200px;">商品外部链接：</td>
								<td style="width: 300px;"> 
									<input type="checkbox" checked style="width:20px!important;height:20px;" id="isExternalUrl" value="1" name="isExternalUrl" />
	                  			    <input type="text" style="width:775px!important" id="externalUrl" name="externalUrl" placeholder="商品外部链接" value="">
								</td>
							</tr>
				        </c:if>  
				        
				        
				        <c:if test="${merchantIsSpecialService==1}">
							<tr>
								<td style="width: 200px;">特色服务：</td>
								<td style="width: 300px;"> 
									<input type="checkbox" checked style="width:20px!important;height:20px;" id="isSpecialService" value="1" name="isSpecialService" />
								</td>
							</tr>
				        </c:if>  
				        
				        <c:if test="${merchantIsSpecialService==1}">
							<tr id="isSpecialServiceDiv">
								<td style="width: 200px;"></td>
								<td style="width: 300px;"> 
									<textarea class="width-100" style="height:400px!important;width:800px!important;" class="col-xs-10 no-float" id="specialService" name="specialService" ></textarea>
								</td>
							</tr>
				        </c:if> 
				        
						<tr >
							<td style="width: 200px;">商品详情</td>
							<td style="width: 300px;"> 
								<textarea class="width-100" style="height:600px!important;width:800px!important;" class="col-xs-10 no-float" id="details" name="details"></textarea>
							</td>
						</tr>
						
					</tbody>
				</table>
			
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
</div>
<!-- /.row -->

<script type="text/javascript">

    var scripts = [null, "/qcloud-admin/assets/js/jquery.colorbox-min.js", "/qcloud-admin/assets/js/bootstrap-tag.min.js", "/qcloud-admin/assets/js/upload-img.js", "/qcloud-admin/assets/js/chosen.jquery.min.js",null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
        jQuery(function ($) {
        	$('.chosen-select').chosen({allow_single_deselect:true});
            $(window)
            .off('resize.chosen')
            .on('resize.chosen', function () {
                $('.chosen-select').each(function () {
                    var $this = $(this);
                    $this.next().css({'width': $this.parent().width()});
                })
            }).trigger('resize.chosen');

			/*$("#specClassifyId").change(function(){
				//specClassifyList
				var specClassifyId=$(this).val();
				if(specClassifyId== -1){
					
				}else{
					$.ajax({
						url:'/admin/merchandise/listDefaultSpec.do',
						data:{specClassifyId:specClassifyId},
						dataType:'json',
						success:function(result){
							var attrList=result.data.attrList;
							var html=""
							for(var i=0;i<attrList.length;i++){
								html+="<span>"+attrList[i].name+"</span><br>";
								var valueList=attrList[i].valueList;
								for(var k=0;k<valueList.length;k++){
									html+="    <span><input type='checkbox' style='width:20px!important;height:20px;' value=''/>     "+valueList[k]+"</span>"
								}
								html+="<br>";
							}
							
							$("#attrList").html(html);
						}
					});
				}
			});*/

            //初始化富文本编辑器
            window.UMEDITOR_HOME_URL = "/qcloud-admin/umeditor/";
            var serverPath = '/admin/file/';
            var um = UM.getEditor('details', {
                imageUrl: serverPath + "addUMEditorFile.do",
                imagePath: "/file/",
                lang: /^zh/.test(navigator.language || navigator.browserLanguage || navigator.userLanguage) ? 'zh-cn' : 'en',
                langPath: UMEDITOR_CONFIG.UMEDITOR_HOME_URL + "lang/",
                autoHeightEnabled:false ,
                focus: true
            });
            UM.clearCache('details');
            
            window.UMEDITOR_HOME_URL = "/qcloud-admin/umeditor/";
            var serverPath = '/admin/file/';
            var umSpecialService = UM.getEditor('specialService', {
                imageUrl: serverPath + "addUMEditorFile.do",
                imagePath: "/file/",
                lang: /^zh/.test(navigator.language || navigator.browserLanguage || navigator.userLanguage) ? 'zh-cn' : 'en',
                langPath: UMEDITOR_CONFIG.UMEDITOR_HOME_URL + "lang/",
                autoHeightEnabled:false ,
                focus: true
            });
            UM.clearCache('specialService');
            
            

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

            //关键词tags
            var tag_input = $('#keywords');
            try {
                tag_input.tag(
                        {
                            placeholder: tag_input.attr('placeholder')
                        }
                );
                var $tag_obj = $('#keywords').data('tag');
            }
            catch (e) {
                tag_input.after('<textarea id="' + tag_input.attr('id') + '" name="' + tag_input.attr('name') + '" rows="3">' + tag_input.val() + '</textarea>').remove();
            }
			
			$("#isCertified").click(function(){
				if($(this).is(":checked")){
					$("#certified").val("正品保证");
					$("#certified").show();
					$(this).val(1);
				}else{
					$("#certified").val("");
					$("#certified").hide();
					$(this).val(2);
				}
			});
			
			$("#isExternalUrl").click(function(){
				if($(this).is(":checked")){
					$("#externalUrl").val("");
					$("#externalUrl").show();
					$(this).val(1);
				}else{
					$("#externalUrl").val("");
					$("#externalUrl").hide();
					$(this).val(2);
				}
			});
			
			$("#isNoReason").click(function(){
				if($(this).is(":checked")){
					$("#noReason").val("7天无理由退货");
					$("#noReason").show();
					$(this).val(1);
				}else{
					$("#noReason").val("");
					$("#noReason").hide();
					$(this).val(2);
				}
			});
			
			$("#isSpecialService").click(function(){
				if($(this).is(":checked")){
					//$(this).parent().find("div").show();
					$("#isSpecialServiceDiv").show();
					$(this).val(1);
				}else{
					//$(this).parent().find("div").hide();
					$("#isSpecialServiceDiv").hide();
					$(this).val(2);
				}
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
					weight:{
						required: true,
						number:true
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
                    else{ 
                    	error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
                    	//error.insertAfter(element.parent());
                    }
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
