<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>

<title>编辑</title>
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
        <form id="model-form" class="form-horizontal" role="form" action="/admin/merchandiseStock/update.do">
            <!-- #section:elements.form -->
            <input type="hidden" name="id" value="${merchandiseStock.id > 0 ? merchandiseStock.id:0}">

            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="unifiedMerchandiseId"> 商品ID </label>

                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="200" readonly id="unifiedMerchandiseId"
                               name="unifiedMerchandiseId" placeholder="商品ID"
                               value="${merchandiseStock.unifiedMerchandiseId}"/>
						<i class="ace-icon fa"></i>
					</span>
                    <c:if test="${!(merchandiseStock.id > 0)}">
                    <a type="button" class="btn btn-info btn-sm select-product">选择商品</a>
                    </c:if>
                </div>
            </div>

            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="merchandiseName"> 商品名称 </label>

                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="200" readonly id="merchandiseName"
                               name="merchandiseName" placeholder="商品名称"
                               value="${merchandiseStock.merchandiseName}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>

            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="purchase"> 进货价 </label>

                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" readonly class="width-100" maxlength="20" id="purchase" name="purchase"
                               placeholder="进货价"
                               value="${merchandiseStock.purchase}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="discount"> 折扣价 </label>

                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" readonly class="width-100" maxlength="20" id="discount" name="discount"
                               placeholder="折扣价"
                               value="${merchandiseStock.discount}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="price"> 原价 </label>

                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" readonly class="width-100" maxlength="20" id="price" name="price" placeholder="原价"
                               value="${merchandiseStock.price}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="stock"> 库存 </label>

                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="stock" name="stock" placeholder="库存"
                               value="0"/>
						<i class="ace-icon fa"></i>
					</span>
					<span style="color:red;">
						该项为库存调整数量：负数则减少，整数则增加
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
</div>
<!-- /.row -->

<script type="text/javascript">
    var scripts = [null, null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
        jQuery(function ($) {
            $(".select-product").on("click", function () {
                BootstrapDialog.show({
                    title: "商品列表",
                    message: $('<div></div>').load('/admin/stockState/selectProductList.do'),
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
                                var specifications = obj.attr("data-specifications");
                                var purchase = obj.attr("data-purchase");
                                var discount = obj.attr("data-discount");
                                var price = obj.attr("data-price");
                                var stock = obj.attr("data-stock");
                                if (id && name) {
                                    $("#unifiedMerchandiseId").val(id);
                                    $("#merchandiseName").val(name);
                                    $("#purchase").val(purchase);
                                    $("#discount").val(discount);
                                    $("#price").val(price);
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

//
//            $(window)
//                    .off('resize.chosen')
//                    .on('resize.chosen', function () {
//                        $('.chosen-select').each(function () {
//                            var $this = $(this);
//                            $this.next().css({'width': $this.parent().width()});
//                        })
//                    }).trigger('resize.chosen');

            //表单验证
            $("#model-form").validate({
                errorElement: 'div',
                errorClass: 'help-block col-xs-12 col-sm-reset inline',
                focusInvalid: false,
                rules: {
                    unifiedMerchandiseId: {
                        required: true
                    },
                    price: {
                        required: true
                    },
                    /**
                    stock: {
                        required: true,
                        range: [-9999, 99999999],
                        digits: true
                    }
                    */
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
