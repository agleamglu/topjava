<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <link href="<c:url value='css/styles.css' />" rel="stylesheet" type="text/css"/>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <div class="container">
        <h3><a href="index.html">Home</a></h3>
    </div>
    <h2 text-align: center>Список приема пищи</h2>
    <form id="filter">
        <div class="row">
            <div class="offset-1 col-2">
                <label for="startDate">От</label>
                <input type="date" class="form-control" name="startDate" id="startDate">
                <label for="startTime">От времени</label>
                <input type="time" class="form-control" name="startTime" id="startTime">
            </div>
            <div class="col-2">
                <label for="endDate">До даты</label>
                <input type="date" class="form-control" name="endDate" id="endDate">
                <label for="endTime">До времени</label>
                <input type="time" class="form-control" name="endTime" id="endTime">
            </div>
        </div>
    </form>
    <div class="card-footer text-right">
        <button class="btn btn-danger" onclick="clearFilter()">
            <span class="fa fa-remove"></span>
            Отменить
        </button>
        <button class="btn btn-primary" onclick="updateTable()">
            <span class="fa fa-filter"></span>
            Отфильтровать
        </button>
    </div>
    <select name="userId" required id="userId"
            onchange="window.location.href = 'meals?action=changeUser&id='+this.value">
        <option value="-1"></option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
    </select>
    <a href="meals?action=create">Add Meal</a>
    <hr/>
    <table>
        <thead>
        <tr>
            <th aria-sort="descending">Дата/Время</th>
            <th>Наименование</th>
            <th>Калории</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>