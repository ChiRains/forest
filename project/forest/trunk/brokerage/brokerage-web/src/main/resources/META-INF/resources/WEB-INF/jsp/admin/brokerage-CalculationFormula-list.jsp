<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>分佣公式管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
           分佣公式管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
               分佣计算公式列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/calculationFormula/toAdd">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>                           
                        </div>
                    </div>                    
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    	<tr role="row">     
								<th>公式名称</th>
	                            <th>公式</th>           
	                            <th>交易用户是否参加分佣</th>           
	                            <th>分配比例</th>     
	                            <th>手续费率</th>
	                            <th>佣金比例设置</th>  
	                            <th>是否启用</th>         
	                            <th class="sorting_disabled">操作</th>
                    	</tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>    
                            	<td>${item.name}</td>        
                                <td class="showValue" title='${item.formula}'>
						            <c:if test="${fn:length(item.formula) >80}">
						                 ${fn:substring(item.formula, 0, 80)}...
						            </c:if>
						            <c:if test="${fn:length(item.formula)<=80}">
						                 ${item.formula}
						            </c:if>
					            </td>
                                <td>${item.tradeUserDistributionStr}</td>                         
                                <td>${item.proportion}</td>      
                                <td>${item.poundageRate}</td>
                                <td>
	                                <a href="#admin/allocationScheme/list?formulaId=${item.id}" class="btn btn-primary btn-xs">
	                                <i class="ace-icon fa fa-users bigger-130"></i> 比例设置</a>
                            	</td>             
                                <td>
						            <input value="${item.id}" <c:if test="${item.state==1}"> checked </c:if>  type="checkbox" data-id="${item.id}" class="ace ace-switch ace-switch-5 ajax_switch" >
						            <span class="lbl middle"></span>
						        </td>                 
                                <td>
	                            <div class="hidden-sm hidden-xs action-buttons">
	                                <a title="修改基本信息" class="green" 
	                                   href="#admin/calculationFormula/toEdit?id=${item.id}&queryStr=${queryStr}">
	                                    <i class="ace-icon fa fa-pencil bigger-130"></i>
	                                </a>							                                 
	                            </div>
                       		</td>
                        </tr>
						</c:forEach>
                    </tbody>
                </table>    
                <div class="row">
                    <div class="col-xs-12">                  
                       	<%@include file="../page.inc.jsp" %>
                    </div>
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
         
         $('.showValue').on('click', function () {
	    	var formula=$(this).attr('title');
	        BootstrapDialog.show({
	            title: '佣金公式',
	            message: formula,
	            buttons: [{
	                id: 'btn-1',
	                label: '确定',
	                cssClass: 'btn btn-primary',
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
                value:el[0].checked?'1':'2'
            };
            var url=el[0].checked?"/admin/calculationFormula/enableFormula.do":"/admin/calculationFormula/disableFormula.do";
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
        });
         
    });
</script>
