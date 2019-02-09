<%@include file="./GetCookies.jsp"%>
<nav class="nav border-bottom">
  <% if(role!=null && username!=null){ %>
  <a class="nav-link">Benvenuto <span class="username-menu"><%=username %> | Sei un <%=role %> </span> </a>
 <a class="nav-link" href="ServletLogout">Logout</a>
 <% }else{%>
 <a class="nav-link" href="/login.jsp">Accedi</a>
 <% }%>
 
</nav>