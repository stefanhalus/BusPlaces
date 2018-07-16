<?php

$this->load->view("templates/header");
if (strlen($title) > 0) {
  echo '<h2 class="align-center">' . $title . '</h2>';
}
echo $body;
$this->load->view("templates/footer");

