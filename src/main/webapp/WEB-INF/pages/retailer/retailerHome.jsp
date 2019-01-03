<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dpc
  Date: 2018/12/31
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>零售商管理</title>
    <style>
        *{margin:0;padding:0;} #menuContent a{text-decoration: none;color:#ffffff}
        .c{
            border-style: solid;width: 200px;height: 130px;
            margin: 4px 23px 0 23px;border-radius:5px;display:block;
            background:#fff;
            margin:10% auto;
        }
        .mask,.addMask{
            width:100%;
            height:100%;
            position: absolute;
            background:rgba(0,0,0,.3);
            display: none;
        }
    </style>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript">
        function changeStatus() {
            var status = document.getElementById("indexStatus").value;
            document.getElementById("status").value = status;
        }

        function init() {
            var countNumber = document.getElementById("countNumber").value;
            var sumPage = document.getElementById("sumPageNumber").value;
            var currentPage=document.getElementById("currentPage").value;
            var info = "一共<span style='color: blue;'>" + countNumber + "</span>条数据， " +
                    "共<span style='color:blue'>" + sumPage + "</span>页," +
                    "当前第<span style='color:blue'>" + currentPage + "</span>页";
            document.getElementById("pageInfo").innerHTML=info;
        }

        function toPrePage() {
            var currentPageObject = document.getElementById("currentPage");
            var currentPage = parseInt(currentPageObject.value);
            if(currentPage == 1) {
                alert("已经是第一页了");
            }else{
                currentPageObject.value = currentPage - 1;
                var pageSize = parseInt(document.getElementById("pageSize").value);
                var startPageObject = document.getElementById("startPage");
                startPageObject.value = parseInt(startPageObject.value) - pageSize;
                document.getElementById("listForm").submit();
            }
        }

        function toNextPage() {
            var currentPageObject = document.getElementById("currentPage");
            var currentPage = parseInt(currentPageObject.value);
            var sumPage = parseInt(document.getElementById("sumPageNumber").value);
            if(currentPage >= sumPage) {
                alert("啊~行啦行啦，我们是有底线的")
            } else{
                currentPageObject.value = currentPage + 1;
                var pageSize = parseInt(document.getElementById("pageSize").value);
                var startPageObject = document.getElementById("startPage");
                startPageObject.value = parseInt(startPageObject.value) + pageSize;
                document.getElementById("listForm").submit();
            }

        }

        function toLocationPage() {
            var pageNumber = document.getElementById("pageNumber").value;
            var currentPageObject = document.getElementById("currentPage");
            var currentPge = currentPageObject.value;
            if(pageNumber == null || pageNumber == "") {
                alert("请输入要跳转的页数");
            }else {
                pageNumber = parseInt(pageNumber);
                var sumPage = parseInt(document.getElementById("sumPageNumber").value);
                if(pageNumber < 1) {
                    alert("数据已经到顶了");
                } else if(pageNumber > sumPage) {
                    alert("数据已经到底了");
                }else {
                    currentPageObject.value = pageNumber;
                    var pageSize = parseInt(document.getElementById("pageSize").value);
                    var startPageObject = document.getElementById("startPage");
                    // if(pageNumber > currentPge) {
                    //     startPageObject.value = parseInt(startPageObject.value) + pageSize;
                    // } else if(pageNumber < currentPge) {
                    //     startPageObject.value = parseInt(startPageObject.value) - pageSize;
                    // }
                    startPageObject.value = (pageNumber - 1) * pageSize;
                    document.getElementById("listForm").submit();
                }
            }

        }

        function changeEditStatus(){
            var status = document.getElementById("eStatus").value;
            document.getElementById("editStatus").value=status;
        }
        function cancelEdit(){
            $(".mask").css("display","none");
        }

        function deleteRetailer(id, name) {
            var r=confirm("确定要删除用户 " + name);
            if (r==true)
            {
                $("#dRetailerId").val(id);
                $("#dStartPage").val($("#startPage").val());
                $("#dCurrentPage").val($("#currentPage").val());
                $("#dPageSize").val($("#pageSize").val());
                $("#deleteForm").submit();
            }

        }
        function showAddMask(flag) {
            if(flag == 'true') {
                $(".addMask").css("display","block");
            }else {
                $(".addMask").css("display", "none");
            }

        }

        function checkAddRetailer() {
            if($("#addName").val() == null || $("#addName").val() == ""){
                alert("用户名不能为空");
                return false
            }

            if($("#addTelphone").val() == null || $("#addTelphone").val() == ""){
                alert("手机号不能为空");
                return false
            }

            var myreg = /^(1(3|5|8)[0-9]{1})\d{8}$/;
            if(!myreg.test($("#addTelphone").val())){
                alert("请输入有效的手机号码");
                return false;
            }

            if($("#addAddress").val() == null || $("#addAddress").val() == ""){
                alert("地址不能为空");
                return false
            }
            return true;


        }

        function editRetailer(id) {
            console.log(id)
            var message="{'id':'" + id + "'}";
            console.log(message);
            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath}/retailer/editRetailer.action',
                contentType: 'application/json;charset=utf-8',
                data:message,
                success:function (data) {
                    console.log(data)
                    $("#editName").val(data["name"]);
                    $("#editTelphone").val(data["telphone"]);
                    $("#editAddress").val(data["address"]);
                    $("#retailerId").val(data["retailerId"]);
                    $("#editStatus").val(data["status"]);
                    $("#eStatus").val(data["status"]);
                    $(".mask").css("display", "block");
                    $("#eStartPage").val($("#startPage").val());
                    $("#eCurrentPage").val($("#currentPage").val());
                    $("#ePageSize").val($("#pageSize").val());
                }
            })
        }
    </script>
</head>
<body onload="init()">
<%@include file="../menu.jsp"%><br>
<div class="mask">
    <div class="c">
        <div style="background-color: #173e65; height: 20px; color: #fff; font-size: 12px; padding-left: 7px;">
            修改信息<span style="float: right; padding-right: 10px;" onclick="cancelEdit()">x</span>
        </div>
        <form id="editForm" action="edit.action" method="post">
            姓名:<input type="text" id="editName" name="name" style="width:120px"/><br>
            手机:<input type="text" id="editTelphone" name="telphone" style="width:120px"/><br>
            地址:<input type="text" id="editAddress" name="address" style="width:120px"/><br/>
            状态:<select id="eStatus" onchange="changeEditStatus()">
            <option value="1">启用</option>
            <option value="0">停用</option>
        </select><br/>
            <input type="hidden" name="retailerId" id="retailerId">
            <input type="hidden" name="status" id="editStatus">
            <input type="submit" value="提交" style="background-color: #173e65; color: #ffffff; width:70px;">
        </form>
</div>
</div>

<div class="addMask">
    <div class="c">
        <div style="background-color: #173e65; height: 20px; color: #fff;font-size: 12px;padding-left: 7px;">
            添加信息
            <span style="float: right; pading-right:10px;" onclick="showAddMask('false')">x</span>
        </div>
        <form id="addForm" action="add.action" method="post" onsubmit="checkAddRetailer()">
            姓名：<input type="text" id="addName" name="name" style="width:120px"/><br/>
            手机：<input type="text" id="addTelphone" name="telphone" style="width:120px"/><br/>
            地址：<input type="text" id="addAddress" name="address" style="width:120px"/><br/>
            <input type="hidden" name="status" value="1">
            <input type="submit" value="添加" style="background-color: #173e65; color:#ffffff; width:70px;">
        </form>
    </div>
</div>
<form action="list.action" method="post" id="listForm">
    姓名：<input type="text" name="name" style="width:120px"/>
    手机：<input type="text" name="telphone" style="width:120px"/>
    地址：<input type="text" name="address" style="width:120px"/>
    状态：<select id="indexStatus" onchange="changeStatus()">
    <option value="-1" selected="selected">全部</option>
    <option value="1">启用</option>
    <option value="0">停用</option>
    </select>
    <input type="hidden" name="status" id="status" value="-1">
    创建日期<input type="text" name="createTime"/>
    <input type="submit" value="搜索" style="background-color: #173e65;color: #ffffff;width: 70px;"/><br/>
    <c:if test="${errorMsg}">
        <span style="color:red">${errorMsg}</span>
    </c:if>
    <input type="hidden" name="startPage" id="startPage" value="${startPage}"/>
    <input type="hidden" name="currentPage" id="currentPage" value="${currentPage}"/>
    <input type="hidden" name="pageSize" id="pageSize" value="${pageSize}"/>
    <input type="hidden" name="sumPageNumber" id="sumPageNumber" value="${sumPageNumber}"/>
    <input type="hidden" name="countNumber" id="countNumber" aria-invalid="countNumber" value="${countNumber}"/>
</form>

    <hr style="margin-top: 10px"/>
    <button onclick="showAddMask('true')" style="background-color: #173e65; color: #ffffff; width: 70px;">添加</button>
    <c:if test="${list != null}">
        <table style="margin-top:10px; width:700px; text-align: center;" border="1">
            <tr>
                <td>序号</td><td>姓名</td><td>手机号</td><td>地址</td>
                <td>状态</td><td>创建日期</td><td>操作</td>
            </tr>
            <f:forEach items="${list}" var="item" varStatus="status">
                <tr>
                    <td>${status.index+1}</td>
                    <td>${item.name}</td>
                    <td>${item.telphone}</td>
                    <td>${item.address}</td>
                    <td>
                        <c:if test="${item.status == 1}">
                            <span style="color:blue">启用</span>
                        </c:if>
                        <c:if test="${item.status == 0}">
                            <span style="color:red">停用</span>
                        </c:if>
                    </td>
                    <td>${item.createTime}</td>
                    <td><a onclick="editRetailer('${item.retailerId}')">编辑</a>|
                        <a onclick="deleteRetailer('${item.retailerId}' , '${item.name}')">删除</a>
                        <form id="deleteForm" action="delete.action" method="post">
                            <input type="hidden" name="retailerId" id="dRetailerId"/>
                            <input type="hidden" name="startPage" id="dStartPage"/>
                            <input type="hidden" name="currentPage" id="dCurrentPage"/>
                            <input type="hidden" name="pageSize" id="dPageSize"/>
                        </form>
                    </td>
                </tr>
            </f:forEach>
        </table>

        <c:if test="${list == null}">
            <b>搜索结果为空</b>
        </c:if>
        <div style="margin-top: 10px">
            <a onclick="toPrePage()">上一页</a><a onclick="toNextPage()">下一页</a>
            <input type="text" id="pageNumber" style="width:50px"><button onclick="toLocationPage()">go</button>
            <div id="pageInfo"></div>
        </div>
    </c:if>



</body>
</html>
