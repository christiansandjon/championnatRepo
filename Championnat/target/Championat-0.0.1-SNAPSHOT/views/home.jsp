<jsp:include page="header.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">

    <h1 style="text-align:center; margin: 30px;">Bienvenue sur FIFA<span style="color: #FFE936">MAN</span><span
            style="color: #FF0F21">AGER</span></h1>
</div>

</div>


<p style="text-align:center;margin-top:1em;">

    <a class="btn btn-primary" href='<c:url value="/championnat/liste" />'> Mes Championnats </a>

</p>

<p style="text-align:center;margin-top:1em;">

    <a class="btn btn-primary" href='<c:url value="/championnat/add" />'> + Nouveau Championnat </a>

</p>

<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>