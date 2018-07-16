<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class SearchUtils {

  private $ci;

  public function __construct() {
    $this->ci = & get_instance();
    $this->ci->load->model('SearchModel');
    $this->ci->load->library('form_validation');
  }

  public function routesList() {
    $ret = '';
    $departureId = $this->ci->input->post('departure');
    $arrivalId = $this->ci->input->post('arrival');
    $stops = $this->ci->SearchModel->getStops($departureId, $arrivalId);
    $departureCity = $this->ci->SearchModel->getStationByID($departureId);
    $arrivalCity = $this->ci->SearchModel->getStationByID($arrivalId);
//    $routes = $this->ci->SearchModel->getRouteListById($stops[0]['route_id']);
    switch (count($stops)) {
      case 0:
        $ret .= '<p>Regretăm, nu am găsit nici o cursă care să oprească în stația ' . $departureCity->city_name . ' - ' . $departureCity->name . ' sau în stația ' . $arrivalCity->city_name . ' - ' . $arrivalCity->name . '.</p>';
        break;
      case 1:
        $ret .= $this->routeOne();
        break;
      default :
        $ret .= '<h4>Curse disponibile:</h4>';
        $ret .= $this->routeTwo();
    }
    return $ret;
  }

  private function routeOne() {
    $ret = '';
    $departureId = $this->ci->input->post('departure');
    $arrivalId = $this->ci->input->post('arrival');
    $stops = $this->ci->SearchModel->getStops($departureId, $arrivalId);
    $stationDetails = $this->ci->SearchModel->getStationByID($stops[0]['station_id']);
    $departureCity = $this->ci->SearchModel->getStationByID($departureId);
    $arrivalCity = $this->ci->SearchModel->getStationByID($arrivalId);
    $routes = $this->ci->SearchModel->getRouteListById($stops[0]['route_id']);
    if ($stops[0]['station_id'] != $departureId) {
      $ret .= '<p>În stația ' . $departureCity->city_name . ' - ' . $departureCity->name . ' nu opresc cursele noastre</p>';
    }
    echo '<p>În stația ' . $stationDetails->city_name . ' - ' . $stationDetails->name . ' opresc următoarele curse:</p>';
    if (count($routes) > 0) {
      $ret .= '<ul>';
      foreach ($routes as $route) {
        $ret .= '<li>' . $route['name'] . '</li>';
      }
      $ret .= '</ul>';
    }
    if ($stops[0]['station_id'] != $arrivalId) {
      $ret .= '<p>În stația ' . $arrivalCity->city_name . ' - ' . $arrivalCity->name . ' nu opresc cursele noastre</p>';
    }
    return $ret;
  }

  private function routeTwo() {
    $departureId = $this->ci->input->post('departure');
    $arrivalId = $this->ci->input->post('arrival');
    $routes = $this->ci->SearchModel->getStopsDistinct($departureId, $arrivalId);
    $ret = '';
    foreach ($routes as $route) {
      $routeData = $this->ci->SearchModel->getRouteById($route['route_id']);
      $routeStops = $this->ci->SearchModel->getRouteStops($route['route_id'], $departureId, $arrivalId);
      $ret .= '<p><input type="radio" name="selectRoute" value="' . $route['route_id'] . '"> ' . $routeData['name'] . '<br>' . $routeStops[0]['station_name'] . ' (' . $routeStops[0]['departure'] . ') &rarr; ' . $routeStops[1]['station_name'] . ' (' . $routeStops[1]['arrival'] . ')</p>';
    }
//    $ret .= '<p><button type="submit" class="btn btn-primary tm-btn tm-btn-search text-uppercase" id="btnSubmit">Rezerv acum</button></p>';
//    $ret .= '</form>';
    return $ret;
  }

}
