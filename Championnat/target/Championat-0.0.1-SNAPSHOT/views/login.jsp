<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

</head>
<body>
<h2 style="text-align:center;margin-top: 30px; font-size: 50px;">FIFA<span style="color: #FFE936">MAN</span><span
        style="color: #FF0F21">AGER</span> - LOGIN</h2>

<div class="content" style=" margin: 100px 50px;">
    <form method='POST' action='<c:url value="checklogin" />'>

        <div class="container">
            <c:if test="${error!=null }">
                <div class="alert alert-danger" role="alert">
                    Mauvais login ou mot de passe
                </div>
            </c:if>
            <div class="form-group row">
                <label for="staticLogin" class="col-sm-2 col-form-label">Login</label>
                <div class="col-sm-10">
                    <input type="text" name="login" class="form-control" id="staticLogin" placeholder="Login">
                </div>
            </div>
            <div class="form-group row">
                <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
                <div class="col-sm-10">
                    <input type="password" name="motpasse" class="form-control" id="inputPassword"
                           placeholder="Password">
                </div>
            </div>
            <div class="form-group row">
                <label for="inputPassword" class="col-sm-2 col-form-label">&nbsp;</label>
                <div class="col-sm-10">
                    <button type="submit" class="btn btn-primary" style="text-align:center;">Se connecter</button>
                    <a class="btn btn-success" href="<c:url value="/register" />"> S'inscrire</a>
                </div>
            </div>

        </div>
    </form>
</div>
</body>
</html>
    