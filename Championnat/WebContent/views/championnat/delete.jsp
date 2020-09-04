<jsp:include page="../header.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content" style="text-align: center; margin: 100px 50px;">

    <h1>Voulez-vous suprimer le championnat : ${championnat.nom } ?</h1>

    <form method="POST" action='<c:url value="/championnat/deletechampionnat" />'>

        <input type="hidden" name="id" value="${championnat.id }"/>
        <a href='<c:url value="/championnat/liste" />'>Annuler</a>
        <button type="submit" name="valider" class="btn btn-danger">Supprimer</button>


    </form>

</div>


<jsp:include page="../footer.jsp"></jsp:include>
</body>

</html>