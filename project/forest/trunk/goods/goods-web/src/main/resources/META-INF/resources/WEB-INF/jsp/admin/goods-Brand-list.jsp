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
        品牌管理
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
                       href="#admin/brand/toAdd?type=${type}">
                        <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                        新&nbsp;增
                    </a>
                </div>
            </div>
        </div>
        <!-- PAGE CONTENT BEGINS -->
        
        
        <div class="row">
            <div class="col-sm-12">
                <div class="dd" id="nestable">
                    <ol class="dd-list">
                        <c:forEach items="${list}" var="levelOne" varStatus="current">
                            <li class="dd-item" data-id="${levelOne.id}">
                                <div class="dd-handle">
                                	<img src="${levelOne.image}" width="80" height="60"/>
                                    <span class="">${levelOne.name}</span>
                                    <div style="" class="pull-right action-buttons">
                                        <a  style="margin-top: 20px" class="blue" href="#admin/brand/toEdit?id=${levelOne.id}"><i class="ace-icon fa fa-pencil bigger-130"></i></a>
                                     <!--   <input value="${levelOne.id}" <c:if test="${levelOne.enable==1}"> checked </c:if>  type="checkbox" data-id="${levelOne.id}" class="ace ace-switch ace-switch-6 ajax_switch" >
                                		<span class="lbl middle"></span>
                                     -->   
                                    </div>
                                </div>
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
        
    });
</script>