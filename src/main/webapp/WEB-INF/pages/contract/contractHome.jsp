<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dpcraft
  Date: 04/01/2019
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>销售合同管理</title>
    <style>
        *{margin:0;padding: 0;}
        #menuContent a{text-decoration: none;color:#ffffff}
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js">
    </script>
    <script type="text/javascript">

       function init() {
           var countNumber = document.getElementById("countNumber").value;
           var sumPage = document.getElementById("sumPageNumber").value;
           var currentPage = document.getElementById("currentPage").value;
           var info="共 <span style='color:blue'>" + countNumber + "</span>条数据， " +
               "共 <span style='color:blue'>" + sumPage + "</span>页， " +
               "当前第 <span style='color:blue'>" + currentPage + "</span>页";
           document.getElementById("pageInfo").innerHTML = info;
       }


       function changeType() {
           var type = document.getElementById("indexType").value;
           document.getElementById("type").value = value;
       }

       function toPrePage() {
           var currentPageObject = document.getElementById("currentPage");
           var currentPage = parseInt(currentPageObject.value);
           if(currentPage == 1) {
               alert("数据已经到顶了！ ");
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
           var currentPage = currentPageObject.value;
           var sumPage = parseInt(document.getElementById("sumPageNumber").value);
           if(currentPage >= sumPage) {
               alert("数据应到底了！");
            }else {
            currentPageObject.value = currentPage + 1;
            var pageSize = parseInt(document.getElementById("pageSize").value);
            var startPageObject = document.getElementById("startPage");
            startPageObject.value = parseInt(startPageObject.value) + pageSize;
            document.getElementById("listForm").submit();
           }
       }

       function toLocationPage() {
           var pageNumber = document.getElementById("pageNumber").value;

           if(pageNumber == null || pageNumber == "") {
               alert("请输入要跳转的页数！");
           }else{
               pageNumber = parseInt(pageNumber);
               var sumPage = parseInt(document.getElementById("sumPageNumber").value);
               if(pageNumber < 1) {
                   alert("数据已经到顶了");
               }else if(pageNumber > sumPage){
                   alert("数据已经到底了");
               }else {
                   var pageSize  = parseInt(document.getElementById("pageSize").value);
                   var startPageObject = document.getElementById("startPage");
                   startPageObject.value = (pageNumber - 1) * pageSize;
                   document.getElementById("listForm").submit();
               }
           }

       }

       function addContract() {
           var url = "${pageContext.request.contextPath}/contract/toAddPage.action";
           window.open(url,"创建合同","height=700,width=700,scrollbar=yes");

       }

       function getContractDetail(id) {
           var url = "${pageContext.request.contextPath}/contract/getContractDetail.action?contractId=" + id;
           window.open(url,"合同详情","height=700,width=700,scrollbars=yes");

       }

       function deleteContract(contractId,barCode) {
           if(window.confirm("你确定要删除编号为 " + barCode + "的合同信息吗？")) {
               $("#dContractId").val(contractId);
               $("#dStartPage").val($("#startPage").val());
               $("#dCurrentPage").val($("#currentPage").val());
               $("#dPageSize").val($("#pageSize").val());
               $("#deleteForm").submit();
           }

       }

       function getContractDetail(id) {
           var url="${pageContext.request.contextPath}/contract/getContractDetail.action?contractId=" + id;
           window.open(url,"合同详情", "height=700,scrollbar=yes");

       }



    </script>
</head>
<body onload="init()">
<%@include file="../menu.jsp"%><br/>
<form id="listForm" action="list.action" method="post">
    合同号：<input type="text" name="barCode" style="width:120px"/>
    零售商：<input type="text" name="retailerName" style="width:120px"/>
    类型：<select type="text" id="indexType" onchange="changeType()">
    <option value="-1" selected="selected">全部</option>
    <option value="1">省外</option>
    <option value="0">省内</option>
    </select>
    <input type="hidden" name="type" id="type" value="-1"><br/>
    创建日期<input type="datetime-local" name="startTime" value="${startTime}"/>
    -<input type="datetime-local" name="startTime" value="${endTime}"/>
    <input type="submit" value="搜索" style="background-color: #173e65;color: #ffffff; width: 70px;">
    <c:if test = "${errorMsg}">
        <span style="color: red">${errorMsg}</span>
    </c:if>

    <input type="hidden" name="startPage" id="startPage" value="${startPage}"/>
    <input type="hidden" name="currentPage" id="currentPage" value="${currentPage}"/>
    <input type="hidden" name="pageSize" id="pageSize" value="${pageSize}"/>
    <input type="hidden" name="sumPageNumber" id="sumPageNumber" value="${sumPageNumber}"/>
    <input type="hidden" name="countNumber" id="countNumber" value="${countNumber}"/>
</form>
<hr style="margin-top:10px;"/>
<button onclick="addContract()" style="background-color: #173e65; color:#ffffff; width:70px;">添加</button><br/>
<c:if test="${list != null}">
    <table style="margin-top: 10px; width:700px; text-align:center;" border="1">
        <tr>
            <td>序号</td>
            <td>合同编号</td>
            <td>零售商</td>
            <td>类型</td>
            <td>创建日期</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${list}" var="item" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td><a href="#" onclick="getContractDetail('${item.contractId}')">${item.barCode}</a> </td>
                <td>${item.retailerName}</td>
                <td>
                    <c:if test="${item.type == 1}">
                        <span style="color: blue">省外</span>
                    </c:if>
                    <c:if test="${item.type == 0}">
                        <span style="color: green">省内</span>
                    </c:if>
                </td>
                <td>${item.createTime}</td>
                <td>
                    <a onclick="editContract('${item.contractId}')">编辑</a>|
                    <a onclick="deleteContract('${item.contractId}', '${item.barCode}')">删除</a>
                    <form id="deleteForm" action="delete.action" method="post">
                        <input type="hidden" name="contractId" id="dContractId"/>
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
    <b>搜索结果为空！</b>
</c:if>
<div style="margin-top:10px;">
    <a onclick="toPrePage()">上一页</a><a onclick="toNextPage()">下一页</a>
    <input type="text" id="pageNumber" style="width: 50px">
    <button onclick="toLocationPage()">go</button>
    <div id="pageInfo"></div>
</div>
</body>
</html>
