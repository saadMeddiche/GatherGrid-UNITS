<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Edit event</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            margin-top: 20px;
            background: #f8f8f8
        }
    </style>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<jsp:include page="../util/taglibs.jsp"/>
<jsp:include page="../util/nav.jsp"/>
<body>
<main class="flexbox-col">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Edit Event</h5>
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
                                    <input type="hidden" name="edit_id" value="${event.id}">
                                    <div class="form-group">
                                        <label for="name">Name</label>
                                        <input class="form-control" type="text" name="name" id="name"
                                               placeholder="Name" value="${event.name}" required>
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-group">
                                        <label for="datetime">Date and Time</label>
                                        <input id="datetime" class="form-control" type="datetime-local"
                                               name="datetime" value="${event.date} ${event.time}" required>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="form-group">
                                        <label for="location">Location</label>
                                        <input id="location" name="location" class="form-control" type="text"
                                               placeholder="Location" value="${event.location}" required>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <label for="basic_price">Basic Price</label>
                                    <div class="input-group">
                                        <input id="basic_price" name="basic_price" type="number" min="1"
                                               step="any"
                                               placeholder="0.00" class="form-control" value="${event.basic_price}"
                                               required>
                                        <div class="input-group-append">
                                            <span class="input-group-text">$</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col">
                                    <label for="regular_price">Regular Price</label>
                                    <div class="input-group">
                                        <input id="regular_price" name="regular_price" type="number" min="1"
                                               placeholder="0.00" step="any" class="form-control"
                                               value="${event.regular_price}" required>
                                        <div class="input-group-append">
                                            <span class="input-group-text">$</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col">
                                    <label for="vip_price">VIP Price</label>
                                    <div class="input-group">
                                        <input id="vip_price" name="vip_price" type="number" min="1"
                                               placeholder="0.00" step="any" class="form-control"
                                               value="${event.vip_price}"
                                               required>
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
                                                        <option value="${category.id}"
                                                                <c:if test="${category.id == event.categorie.id}">selected</c:if>
                                                        >${category.name}</option>
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
                                                  placeholder="Description" required>${event.description}</textarea>
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
</main>

<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
