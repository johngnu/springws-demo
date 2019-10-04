<%-- 
    Document   : update
    Created on : 02-10-2019, 07:35:18 PM
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
                        <a class="nav-link" href="<c:url value="/nuevo"/>">Nuevo <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#">Disabled</a>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="container">
            <!-- Content here -->

            <h1>Actualizar Libro</h1>
            <form method="post" action="<c:url value="/updatelibro"/>">
                <div class="row">
                    <div class="col-6">
                        <div class="form-group">
                            <label for="titulo">Título</label>
                            <input required value="${libro.titulo}" name="titulo" type="text" class="form-control" id="titulo" aria-describedby="tituloHelp" placeholder="Título del libro">
                            <small id="tituloHelp" class="form-text text-muted">Título de la obra.</small>
                        </div>
                        <div class="form-group">
                            <label for="fechaEdicion">Fecha</label>
                            <input required value="<fmt:formatDate value="${libro.fecha_edicion}" pattern="dd/MM/yyyy"/>" name="fecha_edicion" type="text" class="form-control datepicker" id="fechaEdicion" placeholder="dd/mm/aaaa">
                        </div>
                        <input type="hidden" name="id" value="${libro.id}">                        
                        <button type="reset" class="btn btn-default pull-right">Reset</button>
                        <button type="submit" class="btn btn-primary">Update</button>
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
                                        <td><input<c:if test="${a.selected}"> checked</c:if> value="${a.id}" name="ids" type="checkbox" class="form-check-input" id="autor-${a.id}"></td>
                                        </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#confirm-delete">Delete</button>
                    </div>       
                </div>
            </form>
        </div>

        <div id="confirm-delete" class="modal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Eliminar</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>¿Confirma eliminar este elemento?.</p>
                    </div>
                    <div class="modal-footer">
                        <a href="<c:url value="/eliminarlibro?id=${libro.id}"/>" class="btn btn-danger">Aceptar</a>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                    </div>
                </div>
            </div>
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

