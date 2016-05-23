<%@ include file="headerfooter/header.jsp" %>

<h2>Peter's notes:</h2>
</br>
<c:set var="subjectToDisplay" value="${fn:replace(allNotesBySubject[0].subject, '_', ' ')}" />
<h4>Subject: <span class="aqua">${subjectToDisplay}</span></h4>
<ul>
<c:forEach var="note" items="${allNotesBySubject}">
  <li>
  <span class="aqua">${note.data}</span>
  </li>
</c:forEach>
</ul>

<%@ include file="headerfooter/footer.jsp" %>