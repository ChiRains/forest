<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>

<title>编辑商品</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css"/>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        商品管理
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
        
        <table class="table table-striped table-bordered table-hover dataTable no-footer" style="margin-bottom:20px!important;">
            <thead>
	            <tr role="row"  style="background:#6CC0E6!important;color: #000000;height:40px;font-size:16px;">     
	                <td colspan="2"><strong>商品详情</strong></td>
	            </tr>
            </thead>

            <tbody>
                <tr>            
                    <td>商品名称 ： </td><td>${merchandise.name}</td> 
                </tr>
                <tr>                        
                    <td>商家名称 ： </td><td>${merchandise.merchantName}</td>            
	            </tr>
                <tr>                        
                    <td>系统编号 ： </td><td>${merchandise.sysCode}</td>            
	            </tr>
                <tr>                        
                    <td>商品编号 ： </td><td>${merchandise.code}</td>            
	            </tr>
	            <tr>                        
                    <td>规格 ： </td><td>${merchandise.specClassifyStr} </td>            
	            </tr>
	            <tr>                        
                    <td>商城分类 ： </td><td>${merchandise.mallClassifyStr} </td>            
	            </tr>
	            <tr>                        
                    <td>重量 ： </td><td>${merchandise.weight}</td>            
	            </tr>
	            <tr>                        
                    <td>单位 ： </td><td>${merchandise.unit}</td>            
	            </tr>
	            <tr>                        
                    <td>关键字 ： </td><td>${merchandise.keywords}</td>            
	            </tr>
	            <tr>                        
                    <td style="vertical-align: top;">图片 ： </td><td><img src="${merchandise.image}" width="400" height="300"/></td>            
	            </tr>
	            <tr>                        
                    <td style="vertical-align: top;">描述 ： </td><td>${merchandise.desc}</td>            
	            </tr>
	            <tr>                        
                    <td style="vertical-align: top;">详情 ： </td><td>${merchandise.details}</td>            
	            </tr>
            </tbody>
        </table>  
        

        </form>
        <!-- PAGE CONTENT ENDS -->
    </div>
    <!-- /.col -->
</div>
<!-- /.row -->

<script type="text/javascript">
    var scripts = [null, "/qcloud-admin/assets/js/jquery.colorbox-min.js", "/qcloud-admin/assets/js/bootstrap-tag.min.js", "/qcloud-admin/assets/js/upload-img.js", "/qcloud-admin/assets/js/chosen.jquery.min.js", null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
        jQuery(function ($) {
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
