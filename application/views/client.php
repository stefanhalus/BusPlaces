<h3><?php echo $_SESSION['firstName'] . " " . $_SESSION['lastName']; ?></h3>
<h4>Rezervări active</h4>
<table class="fullWidth">
  <tr>
    <th>Crt</th>
    <th>Ruta</th>
    <th>De la</th>
    <th>Până la</th>
    <th>Data</th>
    <th>Locuri</th>
    <th>Distanță</th>
    <th>Preț</th>
    <th>Total</th>
    <th>Stare</th>
    <th>Acțiune</th>
  </tr>
  <?php $i = 0; foreach($model->listReservations() as $reservation) { ?>
    <tr>
        <td><?php echo ++$i; ?></td>
        <td><?php echo $reservation['route_name']; ?></td>
        <td><?php echo $reservation['departure_city'] . ' - ' . $reservation['departure_name']; ?></td>
        <td><?php echo $reservation['arrival_city'] . ' - ' . $reservation['arrival_name']; ?></td>
        <td><?php echo $reservation['date']; ?></td>
        <td><?php echo $reservation['places']; ?></td>
        <td><?php echo $reservation['distance']; ?></td>
        <td><?php echo $reservation['price'];?></td>
        <td><?php echo $reservation['distance'] * $reservation['price'];?></td>
        <td><?php echo $reservation['status_name'];?></td>
        <td><button type="button" name="cancelReservation" onclick="location.href='/client/cancelreservation/<?php echo $reservation['id'];?>'">Anulez</button></td>
    </tr>
<?php } ?>
  
</table>
<p>&nbsp;</p>

<h4>Istoric rezervări</h4>
<table class="fullWidth">
  <tr>
    <th>Crt</th>
    <th>Ruta</th>
    <th>De la</th>
    <th>Până la</th>
    <th>Data</th>
    <th>Locuri</th>
    <th>Distanță</th>
    <th>Preț</th>
    <th>Total</th>
    <th>Stare</th>
  </tr>
  <?php $i = 0; foreach($model->listReservations('history') as $reservation) { ?>
    <tr>
        <td><?php echo ++$i; ?></td>
        <td><?php echo $reservation['route_name']; ?></td>
        <td><?php echo $reservation['departure_city'] . ' - ' . $reservation['departure_name']; ?></td>
        <td><?php echo $reservation['arrival_city'] . ' - ' . $reservation['arrival_name']; ?></td>
        <td><?php echo $reservation['date']; ?></td>
        <td><?php echo $reservation['places']; ?></td>
        <td><?php echo $reservation['distance']; ?></td>
        <td><?php echo $reservation['price'];?></td>
        <td><?php echo $reservation['distance'] * $reservation['price'];?></td>
        <td><?php echo $reservation['status_name'];?></td>
    </tr>
<?php } ?>
</table>

<p>&nbsp;</p>
<button id="userLogOut" name="userLogOut" class="btn btn-primary tm-btn tm-btn-search text-uppercase" onClick="location.href='/client/doLogOut'">Deconectează-te</button>
<p>&nbsp;</p>
