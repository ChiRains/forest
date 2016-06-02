<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>

<title>编辑</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            编辑
        </small>
    </h1>
</div>
<!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->

        <form id="model-form" class="form-horizontal" role="form" action="/admin/merchandise/editSpecifications.do">
            <!-- #section:elements.form -->
            <input type="hidden" name="id" value="${merchandise.id}">
            <div style="text-align: right;">
            <a class="btn btn-sm btn-info" id="updateSpec" api-path="/admin/merchandise/createSpecs.do?id=${merchandise.id}">更新规格</a>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right"> 规格 </label>

                <div class="col-sm-9">
                <span class="col-sm-9 no-padding block input-icon input-icon-right mr10">
                        <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <c:set value="${attributeDefinitionHashMap[list[0].attributeId0] != null}" var="attrOne"></c:set>
                                <c:set value="${attributeDefinitionHashMap[list[0].attributeId1] != null}" var="attrTwo"></c:set>
                                <c:set value="${attributeDefinitionHashMap[list[0].attributeId2] != null}" var="attrThree"></c:set>
                                <c:if test="${attrOne}">
                                    <th>${attributeDefinitionHashMap[list[0].attributeId0].name}</th>
                                </c:if>
                                <c:if test="${attrTwo}">
                                    <th class="center">${attributeDefinitionHashMap[list[0].attributeId1].name}</th>
                                </c:if>
                                <c:if test="${attrThree}">
                                    <th class="center">${attributeDefinitionHashMap[list[0].attributeId2].name}</th>
                                </c:if>
                                 <th class="center">状态</th>
                                <th class="center">进货价</th>
                                <th class="center">折扣价</th>
                                <th class="center">原价</th>
                               
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list}" var="item" varStatus="status">
                                <tr>
                                    <c:if test="${attrOne}">
                                        <td class="col-sm-1">${item.value0}</td>
                                    </c:if>
                                    <c:if test="${attrTwo}">
                                        <td class="col-sm-1">${item.value1}</td>
                                    </c:if>
                                    <c:if test="${attrThree}">
                                        <td class="col-sm-1">${item.value2}</td>
                                    </c:if>
                                    <td>
                                    	<input type="hidden" name="list[${status.index}].id" value="${item.id}"/>
                                    	<input type="hidden" name="list[${status.index}].state" value="${item.state}"/>
                                        <input 
                                          <c:if test="${item.state > 0}">checked="checked"</c:if> 
                                          type="checkbox"  
                                          class="ace ace-switch ace-switch-5 clickState" />
                                          
                                        <span class="lbl middle"></span>
                                    </td>
                                    
                                    <td class="col-sm-3">
                                    	<input class="purchase"  <c:if test="${item.state == 0}">readonly="true"</c:if>  name="list[${status.index}].purchase" value="${item.purchase}"/>
                                    </td>
                                    <td class="col-sm-3">
                                    	<input class="discount"  <c:if test="${item.state == 0}">readonly="true"</c:if>  name="list[${status.index}].discount" value="${item.discount}"/>
                                    </td>
                                    <td class="col-sm-3">
                                    	<input class="price"  <c:if test="${item.state == 0}">readonly="true"</c:if>  name="list[${status.index}].price" value="${item.price}"/>
                                    </td>
                                   
                                </tr>
                            </c:forEach>
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
                    <input type="hidden" id="defaultImages" name="images[-1]" value="${images[""]}"/>
                    <button type="button" mult="true" sid="defaultImages" vid="defaultImages-pic-view" class="btn btn-sm btn-purple btn-upload-pic" upfrom="0">
                        <i class="ace-icon fa fa-upload"></i> 上 传
                    </button>
                    <ul sid="defaultImages" id="defaultImages-pic-view" class="ace-thumbnails clearfix">
                        <c:if test="${images[\"\"] != null && images[\"\"] != \"\"}">
                            <c:forEach items="${images[\"\"].split(\",\")}" var="pic" varStatus="current">
                                <c:if test="${pic.trim().length() > 0}">
                                    <li pic-id="${pic}">
                                        <a style="line-height: 150px;text-align: center;width:150px;height: 150px;"
                                           href="${domain}/file/get.do?uid=${pic}" data-rel="colorbox" class="cboxElement">
                                            <img style="max-height: 150px;max-width: 150px;" alt="" src="${domain}/file/get.do?uid=${pic}">
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
                                            <img style="max-height: 150px;max-width: 150px;" alt="" src="${domain}/file/get.do?uid=${pic}">
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


            <%--<div class="space-4"></div>--%>
            <%--<div class="form-group">--%>
            <%--<label class="col-sm-3 control-label no-padding-right" for="modeId"> 分销模式 </label>--%>

            <%--<div class="col-sm-9">--%>
            <%--<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">--%>
            <%--<select name="modeId" class="width-100" id="modeId">--%>
            <%--<c:forEach items="${modes}" var="item" varStatus="current">--%>
            <%--<option value="${item.key}"--%>
            <%--<c:if test="${item.key == commissionScheme.modeId}">selected</c:if> >${item.value.name}</option>--%>
            <%--</c:forEach>--%>
            <%--</select>--%>
            <%--</span>--%>
            <%--</div>--%>
            <%--</div>--%>

            <%--<div class="space-4"></div>--%>
            <%--<div class="form-group">--%>
            <%--<label class="col-sm-3 control-label no-padding-right" for="effectiveDate"> 生效时间 </label>--%>

            <%--<div class="col-sm-9">--%>
            <%--<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">--%>
            <%--<input type="text" class="width-100" maxlength="50" id="effectiveDate" name="effectiveDate"--%>
            <%--placeholder="生效时间"--%>
            <%--value="<fmt:formatDate value="${commissionScheme.effectiveDate}" pattern="yyyy-MM-dd"/>"/>--%>
            <%--<i class="ace-icon fa"></i>--%>
            <%--</span>--%>
            <%--</div>--%>
            <%--</div>--%>

            <%--<div class="space-4"></div>--%>
            <%--<div class="form-group">--%>
            <%--<label class="col-sm-3 control-label no-padding-right" for="period"> 周期 </label>--%>
            <%--<div class="col-sm-9">--%>
            <%--<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">--%>
            <%--<select name="period" class="width-100" id="period">--%>
            <%--<c:forEach items="${periods}" var="item" varStatus="current">--%>
            <%--<option value="${item.key}"--%>
            <%--<c:if test="${item.key == commissionScheme.period}">selected</c:if> >${item.name}</option>--%>
            <%--</c:forEach>--%>
            <%--</select>--%>
            <%--</span>--%>
            <%--</div>--%>
            <%--</div>--%>

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
</div>
<!-- /.row -->

<script type="text/javascript">
    var scripts = [null, "/qcloud-admin/assets/js/jquery.colorbox-min.js", "/qcloud-admin/assets/js/upload-img.js", null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
        jQuery(function ($) {
			$(".clickState").click(function (){
				if($(this).attr("checked")=="checked"){
					$(this).removeAttr("checked");//不被选中
					$(this).parent().next().find(".purchase").attr("readonly","true");
					$(this).parent().next().next().find(".discount").attr("readonly","true");
					$(this).parent().next().next().next().find(".price").attr("readonly","true");
					$(this).prev().val("0");
					//$(this).parent().prev().html("123456");
					//$(this).parent().prev().prev().html("猪肝色");
					
				}else{//选中
					$(this).attr("checked","true");
					$(this).parent().next().find(".purchase").removeAttr("readonly");
					$(this).parent().next().next().find(".discount").removeAttr("readonly");
					$(this).parent().next().next().next().find(".price").removeAttr("readonly");
					$(this).prev().val("1");
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
//                    name: {
//                        required: true
//                    },
//                    effectiveDate: {
//                        required: true
//                    },
//                    period: {
//                        required: true
//                    },
//                    sort: {
//                        required: true,
//                        range: [0, 99999999],
//                        digits: true
//                    }
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
    })
</script>
