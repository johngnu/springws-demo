<%-- 
    Document   : nuevo
    Created on : 02-10-2019, 04:50:18 PM
    Author     : John
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
        <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
        <title>Service client</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Menú</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="<c:url value="/index"/>">Home </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Nuevo <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#">Disabled</a>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="container">
            <!-- Content here -->

            <h1>Crear Libro</h1>
            <form method="post" action="<c:url value=""/>">
                <div class="row">
                    <div class="col-6">
                        <div class="form-group">
                            <label for="titulo">Título</label>
                            <input required name="titulo" type="text" class="form-control" id="titulo" aria-describedby="tituloHelp" placeholder="Título del libro">
                            <small id="tituloHelp" class="form-text text-muted">Título de la obra.</small>
                        </div>
                        <div class="form-group">
                            <label for="fechaEdicion">Fecha</label>
                            <input required name="fecha_edicion" type="text" class="form-control datepicker" id="fechaEdicion" placeholder="dd/mm/aaaa">
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                    <div class="col-6">
                        <h3>Lista de autores</h3>
                        <table class="table table-striped" id="myTable">
                            <thead>
                                <tr>
                                    <th scope="col">Nombre</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="a" items="${autores}" varStatus="i">
                                    <tr>
                                        <td><label for="autor-${a.id}">${a.nombre}</label></td>
                                        <td><input value="${a.id}" name="ids" type="checkbox" class="form-check-input" id="autor-${a.id}"></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </form>
        </div>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
        <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
        <script>
            $(document).ready(function () {
                $('.datepicker').datepicker({
                    uiLibrary: 'bootstrap4',
                    format: 'dd/mm/yyyy'
                });
            });
        </script>
    </body>
</html>

