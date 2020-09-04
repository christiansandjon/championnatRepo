<jsp:include page="../header.jsp"></jsp:include>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">

<h1>Simprimer  Match=${match.id }</h1>

<form method="POST" action='<c:url value="/match/deletematch" />' >

<input type="hidden" name="id" value="${match.id }" />
 <button type="submit"  name="valider" class="btn btn-danger">Supprimer</button>
 <a href='<c:url value="/championnat/classement?id=${match.championnat.id}" />'>< Annuler</a>
 
</form>
 
 


 
</div>



<jsp:include page="../footer.jsp"></jsp:include>
</body>

</html>