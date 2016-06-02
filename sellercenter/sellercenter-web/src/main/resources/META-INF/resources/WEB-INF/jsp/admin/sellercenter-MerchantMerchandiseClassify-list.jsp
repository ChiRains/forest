<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            <div class="col-sm-12">
                <div class="dd" id="nestable">
                    <ol class="dd-list">
                        <c:forEach items="${result}" var="levelOne" varStatus="current">
                            <li class="dd-item" data-id="${levelOne.id}">
                                <div class="dd-handle">
                                    <span class="">${levelOne.path}</span>
                                    <c:if test="${levelOne.type > 0}">
                                            <span class="btn btn-minier btn-light">
                                                &nbsp; ${levelOne.name}
                                            </span>
                                    </c:if>
                                    <div class="pull-right action-buttons">
											<input value="${levelOne.id}" ${levelOne.message} name="levelOne" type="checkbox" data-id="${levelOne.id}" class="ace ace-switch ace-switch-5 ajax_switch">
    										<span class="lbl middle"></span>
                                        <c:set value="${levelOne.children.size() > 0}" var="levelOneChildren"/>
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
                                                            ${levelTwo.name}
                                                    </span>
                                                    </c:if>
                                                    <div class="pull-right action-buttons">
                                                    	<input value="${levelTwo.id}" ${levelTwo.message}  name="levelTwo" type="checkbox" data-parentId="${levelOne.id}" data-id="${levelTwo.id}" class="ace ace-switch ace-switch-5 ajax_switch">
                    										<span class="lbl middle"></span>
                                                        <c:set value="${levelTwo.children.size() > 0}" var="levelTwoChildren"/>
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
                                                                    ${levelThree.name}
                                                            </span>
                                                                    </c:if>
                                                                    <div class="pull-right action-buttons">
                                                                       	<input value="${levelThree.id}" ${levelThree.message} name="levelThree" type="checkbox" data-parentId="${levelTwo.id}" data-id="${levelThree.id}" class="ace ace-switch ace-switch-5 ajax_switch">
                                										<span class="lbl middle"></span>
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
                    <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info sumbitBtn" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>&nbsp;保&nbsp;存&nbsp;</button>
                    &nbsp; &nbsp; &nbsp;
                    <button id="back" class="btn" type="button"><i class="ace-icon fa fa-undo bigger-110"></i>&nbsp;返&nbsp;回&nbsp;</button>
                </div>
            </div>
                </div>
            </div>
        </div>

    </div>
</div>
<div class="formDiv" style="display:none;">
 <form id="model-form" class="form-horizontal" role="form" action="/admin/merchantMerchandiseClassify/edit.do">
	<input value="${merchantId}" name="merchantId"/>
</form>
</div>
<!-- page specific plugin scripts -->
<script type="text/javascript">
   var scripts = [null, "/qcloud-admin/assets/js/jquery.colorbox-min.js","/qcloud-admin/assets/js/upload-img.js","/qcloud-admin/assets/js/chosen.jquery.min.js", null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
        
        //一级点击
        $("input[name='levelOne']").click(function(){
        	var levelOneId=$(this).attr("data-id");
        	if($(this).is(":checked")){
        		//全选2级
        		$("input[data-parentId="+levelOneId+"]").each(function(){
	        		$(this).prop("checked",true);
	        		//全选3级
	        		var levelTwoId=$(this).attr("data-id");
	        		$("input[data-parentId="+levelTwoId+"]").each(function(){
		        		$(this).prop("checked",true);
        			});
        		});
        	}else{
	        	$("input[data-parentId="+levelOneId+"]").each(function(){
	        		$(this).prop("checked",false);
	        		//全选3级
	        		var levelTwoId=$(this).attr("data-id");
	        		$("input[data-parentId="+levelTwoId+"]").each(function(){
		        		$(this).prop("checked",false);
        			});
        		});
        	}
        });
        
        //二级点击
         $("input[name='levelTwo']").click(function(){
        	var levelTwoId=$(this).attr("data-id");
        	var parentId=$(this).attr("data-parentId");
        	if($(this).is(":checked")){
        		$("input[data-parentId="+levelTwoId+"]").each(function(){
	        		$(this).prop("checked",true);
        		});
        	}else{
	        	$("input[data-parentId="+levelTwoId+"]").each(function(){
	        		$(this).prop("checked",false);
        		});
        	}
        	
        	var cLength=$("input[data-parentId="+parentId+"]").length;
        	var cNum=0;
        	$("input[data-parentId="+parentId+"]").each(function(){
        	 	if($(this).is(":checked")){
        	 		cNum++;
        	 	}
        	});
        	if(cNum==cLength){
        		$("input[name='levelOne']").each(function(){
        	 		if($(this).attr("data-id")==parentId){
        	 			$(this).prop("checked",true);
        	 		}
        		});
        	}else{
        		$("input[name='levelOne']").each(function(){
        	 		if($(this).attr("data-id")==parentId){
        	 			$(this).prop("checked",false);
        	 		}
        		});
        	}
        });
        
        //三级点击
         $("input[name='levelThree']").click(function(){
        	var levelThreeId=$(this).attr("data-id");
        	var parentId=$(this).attr("data-parentId");

        	var cLength=$("input[data-parentId="+parentId+"]").length;
        	var cNum=0;
        	$("input[data-parentId="+parentId+"]").each(function(){
        	 	if($(this).is(":checked")){
        	 		cNum++;
        	 	}
        	});
        	if(cNum==cLength){
        		$("input[name='levelTwo']").each(function(){
        	 		if($(this).attr("data-id")==parentId){
        	 			$(this).prop("checked",true);
        	 			var tLength=$("input[data-parentId="+$(this).attr("data-parentId")+"]").length;
	        			var tNum=0;
	        			$("input[data-parentId="+$(this).attr("data-parentId")+"]").each(function(){
			        	 	if($(this).is(":checked")){
			        	 		tNum++;
			        	 	}
			        	});
	        	 		if(tNum==tLength){
	        	 			$("input[data-id="+$(this).attr("data-parentId")+"]").prop("checked",true);
	        	 		}else{
	        	 			$("input[data-id="+$(this).attr("data-parentId")+"]").prop("checked",false);
	        	 		}
        	 		}
        		});
        	}else{
        		$("input[name='levelTwo']").each(function(){
        	 		if($(this).attr("data-id")==parentId){
        	 			$(this).prop("checked",false);
        	 			var tLength=$("input[data-parentId="+$(this).attr("data-parentId")+"]").length;
	        			var tNum=0;
	        			$("input[data-parentId="+$(this).attr("data-parentId")+"]").each(function(){
			        	 	if($(this).is(":checked")){
			        	 		tNum++;
			        	 	}
			        	});
	        	 		if(tNum==tLength){
	        	 			$("input[data-id="+$(this).attr("data-parentId")+"]").prop("checked",true);
	        	 		}else{
	        	 			$("input[data-id="+$(this).attr("data-parentId")+"]").prop("checked",false);
	        	 		}
        	 		}
        		});
        	}
        });
        
        $(".sumbitBtn").on("click",function(){
        	var levelOneId=new Array();
        	/*$("input[name='levelOne']").each(function(){
        	 	if($(this).is(":checked")){
        	 		levelOneId.push($(this).attr("data-id"));
        	 	}
        	});
        	$("input[name='levelTwo']").each(function(){
        	 	if($(this).is(":checked")){
        	 		levelOneId.push($(this).attr("data-id"));
        	 	}
        	});*/
        	$("input[name='levelThree']").each(function(){
        	 	if($(this).is(":checked")){
        	 		levelOneId.push($(this).attr("data-id"));
        	 	}
        	});
        	for(var i=0;i<levelOneId.length;i++){
        		var str="<input name='longList["+i+"]' value='"+levelOneId[i]+"' >";
        		$("#model-form").append(str);
        	}
        	postForm('model-form');
        });
    });
</script>
