<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../taglib.inc.jsp"%>

<title>广告管理</title>

<!-- ajax layout which only needs content area -->
<div class="page-header">
	<h1>广告管理<small> <i
			class="ace-icon fa fa-angle-double-right"></i> 列表
	</small>
</h1>
</div>
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">

		<div class="table-header">广告列表</div> <!-- <div class="table-responsive"> -->

		<!-- <div class="dataTables_borderWrap"> -->
		<div>
			<div class="dataTables_wrapper form-inline no-footer">
				
            </div>
			</div>
				<table
				class="table table-striped table-bordered table-hover dataTable no-footer">
					<thead>
						<tr role="row">
							<th>类型</th>
							<th class="sorting_disabled">操作</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td>启动页管理</td>
							<td>
								<a class="btn btn-white btn-minier btn-info"
									title="设置" href="#admin/slide/list?sence=2">
                                                                                              设置</a>
							</td>
						</tr>
						<tr>
							<td>首页轮播图管理</td>
							<td>
								<a class="btn btn-white btn-minier btn-info"
									title="设置" href="#admin/slide/list?sence=1">
                                                                                              设置</a>
							</td>
						</tr>
				</tbody>
			</table>
			</div>
		</div>
	</div>
</div>
</div>

<!-- page specific plugin scripts -->
<script type="text/javascript">
    var scripts = [null, null];
    ace.load_ajax_scripts(scripts, function () {
    });
</script>
