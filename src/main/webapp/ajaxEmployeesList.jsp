<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("basePath", basePath);
%>
<!--引入标签库-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<!-- Bootstrap -->
<link
	href="${basePath}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${basePath}/static/bootstrap-3.3.7-dist/js/jQueryv2.1.4.min.js"></script>	
	
<script src="${basePath}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>

<script src="${basePath}/static/js/ajaxEmployeesList.js" charset="utf-8"></script>
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12 col-lg-12 ">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!--按钮-->
		<div class="row">
			<div class="col-md-4 col-lg-4  col-md-offset-8">
				<button class="btn btn-success" id="new_emp">新增</button>
				<button class="btn btn-danger" id="delete_emp">删除</button>
			</div>
		</div>


	<!-- 新增  模态框（Modal） -->
		<div class="modal fade" id="new_emp_model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">员工添加</h4>
					</div>
					
					<div class="modal-body">
					
					
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label class="col-sm-2 control-label">用户名</label>
								<div class="col-sm-10">
									<input class="form-control"  type="text" name="empName"  id="userName" placeholder="请输入用户名">
									<span  class="help-block"></span>
								</div>
							</div>
							<div class="form-group ">
								<label class="col-sm-2 control-label">性别</label>
								<div class="col-sm-10">
									<div class="radio">
										<label> <input type="radio" name="gender"  value="M" checked>
											我是男的
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name="gender"  value="G"> 
											我是女的
										</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" >邮箱</label>
								<div class="col-sm-10">
									<input type="text" class="form-control"  name="email" id="email"  placeholder="554120749@qq.com">
									<span  class="help-block"></span>
								</div>
							</div>
								<div class="form-group ">
								<label class="col-sm-2 control-label" for="inputSuccess">所在部门</label>
								<div class="col-sm-10">
									<select class="form-control" name="deptId">
									</select>
								</div>
							</div>
						</form>


					</div>
					<span id=hint style="display: none;text-align: left;color: purple; font-size: 25px;" class="col-md-offset-8"></span>
					<div class="modal-footer">
					    
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="submit_add_emp">提交</button>
					</div>
					
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>

		<!--显示表格数据  -->
		<div class="row">
			<div class="col-md-12 col-lg-12 ">
				<table
					class="table table-bordered table-hover table-condensed table-striped">
					<thead>
						<tr>
							<th>ID</th>
							<th>名字</th>
							<th>性别</th>
							<th>邮箱</th>
							<th>部门ID</th>
							<th>部门名称</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>
		</div>
		<!-- 显示分页数据 -->
		<div class="row">


			<!-- 显示分页信息 -->
			<div class="col-md-6 col-lg-6 " id="pageInfo"></div>


			<!-- 显示分页条 -->
			<div class="col-md-6 col-lg-6 ">

				<ul class="pagination" id="pageNav">


				</ul>

			</div>
		</div>

	</div>
</body>
</html>