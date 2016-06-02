<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>管理员管理</title>
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
        管理员管理 
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="row">
            <div class="col-xs-3">
                <div class="dataTables_length">
                    <a title="新增" class="btn btn-sm btn-info"
                       href="#admin/distributionGrade/toAdd">
                        <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                        新&nbsp;增 
                    </a>
                    
                    <a title="设置分销公式" class="btn btn-sm btn-info setting" >
                        <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                     设置分销公式
                    </a>
                </div>
            </div>
            
        </div>
        
        <c:set var="index" value="0" scope="request" /><!-- 自增序号，注意scope-->  
		<c:set var="level" value="0" scope="request" /><!-- 记录树的层次，注意scope-->
        <c:import url="childrenList.jsp"/>

    </div>
</div>

<!-- page specific plugin scripts -->
<script type="text/javascript">
    var scripts = [null, null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
        
         
         $(".setting").on("click", function () {
         	$(document).off('click',".select-departmentClerk-trigger");
            BootstrapDialog.show({
                title: "佣计算公式列表",
                message: $('<div></div>').load('/admin/calculationFormula/listBySelect.do'),
                cssClass: "select-product-dialog",
                onshow: function (dialog) {
                    $(document).off("click", ".select-product-dialog a,.search-button");
                    $(document).on("click", ".select-departmentClerk-trigger", function (e) {
                		var $obj=$(this);
                		var formulaId = $(this).attr("data-id");
	                	$.post("/admin/distributionGrade/editFormula.do",
	                	{isSelect:$(this).get(0).checked, formulaId:formulaId},
	                        function (data) {
	                        	var dataStr=JSON.parse(data);
	                        	if(dataStr.status==-1){
	                        	 	$obj.prop("checked",true);
                        		    BootstrapDialog.show({
                        		  	   message:dataStr.message
                        		    });
	                        	}
	                        }
                        );
	                });
                }
            });
        });
    });
</script>
