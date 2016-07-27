<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp" %>

<title>反馈信息列表</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
    <h1>
        反馈信息管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            列表
        </small>
    </h1>
</div><!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">

        <div class="table-header">
            反馈信息列表
        </div>

        <!-- <div class="table-responsive"> -->

        <!-- <div class="dataTables_borderWrap"> -->
        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <div class="row">
                    <div class="col-xs-6">
                    <a title="设置反馈类型"
							class="btn btn-sm btn-info" href="#admin/feedback/listClassify">
							<i class="ace-icon fa fa-cog bigger-125"></i> 
								设&nbsp;置&nbsp;反&nbsp;馈&nbsp;类&nbsp;型
						</a>
                        <div class="dataTables_length">
                        </div>
                    </div>
            	<div class="col-xs-6" style="text-align: right;">
            <label>
              <form action="#admin/feedback/list"  onsubmit="listFormSearch(this); return false">
                <div class="dataTables_length">
  				<select class="form-control" id="state" name="state"  >
				<option value="-1" <c:if test="${query.state eq -1}"> selected</c:if>>请选择处理类型</option>
				<option value="0" <c:if test="${query.state eq 0}"> selected</c:if>>未处理</option>
				<option value="1" <c:if test="${query.state eq 1}"> selected</c:if>>已查看</option>
                </select>	
                <input type="search" maxlength="11" class="form-control search-query" name="keyword" value="${query.keyword}" placeholder="反馈内容">
                 <button id="search-button" type="submit" class="btn btn-purple btn-sm">
                    <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>查询</button>
                </div>
              </form>
            </label>
          </div>                    
                </div>
                <table class="table table-striped table-bordered table-hover dataTable no-footer">
                    <thead>
                    <tr role="row">     
                                                <th>用户名</th>           
                                                <th>反馈内容</th>           
                                                <th>反馈类型</th>           
                                                <th>状态</th>           
                                                <th>反馈时间</th>           
                                                <th class="sorting_disabled">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                           <c:forEach items="${result}" var="item" varStatus="current"> 
                            <tr>            
                                                        <td>${item.userName}</td>                         
                                                        <td><label class="showcontent"     content="${item.content}">
													         <c:choose>
													         <c:when test="${fn:length(item.content)>20}">
													         ${fn:substring(item.content,0,20)} .....
													         </c:when>
													         <c:otherwise> ${item.content}</c:otherwise>
													         </c:choose> 
													         </label>  
										                </td>                          
                                                        <td>${item.classifyName}</td>                         
                                                        <td>${item.stateName}</td>                         
                                                        <td>${item.date}</td>                         
                                                        <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <button class="btn btn-success edit" api-path="#admin/feedback/toEdit?id=${item.id}&queryStr=${queryStr}">查看</button>							                                 
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
        //查看
        $('.edit').on('click',
           function() {
               var editUrl = $(this).attr('api-path');
               window.location.href=editUrl;
        }); 
		//查看详细内容
        $('.showcontent').on('click', function () {
            var content=$(this).attr('content');
            var area=$(this).parent().next().html();
            var time=$(this).parent().next().next().html();
            BootstrapDialog.show({
                message:content,
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

    });
</script>
