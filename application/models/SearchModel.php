<?php

class SearchModel extends CI_Model {

  public function __construct() {
    parent::__construct();
    $this->load->database();
  }

  public function getStations() {
    $sql = 'SELECT `station`.*, `city`.`name` AS `city_name` FROM `station` INNER JOIN `city` ON `station`.`city_id`= `city`.`id` ORDER BY `city_name`, `name`';
    $query = $this->db->query($sql);
    return $query->result_array();
  }

  public function getStationById($id) {
    $sql = 'SELECT `station`.*, `city`.`name` AS `city_name` FROM `station` INNER JOIN `city` ON `station`.`city_id`= `city`.`id` WHERE `station`.`id` = "' . $id . '"';
    $query = $this->db->query($sql);
    return $query->row();
  }
  
  public function getStops($departureId, $arrivalId) {
    $sql = "SELECT * FROM `stops` WHERE `station_id`='$departureId' OR `station_id`='$arrivalId'";
    $query = $this->db->query($sql);
    return $query->result_array();
  }
  
  public function getStopsDistinct($departureId, $arrivalId) {
    $sql = "SELECT * FROM `stops` WHERE `station_id`='$departureId' OR `station_id`='$arrivalId' GROUP BY `route_id`";
    $query = $this->db->query($sql);
    return $query->result_array();
  }
  
  public function getRouteById($id) {
    $sql = "SELECT * FROM `route` WHERE `id`='$id' GROUP BY `name`";
    $query = $this->db->query($sql);
    return $query->row_array();
  }
  
  public function getRouteListById($id) {
    $sql = "SELECT * FROM `route` WHERE `id`='$id' GROUP BY `name`";
    $query = $this->db->query($sql);
    return $query->result_array();
  }
  
  public function getRouteStops($routeId, $departureId, $arrivalId) {
    $sql = "SELECT `stops`.*, `station`.`name` AS `station_name` "
            . "FROM `stops` "
            . "INNER JOIN `station` ON `stops`.`station_id` = `station`.`id` "
            . "WHERE `stops`.`route_id`='$routeId' AND `stops`.`station_id`='$departureId' OR `stops`.`station_id`='$arrivalId' "
            . "ORDER BY `order`";
    $query = $this->db->query($sql);
    return $query->result_array();
  }

}
