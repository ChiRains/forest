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
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/merchandise/toAdd">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>                           
                        </div>
                    </div>                    
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                                                <th>ID</th>           
                                                <th>商家分类ID</th>           
                                                <th>商城分类ID</th>           
                                                <th>规格分类ID</th>           
                                                <th>商家ID</th>           
                                                <th>名称</th>           
                                                <th>系统编号</th>           
                                                <th>商品编号</th>           
                                                <th>图片,缩略图</th>           
                                                <th>关键字</th>           
                                                <th>重量</th>           
                                                <th>状态</th>           
                                                <th>单位</th>           
                                                <th>详情</th>           
                                                <th>描述</th>           
                                                <th></th>           
                                                <th></th>           
                                                <th></th>           
                                                <th></th>           
                                                <th></th>           
                                                <th></th>           
                                                <th></th>           
                                                <th></th>           
                                                <th></th>           
                                                <th></th>           
                                                <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                                        <td>${id}</td>                         
                                                        <td>${merchantClassifyId}</td>                         
                                                        <td>${mallClassifyId}</td>                         
                                                        <td>${specClassifyId}</td>                         
                                                        <td>${merchantId}</td>                         
                                                        <td>${name}</td>                         
                                                        <td>${sysCode}</td>                         
                                                        <td>${code}</td>                         
                                                        <td>${image}</td>                         
                                                        <td>${keywords}</td>                         
                                                        <td>${weight}</td>                         
                                                        <td>${state}</td>                         
                                                        <td>${unit}</td>                         
                                                        <td>${details}</td>                         
                                                        <td>${desc}</td>                         
                                                        <td>${isCertified}</td>                         
                                                        <td>${isSpecialService}</td>                         
                                                        <td>${isNoReason}</td>                         
                                                        <td>${isExternalUrl}</td>                         
                                                        <td>${certified}</td>                         
                                                        <td>${specialService}</td>                         
                                                        <td>${noReason}</td>                         
                                                        <td>${externalUrl}</td>                         
                                                        <td>${isIncludePost}</td>                         
                                                        <td>${brandId}</td>                         
                                                        <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="修改基本信息" class="green" 
                                       href="#admin/merchandise/toEdit?id=${item.id}&queryStr=${queryStr}">
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
         
    });
</script>
