
<%
    	String username= "";
    	Cookie[] cookies = request.getCookies();
   if(cookies!= null){
    for(Cookie c : cookies)
    {
    	if(c.getName().equals("username"))
    	{
    		 username = c.getValue();
    	}
   	} 
   }
%>




<nav class="nav border-bottom">
  <a class="nav-link">Benvenuto <span class="username-menu"><%=username %> </span> </a>
  <a class="nav-link" href="/logout">Logout</a>
</nav>