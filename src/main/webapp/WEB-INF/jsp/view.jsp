<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>物品信息</title>
    <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body style="margin-top:50px;overflow: hidden;">
<form action="${pageContext.request.contextPath}/save" method="post">
    <input type="hidden" name="id" value="${goods.id}"/>
    <table class="gridtable" style="width:800px;">
        <tr>
            <th colspan="5">物品信息 - [<a href="${pageContext.request.contextPath}/list">返回</a>]</th>
        </tr>
        <tr>
            <th>物品名称：</th>
            <td><input type="text" name="name" value="${goods.name}"/></td>
            <th>物品地区：</th>
            <td><input type="text" name="region" value="${goods.region}"/></td>
            <td><input type="submit" value="保存"/></td>
        </tr>
    </table>
</form>
</body>
</html>
