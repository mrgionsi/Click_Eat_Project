<!doctype html>
<html lang="en">
  <head>
  
    <%@include file="./parts/head.jsp" %>
	<title>Accedi a ClickEat</title>
  </head>
  <body>
      <div class="container-fluid">
		<div class="row text-center">
          <span class="col-12 text-center h3">
              Accedi a ClickEat
          </span>
         </div>
        <div class="row">
          <form action="login" method="post" class="col-12">
           
              <div class="form-group col-3 mx-auto">
                  <label for="username_login">username_login</label>
                  <input type="text" class="form-control col-12" id="username_login" placeholder="Enter username_login">
              </div>
               <div class="form-group col-3 mx-auto"> 
                  <label for="password_login">password_login</label>
                  <input type="password" class="form-control col-12" id="password_login" placeholder="Enter password_login">
              </div> 
               <div class="row">
              	<div class="col-2 mx-auto">
              <button type="submit" class="btn btn-lg btn-primary btn-login">Submit</button>
              </div>
              </div>
         </form>
         </div>
         </div>
    </body>
</html>
