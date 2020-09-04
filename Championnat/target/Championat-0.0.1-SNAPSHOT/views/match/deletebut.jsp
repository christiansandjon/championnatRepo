<jsp:include page="../header.jsp"></jsp:include>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">

<h1>Simprimer  But=${but.id }</h1>

<form method="POST" action='<c:url value="/match/deletebutbut" />' >

<input type="hidden" name="id" value="${but.id }" />
 <button type="submit"  name="valider" class="btn btn-danger">Supprimer</button>
 <a href='<c:url value="/match/gestion-score?id=${but.match.id}" />'>< Annuler</a>
 
</form>
 
 


 
</div>



<jsp:include page="../footer.jsp"></jsp:include>
</body>

</html>