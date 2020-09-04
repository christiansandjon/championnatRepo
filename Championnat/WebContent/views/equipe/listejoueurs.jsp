<jsp:include page="../header.jsp"></jsp:include>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">

<h1>${nombrejoueur} Joueurs de ${equipe.nom}</h1>

 <c:if test="${error!=null }">
 <div class="alert alert-danger" role="alert">
Erreur survenue lors de l'operation
</div>
</c:if>

 <c:if test="${ok!=null }">
 <div class="alert alert-success" role="alert">
Modification reussie
</div>
</c:if>


<p style="text-align:center;">


<a class="btn btn-primary" href='<c:url value="/championnat/classement?id=${equipe.championnat.id}" />'> < Retour aux classements </a>

</p>

<p style="margin-top:1em;">
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
  + Ajouter Joueur
</button>
</p>



<table id="example1" class="table table-bordered table-striped dataTable dtr-inline" role="grid" aria-describedby="example1_info">
                  <thead>
                  <tr role="row"><th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending" aria-label="">Nom</th><th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending" aria-label="">Prenom</th><th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending" aria-label="">Age</th><th class="sorting" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="">Action</th></tr>
                  </thead>
                  <tbody>
                 
                  <c:forEach var="list" items="${listejoueurs}">
                   <tr>
                   <td> <c:out value="${list.nom}" /></td> <td> <c:out value="${list.prenom}" /></td>
                   <td> <c:out value="${list.age}" /></td>
                        <td>  

     <a href='<c:url value="/joueur/update?id=${list.id}" />'>Modifier</a>
     <a href='<c:url value="/joueur/delete?id=${list.id}" />'>Supprimer</a>               
    </td>
                  </tr>
                  </c:forEach>
                  
                  
                  </tbody>
                  
 </table>


</div>



<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Nouveau Joueur ${equipe.nom}</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
<form method="POST" action='<c:url value="/joueur/addsave" />' >
    <input type="hidden" name='urlretour' value='0'>
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
  	
  		<option value="${equipe.id}">${equipe.nom}</option>
  	
  	</select>
     
       </div>
  </div>
  
 
  
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary"
         <c:if test="${nombrejoueur > 7 }">
         desabled='desabled'
			</c:if>
        >Save changes</button>
      </div>
    </div>
      </form>
  </div>
</div>


<jsp:include page="../footer.jsp"></jsp:include>
</body>

</html>