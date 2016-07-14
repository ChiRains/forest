<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp"%>

<title>资讯管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
	<h1>资讯管理<small> <i
			class="ace-icon fa fa-angle-double-right"></i> 列表
	</small>
</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">

		<div class="table-header">资讯列表</div> <!-- <div class="table-responsive"> -->

		<!-- <div class="dataTables_borderWrap"> -->
		<div>
			<div class="dataTables_wrapper form-inline no-footer">
				<div class="row">
					<div class="col-xs-6">
						<div class="dataTables_length"><a title="新增"
							class="btn btn-sm btn-info" href="#admin/article/toAdd"> <i
								class="ace-icon fa fa-plus align-bottom bigger-125"></i>
								新&nbsp;增
						</a></div>
				</div>
				          <div class="col-xs-6" style="text-align: right;">
            <label>
              <form action="#admin/article/list" onsubmit="listFormSearch(this); return false">
                <div class="dataTables_length">
  				<select class="form-control" id="classifyId" name="classifyId" >
				<option value="0" >请选择资讯类别</option>
				<c:forEach items="${classifyList}" var="item" varStatus="current">
                             <option value="${item.id}" <c:if test="${item.id eq query.classifyId}">selected</c:if>>
						${item.name}</option>
                         </c:forEach>
                 </select>	
                  <input type="search" maxlength="11" class="form-control search-query" name="keyWord" value="${query.keyWord}" placeholder="主题/活动内容">
                  <button id="search-button" type="submit" class="btn btn-purple btn-sm">
                    <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>搜索</button>
                </div>
              </form>
            </label>
          </div>
			</div>
				<table
				class="table table-striped table-bordered table-hover dataTable no-footer">
					<thead>
						<tr role="row">
							<th>标题</th>
							<th>图片</th>
							<th>简介</th>
							<th>类别</th>
							<th>是否发布</th>
							<th class="sorting_disabled">操作</th>
					</tr>
				</thead>

					<tbody>
						<c:forEach items="${result}" var="item" varStatus="current">
							<tr>
								<td>${item.title}</td>
								<td><img style="max-height: 150px; max-width: 150px;"
									src="${item.image}" />
									</td>
								<td><label class="showbrief" content="${item.brief}">
										<c:choose>
											<c:when test="${fn:length(item.brief)>20}">${fn:substring(item.brief,0,20)} .....</c:when>
											<c:otherwise>${item.brief}</c:otherwise>
										</c:choose>
								</label></td>
								<td>${item.classifyName}</td>
								<td><c:if test="${item.enable eq 1}">是</c:if> <c:if
										test="${item.enable eq 0}">否</c:if></td>
								<td>
									<div class="hidden-sm hidden-xs action-buttons">
									<c:if test="${item.isOffshelves eq 1}">
									<button class="btn btn-warning enable" api-path="/admin/article/enable.do?id=${item.id}">发布</button>
									<button class="btn btn-success edit" api-path="#admin/article/toEdit?id=${item.id}&queryStr=${queryStr}">修改</button>
									<button class="btn btn-danger delete" api-path="/admin/article/delete.do?id=${item.id}">删除</button>
			                    </c:if>
				                    <c:if test="${item.isOffshelves eq 0}">
				                     <button class="btn offshelves" api-path="/admin/article/offshelves.do?id=${item.id}">下架</button>
				                     <button class="btn btn-success edit" api-path="#admin/article/toEdit?id=${item.id}&queryStr=${queryStr}">修改</button>
				                    </c:if>
										</div>
							</td>
							</tr>
						</c:forEach>
				</tbody>
			</table>
				<div class="row">
					<div class="col-xs-12"><%@include file="../page.inc.jsp"%>
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
        //删除
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
				//下架
			    $('.offshelves').on('click',
			    function() {
			        var delUrl = $(this).attr('api-path');
			        BootstrapDialog.show({
			            title: '确认下架？',
			            message: '确认下架该咨讯？！',
			            buttons: [{
			                id: 'btn-1',
			                label: '确定',
			                cssClass: 'btn btn-primary',
			                action: function(dialogItself) {
			                    $.get(delUrl, {},
			                    function(data) {
			                        data = JSON.parse(data);
			                        if (parseInt(data["status"]) === 0) {
			                            dialogItself.setTitle('下架失败');
			                            dialogItself.setMessage(data["message"]);
			                            dialogItself.setType(BootstrapDialog.TYPE_DANGER);
			                            dialogItself.getButton('btn-1').remove();
			                        } else {
			                            dialogItself.setTitle('成功');
			                            dialogItself.setMessage("下架成功！");
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
			    //发布
			    $('.enable').on('click',
			    function() {
			        var delUrl = $(this).attr('api-path');
			        BootstrapDialog.show({
			            title: '确认发布？',
			            message: '发布后不能撤回！',
			            buttons: [{
			                id: 'btn-1',
			                label: '确定',
			                cssClass: 'btn btn-primary',
			                action: function(dialogItself) {
			                    $.get(delUrl, {},
			                    function(data) {
			                        data = JSON.parse(data);
			                        if (parseInt(data["status"]) === 0) {
			                            dialogItself.setTitle('发布活动失败');
			                            dialogItself.setMessage(data["message"]);
			                            dialogItself.setType(BootstrapDialog.TYPE_DANGER);
			                            dialogItself.getButton('btn-1').remove();
			                        } else {
			                            dialogItself.setTitle('成功');
			                            dialogItself.setMessage("发布活动成功！");
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
        //展示简介
	    $('.showbrief').on('click',
			    function() {
			        var content = $(this).attr('content');
			        BootstrapDialog.show({
			            title: '簡介',
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
	    //修改
	    $('.edit').on('click',
	    function() {
	        var editUrl = $(this).attr('api-path');
	        window.location.href=editUrl;
	    });
    });
</script>
