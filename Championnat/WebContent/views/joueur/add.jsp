<jsp:include page="../header.jsp"></jsp:include>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">

<h1>Nouveau Joueur</h1>


<form method="POST" action='<c:url value="/joueur/addsave" />' >

 <c:if test="${error==1 }">
 <div class="alert alert-danger" role="alert">
Erreur survenue lors de l'enregistrement
</div>
</c:if>

 <c:if test="${error==2 }">
 <div class="alert alert-danger" role="alert">
Nombre de joueur dans cette equipe depassse
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
    <input value="" name="nom" type="text" class="form-control" id="inputAddress" placeholder="Nom du Joueur" required>

     
       </div>
  </div>
  <div class="form-group row">
    <label for="inputAddress" class="col-sm-2 col-form-label">Premom</label>
    <div class="col-sm-10">
    <input value="" name="prenom" type="text" class="form-control" id="inputAddress" placeholder="Prenom du Joueur" required>

     
       </div>
  </div>
    <div class="form-group row">
    <label for="inputAddress" class="col-sm-2 col-form-label">Age</label>
    <div class="col-sm-10">
    <input value="" name="age" type="number" class="form-control" id="inputAddress" placeholder="Age du Joueur" required>

     
       </div>
  </div>
<div class="form-group row">
    <label for="inputEquipe" class="col-sm-2 col-form-label">Equipe du Joueur</label>
    <div class="col-sm-10">
  	<select name="equipe" class="form-control" for="inputEquipe" required>
  	
  		<option></option>
  		<c:forEach var="item" items="${liste}">
  		<option value="${item.id}">${item.nom}</option>
  		</c:forEach>
  	
  	</select>
     
       </div>
  </div>

  <button type="submit"  name="valider" class="btn btn-primary">Valider</button>
 

</form>

</div>


</div>



<jsp:include page="../footer.jsp"></jsp:include>
</body>

</html>