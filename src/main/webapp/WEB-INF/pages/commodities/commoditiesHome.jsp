<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dpc
  Date: 2019/1/3
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>货物管理</title>
    <style>
        *{margin:0; padding:0;} #menuContent a{text-decoration:none;color:#ffffff}
        .c{
            border-style: solid;width: 200px;height: 130px;
            margin: 4 23 0 23;border-radius:5;display:block;
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

        function init() {
            var countNumber = document.getElementById("countNumber").value;
            var sumPage = document.getElementById("sumPageNumber").value;
            var currentPage=document.getElementById("currentPage").value;
            var info = "一共<span style='color: blue;'>" + countNumber + "</span>条数据， " +
                "共<span style='color:blue'>" + sumPage + "</span>页," +
                "当前第<span style='color:blue'>" + currentPage + "</span>页";
            document.getElementById("pageInfo").innerHTML=info;
        }
        
        
        function toNextPage() {
            var currentPageObject = document.getElementById("currentPage");
            var currentPage = parseInt(currentPageObject.value);
            var sumPage = parseInt(document.getElementById("sumPageNumber").value);
            if(currentPage >= sumPage) {
                alert("已经到底了");
            }else {
                currentPageObject.value = currentPage + 1;
                var pageSize = parseInt(document.getElementById("pageSize").value);
                var statPageObject = document.getElementById("startPage");
                statPageObject.value = parseInt(statPageObject.value) + pageSize;
                document.getElementById("listForm").submit();
            }
        }

        function toPrePage() {
            var currentPageObject = document.getElementById("currentPage");
            var currentPage = parseInt(currentPageObject.value);
            if(currentPage == 1) {
                alert("数据已经到顶了");
            }else {
                currentPageObject.value = currentPage - 1;
                var pageSize = parseInt(document.getElementById("pageSize").value);
                var startPageObject = document.getElementById("startPage");
                startPageObject.value = parseInt(startPageObject.value) - pageSize;
                document.getElementById("listForm").submit();
            }
        }

        function toLocationPage() {
            var pageNumber = document.getElementById("pageNumber").value;
            var currentPageObject = document.getElementById("currentPage");
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
                    startPageObject.value = (pageNumber - 1) * pageSize;
                    document.getElementById("listForm").submit();
                }
            }

        }

        function showAddMask(flag) {
            if(flag == 'true') {
                $(".addMask").css("display","block");
            }else {
                $(".addMask").css("display", "none");
            }
        }

        function checkAddCommodities() {
            //返回false还是会提交
            if($("#addName").val() == null || $("#addName").val() == ""){
                alert("商品名不能为空");
                return false
            }
            if($("#addPrice").val() == null || $("#addPrice").val() == ""){
                alert("价格不能为空");
                return false
            }
            var myreg = /^\d+(\.\d+)?$/;
            if(!myreg.test($("#addPrice").val())){
                alert("请输入有效的价格");
                return false;
            }
            if($("#addLocality").val() == null || $("#addLocality").val() == ""){
                alert("产地不能为空");
                return false
            }
           return true;
        }


        function cancelEdit(){
            $(".mask").css("display","none");
        }

        function deleteCommodities(id, name) {
            var r=confirm("确定要删除商品 " + name);
            if (r==true)
            {
                $("#dFruitId").val(id);
                $("#dStartPage").val($("#startPage").val());
                $("#dCurrentPage").val($("#currentPage").val());
                $("#dPageSize").val($("#pageSize").val());
                $("#deleteForm").submit();
            }

        }

        function editCommodities(id) {
            console.log(id)
            var message="{'id':'" + id + "'}";
            console.log(message);
            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath}/commodities/editCommodities.action',
                contentType: 'application/json;charset=utf-8',
                data:message,
                success:function (data) {
                    console.log(data)
                    $("#editName").val(data["name"]);
                    $("#editPrice").val(data["price"]);
                    $("#editLocality").val(data["locality"]);
                    $("#fruitId").val(data["fruitId"]);
                    $(".mask").css("display", "block");
                    $("#eStartPage").val($("#startPage").val());
                    $("#eCurrentPage").val($("#currentPage").val());
                    $("#ePageSize").val($("#pageSize").val());
                }
            })
        }

        function openAccessoryWin(id) {
            var url = "${pageContext.request.contextPath}/accessory/list.action?fruitId=" + id;
            window.open(url, "附属品", "height=400, width=700, scrollbars=yes");

        }
    </script>
</head>
<body onload="init()">
<%@ include file="../menu.jsp" %><br/>
<div class="mask">
    <div class="c">
        <div style="background-color: #173e65; height: 20px; color: #fff; font-size: 12px; padding-left: 7px;">
            修改信息<span style="float: right; padding-right: 10px;" onclick="cancelEdit()">x</span>
        </div>
        <form id="editForm" action="edit.action" method="post">
            名称:<input type="text" id="editName" name="name" style="width:120px"/><br>
            价格:<input type="text" id="editPrice" name="price" style="width:120px"/><br>
            产地:<input type="text" id="editLocality" name="locality" style="width:120px"/><br/>
            <input type="hidden" name="fruitId" id="fruitId">
            <input type="submit" value="提交" style="background-color: #173e65; color: #ffffff; width:70px;">
        </form>
    </div>
</div>

<div class="addMask">
    <div class="c">
        <div style="background-color: #173e65; height: 20px; color: #fff;font-size: 12px;padding-left: 7px;">
            添加商品
            <span style="float: right; pading-right:10px;" onclick="showAddMask('false')">x</span>
        </div>
        <form id="addForm" action="add.action" method="post" onsubmit="checkAddCommodities()">
            名称：<input type="text" id="addName" name="name" style="width:120px"/><br/>
            价格：<input type="text" id="addPrice" name="price" style="width:120px"/><br/>
            产地：<input type="text" id="addLocality" name="locality" style="width:120px"/><br/>
            <input type="submit" value="添加" style="background-color: #173e65; color:#ffffff; width:70px;">
        </form>
    </div>
</div>
<form id="listForm" action="list.action" method="post">
    名称：<input type="text" name="name" style="width:120px" value="${commodities.name}"/>
    产地：<input type="text" name="locality" style="width:120px" value="${commodities.locality}"/>
    价格：<input type="text" id="price1" name="startPrice" type="number" min="0.0" step="0.1" value="${startPrice}"/>
    - <input type="text" id="price2" name="endPrice" type="number" min="0.0" step="0.1" value="${endPrice}"/><br/>
    创建日期：<input type="datetime-local" name="startTime" value="${startTime}"/>
    - <input type="datetime-local" name="endTime" value="${endTime}"/>
    <input type="submit" value="搜索" style="background-color: #173e65; color: #ffffff; width: 70px;"/><br/>
    <c:if test="${errorMsg}">
        <span style="color: red;">${errorMsg}</span>
    </c:if>

    <input type="hidden" name="startPage" id="startPage" value="${startPage}"/>
    <input type="hidden" name="currentPage" id="currentPage" value="${currentPage}"/>
    <input type="hidden" name="pageSize" id="pageSize" value="${pageSize}"/>
    <input type="hidden" name="sumPageNumber" id="sumPageNumber" value="${sumPageNumber}"/>
    <input type="hidden" name="countNumber" id="countNumber" value="${countNumber}"/>
</form>
<hr style="margin-top: 10px;"/>
<button onclick="showAddMask('true')" style="background-color: #173e65; color: #ffffff; width: 70px;">添加</button>
<c:if test="${list != null}">
    <table style="margin-top: 10px; width: 700px; text-align: center; " border="1">
        <tr>
            <td>序号</td>
            <td>名称</td>
            <td>价格</td>
            <td>产地</td>
            <td>创建日期</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${list}" var="item" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td>${item.locality}</td>
                <td>${item.createTime}</td>
                <td>
                    <a onclick="editCommodities('${item.fruitId}')">编辑</a> |
                    <a onclick="deleteCommodities('${item.fruitId}','${item.name}')">删除</a> |
                    <a onclick="openAccessoryWin('${item.fruitId}')">附属品</a>

                    <form id="deleteForm" action="delete.action" method="post">
                        <input type="hidden" name="fruitId" id="dFruitId"/>
                        <input type="hidden" name="startPage" id="dStartPage"/>
                        <input type="hidden" name="currentPage" id="dCurrentPage"/>
                        <input type="hidden" name="pageSize" id="dPageSize"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</c:if>
<c:if test="${list == null}">
    <b>搜索结果为空</b>
</c:if>
<div style="margin-top: 10px;">
    <a onclick="toPrePage()">上一页</a>
    <a onclick="toNextPage()">下一页</a>
    <input type="text" id="pageNumber" style="width:50px;"/>
    <button onclick="toLocationPage()">go</button>
    <div id="pageInfo"></div>
</div>
</body>
</html>
