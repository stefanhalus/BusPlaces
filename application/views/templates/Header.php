<!DOCTYPE html>
<html lang="ro">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>BusPlaces <?php echo $title; ?></title>
    
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>appearance/font-awesome-4.7.0/css/font-awesome.min.css">                <!-- Font Awesome -->
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>appearance/css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>appearance/css/jquery-ui.min.css">
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>appearance/css/jquery-ui.theme.min.css">
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>appearance/slick/slick.css">
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>appearance/slick/slick-theme.css">
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>appearance/css/templatemo-style.css">                                   <!-- Templatemo style -->
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>appearance/css/utils.css">                                   <!-- Templatemo style -->

  </head>

  <body>
    <div class="tm-main-content" id="top">


      <div class="tm-top-bar-bg"></div>    
      <div class="tm-top-bar" id="tm-top-bar">
        <div class="container">
          <div class="row">
            <?php
            $this->load->view("templates/menu");
            ?>
          </div> <!-- row -->
        </div> <!-- container -->
      </div> <!-- .tm-top-bar -->

      <div class="tm-page-wrap mx-auto">
