<jsp:include page="../header.jsp"></jsp:include>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">

<h1>Simprimer  Equipe : ${equipe.nom }</h1>

<form method="POST" action='<c:url value="/equipe/deleteequipe" />' >

<input type="hidden" name="id" value="${equipe.id }" />
 <button type="submit"  name="valider" class="btn btn-danger">Supprimer</button>
 <a href='<c:url value="/equipe/liste" />'>< Annuler</a>
 
</form>
 
 


 
</div>



<jsp:include page="../footer.jsp"></jsp:include>
</body>

</html>