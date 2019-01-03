<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dpc
  Date: 2019/1/3
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>附属品管理</title>
    <style>
        *{margin:0; padding:0;} a{text-decoration:none;color:#ffffff}
        .c{
            border-style: solid;width: 200px;height: 130px;
            margin: 4 23 0 23;border-radius:5;display:block;
            background:#fff;
            margin:10% auto;
        }
        .addAccessoryMask{
            width:100%;
            height:100%;
            position: absolute;
            background:rgba(0,0,0,.3);
            display: none;
        }
    </style>

    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js">
    </script>
    <script type="text/javascript">

        function showAddAccessory(flag) {
            if(flag == 'true') {
                $(".addAccessoryMask").css("display","block");
            }else{
                $(".addAccessoryMask").css("display","none");
            }
        }

        function checkAll(obj) {
            var isCheck = obj.checked;
            var checkList = document.getElementsByName("arrays");
            for(var i=0; i < checkList.length; i++) {
                checkList[i].checked = isCheck;
             }
        }

        function deleteAccessory() {
            var myArray = new Array();
            var len = 0;
            var fruitId = document.getElementById("aFruitId").value;
            var arrays = document.getElementsByName("arrays");
            for(var i=0; i< arrays.length; i++){
                if(arrays[i].checked) {
                    myArray[len++] = arrays[i].value;
                }
            }
            console.log(arrays)
            console.log(myArray)
            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath}/accessory/deleteList.action',
                data:{"arrays":myArray, "fruitId":fruitId},
                traditional: true,
                success:function (data) {
                    alert("删除成功");
                    location.reload();
                }
            })

        }
    </script>

</head>
<body>
<div class="addAccessoryMask">
    <div class="c">
        <div style="background-color: #173e65; height: 20px; color: #FFFFFF; font-size: 12px; padding-left: 7px;">
            添加附属品信息
            <span style="float:right;padding-right: 10px;" onclick="showAddAccessory('false')">x</span>
        </div>
        <form id="addAccessoryForm" action="add.action" method="post" onsubmit="checkAddRetailer()">
            名称：<input type="text" id="addAccessoryName" name="name" style="width: 120px"/><br/>
            价格：<input type="text" id="addAccessoryPrice" name="price" style="width: 120px"/><br/>
            <input type="hidden " id="aFruitId" name="fruitId" value="${fruitId}"/>
            <input type="submit" value="添加" style="background-color: #173e65; color: #FFFFFF; width: 70px;"/>
        </form>

    </div>
</div>
<button onclick="showAddAccessory('true')"
        style="background-color: #173e65; color: #ffffff; width: 70px;">添加</button>
<button onclick="deleteAccessory()"
        style="background-color:red; color: #ffffff; width: 70px;">删除</button><br/>
<c:if test="${list != null}">
    <table style="margin-top: 10px; width: 400px; text-align: center;" border="1">
        <tr>
            <td><input type="checkbox" onclick="checkAll(this)"/></td>
            <td>名称</td>
            <td>价格</td>
            <td>创建日期</td>
        </tr>
        <c:forEach items="${list}" var="item" varStatus="status">
            <tr>
                <td><input type="checkbox" name="arrays" value="${item.accessoryId}"></td>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td>${item.createTime}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${list == null}">
    <b>结果为空！</b>
</c:if>

</body>
</html>
