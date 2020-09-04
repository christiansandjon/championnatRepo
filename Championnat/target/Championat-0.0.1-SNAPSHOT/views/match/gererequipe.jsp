<jsp:include page="../header.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">


    <p style="margin-top:1em;">
    <h2 style="text-align:center;">${match.championnat.nom} <b>${match.equipe1.nom} </b>vs <b>${match.equipe2.nom}</b>
    </h2>
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
            l'une des equipes a moins de 5 joueurs
        </div>
    </c:if>

    <c:if test="${ok!=null }">
        <div class="alert alert-success" role="alert">
            Joueur enregistrer avec succes
        </div>
    </c:if>


    <p style="text-align:center;">


        <a class="btn btn-primary" href='<c:url value="/championnat/classement?id=${match.championnat.id}" />'> < Retour
            aux classements </a>

    </p>


    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal1">
        + Selection des joueurs
    </button>

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
                    <c:forEach var="list" items="${listeselectionequipe1}">
                        <tr>
                            <td>${list.nom} ${list.prenom}</td>
                        </tr>
                    </c:forEach>
                </table>
            </td>

            <td>
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


</div>


<!-- Modal -->
<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Selection des joueurs</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="POST" action='<c:url value="/match/savejoueur" />'>
                    <input type="hidden" name="match" value="${match.id}">
                    <h5> ${match.equipe1.nom}</h5>
                    <c:forEach var="list" items="${joueurs1}">
                    <div class="form-check">
                        <input class="form-check-input"
                        <c:forEach var="l" items="${listeselectionequipe1}">
                        <c:if test="${list.id==l.id }">
                               checked="checked"
                        </c:if>
                        </c:forEach>
                               name="lesjoueurs1" type="checkbox" value="${list.id}" id="equipe1${list.id}">
                        <label class="form-check-label" for="equipe1${list.id}">
                                ${list.nom} ${list.prenom}
                        </label>
                    </div>
                    </c:forEach>
                    <h5> ${match.equipe2.nom}</h5>

                    <c:forEach var="list" items="${joueurs2}">
                    <div class="form-check">
                        <input class="form-check-input"
                        <c:forEach var="l" items="${listeselectionequipe2}">
                        <c:if test="${list.id==l.id }">
                               checked="checked"
                        </c:if>
                        </c:forEach>

                               name="lesjoueurs2" type="checkbox" value="${list.id}" id="equipe2${list.id}">
                        <label class="form-check-label" for="equipe2${list.id}">
                                ${list.nom} ${list.prenom}
                        </label>
                    </div>
                    </c:forEach>

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