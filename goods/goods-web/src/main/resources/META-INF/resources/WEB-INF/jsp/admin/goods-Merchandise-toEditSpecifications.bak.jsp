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
        <form id="model-form" class="form-horizontal"  role="form" action="/admin/merchandise/editSpecifications.do" method="post" enctype="multipart/form-data">
            <!-- #section:elements.form -->
			<input type="hidden" name="merchandiseId" value="${merchandise.id}">											
			<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="pics"> 商品默认图片 (五张) </label>
                <div class="col-sm-10">
					<span class="col-sm-9 no-padding block input-icon input-icon-right mr10">
                    <input type="hidden" id="images" name="images" value="${defaultImage.images}"/>
					<button type="button" mult="true" sid="images" vid="images-view" class="btn btn-sm btn-purple btn-upload-images" upfrom="0">
                        <i class="ace-icon fa fa-upload"></i> 上 传
                    </button>
                    <ul sid="images" id="images-view" class="ace-thumbnails clearfix">
                    		<c:forEach items="${defaultImage.list}" var="imageItem" varStatus="current">					                            
					             <li pic-id="${imageItem.uid}">
					                 <a style="line-height: 150px;text-align: center;width:150px;height: 150px;"
					                       href="${imageItem.image}" data-rel="colorbox" class="cboxElement">
					                        <img style="max-height: 150px;max-width: 150px;" alt="" src="${imageItem.image}">
					                 </a>
					                 <div class="tools tools-bottom"><a onclick="imgDel(this);" href="javascript:;" title="删除"><i
					                         class="ace-icon fa fa-times red"></i></a></div>
					             </li>					                           
					       </c:forEach>                        
                    </ul>
					</span>
                </div>
            </div>		
            	
            <c:forEach items="${attributeList}" var="attribute" varStatus="status">
			<div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="xxxx"> ${attribute.name} </label>
                <div class="col-sm-10">
					<span class="col-sm-9 no-padding block input-icon input-icon-right mr10">
						 <div class="checkbox">
						 		<c:forEach items="${attribute.list}" var="item" varStatus="current">                            
	                              	<label >
	                          			<input parent="1" value="${item.key}" name="specifications${status.index}[${current.index}].attribute" type="checkbox" class="ace check_button">
	                                    <span class="lbl">${item.value}</span>
	                                </label>
                                </c:forEach>    
                        </div>    
					</span>
                </div>
            </div>	
			</c:forEach>
			          			
		    <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="pics"> 规格 </label>
                <div class="col-sm-10">
					<span class="col-sm-9 no-padding block input-icon input-icon-right mr10">
						<table id="sample-table-1" class="table table-striped table-bordered table-hover ">
                            <thead>
                                <tr>
                                    <th>${attribute0Str}</th>
                                    <th>${attribute1Str}</th>
                                    <th>${attribute2Str}</th>
                                    <th>库存</th>                                    
                                </tr>
                            </thead>

                            <tbody class="guige-tbody">
                            	<c:forEach items="${specificationsList}" var="specifications" varStatus="status">
	                                <tr style="display:" class="tmp">
	                                    <td>${specifications.value0}</td>
	                                    <td>${specifications.value1}</td>
	                                    <td>${specifications.value2}</td>
	                                    <td>
		                                    <input type="hidden" class="input-mini" name="specificationsList[${status.index}].id" value="${specifications.id}" />
		                                  	<input type="hidden" class="input-mini" name="specificationsList[${status.index}].merchandiseId" value="${specifications.merchandiseId}" />
		                                    <input type="hidden" class="input-mini" name="specificationsList[${status.index}].attributeId0" value="${specifications.attributeId0}" />
		                                    <input type="hidden" class="input-mini" name="specificationsList[${status.index}].attributeId1" value="${specifications.attributeId1}" />
		                                    <input type="hidden" class="input-mini" name="specificationsList[${status.index}].attributeId2" value="${specifications.attributeId2}" />
		                                    <input type="hidden" class="input-mini" name="specificationsList[${status.index}].value0" value="${specifications.value0}" />
		                                    <input type="hidden" class="input-mini" name="specificationsList[${status.index}].value1" value="${specifications.value1}" />
		                                    <input type="hidden" class="input-mini" name="specificationsList[${status.index}].value2" value="${specifications.value2}" />
		                                    <input type="text" class="input-mini" name="specificationsList[${status.index}].stock" value="${specifications.stock}" />	                                     
	                                    </td>	                                  
	                                </tr> 
								</c:forEach>   
                            </tbody>                           
                        </table>
					</span>
                </div>
            </div>     			          
			
			<div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="pics"> 价格 </label>
                <div class="col-sm-10">
					<span class="col-sm-9 no-padding block input-icon input-icon-right mr10">
						<table id="sample-table-1" class="table table-striped table-bordered table-hover ">
                            <thead>
                                <tr>
                                    <th>${price0Str}</th>                                  
                                    <th>进货价</th>     
                                    <th>折扣价</th>
                                    <th>原价</th>                                                                     
                                </tr>
                            </thead>

                            <tbody class="guige-tbody">
                            	<c:forEach items="${priceList}" var="price" varStatus="status">
	                                <tr style="display:" class="tmp">
	                                    <td>${price.value}</td>
	                                    
	                                    <input type="hidden" class="input-mini" name="priceList[${status.index}].id" value="${price.id}"/>
	                                    <input type="hidden" class="input-mini" name="priceList[${status.index}].merchandiseId" value="${price.merchandiseId}"/>
	                                    <input type="hidden" class="input-mini" name="priceList[${status.index}].attributeId" value="${price.attributeId}"/>
	                                    <input type="hidden" class="input-mini" name="priceList[${status.index}].value" value="${price.value}"/>
	                                    	                                   	                                  	                                  
	                                    <td><input type="text" class="input-mini" name="priceList[${status.index}].purchase" value="${price.purchase}"/></td>	
	                                    <td><input type="text" class="input-mini" name="priceList[${status.index}].discount" value="${price.discount}"/></td>	
	                                    <td><input type="text" class="input-mini" name="priceList[${status.index}].price" value="${price.price}"/></td>	
	                                </tr> 
								</c:forEach>   
                            </tbody>                           
                        </table>
					</span>
                </div>
            </div>  
            
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="pics"> 规格图片(五张) </label>
                <div class="col-sm-10">
					<span class="col-sm-9 no-padding block input-icon input-icon-right mr10">
						<table id="sample-table-1" class="table table-striped table-bordered table-hover ">
                            <thead>
                                <tr>
                                    <th>${price0Str}</th>                                  
                                    <th>图片</th>                                                                      
                                </tr>
                            </thead>

                            <tbody class="guige-tbody">
                            	<c:forEach items="${attributeImageList}" var="image" varStatus="status">
	                                <tr style="display:" class="tmp">
	                                    <td>${image.value}</td>
	                                    <td>	                                    
	                                    <input type="hidden" id="imageList[${status.index}].merchandiseId" name="imageList[${status.index}].merchandiseId" value="${image.merchandiseId}"/>
	                                    <input type="hidden" id="imageList[${status.index}].attributeId" name="imageList[${status.index}].attributeId" value="${image.attributeId}"/>
	                                    <input type="hidden" id="imageList[${status.index}].value" name="imageList[${status.index}].value" value="${image.value}"/>
									    <input type="hidden" id="imageList${status.index}image" name="imageList[${status.index}].image" value="${image.images}"/>
										<button type="button" mult="true" sid="imageList${status.index}image" vid="images${status.index}-view" class="btn btn-sm btn-purple btn-upload-images${status.index}" upfrom="0">
					                        <i class="ace-icon fa fa-upload"></i> 上 传
					                    </button>
					                    <ul sid="imageList${status.index}image" id="images${status.index}-view" class="ace-thumbnails clearfix">
					                        <c:forEach items="${image.list}" var="imageItem" varStatus="current">					                            
					                            <li pic-id="${imageItem.uid}">
					                                <a style="line-height: 150px;text-align: center;width:150px;height: 150px;"
					                                   href="${imageItem.image}" data-rel="colorbox" class="cboxElement">
					                                    <img style="max-height: 150px;max-width: 150px;" alt="" src="${imageItem.image}">
					                                </a>
					                                <div class="tools tools-bottom"><a onclick="imgDel(this);" href="javascript:;" title="删除"><i
					                                        class="ace-icon fa fa-times red"></i></a></div>
					                            </li>					                           
					                        </c:forEach>
					                    </ul>
										</span>
										</td>	                                   
	                                </tr> 
								</c:forEach>   
                            </tbody>                           
                        </table>
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
            var btnUpload = $(".btn-upload-images");
            delEvent(getButtonSetting(btnUpload));
            //绑定事件
            btnUpload.on('click', function () {
                var bs = getButtonSetting($(this));
                uploadDialog(bs);
            });
            
            <c:forEach items="${attributeImageList}" var="image" varStatus="status">
              	var btnUpload${status.index} = $(".btn-upload-images${status.index}");
            	delEvent(getButtonSetting(btnUpload${status.index}));
            	//绑定事件
            	btnUpload${status.index}.on('click', function () {
                	var bs${status.index} = getButtonSetting($(this));
                	uploadDialog(bs${status.index});
            	});
            
             </c:forEach>
                   
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
						 postForm('model-form');
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
