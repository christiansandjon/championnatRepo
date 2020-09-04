<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

</head>
<body>

<h2 style="text-align:center;">FIFA<span style="color: darkgoldenrod">MAN</span><span style="color: firebrick">AGER</span> - S'inscrire</h2>

 <form method='POST' action='<c:url value="/registersave" />' >
 
 <div class="container">
 <c:if test="${error==1 }">
 <div class="alert alert-danger" role="alert">
Les deux mots de passe me correspondent pas
</div>
</c:if>

 <c:if test="${error==2 }">
 <div class="alert alert-danger" role="alert">
Erreur lors de l'enregistrement
</div>
</c:if>
  <div class="form-group row">
    <label for="staticLogin" class="col-sm-2 col-form-label">Nom</label>
    <div class="col-sm-10">
      <input type="text" name="nom"  class="form-control" id="staticLogin" placeholder="Nom">
    </div>
  </div>
    <div class="form-group row">
    <label for="staticLogin" class="col-sm-2 col-form-label">Prenom</label>
    <div class="col-sm-10">
      <input type="text" name="prenom"  class="form-control" id="staticLogin" placeholder="Prenom">
    </div>
  </div>
    <div class="form-group row">
    <label for="staticLogin" class="col-sm-2 col-form-label">Login</label>
    <div class="col-sm-10">
      <input type="text" name="login"  class="form-control" id="staticLogin" placeholder="Login">
    </div>
  </div>
  <div class="form-group row">
    <label for="inputPassword" class="col-sm-2 col-form-label">Mot de passe</label>
    <div class="col-sm-10">
      <input type="password" name="motpasse1" class="form-control" id="inputPassword" placeholder="Mot de passe">
    </div>
  </div>
    <div class="form-group row">
    <label for="inputPassword" class="col-sm-2 col-form-label">Confirmation mot de passe</label>
    <div class="col-sm-10">
      <input type="password" name="motpasse2" class="form-control" id="inputPassword" placeholder="Confirmation mot de passe">
    </div>
  </div>
   <div class="form-group row">
    <label for="inputPassword" class="col-sm-2 col-form-label">&nbsp;</label>
    <div class="col-sm-10">
   <button type="submit" class="btn btn-primary" style="text-align:center;">Inscription</button>
   <a class="btn btn-success" href="<c:url value="/login" />"> Se connecter</a>
         </div>
  </div>

  </div>


</form>

</body>
</html>
    