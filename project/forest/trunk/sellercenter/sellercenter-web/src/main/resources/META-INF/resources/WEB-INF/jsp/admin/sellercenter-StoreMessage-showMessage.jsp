<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>消息</title>
<style>
    .select-product-dialog {
        top: 60px;
    }

    .select-product-dialog tr {

        word-break: break-all;
    }

    .select-product-dialog .modal-content {
        min-height: 600px;
        min-width: 600px;
    }

    /*.select-dialog .modal-body{*/
    /*padding: 0;*/
    /*}*/
</style>
<form>
<div class="row">
    <div class="col-xs-12">
			<input type="hidden" name="id" value="${message.id}">
			           <div class="space-4"></div>
			<div class="form-group">
			       <label class="col-sm-2 control-label no-padding-right center"  for="title">标&nbsp;&nbsp;题 </label>
			        <div class="col-sm-9 center">
			           <span class="no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100 " readonly  id="title" name="title" placeholder="标题" value="${message.title}"/>
                    </span>
                    </div>
           </div>
    
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right center" for="content">内&nbsp;&nbsp;容 </label>
                     <div class="col-sm-9" style="height:450px;text-align: center;">
                     		<span class="no-padding block input-icon input-icon-right mr10">
						<textarea  style="height:450px;" class="width-100 valid " readonly id="content" name="content" placeholder="内容" value="${message.content}">${message.content}</textarea>
					</span>
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
                   
    });
      });
</script>
