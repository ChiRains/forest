<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../taglib.inc.jsp" %>

<title>分类管理</title>
<style>
    .dd {
        /*max-width: 100%;*/
        min-width: 300px;
    }
</style>
<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        分类管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div>
<!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="row">
            <div class="col-xs-6">
                <div class="dataTables_length">
                    <a title="新增" class="btn btn-sm btn-info"
                       href="#admin/classify4Type/toAdd?type=${classType}">
                        <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                        新&nbsp;增
                    </a>
                </div>
            </div>
        </div>
        <!-- PAGE CONTENT BEGINS -->
       <!-- <div class="row">
            <div class="col-sm-12">
                <div class="dd" id="nestable">
                    <ol class="dd-list">
                        <c:forEach items="${result}" var="levelOne" varStatus="current">
                            <li class="dd-item" data-id="${levelOne.id}">
                                <div class="dd-handle">
                                    <span class="">${levelOne.path}</span>
                                    <c:if test="${levelOne.type > 0}">
                                            <span class="btn btn-minier btn-light">
                                                &nbsp; ${pagesCat.get(levelOne.type)}
                                            </span>
                                    </c:if>
                                    <div class="pull-right action-buttons">

                                        <a class="btn btn-white btn-minier btn-info" title="${levelOne.attributeStr}" href="#admin/classifyAttribute/toSetAttributes?classifyId=${levelOne.classifyId}">
                                            属性</a>
                                        <a class="btn btn-white btn-minier btn-info" title="" href="#admin/classifySpecifications/list?classifyId=${levelOne.classifyId}">
                                            规格</a>

                                        <a class="blue" href="#admin/classify4Type/toEdit?id=${levelOne.classifyId}"><i class="ace-icon fa fa-pencil bigger-130"></i></a>
                                        
                                        <c:set value="${levelOne.children.size() > 0}" var="levelOneChildren"/>
                                            <%--<c:if test="${!levelOneChildren}">--%>
                                            <%--<a class="del-item-new red" api-path="/admin/nav/del.do?id=${levelOne.id}" href="javascript:;"><i class="ace-icon fa fa-trash-o bigger-130"></i></a>--%>
                                            <%--</c:if>--%>
                                    </div>
                                </div>
                                <c:if test="${levelOneChildren}">
                                    <ol class="dd-list">
                                        <c:forEach items="${levelOne.children}" var="levelTwo" varStatus="current">
                                            <li class="dd-item item-blue2" data-id="${levelTwo.id}">
                                                <div class="dd-handle">
                                                    <span class="">${levelTwo.path}</span>
                                                    <c:if test="${levelTwo.type > 0}">
                                                    <span class="btn btn-minier btn-light">
                                                            ${pagesCat.get(levelTwo.type)}
                                                    </span>
                                                    </c:if>
                                                    <div class="pull-right action-buttons">
                                                        <a class="btn btn-white btn-minier btn-info" title="${levelTwo.attributeStr}" href="#admin/classifyAttribute/toSetAttributes?classifyId=${levelTwo.classifyId}&levelOne=${levelOne.classifyId}">
                                                            属性</a>
                                                        <a class="btn btn-white btn-minier btn-info" title="" href="#admin/classifySpecifications/list?classifyId=${levelTwo.classifyId}">
                                                            规格</a>
                                                        <a class="blue" href="#admin/classify4Type/toEdit?id=${levelTwo.classifyId}"><i class="ace-icon fa fa-pencil bigger-130"></i></a>
                                                        <c:set value="${levelTwo.children.size() > 0}" var="levelTwoChildren"/>
                                                            <%--<c:if test="${!levelTwoChildren}">--%>
                                                            <%--<a class="del-item-new red" api-path="/admin/nav/del.do?id=${levelTwo.id}" href="javascript:;"><i class="ace-icon fa fa-trash-o bigger-130"></i></a>--%>
                                                            <%--</c:if>--%>
                                                    </div>
                                                </div>
                                                <c:if test="${levelTwoChildren}">
                                                    <ol class="dd-list">
                                                        <c:forEach items="${levelTwo.children}" var="levelThree" varStatus="current">
                                                            <li class="dd-item item-orange" data-id="${levelThree.id}">
                                                                <div class="dd-handle">
                                                                    <span class="">${levelThree.path}</span>
                                                                    <c:if test="${levelThree.type > 0}">
                                                            <span class="btn btn-minier btn-light">
                                                                    ${pagesCat.get(levelThree.type)}
                                                            </span>
                                                                    </c:if>
                                                                    <div class="pull-right action-buttons">
                                                                        <a class="btn btn-white btn-minier btn-info" title="${levelThree.attributeStr}" href="#admin/classifyAttribute/toSetAttributes?classifyId=${levelThree.classifyId}&levelOne=${levelOne.classifyId}&levelTwo=${levelTwo.classifyId}">
                                                                            属性</a>
                                                                        <a class="btn btn-white btn-minier btn-info" title="" href="#admin/classifySpecifications/list?classifyId=${levelThree.classifyId}">
                                                                            规格</a>
                                                                        <a class="blue" href="#admin/classify4Type/toEdit?id=${levelThree.classifyId}"><i class="ace-icon fa fa-pencil bigger-130"></i></a>
                                                                            <%--<a class="del-item-new red" api-path="/admin/nav/del.do?id=${levelThree.id}" href="javascript:;"><i class="ace-icon fa fa-trash-o bigger-130"></i></a>--%>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                        </c:forEach>
                                                    </ol>
                                                </c:if>
                                            </li>
                                        </c:forEach>
                                    </ol>
                                </c:if>
                            </li>
                        </c:forEach>
                    </ol>
                </div>
            </div>
        </div>
        -->
        
        
        <div class="row">
            <div class="col-sm-12">
                <div class="dd" id="nestable">
                    <ol class="dd-list">
                        <c:forEach items="${qclassify}" var="levelOne" varStatus="current">
                            <li class="dd-item" data-id="${levelOne.id}">
                                <div class="dd-handle">
                                	<a class="red pplist " style="cursor:pointer;" data-index="${current.index}">
                                		<i class="ace-icon fa fa-angle-down bigger-110"></i>
                                	</a>
                                    <span class="">${levelOne.name}</span>
                                    <div class="pull-right action-buttons">

                                        <a class="btn btn-white btn-minier btn-info" title="设置" href="#admin/classifyAttribute/toSetAttributes?classifyId=${levelOne.id}">
                                            属性</a>
                                        <a class="btn btn-white btn-minier btn-info" title="设置" href="#admin/classifySpecifications/list?classifyId=${levelOne.id}">
                                            规格</a>

                                        <a class="blue" href="#admin/classify4Type/toEdit?id=${levelOne.id}"><i class="ace-icon fa fa-pencil bigger-130"></i></a>
                                        
                                        <input value="${levelOne.id}" <c:if test="${levelOne.enable==1}"> checked </c:if>  type="checkbox" data-id="${levelOne.id}" class="ace ace-switch ace-switch-6 ajax_switch" >
                                		<span class="lbl middle"></span>
                                        
                                    </div>
                                </div>
                                    <ol class="dd-list ddlist${current.index}">
                                        <c:forEach items="${levelOne.childrenList}" var="levelTwo" varStatus="current">
                                            <li class="dd-item item-blue2" data-id="${levelTwo.id}">
                                                <div class="dd-handle">
                                                    <span class="">${levelOne.name}--${levelTwo.name}</span>
                                                    <div class="pull-right action-buttons">
                                                        <a class="btn btn-white btn-minier btn-info" title="设置" href="#admin/classifyAttribute/toSetAttributes?classifyId=${levelTwo.id}&levelOne=${levelOne.id}">
                                                            属性</a>
                                                        <a class="btn btn-white btn-minier btn-info" title="设置" href="#admin/classifySpecifications/list?classifyId=${levelTwo.id}">
                                                            规格</a>
                                                        <a class="blue" href="#admin/classify4Type/toEdit?id=${levelTwo.id}"><i class="ace-icon fa fa-pencil bigger-130"></i></a>
                                                   		<input value="${levelTwo.id}" 
				                                     	<c:if test="${levelTwo.enable==1}"> checked </c:if> <c:if test="${levelOne.enable!=1}"> disabled="true" </c:if> type="checkbox" data-id="${levelTwo.id}" class="ace ace-switch ace-switch-6 ajax_switch" >
		                                				<span class="lbl middle"></span>
                                                    </div>
                                                </div>
                                                    <ol class="dd-list">
                                                        <c:forEach items="${levelTwo.childrenList}" var="levelThree" varStatus="current">
                                                            <li class="dd-item item-orange" data-id="${levelThree.id}">
                                                                <div class="dd-handle">
                                                                    <span class="">${levelOne.name}--${levelTwo.name}--${levelThree.name}</span>
                                                                    <div class="pull-right action-buttons">
                                                                        <a class="btn btn-white btn-minier btn-info" title="设置" href="#admin/classifyAttribute/toSetAttributes?classifyId=${levelThree.id}&levelOne=${levelOne.id}&levelTwo=${levelTwo.id}">
                                                                            属性</a>
                                                                        <a class="btn btn-white btn-minier btn-info" title="" href="#admin/classifySpecifications/list?classifyId=${levelThree.id }">
                                                                            规格</a>
                                                                        <a class="blue" href="#admin/classify4Type/toEdit?id=${levelThree.id}"><i class="ace-icon fa fa-pencil bigger-130"></i></a>
                                                                   		
                                                                   		<input value="${levelThree.id}" <c:if test="${levelOne.enable!=1 || levelTwo.enable!=1}">  disabled="true" </c:if> <c:if test="${levelThree.enable==1}"> checked </c:if>  type="checkbox" data-id="${levelThree.id}" class="ace ace-switch ace-switch-6 ajax_switch" >
			                                							<span class="lbl middle"></span>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                        </c:forEach>
                                                    </ol>
                                            </li>
                                        </c:forEach>
                                    </ol>
                            </li>
                        </c:forEach>
                    </ol>
                </div>
            </div>
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
    });
</script>