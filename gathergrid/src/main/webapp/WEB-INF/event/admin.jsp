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
</head>

<body>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<jsp:include page="../util/taglibs.jsp" />
<main id="main" class="flexbox-col">
<div class="col">
    <div class="e-tabs mb-3 px-3">
        <ul class="nav nav-tabs">
            <li class="nav-item"><a class="nav-link active" href="#">Evenements</a></li>
        </ul>
    </div>

    <div class="row flex-lg-nowrap">
        <div class="col mb-3">
            <div class="e-panel card">
                <div class="card-body">
                    <div class="card-title">
                        <h6 class="mr-2"><span>Evenements</span><small class="px-1">....</small></h6>
                    </div>
                    <div class="e-table">
                        <div class="table-responsive table-lg mt-3">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th class="align-top">
                                        <div
                                                class="custom-control custom-control-inline custom-checkbox custom-control-nameless m-0">
                                            <input type="checkbox" class="custom-control-input" id="all-items">
                                            <label class="custom-control-label" for="all-items"></label>
                                        </div>
                                    </th>
                                    <th>nom</th>
                                    <th class="max-width">date</th>
                                    <th class="sortable">lieu</th>
                                    <th>Catégorie</th>
                                    <th>description</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td class="align-middle">
                                        <div
                                                class="custom-control custom-control-inline custom-checkbox custom-control-nameless m-0 align-top">
                                            <input type="checkbox" class="custom-control-input" id="item-1">
                                            <label class="custom-control-label" for="item-1"></label>
                                        </div>
                                    </td>
                                    <td class="align-middle text-center">
                                        <div class="bg-light d-inline-flex justify-content-center align-items-center align-top"
                                             style="width: 35px; height: 35px; border-radius: 3px;"><i
                                                class="fa fa-fw fa-photo" style="opacity: 0.8;"></i></div>
                                    </td>
                                    <td class="text-nowrap align-middle">Adam Cotter</td>
                                    <td class="text-nowrap align-middle"><span>09 Dec 2017</span></td>
                                    <td class="text-center align-middle">
                                        <div class="btn-group align-top">
                                            hhhhhh
                                        </div>
                                    </td>
                                    <td class="text-center align-middle">
                                        <div class="btn-group align-top">
                                            <h1>jjj</h1>
                                        </div>
                                    </td>
                                    <td class="text-center align-middle">
                                        <div class="btn-group align-top">
                                            <button class="btn btn-sm btn-outline-secondary badge" type="button"
                                                    data-toggle="modal" data-target="#user-form-modal">Edit</button>
                                            <button class="btn btn-sm btn-outline-secondary badge"
                                                    type="button"><i class="fa fa-trash"></i></button>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <div class="card-title">
                        <h6 class="mr-2"><span>Commentaire</span><small class="px-1">....</small></h6>
                    </div>
                    <div class="e-table">
                        <div class="table-responsive table-lg mt-3">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th class="align-top">
                                        <div
                                                class="custom-control custom-control-inline custom-checkbox custom-control-nameless m-0">
                                            <input type="checkbox" class="custom-control-input" id="all-items">
                                            <label class="custom-control-label" for="all-items"></label>
                                        </div>
                                    </th>
                                    <th>Nom de User</th>
                                    <th>Nom de Evenements</th>
                                    <th>Commentaire </th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td class="align-middle">
                                        <div
                                                class="custom-control custom-control-inline custom-checkbox custom-control-nameless m-0 align-top">
                                            <input type="checkbox" class="custom-control-input" id="item-1">
                                            <label class="custom-control-label" for="item-1"></label>
                                        </div>
                                    </td>
                                    <td class="text-nowrap align-middle">Adam Cotter</td>
                                    <td class="text-nowrap align-middle"><span>ddd</span></td>
                                    <td class="text-center align-middle">
                                        <div class="btn-group align-top">
                                            hhhhhh
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-12 col-lg-3 mb-3">
            <div class="card">
                <div class="card-body">
                    <div class="text-center px-xl-3">
                        <button class="btn btn-success btn-block" type="button" data-toggle="modal"
                                data-target="#user-form-modal">New Evenements</button>
                    </div>
                    <hr class="my-3">
                    <hr class="my-3">
                    <div>
                        <div class="form-group">
                            <label>Date from - to:</label>
                            <div>
                                <input id="dates-range" class="form-control flatpickr-input"
                                       placeholder="01 Dec 17 - 27 Jan 18" type="text" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Search by Name:</label>
                            <div><input class="form-control w-100" type="text" placeholder="Name" value></div>
                        </div>
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
                    <h5 class="modal-title">Create Evenements</h5>
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="py-1">
                        <form class="form" novalidate>
                            <div class="row">
                                <div class="col">
                                    <div class="row">
                                        <div class="col">
                                            <div class="form-group">
                                                <label>nom</label>
                                                <input class="form-control" type="text" name="name"
                                                       placeholder="nom" value="">
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="form-group">
                                                <label>date</label>
                                                <input class="form-control" type="date" name="date"
                                                       placeholder="date" value="">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <div class="form-group">
                                                <label>lieu</label>
                                                <input class="form-control" type="lieu" placeholder="lieu">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <div class="form-group">
                                                <label for="category">Catégorie</label>
                                                <select id="category" class="form-control">
                                                    <option value="1">Catégorie 1</option>
                                                    <option value="2">Catégorie 2</option>
                                                    <option value="3">Catégorie 3</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="form-group">
                                                <label>description</label>
                                                <textarea class="form-control" rows="5" name="description"
                                                          placeholder="description"></textarea>
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
