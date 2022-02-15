<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Application Gestion Formation</title>
</head>
<body>
	<center>
		<h1>Gestion Formation</h1>
        <h2>
        	<a href="new">Ajouter un cours</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="list">Liste des cours</a>   	
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Liste des cours</h2></caption>
            <tr>
                <th>ID</th>
                <th>Intitulé</th>
                <th>Coeficient</th>
                <th>Durée</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="cours" items="${listCours}">
                <tr>
                    <td><c:out value="${cours.cours_id}" /></td>
                    <td><c:out value="${cours.intitule}" /></td>
                    <td><c:out value="${cours.coef}" /></td>
                    <td><c:out value="${cours.duree}" /></td>
                    <td>
                    	<a href="edit?id=<c:out value='${cours.cours_id}' />">Modifier</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="delete?id=<c:out value='${cours.cours_id}' />">Supprimer</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
