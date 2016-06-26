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
                               href="#admin/merchandiseItem/toAdd">
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
                                                <th>ID</th>           
                                                <th>商家ID</th>           
                                                <th>商品ID</th>           
                                                <th>商城分类ID</th>           
                                                <th>树编码</th>           
                                                <th>商品分类ID</th>           
                                                <th>树编码</th>           
                                                <th>名称</th>           
                                                <th>关键字</th>           
                                                <th>状态</th>           
                                                <th>进货价</th>           
                                                <th>折扣价</th>           
                                                <th>原价</th>           
                                                <th>库存</th>           
                                                <th>商品规格</th>           
                                                <th>销量</th>           
                                                <th>虚销量</th>           
                                                <th>点击数量</th>           
                                                <th>差评数量</th>           
                                                <th>中评数量</th>           
                                                <th>好评数量</th>           
                                                <th>录入时间</th>           
                                                <th>更新时间</th>           
                                                <th></th>           
                                                <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                                        <td>${id}</td>                         
                                                        <td>${unifiedMerchandiseId}</td>                         
                                                        <td>${merchantId}</td>                         
                                                        <td>${merchandiseId}</td>                         
                                                        <td>${mallClassifyId}</td>                         
                                                        <td>${mallClassifyBsid}</td>                         
                                                        <td>${merchantClassifyId}</td>                         
                                                        <td>${merchantClassifyBsid}</td>                         
                                                        <td>${name}</td>                         
                                                        <td>${keywords}</td>                         
                                                        <td>${state}</td>                         
                                                        <td>${purchase}</td>                         
                                                        <td>${discount}</td>                         
                                                        <td>${price}</td>                         
                                                        <td>${stock}</td>                         
                                                        <td>${merchandiseSpecificationsId}</td>                         
                                                        <td>${salesVolume}</td>                         
                                                        <td>${virtualSalesVolume}</td>                         
                                                        <td>${clickRate}</td>                         
                                                        <td>${lowEvaluation}</td>                         
                                                        <td>${middleEvaluation}</td>                         
                                                        <td>${goodEvaluation}</td>                         
                                                        <td>${recordTime}</td>                         
                                                        <td>${updateTime}</td>                         
                                                        <td>${brandId}</td>                         
                                                        <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a title="修改基本信息" class="green" 
                                       href="#admin/merchandiseItem/toEdit?id=${item.id}&queryStr=${queryStr}">
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
