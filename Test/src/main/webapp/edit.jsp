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
<div>
    <div>
        <form action="" method="post">
            <table>
                <tr>
                    <th>ID</th>
                    <td><label>
                        <input type="text" readonly="readonly" placeholder="id" name="id" value="<c:out value='${product.id}' />">
                    </label></td>
                </tr>
                <tr>
                    <th>Name</th>
                    <td><label>
                        <input type="text" placeholder="name" name="name" value="<c:out value='${product.name}' />"  >
                    </label></td>
                </tr>
                <tr>
                    <th>Price</th>
                    <td><label><input type="text" name="price" placeholder="price" value="<c:out value='${product.price}' />"></label></td>
                </tr>
                <tr>
                    <th>Quantity</th>
                    <td><label><input type="text" name="quantity" placeholder="quantity" value="<c:out value='${product.quantity}' />"></label></td>
                </tr>
                <tr>
                    <th>Color</th>
                    <td><label><input type="text" name="color" placeholder="color" value="<c:out value='${product.color}' />"></label></td>
                </tr>
                <tr>
                    <th>Describe</th>
                    <td><label><input type="text" name="describe" placeholder="describe" value="<c:out value='${product.describe}' />"></label></td>
                </tr>
                <tr>

                    <th>Category</th>
                    <td><label for=""></label><select name="category" id="">
                        <c:forEach items="${category}" var="category">
                            <option value="${category.getId()}">${category.getName()}</option>
                        </c:forEach>
                    </select></td>
                </tr>
                <tr>
                    <td><button type="submit"><a>Save</a></button></td>
                    <td><button><a href="/products">Back</a></button></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
