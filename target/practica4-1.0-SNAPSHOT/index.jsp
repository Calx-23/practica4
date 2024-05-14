<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Post"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    List<Post> posts = (List<Post>) request.getAttribute("posts");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Blog de Tecnología</h1>

        <div style="text-align: right;">
            <p><a href="inicio?action=add">Nueva entrada</a></p>
        </div>
        <div class="post-list">
            <c:forEach var="item" items="${posts}">
                <div class="post">
                    <h2>${item.titulo}</h2>
                    <p><strong>Fecha:</strong> ${item.fecha}</p>
                    <p>${item.contenido}</p>
                    <div class="buttons">
                        <a href="inicio?action=edit&id=${item.id}">Editar</a>
                        <a href="inicio?action=delete&id=${item.id}" onclick="return(confirm('¿Está seguro?'))">Eliminar</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
