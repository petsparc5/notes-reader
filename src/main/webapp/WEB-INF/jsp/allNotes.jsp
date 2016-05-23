<%@ include file="headerfooter/header.jsp" %>

<h2>Peter's notes so far:</h4>
</br>
<table class="table table-striped table-bordered">
<tr>
  <th>Subject</th>
  <th>Data</th>
</tr>
<c:forEach var="note" items="${allNotes}">
  <tr>
    <td>
    <c:set var="subjectToDisplay" value="${fn:replace(note.subject, '_', ' ')}" />
    <a class="btn btn-info" href="/subject/${note.subject}" role="button">${subjectToDisplay}</a>
    </td><td>
    <span class="aqua">${note.data}</span>
    </td>
  </tr>
</c:forEach>
</table>

<%@ include file="headerfooter/footer.jsp" %>