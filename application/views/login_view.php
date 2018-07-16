<div id='login_form' class='container'>
  <div class="row tm-banner-row" id="tm-section-search">
    <form action='/client/doLogIn' method='post' name='process'>
      <div class="form-row tm-search-form-row">


        <div class="form-group tm-form-group tm-form-group-pad tm-form-group-1">
          <label for='username'>Username</label>
          <input type='text' name='username' id='username'  class="form-control" placeholder="cineva@ceva.ro">
        </div>

        <div class="form-group tm-form-group tm-form-group-pad tm-form-group-2">
          <label for='password'>Password</label>
          <input type='password' name='password' id='password'  class="form-control" placeholder="secret">                         
        </div>

        <div class="form-group tm-form-group tm-form-group-pad tm-form-group-2">
          <button type="submit" class="btn btn-primary tm-btn tm-btn-search text-uppercase" id="btnSubmit">Autentifică-te</button>
          <button type="button" class="btn btn-primary tm-btn tm-btn-search text-uppercase" onClick="location.href='/client/register'">Crează cont nou</button>
        </div>

      </div>
      
      
      <div class="form-row tm-search-form-row">
        <p>&nbsp;</p>
      </div>
      
    </form>
  </div>
</div>