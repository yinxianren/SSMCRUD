var basePath;
var totleInfo;

$(function(){
	basePath=$("base").attr("href");
	to_page_ajax(1);
	
	

	
	

});


$(function(){


	
	//新增按钮，点击弹出新增界面
	$("#new_emp").click(function(){
		
		//表达重置
		$("#new_emp_model form")[0].reset();
		
		
		//新增员工按钮下的部门名
		new_emp_department_ajax();
		
		$("#new_emp_model").modal({
			backdrop: "static"
		})
	});
	
	
	$("#submit_add_emp").click(function(){
		//数据校验
		var status=check_emp_info();
		//提交数据
		alert(status);
		if(status==true){
			submint_new_emp();
		}
		
	});
	
	
	//编辑按钮   on用于后来绑定事件   在选择元素上绑定一个或多个事件的事件处理函数。
	$(document).on("click",".editButton",function(){
		
		alert(1);
	});
	
	//删除按钮
	$(document).on("click",".deleteButton",function(){
		
		alert(2);
	});
	
});


function check_emp_info(){
	
	var username=$("#userName").val();
	var reg=/(^[a-zA-Z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]{2,5}$)/;
	
	//注释原因：测试服务器验证jsr
	/*
	if(!reg.test(username)){
		
		$("#userName").parent().addClass("has-error");
		$("#userName").next("span").text("用户名不合法！");
		return false;
	}
	*/
	
	var userMail=$("#email").val();
	reg=/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
	
	if(!reg.test(userMail)){
		
		$("#userName").parent().attr("class","col-sm-10 has-success");
		$("#userName").next("span").text("");
		
		$("#email").parent().addClass("has-error");
		$("#email").next("span").text("邮箱不合法！");
		return false;
	}
	$("#userName").parent().attr("class","col-sm-10 has-success");
	$("#email").parent().attr("class","col-sm-10 has-success");
	$("#email").next("span").text("");
	
	return true;
}


function info_check(){
	//验证用户不重复,ajax
	$("#userName").change(function(){
		
		
	});
	
	//验证邮箱，ajax
	$("email").change(function(){
		
	});
	
}


/**
 * 使用Rest风格URI,将页面的post普通请求转为deleter或者put请求
 * type:定义规则
 * 
 * GET  :查询
 * POST :保存
 * PUT  :修改
 * DELETE :删除
 * 
 * @param page
 * @returns
 */


function to_page_ajax(page){

	$.ajax({
		url:basePath+"/emps",
		data:"pn="+page,
		type:"GET",
		success:function(result){
			//console.log(result);
			//解析并显示员工数据
			build_emps_table(result);
			//显示信息
			buid_page_info(result);
			//解析并显示分页信息
			build_page_nav(result);
			
		}


	});
}


//返回部门信息
function new_emp_department_ajax(){
	
	$.ajax({
		url:basePath+"/department",
		type:"GET",
		success(result){
			dapartment_info(result)
		}
	});
	
}

//提交新增员工
function submint_new_emp(){
	$.ajax({
		url:basePath+"/emps",
		type:"POST",
		data:$("#new_emp_model form").serialize(),
		success(result){
			if(result.messageStatus=="100"){
				//隐藏模态框
				$("#new_emp_model").modal('hide')
				//调到最后一页
				to_page_ajax(totleInfo);
			}else if(result.messageStatus=="104"){
				//提示失败
				$("#hint").html("添加失败").css({"display":"block"});
			    console.log(result);
			}
		}
		
	});
	
}



//显示部门信息
function dapartment_info(result){


	$("body div select").empty();
		
	$.each(result.modelMap.depList,function(index,item){
		$("<option></option>").append(item.deptName).attr("value",item.deptId).appendTo("body div select");
	});
	
	
	
	
}




//解析并显示员工数据
function build_emps_table(result){
	//清空表体		
	$("table tbody").empty();
	var emps=result.modelMap.pageInfo.list;
	$.each(emps,function(index,item){
		var empId=$("<td></td>").append(item.empId);
		var empName=$("<td></td>").append(item.empName);
		var gender=$("<td></td>").append(item.gender=='M'?"男":'女');
		var email=$("<td></td>").append(item.email);
		var deptId=$("<td></td>").append(item.deptId);
		var deptName=$("<td></td>").append(item.department.deptName);
       
		var editButton=$("<button></button>").addClass("btn btn-success btn-sm editButton").append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
		var deleteButton=$("<button></button>").addClass("btn btn-success btn-sm deleteButton").append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");

		var but=$("<td></td>").append(editButton).append("  ").append(deleteButton);

		$("<tr></tr>").append(empId)
		.append(empName)
		.append(gender)
		.append(email)
		.append(email)
		.append(deptId)
		.append(deptName)
		.append(but) 
		.appendTo("table tbody");


	});
}





//显示信息
function buid_page_info(result){
	$("#pageInfo").empty();
	$("#pageInfo").append(  "当前在第"+ result.modelMap.pageInfo.pageNum+"页，总共有"+result.modelMap.pageInfo.pages +"页,共有"+result.modelMap.pageInfo.total +"记录");
	totleInfo=result.modelMap.pageInfo.total;
}



//解析并显示分页信息
function build_page_nav(result){

	$("ul").empty();



	if(result.modelMap.pageInfo.hasPreviousPage){
		$("<li></li>").append($("<a></a>").append("首页")).click(function(){
			to_page_ajax(1);
		}).appendTo("ul");

		$("<li></li>").append($("<a></a>").append("&laquo;")).click(function(){
			to_page_ajax(result.modelMap.pageInfo.pageNum-1);
		}).appendTo("ul");
	}
	$.each(result.modelMap.pageInfo.navigatepageNums,function(index,item){
		var page_num=$("<li></li>").append($("<a></a>").append(item));

		if(result.modelMap.pageInfo.pageNum==item){
			page_num=page_num.addClass("active");
		}else{
			page_num=page_num;
		}

		page_num.click(function(){
			to_page_ajax(item);
		}).appendTo("ul");


	});
	if(result.modelMap.pageInfo.hasNextPage){
		$("<li></li>").append($("<a></a>").append("&raquo;")).click(function(){
			to_page_ajax(result.modelMap.pageInfo.pageNum+1);
		}).appendTo("ul");
		
		$("<li></li>").append($("<a></a>").append("末页")).click(function(){
			to_page_ajax(result.modelMap.pageInfo.pages);
		}).appendTo("ul");
		
	}
	
	
}















