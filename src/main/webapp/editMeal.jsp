<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="contact">New record</div>

<div id="contactForm">

    <small>Please, fill out the form</small>

    <form action="meals" method="post">
        <fmt:parseDate value="${record.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="date"/>
        <fmt:formatDate pattern="yyyy-MM-dd" var="formatted" value="${parsedDateTime}" type="date"/>
        <input name="mealDate" type="date" min="2018-01-01" value="${formatted}" required/>

        <fmt:parseDate value="${record.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="date"/>
        <fmt:formatDate pattern="hh:mm" var="formatted" value="${parsedDateTime}" type="date"/>
        <input name="mealTime" type="time" value="${formatted}" required/>

        <select name="mealType" required value="${record.description}">
            <option value="breakfast">Завтрак</option>
            <option value="lunch">Обед</option>
            <option value="dinner">Ужин</option>
        </select>
        <input placeholder="Calories" name="calories" type="number" size="5" min="0" style="width: 60px;" required
               value="${record.calories}"/>
        <input type="submit" value="Submit"/>
        <input type="reset" value="Reset"/>
    </form>
</div>