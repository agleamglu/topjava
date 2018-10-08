<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Meals</title>

    <link href="<c:url value='mealListView.css' />" rel="stylesheet" type="text/css"/>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Menu</h2>
<table>
    <tr>
        <th>Date</th>
        <th>Meal name</th>
        <th>Calories</th>
    </tr>

    <c:forEach var="meal" items="${pageContext.request.getAttribute('list')}">
        <tr style="${meal.exceed ? 'color: green':'color: red'}">
            <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="date"/>
            <fmt:formatDate pattern="dd.MM.yyyy" var="formattedDate" value="${parsedDateTime}" type="date"/>

            <td><c:out value="${formattedDate}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>

        </tr>
    </c:forEach>


</table>

