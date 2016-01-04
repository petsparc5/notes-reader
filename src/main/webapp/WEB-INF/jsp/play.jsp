<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Peter's Awesome Notes</title>
        <link rel="stylesheet" type="text/css" href="{cp}/resources/css/site.css" />
        <script src="${cp}/resources/js/js.js"></script>
    </head>
    <body>
        <h4>Peter's notes:</h4>
        <c:forEach var="note" items="${notes}">
          ${note.subject}: ${note.data}<br>
        </c:forEach>
        <br>
        <c:forEach var="note" items="${springNotes}">
          ${note.subject}: ${note.data}<br>
        </c:forEach>
    </body>
</html>