<div class="container">
  <?php
  $departureId = $this->input->post('departure');
  $arrivalId = $this->input->post('arrival');
  $stops = $model->getStops($departureId, $arrivalId);
  $departureCity = $model->getStationByID($departureId);
  $arrivalCity = $model->getStationByID($arrivalId);
  ?>

  <table name='results' class='fullWidth align-center'>
    <tr>
      <th class="align-center">Plecare</th>
      <th class="align-center">Sosire</th>
      <th class="align-center">Data</th>
      <th class="align-center">Locuri</th>
    </tr>
    <tr>
      <td><?php echo $departureCity->city_name . ' - ' . $departureCity->name; ?></td>
      <td><?php echo $arrivalCity->city_name . ' - ' . $arrivalCity->name; ?></td>
      <td class="align-center"><?php echo $this->input->post('checkin'); ?></td>
      <td class="align-center"><?php echo $this->input->post('places'); ?></td>
    </tr>
  </table>

  <p>&nbsp;</p>

  <div class="form-row tm-search-form-row">                                
    <div class="form-group tm-form-group tm-form-group-1">  
      <?php
      echo $SearchUtils->routesList();
      ?>
    </div>
    <div class="form-group tm-form-group tm-form-group-1">  
     <?php
      echo $Authentification->userAuthentificated();
      ?>
    </div>
  </div> <!-- form-row -->

  <p>&nbsp;</p>
</div>  <!-- end of container-->