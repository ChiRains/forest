<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>设置商家职员</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        商家职员管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            给${merchant.name}设置职员
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            职员列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">  
                        <th>姓名</th>
                        <th>是否选择</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>                                                       
                            <td>${item.name}</td>                            
                            <td>
                                <input value="" type="checkbox" data-member="${item.memberId}" data-merchant="${merchant.id}" class="ace ace-switch ace-switch-5 ajax_switch"
                                    ${item.checked}>
                                <span class="lbl middle"></span>
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

        $(".ajax_switch").on('change',function(){
            var el = $(this);
            var data = {
                memberId:el.attr('data-member'),
                merchantId:el.attr('data-merchant'),
                value:el[0].checked?'1':'0'
            };
            $.ajax({
                url:'/admin/merchantMember/setMember.do',
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
                    }
                }
            })
        })      
    });
</script>
