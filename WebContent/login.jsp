<!doctype html>
<html lang="en">
  <head>
    <%@include file="./parts/head.jsp" %>
	<title>Accedi a ClickEat</title>
  </head>
  <body>
      <div class="container center">

          <p class="text-center">
              <h3>Accedi a ClickEat</h3>
          </p>
          <form action="login" method="post">
              <div class="form-group">
                  <label for="username_login">username_login</label>
                  <input type="text" class="form-control" id="username_login" placeholder="Enter username_login">
              </div>
              <div class="form-group">
                  <label for="password_login">password_login</label>
                  <input type="password" class="form-control" id="password_login" placeholder="Enter password_login">
              </div>
              <button type="submit" class="btn btn-primary">Submit</button>
         </form>
    </div>
    </body>
</html>
