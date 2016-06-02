<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>新增优惠券子项</title>
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
</style>
<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
      优惠券子项管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            编辑优惠券子项
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="model-form" class="form-horizontal"  role="form" action="/admin/couponItems/set.do">
            <c:if test="${couponItems == null}">
            	 <input type="hidden"  id="id" name="id"  value="0"/>
            </c:if>	
            <c:if test="${couponItems != null}">
            	 <input type="hidden"  id="id" name="id"  value="${couponItems.id}"/>
            </c:if>
            <input type="hidden"  id="couponID" name="couponID" value="${couponID}"/>
						
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name"> 优惠券名称 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="name" name="name" placeholder="优惠券名称" value="${couponItems.name}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="price"> 面额 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
							<input type="text" class="width-100" maxlength="20" id="price" name="price" placeholder="面额" value="${couponItems.price}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="limitPrice"> 使用限额 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100"  maxlength="20" id="limitPrice" name="limitPrice" placeholder="使用限额" value="${couponItems.limitPrice}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div> 
            
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="totalNum"> 总数量 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
							<input type="text" class="width-100" maxlength="20" id="totalNum" name="totalNum" placeholder="总数量" value="${couponItems.totalNum}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="sendNum"> 发放数量 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" readonly maxlength="20" id="sendNum" name="sendNum" placeholder="发放数量" value="${couponItems.sendNum}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="usedNum"> 已使用数量 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" readonly maxlength="20" id="usedNum" name="usedNum" placeholder="已使用数量" value="${couponItems.usedNum}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
             
            <%--     		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="usedNum"> 使用范围 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<select id="type" name="type" class="width-100">
							<option <c:if test="${couponItems.type==1}"> selected </c:if> value="1">线上</option>
							<option <c:if test="${couponItems.type==2}"> selected </c:if> value="2">线下</option>
							<option <c:if test="${couponItems.type==3}"> selected </c:if> value="3">线上+线下</option>
						</select>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>  --%>
             
            <div class="space-4"></div>
            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                	<c:if test="${couponItems==null || couponItems.sendNum==0}">
                		<button class="btn btn-info" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>&nbsp;保&nbsp;存&nbsp;</button>
                	</c:if>
                	<c:if test="${couponItems!=null && couponItems.sendNum!=0}">
                		<button class="btn btn-info" disabled type="button"><i class="ace-icon fa fa-check bigger-110"></i>优惠券已在使用,不允许修改</button>
                	</c:if>
                    &nbsp; &nbsp; &nbsp;
                    <button onclick="window.location='#admin/coupon/list'" class="btn" type="button"><i class="ace-icon fa fa-undo bigger-110"></i>&nbsp;返&nbsp;回&nbsp;</button>
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
                   
            //表单验证
            $("#model-form").validate({
                errorElement: 'div',
                errorClass: 'help-block col-xs-12 col-sm-reset inline',
                focusInvalid: false,
                rules: {
                	name :{
                		required: true
                	},
                	couponID:{
                		required: true
                	},	
                    price: {
                        required: true,
                        number :true,
                        range: [0, 99999999]
                    },
                    limitPrice :{
                    	required: true,
                        number :true,
                        range: [0, 99999999]
                    },
                    totalNum: {
                        required: true,
                        range : [0, 99999999],
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
            $("#search-button").on("click", function () {
	                BootstrapDialog.show({
	                    title: "需要审核商品的商家列表",
	                    message: $('<div ></div>').load('/admin/coupon/showList.do?pageNum=1'),
	                    cssClass: "select-product-dialog",
	                    onshow: function (dialog) {
	                        $(document).off("click", ".select-product-dialog a,.search-button");
	                        $(document).on("click", ".select-product-dialog a,.search-button", function (e) {
	                            e.preventDefault();
	                            var obj = $(this);
	                            //console.log(obj);
	                            //查询按钮
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
	                                //var name = obj.attr("data-name");
	                                //var specifications = obj.attr("data-specifications");
	                                //var stock = obj.attr("data-stock");
	                                if (id ) {
	                                	$("#couponID").val(id);
	                                    dialog.close();
	                                    //window.location.href="#admin/merchandise/list4UnAudit?pageNum=1&merchantId="+id+"&merchantName="+name;
	                                	//var sbURL="#admin/merchandise/list4UnAudit?pageNum=1&merchantId="+id+"&merchantName="+name;
	                                	//$("#fm1").attr("action",sbURL);
	                                	//$("#fm1").submit();
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
        });
    })
</script>
