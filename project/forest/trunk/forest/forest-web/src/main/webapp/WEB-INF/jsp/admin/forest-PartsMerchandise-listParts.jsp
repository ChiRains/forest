<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>

<title>按部位药品管理</title>
<style>
    .dd {
        /*max-width: 100%;*/
        min-width: 300px;
    }
</style>
<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        按部位药品管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
         <small>
     <span style="color:red">[备注：当有部位药品操作上下架时，请点击此按钮
      <a class="btn btn-info flushState" api-path="/admin/partsMerchandise/flushState.do"><i class="ace-icon fa fa-check bigger-110"></i>&nbsp;更新商品状态&nbsp;
        </a> 同步更新.]</span>
       </small>
   
    </h1>
</div>
<!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

		<!-- 
        <div class="row">
            <div class="col-xs-6">
            
                <div class="dataTables_length">
                    <a title="新增" class="btn btn-sm btn-info"
                       href="#admin/partsMerchandise/toAddParts?type=${classType}">
                        <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                        新&nbsp;增
                    </a>
                </div>
            </div>
        </div>
         -->
        
        <div class="row">
        	<c:forEach items="${qclassify}" var="levelOne" varStatus="current">
            	<div class="col-sm-5">
                <div class="dd" id="nestable">
                    <ol class="dd-list">
                            <li class="dd-item" dd-collapsed data-id="${levelOne.id}">
                                <div class="dd-handle">
                                	<c:if test="${fn:length(levelOne.childrenList) > 0}">
	                                	<a class="red pplist" style="cursor:pointer;" data-index="${levelOne.id}">
	                                		<i class="normal-icon ace-icon fa fa-bars blue bigger-130"></i>
	                                	</a>
                                    </c:if>
                                    <span class="">&nbsp;${levelOne.name}</span>
                                     <div class="pull-right action-buttons">
                                        <a class="btn btn-white btn-minier btn-info" title="设置" href="#admin/partsMerchandise/toAddParts?classifyId=${levelOne.id}">
                                            +部位</a>
                                        <a class="blue" href="#admin/partsMerchandise/toEditParts?classifyId=${levelOne.id}"><i class="ace-icon fa fa-pencil bigger-130"></i></a>
                        				<span class="lbl middle"></span>
                        				<!-- 
                        				<a class="red" href="#">
											<i class="ace-icon fa fa-trash-o bigger-130"></i>
										</a>
										 -->
                                    </div>
                                </div>
                                    <ol class="dd-list ddlist${levelOne.id}">
                                        <c:forEach items="${levelOne.childrenList}" var="levelTwo">
                                            <li class="dd-item item-blue2" data-id="${levelTwo.id}">
                                                <div class="dd-handle">
                                                	<c:if test="${fn:length(levelTwo.childrenList) > 0}">
	                                                	<a class="red pplist" style="cursor:pointer;" data-index="${levelTwo.id}">
					                                		<i class="ace-icon fa fa-angle-down bigger-130"></i>
					                                	</a>
                                                    </c:if>
                                                    <span class="">${levelTwo.name}</span>
                                                    <div class="pull-right action-buttons">
                                                        <a class="btn btn-white btn-minier btn-success" title="设置" href="#admin/partsMerchandise/toAddParts?classifyId=${levelTwo.id}">
                                                            +位置</a>
                                                        <a class="blue" href="#admin/partsMerchandise/toEditParts?classifyId=${levelTwo.id}"><i class="ace-icon fa fa-pencil bigger-130"></i></a>
		                                				<span class="lbl middle"></span>
		                                				<!-- 
		                                				<a class="red" href="#">
															<i class="ace-icon fa fa-trash-o bigger-130"></i>
														</a>
														 -->
                                                    </div>
                                                </div>
                                                    <ol class="dd-list ddlist${levelTwo.id}">
                                                        <c:forEach items="${levelTwo.childrenList}" var="levelThree">
                                                            <li class="dd-item item-orange" data-id="${levelThree.id}">
                                                                <div class="dd-handle">
                                                                <c:if test="${fn:length(levelThree.childrenList) > 0}">
	                                                               	<a class="red pplist" style="cursor:pointer;" data-index="${levelThree.id}">
								                                		<i class="ace-icon fa fa-angle-down bigger-130"></i>
								                                	</a>
	                                                        	</c:if>
                                                                   <span class="">${levelThree.name}</span>
                                                                    <div class="pull-right action-buttons">
			                                                        <a class="btn btn-white btn-minier btn-danger" title="设置" href="#admin/partsMerchandise/toAddParts?classifyId=${levelThree.id}">
			                                                            +症状</a>
			                                                        <a class="blue" href="#admin/partsMerchandise/toEditParts?classifyId=${levelThree.id}"><i class="ace-icon fa fa-pencil bigger-130"></i></a>
					                                				<span class="lbl middle"></span>
					                                				<!-- 
					                                				<a class="red" href="#">
																		<i class="ace-icon fa fa-trash-o bigger-130"></i>
																	</a>
																	 -->
			                                                    </div>
                                                                </div>
                                                            </li>
                                                             <ol class="dd-list ddlist${levelThree.id}">
	                                                        <c:forEach items="${levelThree.childrenList}" var="levelFour">
		                                                            <li class="dd-item item-orange" data-id="${levelFour.id}">
		                                                                <div class="dd-handle">
		                                                                   <span class="">${levelFour.name}</span>
		                                                                   <div class="pull-right action-buttons">
		                                                                  	<a class="label label-lg label-pink arrowed-right" title="设置" href="#admin/partsMerchandise/toEditMerchandise?classifyId=${levelFour.id}">
			                                                            	+药品</a>
			                                                            	
					                                                        <a class="blue" href="#admin/partsMerchandise/toEditParts?classifyId=${levelFour.id}"><i class="ace-icon fa fa-pencil bigger-130"></i></a>
							                                				<span class="lbl middle"></span>
							                                				<!-- 
							                                				<a class="red" href="#">
																				<i class="ace-icon fa fa-trash-o bigger-130"></i>
																			</a>
																			 -->
		                                                                </div>
		                                                            </li>
		                                                        </c:forEach>
		                                                    </ol>
                                                        </c:forEach>
                                                    </ol>
                                            </li>
                                        </c:forEach>
                                    </ol>
                            </li>
                    </ol>
                </div>
            </div>
			</c:forEach>
       </div>

    </div>
</div>

<!-- page specific plugin scripts -->
<script type="text/javascript">
    var scripts = [null, null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
        $('.del-item-new').on('click', function () {
            var delUrl = $(this).attr('api-path');
            BootstrapDialog.show({
                title: '确认删除？',
                message: '删除后将无法恢复！',
                buttons: [{
                    label: '确定',
                    cssClass: 'btn btn-primary',
                    action: function (dialogItself) {
                        $.get(delUrl, {},
                                function (data) {
                                    data = JSON.parse(data);
                                    if (parseInt(data["status"]) === 0) {
                                        dialogItself.setTitle('删除失败');
                                        dialogItself.setMessage(data["message"]);
                                        dialogItself.setType(BootstrapDialog.TYPE_WARNING);
                                    } else {
                                        dialogItself.setTitle('成功');
                                        dialogItself.setMessage("删除成功！");
                                        dialogItself.setType(BootstrapDialog.TYPE_SUCCESS);
                                        setTimeout(function () {
                                            dialogItself.close();
                                        }, 1000);
                                        setTimeout(function () {
                                            location.reload();
                                        }, 1500);
                                    }
                                });
                    }
                }, {
                    label: '取消',
                    action: function (dialogItself) {
                        dialogItself.close();
                    }
                }]
            });
        });
        $(".ajax_switch").on('change',function(){
            var el = $(this);
            var data = {
                id:el.attr('data-id'),
                value:el[0].checked?'1':'0'
            };
            var url=el[0].checked?"/admin/classify4Type/enable.do":"/admin/classify4Type/disable.do";
            $.ajax({
                url:url,
                type:'POST',
                data:data,
                dataType: 'json',
                cache: false,
                async: false,
                error: function(){
                    BootstrapDialog.alert({
                        title: '错误',
                        message:'网络错误，请稍后再尝试！',
                        type: BootstrapDialog.TYPE_DANGER,
                        callback: function(){setTimeout(function(){el[0].checked = !el[0].checked;},500)}
                    });
                },
                success:function(rd){
                    if(rd['status'] != 200){
                        BootstrapDialog.alert({
                            title: '错误',
                            message:rd.message,
                            type: BootstrapDialog.TYPE_DANGER,
                            callback: function(){setTimeout(function(){el[0].checked = !el[0].checked;},500)}
                        });
                    }else{
                    	 setTimeout(function () {
                            location.reload();
                        }, 1500);
                    }
                }
            })
        })  
         $(".pplist").click(function(){
        	var index=$(this).attr("data-index");
        	$(".ddlist"+index).toggle();
        });
        
        $('.flushState').on('click', function () {
            var auditURL = $(this).attr('api-path');
            BootstrapDialog.show({
                title: '确认更新',
                message: '商品状态确认更新?',
                buttons: [{
                   	label: '确定',
                    cssClass: 'btn btn-primary',
                    action: function (dialogItself) {
                        $.get(auditURL, {},
                                function (data) {
                                    data = JSON.parse(data);
                                    if (parseInt(data["status"]) === 0) {
                                        dialogItself.setTitle('操作失败');
                                        dialogItself.setMessage(data["message"]);
                                        dialogItself.setType(BootstrapDialog.TYPE_DANGER);
                                    } else {
                                        dialogItself.setTitle('操作成功');
                                        dialogItself.setMessage("更新成功！");
                                        dialogItself.setType(BootstrapDialog.TYPE_SUCCESS);
                                        setTimeout(function () {
                                            dialogItself.close();
                                        }, 1000);
                                        setTimeout(function () {
                                            location.reload(true);
                                        }, 1500);
                                    }
                                });
                    }
                }, {
                    label: '取消',
                    action: function (dialogItself) {
                        dialogItself.close();
                    }
                }]
            });
        });
        
    });
</script>