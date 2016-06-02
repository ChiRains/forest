<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>

<title>编辑佣金分配详情</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/colorbox.css"/>
<link rel="stylesheet" href="/qcloud-admin/assets/css/chosen.css"/>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        佣金分配详情管理
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
	                <td colspan="2"><strong>佣金分配详情</strong></td>
	            </tr>
            </thead>

            <tbody>
                <tr>            
                    <td>佣金拥有者： </td><td>${distributionBrokerage.ownerName}</td> 
                </tr>
                <tr>                        
                    <td>级别 ： </td><td>${distributionBrokerage.name}</td>            
	            </tr>
                <tr>                        
                    <td>计算结果级别路线 ： </td><td>${distributionBrokerage.routes}</td>            
	            </tr>
                <tr>                        
                    <td>分配比例 ： </td><td>${distributionBrokerage.proportion}</td>            
	            </tr>
	            <tr>                        
                    <td>手续费率 ： </td><td> ${distributionBrokerage.poundageRate}</td>            
	            </tr>
	            <tr>                        
                    <td>数据类别 ： </td><td>${distributionBrokerage.typeStr}</td>            
	            </tr>
	            <tr>                        
                    <td>名称： </td><td>${distributionBrokerage.name}</td>            
	            </tr>
	            <tr>                        
                    <td style="vertical-align: top;">图片： </td><td><img src="${distributionBrokerage.image}" width="300" height="200" /></td>            
	            </tr>
	            <tr>                        
                    <td>佣金： </td><td>${distributionBrokerage.brokerage}</td>            
	            </tr>
	            <tr>                        
                    <td>手续费： </td><td>${distributionBrokerage.poundage}</td>            
	            </tr>
	            <tr>                        
                    <td>状态： </td><td>${distributionBrokerage.stateStr}</td>            
	            </tr>
	            <tr>                        
                    <td>审核时间 ： </td><td><fmt:formatDate value="${distributionBrokerage.auditTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>            
	            </tr>
	            <tr>                        
                    <td>购买时间 ： </td><td><fmt:formatDate value="${distributionBrokerage.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>            
	            </tr>
	             <tr>                        
                    <td>计算结果状态： </td><td>${result.resultStateStr}</td>            
	            </tr>
	            <tr>                        
                    <td>购买用户： </td><td>${result.userId}</td>            
	            </tr>
	            <tr>                        
                    <td>计算时间 ： </td><td><fmt:formatDate value="${result.calculateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>            
	            </tr>
	            <tr>                        
                    <td>进货价： </td><td>${dataPool.purchase}</td>            
	            </tr>
	            <tr>                        
                    <td>折扣价： </td><td>${dataPool.discount}</td>            
	            </tr>
	            <tr>                        
                    <td>数量： </td><td>${dataPool.number}</td>            
	            </tr>
	            <tr>                        
                    <td>价格： </td><td>${dataPool.cash}</td>            
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
