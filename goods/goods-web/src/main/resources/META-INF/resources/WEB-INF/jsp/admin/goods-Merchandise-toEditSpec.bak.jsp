<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>


<title>规格设置</title>
<style>
	*{margin: 0px;padding: 0px;}
	.standard-box{}
	.standard-title{float: left;width:90px;}
	.standard-details-box{float: left;width: 80%;background-color: rgb(248,248,248);padding: 10px;border:1px solid rgb(238,238,238);}
	.color-value{width: 14px;height: 14px;}
	.class-title{margin-bottom: 10px;}
	.standard-details-item{display: inline-block;}
	.standard-details-item dt{display: inline-block;}
	.standard-details{margin-bottom: 20px;}
</style>

<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            规格设置
        </small>
    </h1>
</div>
<form id="update-form" class="form-horizontal" role="form" action="/admin/merchandise/editRelation.do"> 
 <input type="hidden" id="id" name="id" value="${merchandise.id}">
 <input type="hidden" id="attrListSize" name="attrListSize" value="${fn:length(attrList)}">
<div class="standard-box">
	<label class="standard-title">基本参数:</label>
	<div class="standard-details-box">
		<c:if test="${defaultSpec!=null}">
			<input type="hidden" value="-1" name="relationForms[0].attributeId" />
			<input type="checkbox" ${isDefault} class="defaultSpecification" value="0" name="relationForms[0].value" /> 默认
		</c:if>
		<c:forEach items="${attrList}" var="item" varStatus="statusItem">
        	<h4 class="class-title">${item.name}：</h4>
        	<div class="standard-details">
        	<c:forEach items="${item.relationForms}" var="list" varStatus="status">
    			<c:if test="${list.type==1}">
	    			<dl class="standard-details-item">
					<dt>
					<input type="hidden" value="${list.oldAlias}" name="relationForms[${(statusItem.index+1)*20+status.index}].oldAlias" />
					<input type="hidden" value="${item.id}" name="relationForms[${(statusItem.index+1)*20+status.index}].attributeId" />
					<input type="hidden" value="${list.value}" name="relationForms[${(statusItem.index+1)*20+status.index}].value" />
					<input type="checkbox" style="width:20px;height:20px;" value="0" data-value="${list.value}" name="relationForms[${(statusItem.index+1)*20+status.index}].isCheck" /></dt>
					<dt><input type="text" name="relationForms[${(statusItem.index+1)*20+status.index}].alias" value="${list.alias}" /></dt>
					</dl>
				</c:if>
    			<c:if test="${list.type==2}">
	    			<dl class="standard-details-item">
					<dt>
					<input type="hidden" value="${list.oldAlias}" name="extraForms[${(statusItem.index+1)*20+status.index}].oldAlias" />
					<input type="hidden" value="${item.id}" name="extraForms[${(statusItem.index+1)*20+status.index}].attributeId" />
					<input type="hidden" value="${list.value}" name="extraForms[${(statusItem.index+1)*20+status.index}].value" />
					<input type="checkbox"  style="width:20px;height:20px;" value="0"  data-value="${list.value}" name="extraForms[${(statusItem.index+1)*20+status.index}].isCheck" /></dt>
					<dt><input type="text" name="extraForms[${(statusItem.index+1)*20+status.index}].alias" value="${list.alias}" /></dt>
					</dl>
				</c:if>
    		</c:forEach>
    		<button class="btn btn-info add" type="button" data-attributeId="${item.id}" ><i class="ace-icon bigger-110"></i>&nbsp;添加&nbsp; </button>
        	</div>
    	</c:forEach>
		<div class="space-4"></div>
        <div class="clearfix form-actions" style="text-align:center;padding-left: 20%;">
            <div class="col-md-9">
                <button class="btn btn-info refBtn" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>&nbsp;更新商品&nbsp;
                </button>
            </div>
        </div>
	</div>
</div>
</form>

<form id="model-form" class="form-horizontal" role="form" action="/admin/merchandise/editSpec.do"> 
 <input type="hidden" id="id" name="id" value="${merchandise.id}">
<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
            <!-- #section:elements.form -->
            <div style="text-align: right;">
           <%-- <a class="btn btn-sm btn-info" id="updateSpec" api-path="/admin/merchandise/createSpecs.do?id=${merchandise.id}">更新规格</a>  --%>
            </div>
            
            <!--
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="defaultImages">  </label>

                <div class="col-sm-9">
                <span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
                	
                	<c:forEach items="${attrList}" var="item" varStatus="statusItem">
                		${item.name}</br>
                		<c:forEach items="${item.list}" var="list" varStatus="status">
                			<input type="checkbox" value="${list.value}" name="list[${statusItem.index}].value" />${list.value}
                		</c:forEach>
                		</br>
                	</c:forEach>
                </span>
                </div> 
            </div>  -->
            
         
            
            <div class="space-4"></div>
            <div class="form-group">
                <label style="text-align:left;" class="col-sm-1 control-label no-padding-right"> 规格 :</label>

                <div class="col-sm-9">
                <span class="col-sm-12 no-padding block input-icon input-icon-right mr10">
                        <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                            	<th class="center">规格</th>
                                <th class="center">进货价</th>
                                <th class="center">折扣价</th>
                                <th class="center">原价</th>
                                <th class="center">库存</th>
                            </tr>
                            </thead>
                            <tbody id="merchandiseList">
                                <c:if test="${defaultSpec!=null && isDefault == 'checked'}">
                            		<tr>
									<td class='col-sm-3 center'> 默认规格
									<input class='purchase' type='hidden' name='list[0].id' value="${defaultSpec.id}">
									<input class='purchase' type='hidden' name='list[0].state' value="1">
									 </td>
									<td class='col-sm-3 center'><input style='text-align: center;height:35px;' class='purchase' name='list[0].purchase' value="${defaultSpec.purchase}" ></td>
									<td class='col-sm-3 center'><input style='text-align: center;height:35px;' class='purchase' name='list[0].discount' value="${defaultSpec.discount}" ></td>
									<td class='col-sm-3 center'><input style='text-align: center;height:35px;' class='purchase' name='list[0].price' value="${defaultSpec.price}" ></td>
									<td class='col-sm-3 center'><input style='text-align: center;height:35px;' readonly value="${defaultSpec.stock}" ></td>
									</tr>
                            	</c:if>  
                            	<c:if test="${mspecVoList !=null}"><tr>
                            	<c:forEach items="${mspecVoList}" var="item" varStatus="statusItem">
									<td class='col-sm-3 center'> ${item.value0} &nbsp;&nbsp;&nbsp;&nbsp;${item.value1}
									<input class='purchase' type='hidden' name='list[${statusItem.index}].id' value="${item.id}">
									<input class='purchase' type='hidden' name='list[${statusItem.index}].state' value="1">
									 </td>
									<td class='col-sm-3 center'><input style='text-align: center;height:35px;' class='purchase' name='list[${statusItem.index}].purchase' value="${item.purchase}" ></td>									
									<td class='col-sm-3 center'><input style='text-align: center;height:35px;' class='purchase' name='list[${statusItem.index}].discount' value="${item.discount}" ></td>
									<td class='col-sm-3 center'><input style='text-align: center;height:35px;' class='purchase' name='list[${statusItem.index}].price' value="${item.price}" ></td>
									<td class='col-sm-3 center'><input style='text-align: center;height:35px;' readonly value="${item.stock}" ></td>
									</tr>
                            	</c:forEach>
                            	</c:if>
                            </tbody>
                        </table>

                </span>
                </div>
            </div>

            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="defaultImages"> 商品默认图片 </label>

                <div class="col-sm-9">
                <span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
                    <input type="hidden" id="defaultImages" name="images[-1]" value="${defaultImages}"/>
                    <button type="button" mult="true" sid="defaultImages" vid="defaultImages-pic-view" class="btn btn-sm btn-purple btn-upload-pic" upfrom="0">
                        <i class="ace-icon fa fa-upload"></i> 上 传
                    </button>
                    <ul sid="defaultImages" id="defaultImages-pic-view" class="ace-thumbnails clearfix">
                    	<!-- <img style="max-height: 150px;max-width: 150px;" alt="" src="/file/get.do?uid=${defaultImages}"> -->
                   		<c:forEach items="${defaultImages.split(\",\")}" var="pic" varStatus="current">
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
                
			
		<c:if test="${imagesAtt > 0}">
                <input type="hidden" name="imagesAtt" value="${imagesAtt}"/>
                <c:forEach items="${enumerations}" var="item">
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="images${item.id}"> ${item.value}商品图片 </label>

                        <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
                    <input type="hidden" id="images${item.id}" name="images[${item.id}]" value="${images[item.value]}"/>
					<button type="button" mult="true" sid="images${item.id}" vid="images${item.id}-pic-view" class="btn btn-sm btn-purple btn-upload-pic" upfrom="0">
                        <i class="ace-icon fa fa-upload"></i> 上 传
                    </button>

                    <ul sid="images${item.id}" id="images${item.id}-pic-view" class="ace-thumbnails clearfix">
                        <c:if test="${images[item.value] != null && images[item.value] != \"\"}">
                            <c:forEach items="${images[item.value].split(\",\")}" var="pic" varStatus="current">
                                <c:if test="${pic.trim().length() > 0}">
                                    <li pic-id="${pic}">
                                        <a style="line-height: 150px;text-align: center;width:150px;height: 150px;"
                                           href="${domain}/file/get.do?uid=${pic}" data-rel="colorbox" class="cboxElement">
                                            <img style="max-height: 150px;max-width: 150px;" alt="" src="/file/get.do?uid=${pic}">
                                        </a>


                                        <div class="tools tools-bottom"><a onclick="imgDel(this);" href="javascript:;" title="删除"><i
                                                class="ace-icon fa fa-times red"></i></a></div>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </ul>
                    </span>
                        </div>
                    </div>
                </c:forEach>
            </c:if> 
            
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

        
        <!-- PAGE CONTENT ENDS -->
    </div>
    <!-- /.col -->
</div>
<!-- /.row -->
</form>
<script type="text/javascript">
    var scripts = [null, "/qcloud-admin/assets/js/jquery.colorbox-min.js", "/qcloud-admin/assets/js/upload-img.js", null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
        jQuery(function ($) {
        	var merchandiseId=$("#id").val();
        	var getRelationUrl="/admin/merchandise/getRelation.do";
        	$.ajax({
        		type:"get",
				url: getRelationUrl,
				data: {merchandiseId:merchandiseId},
				dataType:"json",
				success:function(data){
					var attrlists=data.data.relations;
					$.each(attrlists,function(i,obj){
	    				$("input[name$='isCheck']").each(function(){
	    					if($(this).attr("data-value")==attrlists[i].value&&attrlists[i].isCheck==1){
	    						$(this).prop("checked",true);
	    						$(this).val("1");
	    					}
	            		});
	            	});
	            	//$(".refBtn").click();
    			},
    			error:function(){
						
				}
			});       
			//
			$("input[name$='isCheck']").click(function(){
				if($(this).is(":checked")){
					$(this).val("1");
				}else{
					$(this).val("0");
				}
			
			});
			
			//
			if($(".defaultSpecification").is(":checked")){
				$(".defaultSpecification").val(1);
			}
			
			$(".defaultSpecification").click(function(){
				if($(this).is(":checked")){
					$(this).val(1);
				}else{
					$(this).val(0);
				}
			});
			 
            $('#updateSpec').on('click', function () {
                var delUrl = $(this).attr('api-path');
                BootstrapDialog.show({
                    title: '确认更新？',
                    message: '如果商品规格无问题，请不要更新！',
                    buttons: [{
                        id: 'btn-1',
                        label: '确定',
                        cssClass: 'btn btn-primary',
                        action: function (dialogItself) {
                            $.get(delUrl, {},
                                    function (data) {
                                        data = JSON.parse(data);
                                        if (parseInt(data["status"]) === 0) {
                                            dialogItself.setTitle('更新失败');
                                            dialogItself.setMessage(data["message"]);
                                            dialogItself.setType(BootstrapDialog.TYPE_DANGER);
                                            dialogItself.getButton('btn-1').remove();
                                        } else {
                                            dialogItself.setTitle('成功');
                                            dialogItself.setMessage("更新成功！");
                                            dialogItself.setType(BootstrapDialog.TYPE_SUCCESS);
                                            setTimeout(function () {
                                                dialogItself.close();
                                            }, 1000);
                                            setTimeout(function () {
                                                location.reload();
                                            }, 1500);
                                        }
                                    });
                        }
                    }, {
                        label: '取消',
                        action: function (dialogItself) {
                            dialogItself.close();
                        }
                    }]
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
            
            

            $(window)
            .off('resize.chosen')
            .on('resize.chosen', function () {
                $('.chosen-select').each(function () {
                    var $this = $(this);
                    $this.next().css({'width': $this.parent().width()});
                })
            }).trigger('resize.chosen');

            //表单验证
            $("#update-form").validate({
                errorElement: 'div',
                errorClass: 'help-block col-xs-12 col-sm-reset inline',
                focusInvalid: false,
                rules: {
                
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
                    postForm('update-form');
                },
                invalidHandler: function (form) {
                }
            });
            
            $("#model-form").validate({
                errorElement: 'div',
                errorClass: 'help-block col-xs-12 col-sm-reset inline',
                focusInvalid: false,
                rules: {
                
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
            
            var attrSize=0;
            $(".add").on('click',function(){
            	var attributeId=$(this).attr("data-attributeId");
            	var str="";
            	str="<dl class='standard-details-item'>"
				+"<dt>"
				+"<input type='hidden' class='appendOa' value=''  name='extraForms["+attrSize+"].oldAlias'/>"
				+"<input type='hidden' value='"+attributeId+"' name='extraForms["+attrSize+"].attributeId' />"
				+"<input type='hidden' class='appendVa' value='' name='extraForms["+attrSize+"].value' />"
				+"<input type='checkbox'  style='width:20px;height:20px;' value='0' data-value='' name='extraForms["+attrSize+"].isCheck' /></dt>"
				+"<dt><input type='text' class='appendDt' name='extraForms["+attrSize+"].alias' value='' /></dt>"
				+"</dl>";
            	$(this).prev().append(str);
            	attrSize++;
            	$("input[name$='isCheck']").click(function(){
				if($(this).is(":checked")){
						$(this).val("1");
					}else{
						$(this).val("0");
					}
				});
				
				$(".appendDt").blur(function(){
					$(this).parent().prev().find(".appendOa").val($(this).val());
					$(this).parent().prev().find(".appendVa").val($(this).val());
				});
            });
            
            
            
            
            
            
        });
    })
</script>
