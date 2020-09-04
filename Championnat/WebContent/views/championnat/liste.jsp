<jsp:include page="../header.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content" style="margin: 50px;">

    <h1 style="text-align: center;">Liste des Championnats</h1>

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

    <table id="example1" class="table table-bordered table-striped dataTable dtr-inline" role="grid"
           aria-describedby="example1_info">
        <thead>
        <tr role="row">
            <th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending"
                aria-label="">Nom
            </th>
            <th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending"
                aria-label="">Description
            </th>
            <th class="sorting" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="">Action</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="list" items="${liste}">
            <tr>
                <td><c:out value="${list.nom}"/></td>
                <td><c:out value="${list.description}"/></td>
                <td>
                    <a href='<c:url value="/championnat/classement?id=${list.id}" />'>Classement</a>
                    <a href='<c:url value="/championnat/update?id=${list.id}" />'>Modifier</a>
                    <a href='<c:url value="/championnat/delete?id=${list.id}" />'>Supprimer</a>
                </td>
            </tr>
        </c:forEach>


        </tbody>

    </table>


</div>


<jsp:include page="../footer.jsp"></jsp:include>
</body>

</html>