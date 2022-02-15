<%--
  Created by IntelliJ IDEA.
  User: NGO MAI
  Date: 15/02/2022
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <button><a href="${pageContext.request.contextPath}/products?action=create">CREATE NEW PRODUCT</a></button>
</div>
<div style="float:right;">
    <form action="/products?action=search">
        <label>
            <input type="text" name="search">
        </label>
        <button type="submit">Search</button>
    </form>
</div>
<div>
    <div>
        <div>
            <p>Product List</p>
        </div>
        <div>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Color</th>
                    <th>Category</th>
                    <th colspan="2">ACTION</th>
                </tr>
                <c:forEach items="${products}" var="product">
                    <tr>
                        <td>${product.getId()}</td>
                        <td>${product.getName()}</td>
                        <td>${product.getPrice()}</td>
                        <td>${product.getQuantity()}</td>
                        <td>${product.getDescribe()}</td>
                        <td>${product.getCategory()}</td>
                        <td><button><a href="${pageContext.request.contextPath}/products?action=edit&id=${product.getId()}">EDIT</a></button></td>
                        <td><button><a href="${pageContext.request.contextPath}/products?action=delete&id=${product.getId()}">DELETE</a></button></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
