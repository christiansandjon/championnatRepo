<jsp:include page="../header.jsp"></jsp:include>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">

<p style="text-align:center; margin-top:1em;">


<a class="btn btn-primary" href='<c:url value="/championnat/classement?id=${joueur.equipe.championnat.id}" />'> < Retour aux classements </a>

</p>

<h1>Simprimer  Joueur : ${joueur.nom }</h1>

<form method="POST" action='<c:url value="/joueur/deletejoueur" />' >

<input type="hidden" name="id" value="${joueur.id }" />
 <button type="submit"  name="valider" class="btn btn-danger">Supprimer</button>
 <a href='<c:url value="/championnat/classement?id=${joueur.equipe.championnat.id}" />'>< Annuler</a>
 
</form>
 
 


 
</div>



<jsp:include page="../footer.jsp"></jsp:include>
</body>

</html>