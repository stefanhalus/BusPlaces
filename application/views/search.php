<section class="tm-banner">
  <div class="tm-container-outer tm-banner-bg">
    <div class="container">

      <div class="row tm-banner-row tm-banner-row-header">
        <div class="col-xs-12">
          <div class="tm-banner-header">
            <h1 class="text-uppercase tm-banner-title">Unde vrei să mergi azi?</h1>
            <img src="<?php echo base_url(); ?>appearance/img/dots-3.png" alt="Dots">
            <p class="tm-banner-subtitle">Poți selecta ușor cursa dorită pentru a rezerva un loc</p>
            <a href="javascript:void(0)" class="tm-down-arrow-link"><i class="fa fa-2x fa-angle-down tm-down-arrow"></i></a>       
          </div>    
        </div>  <!-- col-xs-12 -->                      
      </div> <!-- row -->


      <div class="row tm-banner-row" id="tm-section-search">

        <form action="/search/places" method="post" class="tm-search-form tm-section-pad-2">
          <div class="form-row tm-search-form-row">                                
            <div class="form-group tm-form-group tm-form-group-1">                                    
              <label for="inputRoom">Stația de plecare</label>
              <select name="departure" class="form-control tm-select" id="inputDeparture">
                <option value=""> -  pecare - </option>
                <?php foreach ($stations as $station): ?>
                  <option value="<?php echo $station['id']; ?>"><?php echo $station['city_name'] . ' - ' . $station['name']; ?></option>
                <?php endforeach; ?>
              </select>  
            </div>
            <div class="form-group tm-form-group tm-form-group-pad tm-form-group-1">
              <div class="form-group tm-form-group tm-form-group-pad tm-form-group-1">
                <label for="inputCheckIn">Data călătoriei</label>
                <input name="checkin" type="text" data-date-format="dd.mm.yyyy" class="form-control tm-select" id="inputCheckIn" placeholder="Dați click" autocomplete="off">
              </div>
              <div class="form-group tm-form-group tm-form-group-pad tm-form-group-3">                                       
                <label for="inputPlacest">Locuri</label>     
                <select name="places" class="form-control tm-select" id="inputPlacest">
                  <option value=""> - ? - </option>
                  <option value="1">1</option>
                  <option value="2">2</option>
                  <option value="3">3</option>
                  <option value="4">4</option>
                  <option value="5">5</option>
                  <option value="6">6</option>
                  <option value="7">7</option>
                  <option value="8">8</option>
                  <option value="9">9</option>
                  <option value="10">10</option>
                </select>                                        
              </div>
            </div>
          </div> <!-- form-row -->

          <div class="form-row tm-search-form-row">
            <div class="form-group tm-form-group tm-form-group-pad tm-form-group-1">                                       
              <label for="inputAdult">Stația de sosire</label>     
              <select name="arrival" class="form-control tm-select" id="inputArrival">
                <option value=""> - sosire - </option>
                <?php foreach ($stations as $station): ?>
                <option value="<?php echo $station['id']; ?>"><?php echo $station['city_name'] . ' - ' . $station['name']; ?></option>
                <?php endforeach; ?>
              </select>                                        
            </div>
            <div class="form-group tm-form-group tm-form-group-pad tm-form-group-2">
              <label for="btnSubmit">&nbsp;</label>
              <button type="submit" class="btn btn-primary tm-btn tm-btn-search text-uppercase" id="btnSubmit">Verifică disponibilitatea</button>
            </div>
          </div>    <!-- form-row -->                           
        </form>                             
      </div> <!-- row -->

      <div class="tm-banner-overlay"></div>
    </div>  <!-- .container -->                   
  </div>     <!-- .tm-container-outer -->                 
</section>