<jsp:include page="../header.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content" style="margin: 50px;">


    <p style="margin-top:1em;">
    <h2 style="text-align:center;"><b>${championnat.nom}</b></h2>
    <c:if test="${error==1 }">
        <div class="alert alert-danger" role="alert">
            Equipe 1 et Equipe 2 correspondent a la meme equipe
        </div>
    </c:if>

    <c:if test="${error==2 }">
        <div class="alert alert-danger" role="alert">
            Erreur lors de l'enregistrement
        </div>
    </c:if>

    <c:if test="${error==3 }">
        <div class="alert alert-danger" role="alert">
            Erreur lors de la suppression
        </div>
    </c:if>

    <c:if test="${ok!=null }">
        <div class="alert alert-success" role="alert">
            Modification reussie
        </div>
    </c:if>
    </p>

    <div style="display: inline-block">
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal2">
            + Nouvelle Equipe
        </button>

        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
            + Nouveau Match
        </button>

    </div>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-item nav-link" href='<c:url value="/championnat/classement2"/>'>Classement</a>
                <a class="nav-item nav-link active" href='<c:url value="/championnat/matchsattente" />'><strong>Matchs en attente</strong></a>
                <a class="nav-item nav-link" href='<c:url value="/championnat/matchstermines" />'>Matchs termines</a>
                <a class="nav-item nav-link" href='<c:url value="/championnat/statsjoueurs" />'>Stats des joueurs</a>
            </div>
        </div>
    </nav>


    <!-- Match en Attente -->
    <h2 style="text-align:center;">Matchs <b>En Attente</b></h2>
    <table id="example1" class="table table-bordered table-striped dataTable dtr-inline" role="grid"
           aria-describedby="example1_info">
        <thead>
        <tr role="row">
            <th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending"
                aria-label="">Date
            </th>
            <th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending"
                aria-label="">Equipe 1
            </th>
            <th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending"
                aria-label="">Equipe 2
            </th>
            <th class="sorting" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="">Action</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="list" items="${matchscours}">
            <tr>
                <td><c:out value="${list.jourheurematch}"/></td>
                <td><c:out value="${list.equipe1.nom}"/></td>
                <td><c:out value="${list.equipe2.nom}"/></td>
                <td><a href='<c:url value="/match/gerer-equipe?id=${list.id}" />'>Gestion des joueurs classes</a> &nbsp;
                    <a href='<c:url value="/match/terminer?id=${list.id}" />'>Terminer</a>&nbsp;<a
                            href='<c:url value="/match/delete?id=${list.id}" />'>Supprimer</a>
                <td>
            </tr>
        </c:forEach>


        </tbody>

    </table>


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Nouveau Match</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="POST" action='<c:url value="/match/addsave" />'>

                    <input type='hidden' name='championnat' value='${championnat.id}'/>
                    <div class="form-group row">
                        <label for="inputEquipe" class="col-sm-2 col-form-label">Equipe 1</label>
                        <div class="col-sm-10">
                            <select name="equipe1" class="form-control" for="inputEquipe" required>

                                <option></option>
                                <c:forEach var="item" items="${liste}">
                                    <option value="${item.id}">${item.nom}</option>
                                </c:forEach>

                            </select>

                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputEquipe" class="col-sm-2 col-form-label">Equipe 2</label>
                        <div class="col-sm-10">
                            <select name="equipe2" class="form-control" for="inputEquipe" required>

                                <option></option>
                                <c:forEach var="item" items="${liste}">
                                    <option value="${item.id}">${item.nom}</option>
                                </c:forEach>

                            </select>

                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="inputAddress" class="col-sm-2 col-form-label">Date</label>
                        <div class="col-sm-10">
                            <input value="" name="date" type="datetime-local" class="form-control" id="inputAddress"
                                   placeholder="Nom du championnat" required>


                        </div>
                    </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary">Save changes</button>
            </div>
        </div>
        </form>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Nouvelle Equipe</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="POST" action='<c:url value="/equipe/addsave" />'>

                    <input type='hidden' name='url' value='0'/>
                    <div class="form-group row">
                        <label for="inputAddress" class="col-sm-2 col-form-label">Nom</label>
                        <div class="col-sm-10">
                            <input value="" name="nom" type="text" class="form-control" id="inputAddress"
                                   placeholder="Nom Equipe" required>


                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="inputAddress" class="col-sm-2 col-form-label">Description</label>
                        <div class="col-sm-10">
                            <textarea rows="3" name="description" class="form-control" id="inputAddress"
                                      placeholder="Description de l'Equipe" required></textarea>


                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputEquipe" class="col-sm-2 col-form-label">Championnat Jouer</label>
                        <div class="col-sm-10">
                            <select name="championnat" class="form-control" for="inputEquipe" required>

                                <option value="${championnat.id}">${championnat.nom}</option>

                            </select>

                        </div>
                    </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary">Save changes</button>
            </div>
        </div>
        </form>
    </div>
</div>


<jsp:include page="../footer.jsp"></jsp:include>
</body>

</html>