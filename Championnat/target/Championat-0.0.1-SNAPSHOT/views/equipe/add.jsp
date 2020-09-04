<jsp:include page="../header.jsp"></jsp:include>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">

<h1>Nouvelle Equipe</h1>


<form method="POST" action='<c:url value="/equipe/addsave" />' >

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
    <input value="" name="nom" type="text" class="form-control" id="inputAddress" placeholder="Nom Equipe" required>

     
       </div>
  </div>
<div class="form-group row">
    <label for="inputAddress" class="col-sm-2 col-form-label">Description</label>
    <div class="col-sm-10">
    <textarea rows="3" name="description" class="form-control" id="inputAddress" placeholder="Description de l'Equipe" required></textarea>

     
       </div>
  </div>
  
  <div class="form-group row">
    <label for="inputEquipe" class="col-sm-2 col-form-label">Championnat Jouer</label>
    <div class="col-sm-10">
  	<select name="championnat" class="form-control" for="inputEquipe" required>
  	
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