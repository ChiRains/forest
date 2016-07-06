<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>新增自定义分类</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />
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

    /*.select-dialog .modal-body{*/
    /*padding: 0;*/
    /*}*/
</style>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css" />

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        分类管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="model-form" class="form-horizontal"  role="form" action="/admin/salesPromotion/addClassifyForMallCustom.do">
            <!-- #section:elements.form -->
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="classifyId"> 分类定义 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<select class="chosen-select" id="classifyId" name="classifyId" data-placeholder="请选择分类" onchange="change();">
	                		<option value="0">请选择分类</option>
		                 	<c:forEach items="${classifyMap}" var="classifyMap" varStatus="current">
		                   		 <option value="${classifyMap.key}"
		                   		 <c:if test="${classifyMap.key eq query.classifyId}">
			                   		 selected
	                			 </c:if>>${classifyMap.value}</option>
		                   	</c:forEach>
	                	</select>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="stock"> 商品 </label>

                <div class="col-sm-6">
                    <table class="table table-striped table-bordered table-hover kv-table">
                        <thead>
                        <tr>
                            <th>商品编号</th>
                            <th>商品名称</th>
                            <th>商品规格</th>
                            <th>商品库存</th>
                            <th>排序</th>
                            <th class="del-column"></th>
                        </tr>
                        </thead>
                        <tbody id="productList">
                        <c:forEach items="${voList}" var="item" varStatus="current">
                            <tr class="template kv-table-row">
                                <td><input type="text"
                                           name="unifiedMerchandiseIds[${current.index}]"
                                           value="${item.unifiedMerchandiseId}" readonly class="config-key"></td>
                                <td><input type="text" value="${item.qUnifiedMerchandise.name}" readonly class="config-value"></td>
                                <td><input type="text" value="${item.qUnifiedMerchandise.specifications}" readonly class="config-value"></td>
                                <td><input type="text" value="${item.qUnifiedMerchandise.stock}" readonly class="config-value"></td>
                                <td><input type="text" name="orderNums[${current.index}]"
                                value="${item.orderNum}"></td>
                                <td class="del-column">
                                    <button type="button" class="close del-row-trigger">×</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="6">
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
                    <button class="btn btn-info" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>&nbsp;保&nbsp;存&nbsp;</button>
                    &nbsp; &nbsp; &nbsp;
                    <button class="btn" type="button" onclick="window.location.href='#admin/salesPromotion/mallList'">
                    <i class="ace-icon fa fa-undo bigger-110"></i>&nbsp;返&nbsp;回&nbsp;</button>
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
        	function delProduct() {
                $(".del-row-trigger").on("click", function () {
                    $(this).parents("tr").remove();
                    reSetIndex();
                });
            }

            function reSetIndex() {
                $("#productList").children().each(function (i) {
                    $(this).find("input[name^='unifiedMerchandiseIds']").attr("name", $(this).find("input[name^='unifiedMerchandiseIds']").attr("name").replace(/\[.*?\]/g, "[" + i + "]"));
                    $(this).find("input[name^='orderNums']").attr("name", $(this).find("input[name^='orderNums']").attr("name").replace(/\[.*?\]/g, "[" + i + "]"));
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
                                	var exist=0;
									$("input[name^='unifiedMerchandiseIds']").each(function(){
										var existId=$(this).val();
										if(existId==id){
											exist=1;
										}
									});
									if(exist==0){
	                                    var productList = $("#productList");
	                                    var count = productList[0].childElementCount;
	                                    //console.log(count);
	                                    count >= 0 && $("#productList").append(
	                                            '<tr class="template kv-table-row"> ' +
	                                            '<td><input type="text" name="unifiedMerchandiseIds[]" value="' + id + '" readonly class="config-key"></td> ' +
	                                            '<td><input type="text" value="' + name + '" readonly class="config-value"></td>' +
	                                            '<td><input type="text" value="' + specifications + '" readonly class="config-value"></td>' +
	                                            '<td><input type="text" value="' + stock + '" readonly class="config-value"></td>' +
	                                            '<td><input type="text" name="orderNums[]" value="" class="config-value"></td>' +
	                                            '<td class="del-column"> <button type="button" class="close del-row-trigger">×</button> </td> ' +
	                                            '</tr>');
	                                    delProduct();
	                                    reSetIndex();
                                    }else{
                                    	BootstrapDialog.show({
                                    		title:'商品已存在.',
                                    		message:'商品已存在.'
                                    	});
                                    }
                                   
                                }
                                return false;
                            }

                            var url = obj.attr("href").replace(/#/, "/");

                            if (url) {
                                dialog.getModalBody().html($('<div></div>').load(url));
                                return false;
                            }

                            return false;
                        });
                    }
                });
            });
            $('.chosen-select').chosen({allow_single_deselect:true}); 
            $(window)
            .off('resize.chosen')
            .on('resize.chosen', function() {
                $('.chosen-select').each(function() {
                     var $this = $(this);
                     $this.next().css({'width': $this.parent().width()});
                })
            }).trigger('resize.chosen');
            
            var width = $('.chosen-choices input').parent().parent().parent().width();                       
            $('.chosen-choices input').css({'width':width});
            
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
    });
    
    function change() {
		window.location.href = "#admin/salesPromotion/toAddClassifyForMallCustom?classifyId="+$("#classifyId").val();
	}
</script>
