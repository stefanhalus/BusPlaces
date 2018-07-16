<?php

if (!defined('BASEPATH'))
  exit('No direct script access allowed');

class ClientModel extends CI_Model {

  function __construct() {
    parent::__construct();
    $this->load->database();
  }

  public function saveNewUser() {
    $firstName = $this->security->xss_clean($this->input->post('firstName'));
    $lastName = $this->security->xss_clean($this->input->post('lastName'));
    $email = $this->security->xss_clean($this->input->post('email'));
    $phone = $this->security->xss_clean($this->input->post('phone'));
    $password = $this->security->xss_clean($this->input->post('password'));
    $sql = "INSERT INTO `client` (`firstName`, `lastName`, `phone`, `email`, `pass`) "
            . "VALUES ('$firstName', '$lastName', '$phone', '$email', '$password');";
    $this->db->query($sql);
  }

  public function cancelReservation() {
    $id = $this->uri->segment('3');
    $sql = "UPDATE `reservation` SET `status_id`= '2' WHERE `id`='$id';";
    $this->db->query($sql);
  }

  public function saveReservation() {
    $client_id = $_SESSION['userId'];
    $route_id = $this->security->xss_clean($this->input->post('selectedRoute'));
    $departure_id = $this->security->xss_clean($this->input->post('departure'));
    $arrival_id = $this->security->xss_clean($this->input->post('arrival'));
    $date = $this->security->xss_clean($this->input->post('checkin'));
    $places = $this->security->xss_clean($this->input->post('places'));
//    $distance = $this->security->xss_clean($this->input->post(''));
//    $price = $this->security->xss_clean($this->input->post(''));
//    $status_id = $this->security->xss_clean($this->input->post(''));
    $distance = 0;
    $price = 0;
    $status_id = 1;
    $sql = "INSERT INTO `reservation` (`client_id`, `route_id`, `departure_id`, `arrival_id`, `date`, `places`, `distance`, `price`, `status_id`) "
            . "VALUES ('$client_id', '$route_id', '$departure_id', '$arrival_id', '$date', '$places', '$distance', '$price', '$status_id');";
    $this->db->query($sql);
  }

  public function clientExists() {
    $findUser = $this->input->post('findUser');
    $sql = "SELECT `id`, `firstName`, `lastName` "
            . "FROM `client` "
            . "WHERE `phone`='$findUser' OR `email`='$findUser' "
            . "ORDER BY `phone`, `email`";
    $query = $this->db->query($sql);
    return $query->result_array();
  }

  public function listReservations($type = 'active') {
    if ($type == 'active') {
      $sql = "SELECT * FROM `v_reservations` "
              . "WHERE `client_id`= " . $_SESSION['userId'] . " AND `status_id` = '1' AND DATE(`date`) >= DATE_SUB(NOW(),INTERVAL 1 DAY)"
              . "ORDER BY `date` DESC";
    } else {
      $sql = "SELECT * FROM `v_reservations` "
              . "WHERE `client_id`='" . $_SESSION['userId'] . "' AND `status_id` != '1' OR `client_id`='" . $_SESSION['userId'] . "' AND DATE(`date`) < CURDATE() "
              . "ORDER BY `date` DESC ";
    }
    $query = $this->db->query($sql);
    return $query->result_array();
  }

}
