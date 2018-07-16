<div class="row tm-banner-row" id="tm-section-search1">
  <?php
  $attributes = [
      'class' => 'tm-search-form tm-section-pad-2',
      'method' => 'post'
  ];
  echo form_open("search/places", $attributes);
  echo form_fieldset('Verificare locuri disponibile');
  echo '<div class="form-group tm-form-group tm-form-group-1">  ';
  echo form_label("Stație de plecare", "departure");
  $data = [
      'name' => 'departure',
      'id' => 'departure'
  ];
  $statii = [
      'Cluj-Napoca (Autogara Fany)', 
      'Cluj-Napoca (Observator)', 
      'Turda', 
      'Câmpia Turzii', 
      'Luduș (centru)', 
      'Luduș (Vulcanizare)', 
      'Iernut', 
      'Ungheni', 
      'Târgu Mureș (Kaufland)', 
      'Târgu Mureș (Poli 2)', 
      'Târgu Mureș (Fortuna)'
      ];
//echo form_input($data, "");
  echo form_dropdown($data, $statii);
  echo '</div>';

  echo '<div class="form-group tm-form-group tm-form-group-pad tm-form-group-1">';
  
  echo '<div class="form-group tm-form-group tm-form-group-pad tm-form-group-1">';
  form_label('Data călătoriei', 'inputCheckIn');
  $checkin = [
      'id' => 'inputCheckIn', 
      'class' => 'form-control', 
      'placeholder' => 'Dați click', 
      'name' => 'check-in'
  ];
  form_input($checkin);
  echo '</div>';
  
  echo '<div class="form-group tm-form-group tm-form-group-pad tm-form-group-1">';
  echo form_label('Locuri', 'inputPlacest');
  $places = [1,2,3,4,5,6,7,8,9,10];
  echo form_dropdown("inputPlacest", $places);
  echo '</div>';
  echo '</div>';



  echo '<div class="form-group tm-form-group tm-form-group-pad tm-form-group-3">    ';

  echo '</div>';




  echo form_close();
  ?>
</div>