<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Meals</title>

    <link href="<c:url value='css/mealListView.css' />" rel="stylesheet" type="text/css"/>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Menu</h2>
<table>
    <tr>
        <td colspan="6" align="right">
            <table>
                <tr>
                    <td align="right"><input type="button" value="add" class="btn1"
                                             onclick="window.location='meals?action=add_new'"/>
                    </td>
                </tr>
            </table>
    </tr>
    <tr>
        <th>Date</th>
        <th>Meal name</th>
        <th>Calories</th>
        <th></th>
    </tr>

    <c:forEach var="meal" items="${pageContext.request.getAttribute('list')}">
        <tr style="${meal.exceed ? 'color: red':'color: green'}">

            <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="date"/>
            <fmt:formatDate pattern="dd.MM.yyyy hh:mm" var="formattedDate" value="${parsedDateTime}" type="date"/>

            <td><c:out value="${formattedDate}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td>

                <form name="changes" action="meals" method="post">
                    <input type="hidden" name="id" value="${meal.id}"/>
                    <input type="submit" value="" class="btn_edit">
                    <a href="meals?action=delete&id=<c:out value="${meal.id}"/>">
                        <input type="button" class="btn_delete">
                    </a>
                </form>


            </td>
                <%-- <td><a href="meals?action=edit&id=<c:out value="${meal.id}"/>">Update</a>
                 <a href="meals?action=delete&id=<c:out value="${meal.id}"/>">Delete</a></td> --%>

        </tr>
    </c:forEach>


</table>

