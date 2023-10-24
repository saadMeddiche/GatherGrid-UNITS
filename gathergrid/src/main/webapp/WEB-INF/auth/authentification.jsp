<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet" />
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet" />
    <!-- MDB -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.2/mdb.min.css" rel="stylesheet" />
    <!-- MDB -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.2/mdb.min.js"></script>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@taglib prefix="d" uri="http://java.sun.com/jsp/jstl/fmt" %>
</head>

<body>
<div class="container p-5 " style="width: 60%;border: black solid 1px;border-radius: 3px" >

    <c:choose>
        <c:when test="${successCreationAccount}">
                <div class="alert alert-success" role="alert">
                    Account Created Successfully
                </div>
        </c:when>
    </c:choose>

    <div class="error-container" style="height: 100px; overflow-y: auto;">
        <c:choose>
            <c:when test="${not empty errors}">
                <c:forEach var="error" items="${errors}">
                    <div class="alert alert-danger" role="alert">
                        ${error}
                    </div>
                </c:forEach>
                <c:remove var="errors" scope="session"/>

            </c:when>
        </c:choose>
    </div>
    <!-- Pills navs -->
    <ul class="nav nav-pills nav-justified " id="ex1" role="tablist">
        <li class="nav-item" role="presentation">
            <a class="nav-link active" id="tab-login" data-mdb-toggle="pill" href="#pills-login" role="tab"
               aria-controls="pills-login" aria-selected="true">Login</a>
        </li>
        <li class="nav-item" role="presentation">
            <a class="nav-link" id="tab-register" data-mdb-toggle="pill" href="#pills-register" role="tab"
               aria-controls="pills-register" aria-selected="false">Register</a>
        </li>
    </ul>
    <!-- Pills navs -->

    <!-- Pills content -->
    <div class="tab-content">
        <div class="tab-pane fade show active" id="pills-login" role="tabpanel" aria-labelledby="tab-login">
            <form action="signIn" method="post">
                <!-- Email input -->
                <div class="form-outline mb-4">
                    <input type="email" id="loginName" name="email" class="form-control" value="${userOnLogin.email.addressEmail}"/>
                    <label class="form-label" for="loginName">Email</label>
                </div>
                <!-- Password input -->
                <div class="form-outline mb-4">
                    <input type="password" id="loginPassword" class="form-control" name="password"/>
                    <label class="form-label" for="loginPassword">Password</label>
                </div>
                <!-- Submit button -->
                <button type="submit" class="btn btn-primary btn-block mb-4">Sign in</button>
                <!-- Register buttons -->
                <div class="text-center">
                    <p>Not a member? <a href="#!">Register</a></p>
                </div>
            </form>
        </div>
        <div class="tab-pane fade" id="pills-register" role="tabpanel" aria-labelledby="tab-register">
            <form method="post" action="signUp">
                <!-- Name input -->
                <div class="form-outline mb-4">
                    <input type="text"  class="form-control" name="firstName" value="${userOnRegistration.name.firstName}"/>
                    <label class="form-label" >Firstname</label>
                </div>
                <div class="form-outline mb-4">
                    <input type="text"  class="form-control" name="lastName" value="${userOnRegistration.name.lastName}"/>
                    <label class="form-label" >Lastname</label>
                </div>
                <div class="form-outline mb-4">
                    <input type="text"  class="form-control" name="userName" value="${userOnRegistration.name.userName}"/>
                    <label class="form-label" >UserName</label>
                </div>
                <!-- Email input -->
                <div class="form-outline mb-4">
                    <input type="text" id="registerEmail" class="form-control" name="email" value="${userOnRegistration.email.addressEmail}"/>
                    <label class="form-label" for="registerEmail">Email</label>
                </div>
                <!-- Password input -->
                <div class="form-outline mb-4">
                    <input type="text" id="registerPassword" class="form-control" name="password" />
                    <label class="form-label" for="registerPassword">Password</label>
                </div>
                <!-- Submit button -->
                <button type="submit" class="btn btn-primary btn-block mb-3">Sign in</button>
            </form>
        </div>
    </div>
    <!-- Pills content -->
</div>
</body>

</html>