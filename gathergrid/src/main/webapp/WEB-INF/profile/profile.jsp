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
        <c:when test="${successUpdationAccount}">
            <div class="message-container" style="height: 100px; overflow-y: auto;">

                <div class="alert alert-success" role="alert">
                    Account Updated Successfully
                </div>

            </div>

            <c:remove var="successUpdationAccount" scope="session"/>

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
    <h1>Edit Profile</h1>
    <form action="profile" method="post">
        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" class="form-control" id="firstName" name="firstName" value="<c:out value='${user.name.firstName}' />" placeholder="Enter your first name">
        </div>
        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" class ="form-control" id="lastName"  name="lastName" value="<c:out value='${user.name.lastName}' />" placeholder="Enter your last name">
        </div>
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="userName" value="<c:out value='${user.name.userName}' />" placeholder="Enter your username">
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" value="<c:out value='${user.email.addressEmail}' />" placeholder="Enter your email">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password">
        </div>
        <button type="submit" class="btn btn-primary">Save Changes</button>
    </form>

    <form action="changePassword" method="get">
        <div style="height: 100px;margin-top:20px">
            <button type="submit" class="btn btn-primary" >Change Password</button>
        </div>
    </form>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
