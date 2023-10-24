<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mnachit
  Date: 2023/10/20
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Edit Profile</title>
    <jsp:include page="../util/taglibs.jsp"/>
</head>
<body>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<jsp:include page="../util/nav.jsp"/>

<div class="container">
    <c:choose>
        <c:when test="${successChangingPassword}">
            <div class="message-container" style="height: 100px; overflow-y: auto;">
                <div class="alert alert-success" role="alert">
                    Password Has Been Changed Successfully
                </div>
            </div>
            <c:remove var="successChangingPassword" scope="session"/>
        </c:when>
        <c:when test="${not empty errors}">
            <div class="error-container" style="height: 100px; overflow-y: auto;">
               <c:forEach var="error" items="${errors}">
                    <div class="alert alert-danger" role="alert">
                        ${error}
                    </div>
                </c:forEach>
            </div>
            <c:remove var="errors" scope="session"/>
        </c:when>
    </c:choose>

    <h2>Changing Password</h2>

    <form action="changePassword" method="post">
        <!-- Current Password -->
        <div class="form-group">
            <label for="current-password">Current Password:</label>
            <input type="password" class="form-control" id="current-password" name="current-password" required>
        </div>

        <!-- New Password -->
        <div class="form-group">
            <label for="new-password">New Password:</label>
            <input type="password" class="form-control" id="new-password" name="new-password" required>
        </div>

        <!-- Repeat New Password -->
        <div class="form-group">
            <label for="repeat-new-password">Repeat New Password:</label>
            <input type="password" class="form-control" id="repeat-new-password" name="repeat-new-password" required>
        </div>

        <button type="submit" class="btn btn-primary">Change Password</button>
    </form>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</div>
