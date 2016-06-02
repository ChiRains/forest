<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>编辑</title>
<link rel="stylesheet" href="/qcloud-admin/assets/css/datepicker.css" />

<!-- ajax layout which only needs content area -->
<style>
    .edui-container{
        z-index: 9!important;
    }
</style>
<div class="page-header">
    <h1>
        资讯信息管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            编辑
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form id="delivery-form" class="form-horizontal"  role="form" action="/admin/article/edit.do">
            <!-- #section:elements.form -->
			<input type="hidden" name="id" value="${article.id}">
		
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name"> 标题 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="name" name="name" placeholder="标题" value="${article.name}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="icon"> 图标 </label>
                <div class="col-sm-9">
							<input type="hidden"  id="icon" name="icon" value="${article.icon}"/>
						<button type="button" mult="false" sid="icon" vid="pic-pic-view" class="btn btn-sm btn-purple btn-upload-pic"  upfrom="0"><i class="ace-icon fa fa-upload">
					           </i> 上 传</button>
						 <ul sid="pic" id="pic-pic-view" class="ace-thumbnails clearfix">
                           <li pic-id="${article.icon}">
                             <a style="line-height: 150px;text-align: center;width:150px;height: 150px;" href="${article.icon}" data-rel="colorbox" class="cboxElement">
                                <img style="max-height: 150px;max-width: 150px;" alt="" src="/file/get.do?uid=${article.icon}">
                            </a>
                            <div class="tools tools-bottom"><a onclick="imgDel(this);" href="javascript:;" title="删除"><i class="ace-icon fa fa-times red"></i></a></div>
                        </li>
                    </ul>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="keywords"> 关键词 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="keywords" name="keywords" placeholder="关键词" value="${article.keywords}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="label"> 标签 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="label" name="label" placeholder="标签" value="${article.label}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>

          
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="date"> 日期 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<span class="input-daterange input-group date">
                             <input type="text" id="date" name="date" maxlength="100" readonly class="input-sm form-control" value="<fmt:formatDate value="${article.date}" pattern="yyyy-MM-dd"/>" style="width:100px;margin:0px;">
                        </span>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="sort"> 排序号 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="sort" name="sort" placeholder="排序号" value="${article.sort}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                 <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="enable">是否显示</label>
                <div class="col-sm-9">
                    <input id="enable" type="checkbox" class="ace ace-switch ace-switch-5" name="enable" value="1"
                    <c:if test="${article.enable eq 1}">checked</c:if>/>
                    <span class="lbl middle"></span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="classify"> 分类 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<select name="classifyId" class="width-100">
							<c:forEach var="item" items="${classifyList}" varStatus="current">
									<option value="${item.id}"> ${item.name} </option>
							</c:forEach>
						</select>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="viewCount"> 查看次数 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="viewCount" name="viewCount" placeholder="查看次数" value="${article.viewCount}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                  		<div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="likeCount"> 点赞次数 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
						<input type="text" class="width-100" maxlength="20" id="likeCount" name="likeCount" placeholder="点赞次数" value="${article.likeCount}"/>
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="brief"> 简介 </label>
                <div class="col-sm-9">
					<span class="col-sm-5 no-padding block input-icon input-icon-right mr10">
                  <textarea class="width-100 valid" id="brief" name="brief" rows="4" aria-invalid="false">${article.brief}</textarea>					
						<i class="ace-icon fa"></i>
					</span>
                </div>
            </div>
                
            
              <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="detail"> 资讯详情 </label>
                <div class="col-sm-9">
                    <textarea style="height: 300px;" class="col-xs-10 no-float" id="detail" name="detail">${article.detail}</textarea>
                </div>
            </div>
            
            <div class="space-4"></div>
            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>&nbsp;保&nbsp;存&nbsp;</button>
                    &nbsp; &nbsp; &nbsp;
                    <button id="back" class="btn" type="button"><i class="ace-icon fa fa-undo bigger-110"></i>&nbsp;返&nbsp;回&nbsp;</button>
                </div>
            </div>

        </form>
        <!-- PAGE CONTENT ENDS -->
    </div>
    <!-- /.col -->
</div><!-- /.row -->

<script type="text/javascript">
    var scripts = [null,"/qcloud-admin/assets/js/date-time/bootstrap-datepicker.min.js","/qcloud-admin/assets/js/upload-img.js","/qcloud-admin/assets/js/jquery.colorbox-min.js",null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
        jQuery(function ($) {
          
         //初始化富文本编辑器
            window.UMEDITOR_HOME_URL = "/qcloud-admin/umeditor/";
            var serverPath = '/admin/file/';
            var um = UM.getEditor('detail', {
                imageUrl: serverPath + "addUMEditorFile.do",
                imagePath: "/file/",
                lang: /^zh/.test(navigator.language || navigator.browserLanguage || navigator.userLanguage) ? 'zh-cn' : 'en',
                langPath: UMEDITOR_CONFIG.UMEDITOR_HOME_URL + "lang/",
                focus: true
            });
            UM.clearCache('detail');     
                  
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
            
             //日期
                 $('#date').datepicker({
                    format:'yyyy-mm-dd',
                    autoclose:true,
                    startDate: '-1y'
                });
                
                   
  
         $("#delivery-form").validate({
                errorElement: 'div',
                errorClass: 'help-block col-xs-12 col-sm-reset inline',
                focusInvalid: false,
                rules: {
                    "name": {
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
                    postForm('delivery-form');
                },
                invalidHandler: function (form) {
                }
            });
        });
    })
</script>
