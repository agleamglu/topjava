<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meal</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>${param.action == 'create' ? 'New user account' : 'Edit user account'}</h2>
    <hr>
    <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User" scope="request"/>
    <form name="info" method="post" action="users">
        <input type="hidden" name="id" value="${user.id}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${user.name}" name="name" required></dd>
        </dl>
        <dl>
            <dt>Email:</dt>
            <dd><input type="text" value="${user.email}" size=40 name="email" required></dd>
        </dl>
        <dl>
            <dt>Password:</dt>
            <dd><input type="password" value="${user.password}" name="password" required></dd>
        </dl>
        <dl>
            <dt>Active:</dt>
            <dd><p><input type="radio" name="enabled" id="enabled"
                          value="true" ${user.enabled ?'checked':''}>Enabled</p>
                <p><input type="radio" name="enabled" id="disabled"
                          value="false" ${user.enabled ?'':'checked'}>Disabled</p></dd>
            <%-- <dd><input type="checkbox" value="${user.enabled}" name="enabled" ${user.enabled ? "checked":""}></dd>--%>

        </dl>
        <dl>
            <dt>Daily limit:</dt>
            <dd><input type="text" value="${user.caloriesPerDay}" name="calories" required></dd>
            <dt> calories</dt>
        </dl>
        <dl>
            <dt>Roles:</dt>
            <dd><input type="text" value="${user.roles}" name="roles" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
    <body OnLoad="document.info.name.focus();"></body>
</section>
</body>
</html>
