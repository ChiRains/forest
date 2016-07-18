<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>管理员管理</title>

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

        <div class="table-header">
            管理员列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="保存" class="btn btn-sm btn-info" id="saveEdit"
                              >
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                保存修改
                            </a> 
                            <a title="返回" class="btn btn-sm btn-info" id="back"
                              >
                                <i class="ace-icon fa fa-undo align-bottom bigger-125"></i>
                                返回
                            </a>                           
                        </div>
                    </div>                    
                </div>
               <form id="model-form" class="form-horizontal"  role="form" action="/admin/unifiedMerchandise/editList.do">
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
	                        <th>名称</th>           
	                        <th>唯一编码</th>           
	                        <th>规格</th>        
	                        <th>状态</th>
	                        <th>进货价</th>
	                        <th>原价</th>
	                        <th>折扣价</th>
	                        <th>库存</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                            	<td>${item.name}</td>
                                <td>${item.code}</td>
                                <td>${item.specifications}</td>                 
                                <td>  
                                	<c:if test="${item.state==4}"><span class="label label-success arrowed">上线</span></c:if>
	                            	<c:if test="${item.state==5}"><span class="label label-danger arrowed-in">下线</span></c:if>
                                </td>                         
                                <td>
                                	<input style="height: 35px;" type="hidden" name="merchandiseList[${current.index}].id" value="${item.id}" />
                                	<input style="height: 35px;" class="required number" name="merchandiseList[${current.index}].purchase" value="${item.purchase}" />
                                </td>
                                <td><input style="height: 35px;" class="required number" name="merchandiseList[${current.index}].price" value="${item.price}" /> </td>
                                <td><input style="height: 35px;" class="required number" name="merchandiseList[${current.index}].discount" value="${item.discount}" /></td>
                                <td><input style="height: 35px;" class="required number" name="merchandiseList[${current.index}].stock" value="${item.stock}" /></td>                         
	                        </tr>
							</c:forEach>
                    </tbody>
                </table>   
                </form> 
            </div>
        </div>
    </div>
</div>

<!-- page specific plugin scripts -->
<script type="text/javascript">
    var scripts = [null, null];
    ace.load_ajax_scripts(scripts, function () {
        //inline scripts related to this page
         $("#saveEdit").click(function(){
         	$("#model-form").submit();
         });
         
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
                    }else{
                    	/*BootstrapDialog.show({
                    		title:"输入信息不合法",
                    		message:""+error[0].innerHTML
                    	});*/
                    	error.insertAfter(element);
                    }
                    
                },

                submitHandler: function (form) {
                    postForm('model-form');
                },
                invalidHandler: function (form) {
                
                }
            });
    });
</script>
