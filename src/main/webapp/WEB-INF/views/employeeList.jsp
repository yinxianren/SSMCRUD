<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<link href="${basePath}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${basePath}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="${basePath}/text/javascript" src="static/bootstrap-3.3.7-dist/js/jQueryv2.1.4.min.js"></script>
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
           <div class="col-md-4 col-lg-4  col-md-offset-8" >
                <button class="btn btn-success">新增</button>
                <button class="btn btn-danger">删除</button>
           </div>
        </div>
        
         <!--显示表格数据  --> 
        <div class="row" >
          <div class="col-md-12 col-lg-12 " >
             <table class="table table-bordered table-hover table-condensed table-striped" >
                <tr>
                  <th>ID</th>
                  <th>名字</th>
                  <th>性别</th>
                  <th>邮箱</th>
                  <th>部门ID</th>
                  <th>部门名称</th>
                  <th>操作</th>
                </tr>
               <c:forEach items="${pageInfo.list}" var="emp">
                <tr>
                  <th>${ emp.empId}</th>
                  <th>${ emp.empName}</th>
                  <th>${ emp.gender=="M"?"男":"女"}</th>
                  <th>${ emp.email}</th>
                  <th>${ emp.deptId}</th>
                  <th>${ emp.department.deptName}</th>
                  <th>
                     <button class="btn btn-success btn-sm ">
                       <span class="glyphicon glyphicon-pencil"></span>                        
                                                         编辑
                     </button>
                     <button class="btn btn-danger btn-sm ">
                       <span class="glyphicon glyphicon-trash"></span>
                                                          删除
                     </button>
                  </th>
                </tr>
              </c:forEach>   
             </table>
          </div>
        </div>
        <!-- 显示分页数据 --> 
        <div class="row">
            <!-- 显示分页信息 --> 
           <div class="col-md-6 col-lg-6 ">
                             当前在第${pageInfo.pageNum}页，总共有${pageInfo.pages}页,共有${pageInfo.total }记录
           </div>
             <!-- 显示分页条 -->
            <div class="col-md-6 col-lg-6 ">
              <ul class="pagination">
              
                <li><a href="${basePath}/emps?pn=1">首页</a></li>
                
                <c:if test="${pageInfo.hasPreviousPage }">
                 <li><a href="${basePath}/emps?pn=${pageInfo.pageNum-1}">&laquo;</a></li>
                </c:if>
                
                 <c:forEach items="${pageInfo.navigatepageNums}" var="pageNums">
                   <c:if test="${pageNums==pageInfo.pageNum}">
                     <li class="active"><a href="#">${pageNums}</a></li>
                   </c:if>
                   
                   <c:if test="${pageNums!=pageInfo.pageNum }">
                     <li><a href="${basePath}/emps?pn=${pageNums}">${pageNums}</a></li>
                   </c:if>
                  </c:forEach>
                  
                <c:if test="${pageInfo.hasNextPage }"> 
                <li><a href="${basePath}/emps?pn=${pageInfo.pageNum+1}">&raquo;</a></li>
                </c:if>
                <li><a href="${basePath}/emps?pn=${pageInfo.pages}">末页</a></li>
              </ul>
           </div>
        </div>
    
    </div>


</body>
</html>