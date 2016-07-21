<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        组合管理
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
        <form id="model-form" class="form-horizontal" role="form" action="/admin/combinationMerchandise/update.do">
            <!-- #section:elements.form -->
            <input type="hidden" name="combinationMerchandise.id"
                   value="${combinationMerchandise.id > 0?combinationMerchandise.id:0}">

            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name"> 组合名称 </label>

                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="name" name="combinationMerchandise.name"
                               placeholder="组合名称" value="${combinationMerchandise.name}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>

            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="purchase"> 进货价 </label>

                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="purchase"
                               name="combinationMerchandise.purchase"
                               placeholder="进货价" value="${combinationMerchandise.purchase}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="discount"> 折扣价 </label>

                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="discount"
                               name="combinationMerchandise.discount"
                               placeholder="折扣价" value="${combinationMerchandise.discount}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="price"> 原价 </label>

                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="price"
                               name="combinationMerchandise.price" placeholder="原价"
                               value="${combinationMerchandise.price}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="stock"> 库存 </label>

                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="stock"
                               name="combinationMerchandise.stock" placeholder="库存"
                               value="${combinationMerchandise.stock}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="image"> 缩略图 </label>

                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="hidden"  id="image" name="combinationMerchandise.image" value="${combinationMerchandise.imageUID}"/>
						<button type="button" mult="false" sid="image" vid="pic-pic-view"   class="btn btn-sm btn-purple btn-upload-pic"  upfrom="0"  ><i class="ace-icon fa fa-upload"></i> 上 传</button><ul sid="pic" id="pic-pic-view" class="ace-thumbnails clearfix">
							<img style="max-width:150px;max-height:150px;" alt="" src="${combinationMerchandise.image}">
						</ul>
				</span>
                </div>
            </div>
            
                    <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="">  </label>
               <div class="col-sm-9">
					<span style="color:red;" class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						${fileSize}
					</span>
                </div>
            </div>
            
            
             		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="type"> 类型（新增后不能再修改） </label>

                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<c:if test="${combinationMerchandise != null}">
							<select class="width-100" name="combinationMerchandise.type" id="type">
								<c:if test="${combinationMerchandiseType == 1}">
									<option value="1">固定搭配</option>	
								</c:if>
								<c:if test="${combinationMerchandiseType == 2}">
									<option value="2">自由搭配</option>
								</c:if>
							</select>
						</c:if>
						<c:if test="${combinationMerchandise == null}">
							<select class="width-100" name="combinationMerchandise.type" id="type">
								<option value="1">固定搭配</option>
								<option value="2">自由搭配</option>
							</select>
						</c:if>
					</span>
                </div>
            </div>
            

            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="stock"> 组合商品 </label>

                <div class="col-sm-6">
                    <table class="table table-striped table-bordered table-hover kv-table">
                        <thead>
                        <tr>
                            <th>商品编号</th>
                            <th>商品名称</th>
                            <th>商品规格</th>
                            <th>商品库存</th>
                            <th>商品数量</th>
                            <th>折扣价</th>
                            <th class="del-column"></th>
                        </tr>
                        </thead>
                        <tbody id="productList">
                        <c:forEach items="${voList}" var="item" varStatus="current">
                            <tr class="template kv-table-row">
                                <td><input type="text"
                                           name="combinationMerchandiseItems[${current.index}].relaUnifiedMerchandiseId"
                                           value="${item.unid}" readonly class="config-key"></td>
                                <td><input type="text" value="${item.name}" readonly class="config-value"></td>
                                <td><input type="text" value="${item.specifications}" readonly
                                           class="config-value"></td>
                                <td><input type="text" value="${item.stock}" readonly class="config-value"></td>
                                <td><input type="text" value="${item.num}" name="combinationMerchandiseItems[${current.index}].number" class="config-value"></td>
                                <td><input type="text" value="${item.discount}" name="combinationMerchandiseItems[${current.index}].discount" class="config-value"></td>
                                <td class="del-column">
                                    <button type="button" class="close del-row-trigger">×</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="7">
                                <button type="button" class="add-row-trigger btn-link">增加</button>
                            </td>
                        </tr>
                        </tfoot>
                    </table>
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
   var scripts = [null, "/qcloud-admin/assets/js/jquery.colorbox-min.js","/qcloud-admin/assets/js/upload-img.js","/qcloud-admin/assets/js/chosen.jquery.min.js", null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
        jQuery(function ($) {
        
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

            function delProduct() {
                $(".del-row-trigger").on("click", function () {
                    $(this).parents("tr").remove();
                    reSetIndex();
                });
            }

            function reSetIndex() {
                $("#productList").children().each(function (i) {
                    $(this).find("input[name$='relaUnifiedMerchandiseId']").attr("name", $(this).find("input[name$='relaUnifiedMerchandiseId']").attr("name").replace(/\[.*?\]/g, "[" + i + "]"));
                    $(this).find("input[name$='number']").attr("name", $(this).find("input[name$='number']").attr("name").replace(/\[.*?\]/g, "[" + i + "]"));
                	$(this).find("input[name$='discount']").attr("name", $(this).find("input[name$='discount']").attr("name").replace(/\[.*?\]/g, "[" + i + "]"));
                });
            }

            delProduct();

            $(".add-row-trigger").on("click", function () {
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
                                var specifications = obj.attr("data-specifications");
                                var stock = obj.attr("data-stock");
                                if (id && name) {

                                    var productList = $("#productList");
                                    var count = productList[0].childElementCount;
                                    //console.log(count);
                                    count >= 0 && $("#productList").append(
                                            '<tr class="template kv-table-row"> ' +
                                            '<td><input type="text" name="combinationMerchandiseItems[].relaUnifiedMerchandiseId" value="' + id + '" readonly class="config-key"></td> ' +
                                            '<td><input type="text" value="' + name + '" readonly class="config-value"></td>' +
                                            '<td><input type="text" value="' + specifications + '" readonly class="config-value"></td>' +
                                            '<td><input type="text" value="' + stock + '" readonly class="config-value"></td>' +
                                            '<td><input type="text" id="num" name="combinationMerchandiseItems[].number" value="0"/></td>' +
                                            '<td><input type="text" id="num" name="combinationMerchandiseItems[].discount" value="0"/></td>' +
                                            '<td class="del-column"> <button type="button" class="close del-row-trigger">×</button> </td> ' +
                                            '</tr>');
                                    delProduct();
                                    reSetIndex();
                                  //  dialog.close();
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

            //表单验证
            $("#model-form").validate({
                errorElement: 'div',
                errorClass: 'help-block col-xs-12 col-sm-reset inline',
                focusInvalid: false,
                rules: {
//                    name: {
//                        required: true
//                    },
//                    mobile: {
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
