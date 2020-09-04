<jsp:include page="header.jsp"></jsp:include>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">

<h1>Profil</h1>


<form method="POST" action='<c:url value="modifierprofil" />' >

 <c:if test="${error!=null }">
 <div class="alert alert-danger" role="alert">
Erreur survenue lors de la mise a jour
</div>
</c:if>

 <c:if test="${ok!=null }">
 <div class="alert alert-success" role="alert">
Mise a jour Ok
</div>
</c:if>
<div class="form-group row">
    <label for="inputAddress" class="col-sm-2 col-form-label">Login</label>
    <div class="col-sm-10">
    <input value="${user.login}" name="login" type="text" class="form-control" id="inputAddress" placeholder="Votre Login" required>

     
       </div>
  </div>
<div class="form-group row">
    <label for="inputAddress" class="col-sm-2 col-form-label">Prenom</label>
    <div class="col-sm-10">
    <input value="${user.nom}" name="nom" type="text" class="form-control" id="inputAddress" placeholder="Votre Nom" required>

     
       </div>
  </div>

  <div class="form-group row">
    <label for="inputAddress" class="col-sm-2 col-form-label">Nom</label>
    <div class="col-sm-10">
    <input value="${user.prenom}" name="prenom" type="text" class="form-control" id="inputAddress" placeholder="Votre Prenom" required>

     
       </div>
  </div>
  <button type="submit"  name="valider" class="btn btn-primary">Modifier</button>
 

</form>

</div>


</div>



<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>