<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>新增管理员</title>
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
        min-height: 800px;
        min-width: 1000px;
    }

    /*.select-dialog .modal-body{*/
    /*padding: 0;*/
    /*}*/
</style>
<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        管理里管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            新增
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="model-form" class="form-horizontal"  role="form" action="/admin/userDistributionGrade/add.do">
            <!-- #section:elements.form -->
			<input type="hidden" value="${gradeId}" name="gradeId"/>
			<div class="space-4"></div>
            <div class="form-group">
                <div class="col-sm-12">
                    <table class="table table-striped table-bordered table-hover kv-table">
                        <thead>
                        <tr>
                            <th>姓名</th>
                            <th>手机</th>
                         	<th>性别</th>
                          	<th>昵称</th>
                           	<th>邮箱</th>
                            <th class="del-column"></th>
                        </tr>
                        </thead>
                        <tbody id="productList">
                        
                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="8">
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
                    <button class="btn" type="button" onclick="window.location.href='#admin/userDistributionGrade/list?gradeId=${gradeId}'">
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
                	$(this).find("input[name$='userId']").attr("name", $(this).find("input[name$='userId']").attr("name").replace(/\[.*?\]/g, "[" + i + "]"));
                	$(this).find("input[name$='name']").attr("name", $(this).find("input[name$='name']").attr("name").replace(/\[.*?\]/g, "[" + i + "]"));
                });
            }

            delProduct();
            $(".add-row-trigger").on("click", function () {
                BootstrapDialog.show({
                    title: "用户列表",
                    message: $('<div></div>').load('/admin/user/listBySelect.do'),
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
				    			var id = obj.attr("data-id");
                                var name = obj.attr("data-name");
                                var nickname = obj.attr("data-nickname");
                                var mobile = obj.attr("data-mobile");
                                var sexStr=obj.attr("data-sexStr");
                                var email=obj.attr("data-email");
                              
                                if ( id) {

                                    var productList = $("#productList");
                                    var count = productList[0].childElementCount;
                                    
                                    //console.log(count);
                                    count >= 0 && $("#productList").append(
                                            '<tr class="template kv-table-row"> ' +
                                            '<input type="hidden" name="userGradeList[].userId" value="' + id + '" readonly class="config-key"> ' +
                                            '<td><input type="text" name="userGradeList[].name" value="' + name + '"   readonly class="config-key"></td> ' +
                                            '<td><input type="text" value="' + mobile + '" readonly class="config-value"></td>' +
                                            '<td><input type="text" value="' + sexStr + '" readonly class="config-value"></td>' +
                                            '<td><input type="text" value="' + nickname + '" readonly  class="config-value"></td>' +
                                            '<td><input type="text" value="' + email + '" readonly  class="config-value"></td>' +
                                            '</td>' +
                                            '<td class="del-column"> <button type="button" class="close del-row-trigger">×</button> </td> ' +
                                            '</tr>');
                                    delProduct();
                                    reSetIndex();
                                    // dialog.close();
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
                    stock: {
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
                    postForm('model-form');
                },
                invalidHandler: function (form) {
                }
            });
        });
    });
    
</script>