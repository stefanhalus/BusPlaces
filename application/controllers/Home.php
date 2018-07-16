<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Home extends CI_Controller {

  public function __construct() {
    parent::__construct();
    $this->load->model('SearchModel');
    $this->load->helper(['url','form']);
    $this->load->library('session');
    $this->load->library('form_validation');
  }

  public function index() {
    $data = [
        'title' => 'Bine ați venit!'
    ];
    $data['stations'] = $this->SearchModel->getStations();
    $this->load->library('template');
    $this->template->load('home', null, $data);
  }

}
