<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mnachit
  Date: 2023/10/16
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">


    <title>bs4 crud users - Bootdey.com</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        body {
            margin-top: 20px;
            background: #f8f8f8
        }
    </style>
    <jsp:include page="../util/taglibs.jsp"/>
</head>

<body>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<jsp:include page="../util/nav.jsp"/>
<main id="main" class="flexbox-col">
    <div class="container">
        <div class="row flex-lg-nowrap">
            <div class="col-12 col-lg-auto mb-3" style="width: 200px;">
            </div>
        </div>
    </div>
    <div class="col">
        <div class="row flex-lg-nowrap">
            <div class="col mb-3">
                <div class="e-panel card">
                    <div class="card-header text-center">
                        <h6 class="mr-2 "><span class="text-success">Event : </span><small
                                class="px-1 text-dark">${event.name}</small></h6>
                    </div>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col" class="text-danger">Category</th>
                                <th scope="col" class="text-danger">Date</th>
                                <th scope="col" class="text-danger">Time</th>
                                <th scope="col" class="text-danger">Location</th>
                                <%--                                <th scope="col">Description</th>--%>
                                <th scope="col" class="text-danger">Basic Price</th>
                                <th scope="col" class="text-danger">Regular Price</th>
                                <th scope="col" class="text-danger">Vip Price</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td class="text-primary"> ${event.categorie.name}</td>
                                <td class="text-primary"> ${event.date}</td>
                                <td class="text-primary"> ${event.time}</td>
                                <td class="text-primary"> ${event.location}</td>
                                <%--                                <td> ${event.description}</td>--%>
                                <td class="text-primary"> ${event.basic_price} $</td>
                                <td class="text-primary"> ${event.regular_price} $</td>
                                <td class="text-primary"> ${event.vip_price} $</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="description card">
                        <div class="card-header">
                            <h5 class="mb-0 h3 text-danger">Description</h5>
                        </div>
                        <div class="card-body text-primary">
                            <p>${event.description}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <form action="submitTicket/${event.id}" method="post">
            <div class="text-center mb-3"><div class="container">
                <div class="row">
                    <div class="col-md-4 offset-md-4">
                        <input min="1" max="4" type="number" name="input" id="typeNumber" class="form-control text-center" value="1"/>
                    </div>
                </div>
            </div>
                <button type="submit" class="mt-2 btn btn-success">Get Ticket</button>
            </div>
        </form>
        <div class="row justify-content-center">
            <div class="col">
                <div class="card shadow-0 border" style="background-color: #f0f2f5;">
                    <c:forEach items="${event.comments}" var="comment">
                        <div class="card-body p-2">
                            <div class="card mb-2">
                                <div class="card-body">
                                    <p>${comment.text}</p>
                                    <div class="d-flex justify-content-between">
                                        <div class="d-flex flex-row align-items-center">
                                            <img src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/img%20(4).webp"
                                                 alt="avatar" width="25" height="25"/>
                                            <p class="small mb-0 ms-2" style="margin-left: 0.2cm;">
                                                    ${comment.user.name.firstName}   ${comment.user.name.lastName}  At ${comment.date} </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <form action="" method="post">
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="Add a comment" name="text"
                                   required>
                            <input type="hidden" name="event_id" value="${event.id}">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </div>
    </div>
</main>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">

</script>
</body>

</html>
