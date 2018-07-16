<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Authentification {

  private $ci;

  public function __construct() {
    $this->ci = & get_instance();
    $this->ci->load->library('form_validation');
    $this->ci->load->model('ClientModel');
    $this->ci->load->library('form_validation');
  }

  public function userAuthentificated() {
    $ret = '';
    if (isset($_SESSION['validated']) && $_SESSION['validated'] == true) {
      $ret .= $this->formDoReservation();
    } else {
      $ret .= $this->formFindUserAjax();
      $ret .= $this->formAuthentification();
      $ret .= '<div id="userRegister"></div>';
    }
    return $ret;
  }

  private function formDoReservation() {
    $departureId = $this->ci->input->post('departure');
    $arrivalId = $this->ci->input->post('arrival');
    $checkIn = $this->ci->input->post('checkin');
    $places = $this->ci->input->post('places');

    $ret = '<h4>Bun regăsit, ' . $_SESSION['firstName'] . '!</h4>';
    $ret .= '<p>Dacă e ok cursa bifată, suntem gata să rezervăm locurile!</p>';

    $ret .= '<form id="doReservation" action="/client/reservenow" method="post" class="tm-search-form tm-section-pad-1">';
    $ret .= '<div class="form-row tm-search-form-row"> ';
    $ret .= '<div class="form-group tm-form-group tm-form-group-pad tm-form-group-2">';
    $ret .= '<input type="hidden" name="departure" value="' . $departureId . '">';
    $ret .= '<input type="hidden" name="arrival" value="' . $arrivalId . '">';
    $ret .= '<input type="hidden" name="checkin" value="' . $checkIn . '">';
    $ret .= '<input type="hidden" name="places" value="' . $places . '">';
    $ret .= '<input type="hidden" name="selectedRoute" value="">';
    $ret .= '<button type="submit" class="btn btn-primary tm-btn tm-btn-search text-uppercase" id="btnDoReservation">Rezerv acum</button>';
    $ret .= '</div>';
    $ret .= '</div>';
    $ret .= '</form>';
    return $ret;
  }

  private function formFindUserAjax() {
    $ret = '';
    // user find
    $ret .= '<div class="form-row tm-search-form-row"> ';
    $ret .= '<div class="form-group tm-form-group tm-form-group-pad tm-form-group-2">';
    $ret .= '<label form="findUser">Email sau telefon</label>';
    $ret .= '<input type="text" id="findUser" name="findUser" placeholder="cineva@ceva.ro | 0701234567" autocomplete="off">';
    $ret .= '</div>';
    $ret .= '</div>';
    // user status
    $ret .= '<div class="form-row tm-search-form-row"> ';
    $ret .= '<div class="form-group tm-form-group tm-form-group-pad tm-form-group-2">';
    $ret .= '<p id="findUserStatus"></p>';
    $ret .= '</div>';
    $ret .= '</div>';
    return $ret;
  }

  private function formAuthentification() {
    $ret = '';
    $departureId = $this->ci->input->post('departure');
    $arrivalId = $this->ci->input->post('arrival');
    $checkIn = $this->ci->input->post('checkin');
    $places = $this->ci->input->post('places');
    $ret .= '<form id="userAuthentify" action="/client/validate" method="post">';
    $ret .= '<div class="form-row tm-search-form-row"> ';
    $ret .= '<div class="form-group tm-form-group tm-form-group-pad tm-form-group-2">';
    $ret .= '<input type="hidden" name="departure" value="' . $departureId . '">';
    $ret .= '<input type="hidden" name="arrival" value="' . $arrivalId . '">';
    $ret .= '<input type="hidden" name="check-in" value="' . $checkIn . '">';
    $ret .= '<input type="hidden" name="places" value="' . $places . '">';
    $ret .= '<input type="hidden" name="selectedRoute" value="">';
    $ret .= '<input type="hidden" name="userId" value="">';
    $ret .= '<label form="userPass">Parola ta</label>';
    $ret .= '<input type="password" id="userPass" name="userPass" placeholder="secret" autocomplete="off" autofocus="on">';
    $ret .= '<button type="button" id="userAuth" name="userAuth" class="btn btn-primary tm-btn tm-btn-search text-uppercase">Intră în cont!</button>';
    $ret .= '</div></div>';
    $ret .= '</form>';
    return $ret;
  }

}
