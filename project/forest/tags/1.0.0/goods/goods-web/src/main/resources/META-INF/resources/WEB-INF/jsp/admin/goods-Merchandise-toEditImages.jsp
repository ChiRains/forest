<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>编辑商品规格信息</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        商品管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            编辑
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="model-form" class="form-horizontal"  role="form" action="/admin/merchandise/editImages.do" method="post" enctype="multipart/form-data">
            <!-- #section:elements.form -->
			<input type="hidden" name="merchandiseId" value="${merchandise.id}">											
			<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="pics"> 商品图片 </label>
                <div class="col-sm-10">
					<span class="col-sm-9 no-padding block input-icon input-icon-right mr10">
						<%--<input type="text" class="width-100" id="pics" name="pics" placeholder="图标"--%>
                               <%--value="${pages.icon}"/>--%>
						<%--<i class="ace-icon fa"></i>--%>
                    <input type="hidden" id="pics" name="pics" value="${pages.pics}"/>
					<button type="button" mult="true" sid="pics" vid="pics-view" class="btn btn-sm btn-purple btn-upload-pic" upfrom="0">
                        <i class="ace-icon fa fa-upload"></i> 上 传
                    </button>
                    <ul sid="pics" id="pics-view" class="ace-thumbnails clearfix">
                        <c:forEach items="${pages.pics.split(\",\")}" var="pic" varStatus="current">
                            <c:if test="${pic.trim().length() > 0}">
                            <li pic-id="${pic}">
                                <a style="line-height: 150px;text-align: center;width:150px;height: 150px;"
                                   href="/file/get.do?uid=${pic}" data-rel="colorbox" class="cboxElement">
                                    <img style="max-height: 150px;max-width: 150px;" alt="" src="/file/get.do?uid=${pic}">
                                </a>
                                <div class="tools tools-bottom"><a onclick="imgDel(this);" href="javascript:;" title="删除"><i
                                        class="ace-icon fa fa-times red"></i></a></div>
                            </li>
                            </c:if>
                        </c:forEach>
                    </ul>
					</span>
                </div>
            </div>								
											
											
											
			<label><input name="colour" type="radio" value="" id="white" checked="checked"/>白色 </label>
			<label><input name="colour" type="radio" value="" id="red" />红色 </label>
			<label><input name="colour" type="radio" value="" id="yellow" />黄色 </label>
			<label><input name="colour" type="radio" value="" id="black"/>黑色</label>
			<label><input name="colour" type="radio" value="" id="blue"/>蓝色 </label>
			<label><input name="colour" type="radio" value="" id="gray"/>灰色 </label>
			<label><input name="colour" type="radio" value="" id="green"/>绿色</label>  

			<label><input name="colour" type="radio" value="" id="green"/>绿色</label>    		

            
            <div class="space-4" style="margin:10px 0px;"></div>
            
			<div class="form-group" style="display: inline-block;">
		        <label for="pic" class="col-xs-12 col-sm-3 control-label no-padding-right">		
		           </label>
		        <div class="col-xs-12 col-sm-2">
		            <span class="block input-icon input-icon-right" id="colour" style="width:200px;">
		               <input  type="file" name="p1" id="p1"  />
		            </span>
		        </div>
		    </div>
		    
		    <div class="form-group" style="display: inline-block;">
		        <label for="pic" class="col-xs-12 col-sm-3 control-label no-padding-right">		
		           </label>
		        <div class="col-xs-12 col-sm-2">
		            <span class="block input-icon input-icon-right" style="width:200px;">
		               <input  type="file" name="p2" id="p2"  />
		            </span>
		        </div>
		        
		    </div>
		    <div class="form-group" style="display: inline-block;" >
		        <label for="pic" class="col-xs-12 col-sm-3 control-label no-padding-right">		
		           </label>
		        <div class="col-xs-12 col-sm-2">
		            <span class="block input-icon input-icon-right" style="width:200px;">
		               <input  type="file" name="p3" id="p3"  />
		            </span>
		        </div>
		    </div>
		    
		    <div class="form-group" style="display: inline-block;">
		        <label for="pic" class="col-xs-12 col-sm-3 control-label no-padding-right">		
		           </label>
		        <div class="col-xs-12 col-sm-2">
		            <span class="block input-icon input-icon-right" style="width:200px;">
		               <input  type="file" name="p4" id="p4"  />
		            </span>
		        </div>
		    </div>
		    
		    <div class="form-group" style="display: inline-block;">
		        <label for="pic" class="col-xs-12 col-sm-3 control-label no-padding-right">		
		           </label>
		        <div class="col-xs-12 col-sm-2">
		            <span class="block input-icon input-icon-right" style="width:200px;">
		               <input  type="file" name="p5" id="p5"  />
		            </span>
		        </div>
		    </div>            
            
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
            var $form = $('#model-form');
				var deal_pic = function(){
					//you can have multiple files, or a file input with "multiple" attribute
					var file_input = $form.find('input[type=file]');
					var upload_in_progress = false;

					file_input.ace_file_input({
						style : 'well',
						btn_choose : '请选择图片文件',
						btn_change: null,
						droppable: true,
						thumbnail: 'large',
						
						maxSize: 1048576*5,//bytes(10m)
						//allowExt: ["xls"],
						allowMime: ["image/jpg", "image/jpeg", "image/png", "image/gif"],

						before_remove: function() {
							if(upload_in_progress)
								return false;//if we are in the middle of uploading a file, don't allow resetting file input
							return true;
						},

						preview_error: function(filename , code) {
							//code = 1 means file load error
							//code = 2 image load error (possibly file is not an image)
							//code = 3 preview failed
						}
					})
					file_input.on('file.error.ace', function(ev, info) {
						if(info.error_count['ext'] || info.error_count['mime']) alert('上传文件格式错误！');
						if(info.error_count['size']) alert('文件上限为2M');
						//you can reset previous selection on error
						//ev.preventDefault();
						//file_input.ace_file_input('reset_input');
					});
				}
				
				

				
					deal_pic();

					$form.find('button[type=reset]').on('click',function(){	//点击重置恢复成空白
						$('#pack').parent().parent().append('<input  type="file" name="pack" id="pack"  />');
						$('#pack').parent().remove();
						deal_pic();
					});
				

				$('#model-form').validate({
					errorElement: 'div',
					errorClass: 'help-block',
					focusInvalid: false,
					rules: {
						content: {
							required: true,
							//maxlength:50
						},
						version: {
							required: true,
							//minlength: 5
						},
						
					},
			
					messages: {					
						
					},
			
			
					highlight: function (e) {
		                $(e).closest('.form-group').removeClass('has-success').addClass('has-error');
		            },
		    
		            success: function (e) {
		                $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
		                $(e).remove();
		            },



		            errorPlacement: function (error, element) {
		                if(element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
		                    var controls = element.closest('div[class*="col-"]');
		                    if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
		                    else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
		                }
		                else {
		                    error.insertAfter(element.parents('div').eq(0));
		                    if(!error.html()){
		                        element.next('i.ace-icon').removeClass('fa-times-circle').addClass('fa-check-circle');
		                    }else{
		                        element.next('i.ace-icon').removeClass('fa-check-circle').addClass('fa-times-circle');
		                    }
		                }
		            },
			
					submitHandler: function (form) {
						form.submit();						
						/*postForm($(form).attr('id'));
                		return false;*/
					},
					invalidHandler: function (form) {
					}
				});


			});
		
		
		 $("#red").click(function(){
            var str="";
            id=$("#colour").attr("id");
            id.replace("name");
		    //$("#colour").html(str);
           
		});				
    })
</script>
