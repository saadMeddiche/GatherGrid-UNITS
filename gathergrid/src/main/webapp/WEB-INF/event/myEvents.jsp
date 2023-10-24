<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">


    <title>bs4 crud users - Bootdey.com</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            margin-top: 20px;
            background: #f8f8f8
        }
    </style>
</head>

<body>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<jsp:include page="../util/taglibs.jsp"/>
<jsp:include page="../util/nav.jsp"/>
<main id="main" class="flexbox-col">
    <c:choose>
        <%--@elvariable id="success" type="java.lang.String"--%>
        <c:when test="${not empty success}">
            <div class="alert alert-success" role="alert">
                ${success}
            </div>
            <c:remove var="success" scope="session"/>
        </c:when>

        <%--@elvariable id="error" type="java.lang.String"--%>
        <c:when test="${not empty error}">
            <div class="error-container" style="height: 100px; overflow-y: auto;">
                <div class="alert alert-danger" role="alert">
                        ${error}
                </div>
            </div>
            <c:remove var="error" scope="session"/>
        </c:when>
    </c:choose>
    <div class="col">
        <div class="row flex-lg-nowrap mt-5">
            <div class="col mb-3">
                <div class="e-panel card">
                    <div class="card-body">
                        <div class="card-title">
                            <h6 class="mr-2">My Events</h6>
                        </div>
                        <div class="e-table">
                            <div class="table-responsive table-lg mt-3">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Description</th>
                                        <th>Location</th>
                                        <th class="max-width">Date and Time</th>
                                        <th>Category</th>
                                        <th>Basic price</th>
                                        <th>Regular price</th>
                                        <th>VIP price</th>
                                        <th>Action</th>
                                    </tr>
                                    </thead>
                                    <tr>

                                    </tr>
                                    <tbody>
                                    <%--@elvariable id="events" type="java.util.List"--%>
                                    <c:forEach items="${events}" var="event">
                                        <tr>
                                            <td class="text-nowrap align-middle"><span>${event.name}</span></td>
                                            <td class="text-nowrap align-middle"><span>${event.description}</span>
                                            </td>
                                            <td class="text-center align-middle"><span>${event.location}</span></td>
                                            <td class="text-nowrap align-middle">
                                                <span>${event.date} ${event.time} </span>
                                            </td>
                                            <td class="text-center align-middle"><span>${event.categorie.name}</span>
                                            </td>
                                            <td class="text-center align-middle"><span>${event.basic_price} $</span>
                                            </td>
                                            <td class="text-center align-middle"><span>${event.regular_price} $</span>
                                            </td>
                                            <td class="text-center align-middle"><span>${event.vip_price} $</span></td>
                                            <td class="text-center align-middle">
                                                <div class="btn-group align-top">
                                                    <form action="<c:url value=""/>" method="POST">
                                                        <input type="hidden" name="edit_id" value="${event.id}"/>
                                                        <button class="btn btn-sm btn-outline-secondary badge"
                                                                type="submit"><i class="fa fa-edit"></i></button>
                                                    </form>
                                                    <form action="<c:url value=""/>" method="POST">
                                                        <input type="hidden" name="delete_id" value="${event.id}"/>
                                                        <button class="btn btn-sm btn-outline-secondary badge"
                                                                type="submit"><i class="fa fa-trash"></i></button>
                                                    </form>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <li class="page-item <%--@elvariable id="pageNumber" type="int"--%>
                                <c:if test="${pageNumber == 1}">disabled</c:if>">
                                    <a class="page-link" href="?page=${pageNumber - 1}">&laquo;</a>
                                </li>
                                <%--@elvariable id="totalPages" type="int"--%>
                                <c:forEach var="i" begin="1" end="${totalPages}">
                                    <li class="page-item <c:if test="${pageNumber == i}">active</c:if>">
                                        <a class="page-link" href="?page=${i}"><c:out value="${i}"/></a>
                                    </li>
                                </c:forEach>
                                <li class="page-item <c:if test="${pageNumber == totalPages}">disabled</c:if>">
                                    <a class="page-link" href="?page=${pageNumber + 1}">&raquo;</a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="col-12 col-lg-3 mb-3">
                <div class="card">
                    <div class="card-body">
                        <div class="text-center px-xl-3">
                            <button class="btn btn-success btn-block" type="button" data-toggle="modal"
                                    data-target="#user-form-modal">New Event
                            </button>
                        </div>
                        <hr class="my-3">
                        <div>
                            <form action="" method="get">
                                <div class="input-group rounded">
                                    <input type="search" class="form-control rounded" placeholder="Search"
                                           aria-label="Search"
                                           aria-describedby="search-addon" name="search"/>
                                    <button type="submit" class="btn btn-black bg-black" style="margin-left: 10px"><i
                                            class="bi bi-binoculars"></i></button>
                                </div>
                            </form>
                        </div>
                        <hr class="my-3">
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" role="dialog" tabindex="-1" id="user-form-modal">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Create Event</h5>
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form class="form" method="post">
                            <div class="row">
                                <div class="col">
                                    <div class="row">
                                        <div class="col">
                                            <div class="form-group">
                                                <label for="name">Name</label>
                                                <input class="form-control" type="text" name="name" id="name"
                                                       placeholder="Name" required>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="form-group">
                                                <label for="datetime">Date and Time</label>
                                                <input id="datetime" class="form-control" type="datetime-local"
                                                       name="datetime" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <div class="form-group">
                                                <label for="location">Location</label>
                                                <input id="location" name="location" class="form-control" type="text"
                                                       placeholder="Location" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <label for="basic_price">Basic Price</label>
                                            <div class="input-group">
                                                <input id="basic_price" name="basic_price" type="number" min="1"
                                                       step="any"
                                                       placeholder="0.00" class="form-control" required>
                                                <div class="input-group-append">
                                                    <span class="input-group-text">$</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <label for="regular_price">Regular Price</label>
                                            <div class="input-group">
                                                <input id="regular_price" name="regular_price" type="number" min="1"
                                                       placeholder="0.00" step="any" class="form-control" required>
                                                <div class="input-group-append">
                                                    <span class="input-group-text">$</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <label for="vip_price">VIP Price</label>
                                            <div class="input-group">
                                                <input id="vip_price" name="vip_price" type="number" min="1"
                                                       placeholder="0.00" step="any" class="form-control" required>
                                                <div class="input-group-append">
                                                    <span class="input-group-text">$</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <div class="form-group">
                                                <label for="category">Category</label>
                                                <select name="category" id="category" class="form-control" required>
                                                    <c:choose>
                                                        <%--@elvariable id="categories" type="java.util.List"--%>
                                                        <c:when test="${not empty categories}">
                                                            <c:forEach items="${categories}" var="category">
                                                                <option value="${category.id}">${category.name}</option>
                                                            </c:forEach>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="">No categories</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="form-group">
                                                <label for="description">Description</label>
                                                <textarea id="description" class="form-control" rows="5"
                                                          name="description"
                                                          placeholder="Description" required></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col d-flex justify-content-end">
                                    <button class="btn btn-primary" type="submit">Save Changes</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>