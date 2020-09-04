<jsp:include page="../header.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">


    <p style="margin-top:1em;">
    <h2 style="text-align:center;">${match.championnat.nom} <b>${match.equipe1.nom} </b>vs <b>${match.equipe2.nom}</b>
    </h2>
    <c:if test="${error==1 }">
        <div class="alert alert-danger" role="alert">
            Erreur d'enregistrement du but
        </div>
    </c:if>


    <c:if test="${ok!=null }">
        <div class="alert alert-success" role="alert">
            Operation effectuee
        </div>
    </c:if>


    <!-- Button trigger modal -->


    </p>

    <p style="text-align:center;">


        <a class="btn btn-primary" href='<c:url value="/championnat/classement?id=${match.championnat.id}" />'> < Retour
            aux classements </a>

    </p>


    <table id="example2" class="table table-bordered table-striped dataTable dtr-inline" role="grid"
           aria-describedby="example1_info">
        <thead>
        <tr role="row">
            <th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending"
                aria-label="">${match.equipe1.nom}</th>
            <th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending"
                aria-label="">${match.equipe2.nom}</th>
        </tr>
        </thead>
        <tbody>


        <tr>
            <td>
                <table>

                    <tr>
                        <td></td>
                        <td>
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#exampleModal1">
                                + But
                            </button>
                            &nbsp;
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#exampleModal11">
                                + Carton
                            </button>
                        </td>
                        </td>
                    </tr>

                </table>
                <table>
                    <c:forEach var="list" items="${listeselectionequipe1}">
                        <tr>
                            <td>${list.nom} ${list.prenom}</td>

                        </tr>
                    </c:forEach>
                </table>
            </td>

            <td>
                <table>

                    <tr>
                        <td></td>
                        <td>
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#exampleModal2">
                                + But
                            </button>
                            &nbsp;
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#exampleModal22">
                                + Carton
                            </button>
                        </td>
                        </td>
                    </tr>

                </table>
                <table>
                    <c:forEach var="list" items="${listeselectionequipe2}">
                        <tr>
                            <td>${list.nom} ${list.prenom}</td>

                        </tr>
                    </c:forEach>
                </table>

            </td>
        </tr>


        </tbody>

    </table>


    <h2>Les buteurs</h2>

    <table id="example2" class="table table-bordered table-striped dataTable dtr-inline" role="grid"
           aria-describedby="example1_info">
        <thead>
        <tr role="row">
            <th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending"
                aria-label="">Minute
            </th>
            <th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending"
                aria-label="">Nom joueur
            </th>
            <th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending"
                aria-label="">Equipe
            </th>
            <th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending"
                aria-label="">Nombre
            </th>
            <th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending"
                aria-label="">Action
            </th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="list" items="${lesbuts}">
            <tr>
                <td><c:out value="${list.minute}"/></td>
                <td><c:out value="${list.joueur.nom} ${list.joueur.prenom}"/></td>
                <td><c:out value="${list.joueur.equipe.nom}"/></td>
                <td><c:out value="1"/></td>
                <td>
                    <a href='<c:url value="/match/deletebut?id=${list.id}" />'>Supprimer</a>
                </td>
            </tr>
        </c:forEach>

        </tbody>

    </table>


    <h2>Les cartons</h2>

    <table id="example2" class="table table-bordered table-striped dataTable dtr-inline" role="grid"
           aria-describedby="example1_info">
        <thead>
        <tr role="row">
            <th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending"
                aria-label="">Nom joueur
            </th>
            <th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending"
                aria-label="">Equipe
            </th>
            <th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending"
                aria-label="">Type carton
            </th>
            <th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending"
                aria-label="">Action
            </th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="list" items="${lescartons}">
            <tr>
                <td><c:out value="${list.joueur.nom} ${list.joueur.prenom}"/></td>
                <td><c:out value="${list.joueur.equipe.nom}"/></td>
                <td>

                    <c:if test="${list.type == true }">
                        Rouge
                    </c:if>

                    <c:if test="${list.type == false }">
                        Jaune
                    </c:if>

                </td>
                <td>
                    <a href='<c:url value="/match/deletecarton?id=${list.id}" />'>Supprimer</a>
                </td>
            </tr>
        </c:forEach>

        </tbody>

    </table>

</div>


<!-- Modal 1-->
<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">But Marque par <b>${match.equipe1.nom}</b></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="POST" action='<c:url value="/match/save-but" />'>
                    <input type="hidden" name="match" value="${match.id}">
                    <div class="form-group row">
                        <label for="inputEquipe" class="col-sm-2 col-form-label">Joueur</label>
                        <div class="col-sm-10">
                            <select name="joueur" class="form-control" for="inputEquipe" required>

                                <option></option>
                                <c:forEach var="item" items="${listeselectionequipe1}">
                                    <option value="${item.id}">${item.nom}</option>
                                </c:forEach>

                            </select>

                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="inputAddress" class="col-sm-2 col-form-label">Nombre But</label>
                        <div class="col-sm-10">
                            <input value="" name="nombrebut" min='0' max='1' type="number" class="form-control"
                                   id="inputAddress" placeholder="But" required>


                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="inputAddress" class="col-sm-2 col-form-label">Minute</label>
                        <div class="col-sm-10">
                            <input value="" name="minute" min='0' max='125' type="number" class="form-control"
                                   id="inputAddress" placeholder="Minute" required>


                        </div>
                    </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" name="envoyerequipe1" class="btn btn-primary">Save changes</button>
            </div>
        </div>
        </form>
    </div>
</div>


<!-- Modal 2-->
<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">But Marque par <b>${match.equipe2.nom}</b></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="POST" action='<c:url value="/match/save-but" />'>
                    <input type="hidden" name="match" value="${match.id}">
                    <div class="form-group row">
                        <label for="inputEquipe" class="col-sm-2 col-form-label">Joueur</label>
                        <div class="col-sm-10">
                            <select name="joueur" class="form-control" for="inputEquipe" required>

                                <option></option>
                                <c:forEach var="item" items="${listeselectionequipe2}">
                                    <option value="${item.id}">${item.nom}</option>
                                </c:forEach>

                            </select>

                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="inputAddress" class="col-sm-2 col-form-label">Nombre But</label>
                        <div class="col-sm-10">
                            <input value="" name="nombrebut" min='0' max='1' type="number" class="form-control"
                                   id="inputAddress" placeholder="But" required>


                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="inputAddress" class="col-sm-2 col-form-label">Minute</label>
                        <div class="col-sm-10">
                            <input value="" name="minute" min='0' max='125' type="number" class="form-control"
                                   id="inputAddress" placeholder="Minute" required>


                        </div>
                    </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" name="envoyerequipe1" class="btn btn-primary">Save changes</button>
            </div>
        </div>
        </form>
    </div>
</div>


<!-- Modal 11-->
<div class="modal fade" id="exampleModal11" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Carton pris par <b>${match.equipe1.nom}</b></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="POST" action='<c:url value="/match/save-carton" />'>
                    <input type="hidden" name="match" value="${match.id}">
                    <div class="form-group row">
                        <label for="inputEquipe" class="col-sm-2 col-form-label">Joueur</label>
                        <div class="col-sm-10">
                            <select name="joueur" class="form-control" for="inputEquipe" required>

                                <option></option>
                                <c:forEach var="item" items="${listeselectionequipe1}">
                                    <option value="${item.id}">${item.nom}</option>
                                </c:forEach>

                            </select>

                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="inputEquipe" class="col-sm-2 col-form-label">Type </label>
                        <div class="col-sm-10">
                            <select name="type" class="form-control" for="inputEquipe" required>

                                <option value="0">Jaune</option>
                                <option value="1">Rouge</option>

                            </select>

                        </div>
                    </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" name="envoyerequipe1" class="btn btn-primary">Save changes</button>
            </div>
        </div>
        </form>
    </div>
</div>


<!-- Modal 22-->
<div class="modal fade" id="exampleModal22" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Carton pris par <b>${match.equipe2.nom}</b></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="POST" action='<c:url value="/match/save-carton" />'>
                    <input type="hidden" name="match" value="${match.id}">
                    <div class="form-group row">
                        <label for="inputEquipe" class="col-sm-2 col-form-label">Joueur</label>
                        <div class="col-sm-10">
                            <select name="joueur" class="form-control" for="inputEquipe" required>

                                <option></option>
                                <c:forEach var="item" items="${listeselectionequipe2}">
                                    <option value="${item.id}">${item.nom}</option>
                                </c:forEach>

                            </select>

                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="inputEquipe" class="col-sm-2 col-form-label">Type </label>
                        <div class="col-sm-10">
                            <select name="type" class="form-control" for="inputEquipe" required>

                                <option value="0">Jaune</option>
                                <option value="1">Rouge</option>

                            </select>

                        </div>
                    </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" name="envoyerequipe1" class="btn btn-primary">Save changes</button>
            </div>
        </div>
        </form>
    </div>
</div>


<jsp:include page="../footer.jsp"></jsp:include>
</body>

</html>