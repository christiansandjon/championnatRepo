<jsp:include page="../header.jsp"></jsp:include>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">

<p style="text-align:center;margin-top:1em;">


<a class="btn btn-primary" href='<c:url value="/championnat/classement?id=${joueur.equipe.championnat.id}" />'> < Retour aux classements </a>

</p>

<h1>Modifier joueur : ${joueur.nom}</h1>


<form method="POST" action='<c:url value="/joueur/updatesave" />' >

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
    <input value="${joueur.nom}" name="nom" type="text" class="form-control" id="inputAddress" placeholder="Nom du Joueur" required>

        <input value="${joueur.id}" name="id" type="hidden" class="form-control" id="inputAddress" placeholder="Nom du Joueur" required>
     
       </div>
  </div>
  <div class="form-group row">
    <label for="inputAddress" class="col-sm-2 col-form-label">Premom</label>
    <div class="col-sm-10">
    <input value="${joueur.prenom}" name="prenom" type="text" class="form-control" id="inputAddress" placeholder="Prenom du Joueur" required>

     
       </div>
  </div>
    <div class="form-group row">
    <label for="inputAddress" class="col-sm-2 col-form-label">Age</label>
    <div class="col-sm-10">
    <input value="${joueur.age}" name="age" type="number" class="form-control" id="inputAddress" placeholder="Age du Joueur" required>

     
       </div>
  </div>
<div class="form-group row">
    <label for="inputEquipe" class="col-sm-2 col-form-label">Equipe du Joueur</label>
    <div class="col-sm-10">
  	<select name="equipe" class="form-control" for="inputEquipe" required>
  	
  		<option value="${joueur.equipe.id}">${joueur.equipe.nom}</option>
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