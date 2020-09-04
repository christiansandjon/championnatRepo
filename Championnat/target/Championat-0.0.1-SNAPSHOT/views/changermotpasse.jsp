<jsp:include page="header.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">

    <h1>Profil</h1>


    <form method="POST" action='<c:url value="changermotpasseform" />'>


        <c:if test="${ok!=null }">
            <div class="alert alert-success" role="alert">
                Mise a jour Ok
            </div>
        </c:if>

        <c:if test="${error==1 }">
            <div class="alert alert-danger" role="alert">
                Ancien mot de passe ne correspond pas
            </div>
        </c:if>

        <c:if test="${error==2 }">
            <div class="alert alert-danger" role="alert">
                Les deux nouveaux mots de passe ne sont pas identiques
            </div>
        </c:if>

        <c:if test="${error==3 }">
            <div class="alert alert-danger" role="alert">
                Erreur Interne survenue
            </div>
        </c:if>

        <div class="form-group row">
            <label for="inputAddress" class="col-sm-2 col-form-label">Ancien mot de passe</label>
            <div class="col-sm-10">
                <input name="ancienmotpasse" type="password" class="form-control" id="inputAddress"
                       placeholder="Ancien mot de passe" required>


            </div>
        </div>
        <div class="form-group row">
            <label for="inputAddress" class="col-sm-2 col-form-label">Nouveau mot de passe</label>
            <div class="col-sm-10">
                <input name="nouveaumotpasse" type="password" class="form-control" id="inputAddress"
                       placeholder="Nouveau mot de passe" required>


            </div>
        </div>

        <div class="form-group row">
            <label for="inputAddress" class="col-sm-2 col-form-label">confirmer nouveau mot de passe</label>
            <div class="col-sm-10">
                <input name="renouveaumotpasse" type="password" class="form-control" id="inputAddress"
                       placeholder="Confirmation mot de passe" required>


            </div>
        </div>
        <button type="submit" name="valider" class="btn btn-primary">Modifier</button>


    </form>

</div>


</div>


<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>