<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Client extends CI_Controller {

  public function __construct() {
    parent::__construct();
    $this->load->helper('url');
    $this->load->model('ClientModel');
    $this->load->library('template');
    $this->load->library('session');
//    $this->check_isvalidated();
  }

  public function index() {
    $this->checkIsValidated();
    $data = [
        'title' => 'Clienții BusPlaces',
        'body'=>'', 
        'model'=>$this->ClientModel
    ];
    $this->template->load('pagefull', 'client', $data);
  }

  public function exists() {
    $userData = array();
    $dbrez = $this->ClientModel->clientExists();
    if (count($dbrez) == 1) {
      $_SESSION['userIdLogin'] = $dbrez[0]['id'];
      $userData['status'] = 'ok';
      $userData['result'] = $dbrez;
    } else {
      $userData['status'] = 'err';
      $userData['result'] = '';
    }
    $data = [
        'body' => json_encode($userData)
    ];
    $this->template->load('txt', null, $data);
  }

  public function login() {
    $data = [
        'title' => 'Autentificare client'
    ];
    $this->template->load('pagefull', 'login_view', $data);
  }

  public function register() {
    $data = [
        'title' => 'Creare cont client',
        'body' => ''
    ];
    $this->template->load('pagefull', 'register_view', $data);
  }

  public function cancelReservation() {
    $this->ClientModel->cancelReservation();
    $data = [
        'title' => 'Se crează noul cont',
        'body' => ''
    ];
    redirect('/client');
    $this->template->load('pagefull', null, $data);
  }
  
  public function saveRegistration() {
    $this->ClientModel->saveNewUser();
    $data = [
        'title' => 'Se crează noul cont',
        'body' => ''
    ];
    redirect('/client');
    $this->template->load('pagefull', null, $data);
  }

  private function checkIsValidated() {
    if (!$_SESSION['validated']) {
      redirect('/client/login');
    }
  }
  
  public function reservenow(){
    $this->ClientModel->saveReservation();
    $data = [
        'title' => 'Se salvează rezervarea dumneavoastră',
        'body' => ''
    ];
    redirect('/client');
    $this->template->load('pagefull', null, $data);
  }

  public function validate() {
    // grab user input
    $username = $this->security->xss_clean($_SESSION['userIdLogin']);
    $password = $this->security->xss_clean($this->input->post('userPass'));
    // Prep the query
    $this->db->where('id', $username);
    $this->db->where('pass', $password);
    // Run the query
    $query = $this->db->get('client');
    // Let's check if there are any results
    $row = $query->row();
    if ($row != null) {
      // If there is a user, then create session data
      $row = $query->row();
      $row->pass = 'null';
      $data = array( 'userId' => $row->id, 'firstName' => $row->firstName, 'lastName' => $row->lastName, 'phone' => $row->phone, 'email' => $row->email, 'validated' => true );
      $this->session->set_userdata($data);
      $userData['status'] = 'ok';
      $userData['result'] = $row;
      unset($_SESSION['userIdLogin']);
    } else {
      $userData['status'] = 'err';
      $userData['result'] = '';
    }
    // If the previous process did not validate
    // then return error.
    $data = [ 'title' => '', 'body' => json_encode($userData)];
    $this->template->load('txt', null, $data);
  }

  public function doLogIn() {
    $username = $this->security->xss_clean($this->input->post('username'));
    $password = $this->security->xss_clean($this->input->post('password'));
    $this->db->where('email', $username);
    $this->db->where('pass', $password);
    $query = $this->db->get('client');
    $row = $query->row();
    if ($row != null) {
      $userData = array('userId' => $row->id, 'firstName' => $row->firstName, 'lastName' => $row->lastName, 'phone' => $row->phone, 'email' => $row->email, 'validated' => true );
      $this->session->set_userdata($userData);
      $status = 'OK';
      redirect('/client');
    } else {
      $status = 'Eroare';
      redirect('/client');
    }
    $data = [ 'title' => 'Se autentifică &8223;', 'body' => '<br>user: '.$username.' , password: ' . $password . ', status: '.$status];
    $this->template->load('pagefull', null, $data);
  }

  public function doLogOut() {
    $data = [
        'title' => 'Se deconectează&8223;',
        'body' => ''
    ];
    $this->template->load('pagefull', null, $data);
    $this->session->sess_destroy();
    redirect('/home');
  }

}
