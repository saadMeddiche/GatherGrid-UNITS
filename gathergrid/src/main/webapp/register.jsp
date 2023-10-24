<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>User Registration</title>
</head>
<body>

<h2>User Registration</h2>

<form action="register" method="post">
    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" ><br>

    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" ><br>

    <label for="userName">User Name:</label>
    <input type="text" id="userName" name="userName" ><br>

    <label for="email">Email:</label>
    <input type="text" id="email" name="email" ><br>

    <label for="password">Password:</label>
    <input type="text" id="password" name="password" ><br>

    <input type="submit" value="Register">
</form>

</body>
</html>
