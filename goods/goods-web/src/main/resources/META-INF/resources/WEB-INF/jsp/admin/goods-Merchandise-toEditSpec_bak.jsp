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
<form id="update-form" class="form-horizontal" role="form" action="/admin/merchandise/getSpecMerchandiseList.do"> 
 <input type="hidden" id="id" name="id" value="${merchandise.id}">
 <input type="hidden" id="attrListSize" name="attrListSize" value="${fn:length(attrList)}">
<div class="standard-box">
	<label class="standard-title">基本参数:</label>
	<div class="standard-details-box">
		<c:if test="${defaultSpec!=null}">
			<input type="checkbox" ${isDefault} value="-1" name="attrlist[0].value" /> 默认
		</c:if>
		<c:forEach items="${attrList}" var="item" varStatus="statusItem">
        	<h4 class="class-title">${item.name}：</h4>
        	<div class="standard-details">
        	<c:forEach items="${item.list}" var="list" varStatus="status">
    			
    			<dl class="standard-details-item">
				<dt>
				<input type="hidden" value="${list.value}" name="initStr[${statusItem.index}].value" />
				<input type="checkbox" value="${list.value}" name="attrlist[${statusItem.index}].value" /></dt>
				<dt>${list.value}</dt>
				</dl>
    		
    		</c:forEach>
        	</div>
    	</c:forEach>
		<div class="space-4"></div>
        <div class="clearfix form-actions">
            <div class="col-md-offset-3 col-md-9">
                <button class="btn btn-info refBtn" type="button"><i class="ace-icon fa fa-check bigger-110"></i>&nbsp;更新商品&nbsp;
                </button>
            </div>
        </div>
	</div>
</div>
</form>

<form id="model-form" class="form-horizontal" role="form" action="/admin/merchandise/editSpec.do"> 
<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
            <!-- #section:elements.form -->
            <div style="text-align: right;">
            <a class="btn btn-sm btn-info" id="updateSpec" api-path="/admin/merchandise/createSpecs.do?id=${merchandise.id}">更新规格</a>
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
                <label class="col-sm-1 control-label no-padding-right"> 规格 </label>

                <div class="col-sm-9">
                <span class="col-sm-9 no-padding block input-icon input-icon-right mr10">
                        <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                            	<th class="center">规格</th>
                                <th class="center">进货价</th>
                                <th class="center">折扣价</th>
                                <th class="center">原价</th>
                               
                            </tr>
                            </thead>
                            <tbody id="merchandiseList">
                          <!--  	<c:if test="${defaultVo!=null}">
                            		<tr>
									<td class='col-sm-3 center'> 默认规格
									<input class='purchase' type='hidden' name='list[0].id' value="${defaultVo.id}">
									<input class='purchase' type='hidden' name='list[0].state' value="1">
									 </td>
									<td class='col-sm-3 center'><input class='purchase' name='list[0].purchase' value="${defaultVo.purchase}" ></td>
									<td class='col-sm-3 center'><input class='purchase' name='list[0].price' value="${defaultVo.price}" ></td>
									<td class='col-sm-3 center'><input class='purchase' name='list[0].discount' value="${defaultVo.discount}" ></td>
									</tr>
                            	</c:if>  -->
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
	    				$("input[name^='attrlist']").each(function(){
	    					if($(this).val()==attrlists[i].value){
	    						$(this).prop("checked",true);
	    					}
	            		});
	            	});
	            	$(".refBtn").click();
    			},
    			error:function(){
						
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
            
           $(".refBtn").click(function(){
            	var str1="";
            	var str2="";
            	var initStr1="";
            	var initStr2="";
            	var id=$("#id").val();
            	var attrListSize=$("#attrListSize").val();
            	if(attrListSize==0){
            		if($("input[name^='attrlist[0]").is(":checked")){
            			str1="-1";
            		}else{
            			str1="0";
            		}
            	}else{
            		for(var i=0;i<attrListSize;i++){
	            		$("input[name^='attrlist["+i+"]']").each(function(){
	            			if($(this).is(":checked")){
	            				if(i==1){
	            					str2=str2+$(this).val()+",";
	            					initStr2+=$(this).prev().val()+",";
	            				}
	            				if(i==0){
	            					str1=str1+$(this).val()+",";
	            					initStr1+=$(this).prev().val()+",";
	            				}
	            				
	            			}
	            		});
            		}
            	}
            	//alert(initStr1);
            	var url="/admin/merchandise/getSpecMerchandiseList.do?id="+id+"&str1="+str1+"&str2="+str2+"&initStr1="+initStr1+"&initStr2="+initStr2;
            	if(str1==""&&str2==""){
            		return ;
            	}
            	$.ajax({
					type:"get",
					url:url,
					dataType:"json",
					success:function(data){
						var merchandiseList=data.data.merchandiseList;
						var imageList=data.data.images;
						var str="";
						$.each(merchandiseList,function(i,obj){
							str+="<tr>";
							str+="<td class='col-sm-3 center'>"+merchandiseList[i].value0+"&nbsp;&nbsp;&nbsp;&nbsp;"+merchandiseList[i].value1
							+"<input class='purchase' type='hidden' name='list["+i+"].id' value="+merchandiseList[i].id +">" 
							+"<input class='purchase' type='hidden' name='list["+i+"].state' value='1'>" 
							+" </td>";
							str+="<td class='col-sm-3 center'><input class='purchase' name='list["+i+"].purchase' value="+merchandiseList[i].purchase+" ></td>";
							str+="<td class='col-sm-3 center'><input class='purchase' name='list["+i+"].discount' value="+merchandiseList[i].discount+" ></td>";
							str+="<td class='col-sm-3 center'><input class='purchase' name='list["+i+"].price' value="+merchandiseList[i].price+" ></td>";
							str+="</tr>";
						});
						$("#merchandiseList").html(str);
						
					},
					error:function(){
						
					}
				});
            	
            }); 
        });
    })
</script>
