<jsp:include page="../header.jsp"></jsp:include>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">

<h1>Simprimer  Championat ID=${championat.id }</h1>

<form method="POST" action='<c:url value="/championat/deletechampionat" />' >

<input type="hidden" name="id" value="${championat.id }" />
 <button type="submit"  name="valider" class="btn btn-danger">Supprimer</button>
 <a href='<c:url value="/championat/liste" />'>< Annuler</a>
 
</form>
 
 


 
</div>



<jsp:include page="../footer.jsp"></jsp:include>
</body>

</html>