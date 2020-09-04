<jsp:include page="../header.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content" style="text-align: center; margin: 100px 50px;">

    <h1 style="text-align: center;">Nouveau Championnat</h1>


    <form method="POST" action='<c:url value="/championnat/addsave" />' style="margin: 50px;">

        <c:if test="${error!=null }">
            <div class="alert alert-danger" role="alert">
                Erreur survenue lors de l'enregistrement
            </div>
        </c:if>

        <c:if test="${ok!=null }">
            <div class="alert alert-success" role="alert">
                Enregistrement Ok
            </div>
        </c:if>
        <div class="form-group row">
            <label for="inputAddress" class="col-sm-2 col-form-label">Nom</label>
            <div class="col-sm-10">
                <input value="" name="nom" type="text" class="form-control" id="inputAddress"
                       placeholder="Nom du championnat" required>


            </div>
        </div>
        <div class="form-group row">
            <label for="inputAddress" class="col-sm-2 col-form-label">Description</label>
            <div class="col-sm-10">
                <textarea rows="3" name="description" class="form-control" id="inputAddress"
                          placeholder="Description du championnat" required></textarea>


            </div>
        </div>

        <a href='<c:url value="/championnat/liste" />'>Annuler</a>
        <button type="submit" name="valider" class="btn btn-primary">Valider</button>


    </form>

</div>


</div>


<jsp:include page="../footer.jsp"></jsp:include>
</body>

</html>