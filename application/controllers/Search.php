<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Search extends CI_Controller {

  public function __construct() {
    parent::__construct();
    $this->load->model('SearchModel');
    $this->load->helper(['url', 'form']);
    $this->load->library('session');
    $this->load->library('form_validation');
    $this->load->library('template');
    $this->load->library('searchutils');
    $this->load->library('Authentification');
  }

  public function index() {
    $data = [
        'title' => '',
        'body' => ''
    ];
    $data['stations'] = $this->SearchModel->getStations();
//    $this->load->library('template');
    $this->template->load('pagefull', 'search', $data);
  }

  public function places() {
    $data = [
        'title' => 'Rezultate căutare',
        'body' => '',
        'model' => $this->SearchModel, 
        'SearchUtils' => $this->searchutils, 
        'Authentification' => $this->authentification
    ];
    $this->template->load('pagefull', 'search_results', $data);
  } 

}
