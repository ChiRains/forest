<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>

<title>编辑商家</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css"/>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        商家管理
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
	                <td colspan="2"><strong>商家详情</strong></td>
	            </tr>
            </thead>

            <tbody>
                <tr>            
                    <td>系统编码： </td><td>${merchant.code}</td> 
                </tr>
                <tr>                        
                    <td>商家名称 ： </td><td>${merchant.name}</td>            
	            </tr>
                <tr>                        
                    <td>分类 ： </td><td>${merchant.classifyStr}</td>            
	            </tr>
                <tr>                        
                    <td>主打产品 ： </td><td>${merchant.flagship}</td>            
	            </tr>
	            <tr>                        
                    <td>地址 ： </td><td> ${merchant.province} ${merchant.city} ${merchant.district} ${merchant.address} </td>            
	            </tr>
	            <tr>                        
                    <td>联系人 ： </td><td>${merchant.linkman}</td>            
	            </tr>
	            <tr>                        
                    <td>联系电话 ： </td><td>${merchant.phone}</td>            
	            </tr>
	            <tr>                        
                    <td>登记时间 ： </td><td><fmt:formatDate value="${merchant.registTime}" pattern="yyyy-MM-dd"/></td>            
	            </tr>
	            <tr>                        
                    <td style="vertical-align: top;">LOGO ： </td><td><img src="${merchant.logo}" width="300" height="200"/></td>            
	            </tr>  
	            <tr>                        
                    <td style="vertical-align: top;">图片： </td><td><img src="${merchant.image}" width="300" height="200" /></td>            
	            </tr>
	            <tr>                        
                    <td>积分模式 ： </td><td>${merchant.integralModeId}</td>            
	            </tr>
	            <tr>                        
                    <td>上传商品是否需要审核 ： </td><td>${merchant.commodityAuditingStr}</td>            
	            </tr>
	            <tr>                        
                    <td>卖出商品是否支持分销 ： </td><td>${merchant.distributionStr}</td>            
	            </tr>
	            <tr>                        
                    <td>商家是否禁用： </td><td>${merchant.stateStr}</td>            
	            </tr>
	            <tr>                        
                    <td>消费币： </td><td>${merchant.consumptionCurrency}</td>            
	            </tr>
	            <tr>                        
                    <td>商家类型： </td><td>${merchant.merchantType}</td>            
	            </tr>
	             <tr>                        
                    <td>成交扣点： </td><td>${merchant.buckle}</td>            
	            </tr>
	             <tr>                        
                    <td>是否要短信通知： </td><td>${merchant.notifyStr}</td>            
	            </tr>
	             
	             <tr>                        
                    <td>接收短信的手机号： </td><td>${merchant.receiveMobile}</td>            
	            </tr>
	             <tr>                        
                    <td>有效期： </td><td><fmt:formatDate value="${merchant.validDate}" pattern="yyyy-MM-dd"/></td>          
	            </tr>
	             <tr>                        
                    <td>是否正品： </td><td>${merchant.isCertifiedStr}</td>            
	            </tr>
	            <tr>                        
                    <td>是否特色服务： </td><td>${merchant.isSpecialServiceStr}</td>            
	            </tr>
	            <tr>                        
                    <td>无理由退货： </td><td>${merchant.isNoReasonStr}</td>            
	            </tr>
	            <tr>                        
                    <td>是否有外部链接： </td><td>${merchant.isExternalUrlStr}</td>            
	            </tr>
	            <tr>                        
                    <td>是否包邮： </td><td>${merchant.isIncludePostStr}</td>            
	            </tr>
	            <tr>                        
                    <td style="vertical-align: top;">简介  ： </td><td>${merchant.introduction}</td>            
	            </tr>
	            <tr>                        
                    <td style="vertical-align: top;">详细介绍 ： </td><td>${merchant.detailIntroduction}</td>            
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
