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
		<c:if test="${cours != null}">
			<form action="update" method="get">
        </c:if>
        <c:if test="${cours == null}">
			<form action="insert" method="get">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${cours != null}">
            			Editer un cours existant
            		</c:if>
            		<c:if test="${cours == null}">
            			Ajouter un nouveau cours
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${cours != null}">
        			<input type="hidden" name="id" value="<c:out value='${cours.cours_id}' />" />
        		</c:if>            
            <tr>
                <th>Intitulé : </th>
                <td>
                	<input type="text" name="intitule" size="45"
                			value="<c:out value='${cours.intitule}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Coéficient : </th>
                <td>
                	<input type="text" name="coef" size="45"
                			value="<c:out value='${cours.coef}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Durée : </th>
                <td>
                	<input type="text" name="duree" size="5"
                			value="<c:out value='${cours.duree}' />"
                	/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Enregistrer" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
