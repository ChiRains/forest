<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>添加秒杀商品</title>
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
	.datepicker {
    	z-index:10000!important;
    }
    .datetimepicker {
    	z-index:10000!important;
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
       秒杀商品
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="groupbuys-form" class="form-horizontal"  role="form" action="/admin/merchandiseSeckill/add.do?screeningsId=${query.screeningsId}">
            <!-- #section:elements.form -->
            
            <div class="space-4"></div>
            <%-- <div class="form-group">
            	<label class="col-sm-6" style="color:red;"> 注: 下表所示原价为积分价格 ，其兑换率为 ：${bit}/100人民币</label>
            </div> --%>
            
            <div class="space-4"></div>
            <div class="form-group">
                <div class="col-sm-12">
                	<%-- <input type="hidden" name="screeningsId" value="${query.screeningsId}" readonly class="config-key"> --%>
                    <table class="table table-striped table-bordered table-hover kv-table">
                        <thead>
                        <tr>
                            <th>统一商品ID</th>
                            <th>商品名称</th>
                         	<!-- <th>进货价</th> -->
                          	<th>折扣价</th>
                           	<!-- <th>原价</th> -->
                            <th>商品原库存</th>
                            <th>秒杀库存</th>
                            <th>秒杀价格</th>
                            <th>排序</th>
                            <th class="del-column"></th>
                        </tr>
                        </thead>
                        <tbody id="productList">
                        	
                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="11">
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
                    <button class="btn" type="button" onclick="window.location.href='#admin/merchandiseSeckill/list?screeningsId=${query.screeningsId}'">
                    <i class="ace-icon fa fa-undo bigger-110"></i>&nbsp;返&nbsp;回&nbsp;</button>
                </div>
            </div>

        </form>
        <!-- PAGE CONTENT ENDS -->
    </div>
    <!-- /.col -->
</div><!-- /.row -->

<script type="text/javascript">
	var scripts = [null, "/qcloud-admin/assets/js/jquery.colorbox-min.js","/qcloud-admin/assets/js/upload-img.js","/qcloud-admin/assets/js/chosen.jquery.min.js", "/qcloud-admin/assets/js/jquery-ui.custom.min.js","/qcloud-admin/assets/js/date-time/moment.min.js", null];

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
	            	//$(this).find("input[name^='screeningsId']").attr("name", $(this).find("input[name^='screeningsId']").attr("name").replace(/\[.*?\]/g, "[" + i + "]"));//场次
	            	$(this).find("input[name^='merchantIds']").attr("name", $(this).find("input[name^='merchantIds']").attr("name").replace(/\[.*?\]/g, "[" + i + "]"));
	            	$(this).find("input[name^='merchandiseItemsIds']").attr("name", $(this).find("input[name^='merchandiseItemsIds']").attr("name").replace(/\[.*?\]/g, "[" + i + "]"));
	                $(this).find("input[name^='unifiedMerchandiseIds']").attr("name", $(this).find("input[name^='unifiedMerchandiseIds']").attr("name").replace(/\[.*?\]/g, "[" + i + "]"));//统一商品ID
	                //$(this).find("input[name^='purchase']").attr("name", $(this).find("input[name^='purchase']").attr("name").replace(/\[.*?\]/g, "[" + i + "]"));//进货价
	                $(this).find("input[name^='discount']").attr("name", $(this).find("input[name^='discount']").attr("name").replace(/\[.*?\]/g, "[" + i + "]"));//折扣价
	                //$(this).find("input[name^='price']").attr("name", $(this).find("input[name^='price']").attr("name").replace(/\[.*?\]/g, "[" + i + "]"));//原价
	                $(this).find("input[name^='stock']").attr("name", $(this).find("input[name^='stock']").attr("name").replace(/\[.*?\]/g, "[" + i + "]"));//库存
	                $(this).find("input[name^='seckillPrice']").attr("name", $(this).find("input[name^='seckillPrice']").attr("name").replace(/\[.*?\]/g, "[" + i + "]"));//秒杀价格
	                $(this).find("input[name^='orderNums']").attr("name", $(this).find("input[name^='orderNums']").attr("name").replace(/\[.*?\]/g, "[" + i + "]"));//排序
	            	$(this).find("input[name^='orderNums']").val(i);
	            });
	        }
	
	        delProduct();
	        $(".add-row-trigger").on("click", function () {
	            BootstrapDialog.show({
	                title: "商品列表",
	                message: $('<div></div>').load('/admin/merchandiseItem/list4Select4Admin.do'),
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
	                      	 	$(this).removeClass("add-btn");
				    			$(this).addClass("grey");
	                            var merchandiseItemsIds = obj.attr("data-id");
	                            var name = obj.attr("data-name");
	                            var purchase=obj.attr("data-purchase");
	                            var discount=obj.attr("data-discount");
	                            var price=obj.attr("data-price");
	                            var unifiedMerchandiseIds=obj.attr("data-unifiedMerchandiseId");
	                            var merchantIds=obj.attr("data-merchantId");
	                            var stock = obj.attr("data-stock");
	                            if (name) {
	
	                                var productList = $("#productList");
	                                var count = productList[0].childElementCount;
	                                
	                                //console.log(count);
	                                count >= 0 && $("#productList").append(
	                                        '<tr class="template kv-table-row"> ' +
	                                        //'<input type="hidden" name="screeningsId[]" value="' + screeningsId + '" readonly class="config-key"> ' +
	                                        '<input type="hidden" name="unifiedMerchandiseIds[]" value="' + unifiedMerchandiseIds + '" readonly class="config-key"> ' +
	                                        '<input type="hidden" name="merchantIds[]" value="' + merchantIds + '" readonly class="config-key"> ' +
	                                        '<td><input type="text" name="merchandiseItemsIds[]" value="' + merchandiseItemsIds + '" readonly class="config-key"></td> ' +
	                                        '<td><input type="text" value="' + name + '" readonly class="config-value"></td>' +
	                                        /* '<td><input type="text" name="purchase[]" value="' + purchase + '" readonly class="config-value"></td>' + */
	                                        '<td><input type="text" name="discount[]" value="' + discount + '" readonly class="config-value"></td>' +
	                                        /* '<td><input type="text" name="price[]" value="' + price + '" readonly  class="config-value"></td>' + */
	                                        '<td><input type="text" readonly value="' + stock + '" class="config-value"></td>'+
	                                        '<td><input type="text" name="stock[]" value="' + 0 + '" class="config-value"></td>'+
	                                        '<td><input type="text" name="seckillPrice[]" value="0" class="config-value"></td>' +
	                                        '<td><input type="text" name="orderNums[]" value='+count+' class="config-value"></td>' +
	                                        '<td class="del-column"> <button type="button" class="close del-row-trigger">×</button> </td> ' +
	                                        '</tr>');

	                                delProduct();
	                                reSetIndex();
	                                //dialog.close();
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
	        $("#groupbuys-form").validate({
	            errorElement: 'div',
	            errorClass: 'help-block col-xs-12 col-sm-reset inline',
	            focusInvalid: false,
	            rules: {
	            	seckillPrice: {
	                    required: true,
	                    range: [0, 99999999],
	                    digits: true
	                },
	                orderNums: {
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
	                postForm('groupbuys-form');
	            },
	            invalidHandler: function (form) {
	            }
	        });
	    });
	});
</script>
