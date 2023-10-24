
<%--
  Created by IntelliJ IDEA.
  User: mnachit
  Date: 2023/10/16
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">


    <title>GatherGrid</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        body {
            margin-top: 20px;
            background: #f8f8f8
        }



    </style>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
</head>

<body>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<!-- Navbar -->
<jsp:include page="../util/nav.jsp" />
<!-- Main -->
<main id="main" class="flexbox-col">
    <div class="col">
        <div class="e-tabs mb-3 px-3">
            <ul class="nav nav-tabs">
                <form action="" method="get">
                    <li class="nav-item"><a class="nav-link active" href="#">
                        <div class="input-group rounded">
                            <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search"
                                   aria-describedby="search-addon" name="search"/>
                            <button type="submit" class="btn btn-black bg-black" style="margin-left: 10px"><i class="bi bi-binoculars"></i></button>
                        </div>
                    </a></li>
                </form>

            </ul>
        </div>

        <div class="row flex-lg-nowrap">
            <div class="col mb-3">
                <div class="e-panel card">
                    <div class="card-body">
                        <div class="card-title">
                            <h6 class="mr-2"><span>Event Available</span><small class="px-1">....</small></h6>
                        </div>
                        <div class="e-table">
                            <div class="table-responsive table-lg mt-3">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th class="max-width">Date</th>
                                        <th class="sortable">Location</th>
                                        <th>Catégory</th>
                                        <th>Basic Price</th>
                                        <th>Regular Price</th>
                                        <th>Vip Price</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${events}" var="event">
                                        <tr>
                                            <td class="align-middle text-center">
                                                    ${event.event.name}
                                            </td>
                                            <td class="text-nowrap align-middle">${event.event.date}</td>
                                            <td class="text-nowrap align-middle"><span><c:out value="${ event.event.location}"></c:out></span></td>
                                            <td class="text-center align-middle">
                                                <div class="btn-group align-top">
                                                        ${event.event.categorie.name}
                                                </div>
                                            </td>
                                            <td class="text-center align-middle">
                                                <div class="btn-group align-top">
                                                        ${event.event.basic_price} $
                                                </div>
                                            </td>
                                            <td class="text-center align-middle">
                                                <div class="btn-group align-top">
                                                        ${event.event.regular_price} $
                                                </div>
                                            </td>
                                            <td class="text-center align-middle">
                                                <div class="btn-group align-top">
                                                        ${event.event.vip_price} $
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
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
