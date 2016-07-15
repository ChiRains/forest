<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>新增品牌特卖商品</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        品牌特卖商品管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="model-form" class="form-horizontal"  role="form" action="/admin/brandSales/addMerchandise.do">
            <!-- #section:elements.form -->
			<input type="hidden" name="classifyId" value="${classifyId}">
			<input type="hidden" id="unifiedMerchandiseId" name="unifiedMerchandiseId" value="${item.unid}" readonly class="config-key">

                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="discount"> 商品名称</label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
					<input type="text" id="unifiedMerchandiseName" value="${item.name}" readonly class="config-value">                                
					<button type="button" class="add btn-link">增加</button>
											<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="image"> 图片 </label>
                <div class="col-sm-9">
					<input type="hidden"  id="image" name="image" value="" onchange="fileChange(this);"/>
					<button type="button" mult="false" sid="image" vid="pic-pic-view2" class="btn btn-sm btn-purple btn-upload-pic"  upfrom="0"  ><i class="ace-icon fa fa-upload">
					</i> 上 传</button>
                    <ul sid="image" id="pic-pic-view2" class="ace-thumbnails clearfix">
                        <li pic-id="">
                            <a style="line-height: 150px;text-align: center;width:150px;height: 150px;" href="" data-rel="colorbox" class="cboxElement">
                                <img style="max-height: 150px;max-width: 150px;" alt="" src="">
                            </a>
                            <div class="tools tools-bottom"><a onclick="imgDel(this);" href="javascript:;" title="删除"><i class="ace-icon fa fa-times red"></i></a></div>
                        </li>
                    </ul>
                </div>
            </div>

                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="discount"> 商品价格</label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="discount" name="discount" placeholder="商品价格" value="" onkeyup="this.value=this.value.replace(/\D/g, '')"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>

                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="integral">积分</label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="integral" name="integral" placeholder="积分" value=""/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                     
                       <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="stock"> 商品库存 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="stock" name="stock" placeholder="商品库存" value="" onkeyup="this.value=this.value.replace(/\D/g, '')"/>
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
            //图片
	       	 $('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
	            $("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange'></i>");
	            var btnUpload = $(".btn-upload-pic");
	            delEvent(getButtonSetting(btnUpload));
	            btnUpload.on('click', function () {
	                var bs = getButtonSetting($(this));
	                uploadDialog(bs);
	            });
            //选择商品
            $(".add").on("click", function () {
                BootstrapDialog.show({
                    title: "商品列表",
                    message: $('<div></div>').load('/admin/unifiedMerchandise/selectProductList.do'),
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
                                $("#unifiedMerchandiseName").attr("value",name);
                                $("#unifiedMerchandiseId").attr("value",id);
                                    reSetIndex();
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
                    postForm('model-form');
                },
                invalidHandler: function (form) {
                }
            });
        });
    })
</script>
