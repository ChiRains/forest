<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>资讯评论管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        资讯评论管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            评论列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="dataTables_length">
                            <a title="新增" class="btn btn-sm btn-info"
                               href="#admin/articleEvaluation/toAdd">
                                <i class="ace-icon fa fa-plus align-bottom bigger-125"></i>
                                新&nbsp;增
                            </a>                           
                        </div>
                    </div>   
             <div class="col-xs-6" style="text-align: right;">
            <label>
              <form action="#admin/articleEvaluation/list" onsubmit="listFormSearch(this); return false">
                <div class="dataTables_length">
	
                  <input type="search" maxlength="11" class="form-control search-query" name="keyWord" value="${query.keyWord}" placeholder="主题/活动内容">
                  <button id="search-button" type="submit" class="btn btn-purple btn-sm">
                    <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>搜索</button>
                </div>
              </form>
            </label>
          </div>                 
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                                                <th>评论内容</th>           
                                                <th>文章</th>           
                                                <th>用户名</th>           
                                                <th>评论时间</th>           
                                                <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                                        <td>                  
                                                        <label class="showcontent" content="${item.content}">
											                    <c:choose>
											                      <c:when test="${fn:length(item.content)>30}">${fn:substring(item.content,0,30)} .....</c:when>
											                      <c:otherwise>${item.content}</c:otherwise>
											                     </c:choose>
											                  </label>
											             </td>                         
                                                        <td>${item.articleName}</td>                         
                                                        <td>${item.userName}</td>                         
                                                        <td><fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd  HH:mm:ss" /></td>                         
                                                        <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                            		<a title="删除" class="hidden-sm hidden-xs action-buttons red delete" api-path="/admin/articleEvaluation/delete.do?id=${item.id}">删除</a>
						                                 
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
        //删除评论
    	 $('.delete').on('click',
 			    function() {
 			        var delUrl = $(this).attr('api-path');
 			        BootstrapDialog.show({
 			            title: '确认删除信息？',
 			            message: '删除信息将不能恢复！',
 			            buttons: [{
 			                id: 'btn-1',
 			                label: '确定',
 			                cssClass: 'btn btn-primary',
 			                action: function(dialogItself) {
 			                    $.get(delUrl, {},
 			                    function(data) {
 			                        data = JSON.parse(data);
 			                        if (parseInt(data["status"]) === 0) {
 			                            dialogItself.setTitle('删除信息失败');
 			                            dialogItself.setMessage(data["message"]);
 			                            dialogItself.setType(BootstrapDialog.TYPE_DANGER);
 			                            dialogItself.getButton('btn-1').remove();
 			                        } else {
 			                            dialogItself.setTitle('成功');
 			                            dialogItself.setMessage("删除信息成功！");
 			                            dialogItself.setType(BootstrapDialog.TYPE_SUCCESS);
 			                            setTimeout(function() {
 			                                dialogItself.close();
 			                            },
 			                            1000);
 			                            setTimeout(function() {
 			                                location.reload(true);
 			                            },
 			                            1500);
 			                        }
 			                    });
 			                }
 			            },
 			            {
 			                label: '取消',
 			                action: function(dialogItself) {
 			                    dialogItself.close();
 			                }
 			            }]
 			        });
 			    });
		//展示内容
    	 $('.showcontent').on('click',
 			    function() {
 			        var content = $(this).attr('content');
 			        BootstrapDialog.show({
 			            title: '区域',
 			            message: content,
 			            buttons: [{
 			                id: 'btn-1',
 			                label: '确定',
 			                cssClass: 'btn btn-primary',
 			                action: function(dialogItself) {
 			                    dialogItself.close();
 			                }
 			            }]
 			        });
 			    });
 			
    });
</script>
