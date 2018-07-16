<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Company extends CI_Controller {

  public function __construct() {
    parent::__construct();
    $this->load->helper('url');
    $this->load->model('ClientModel');
    $this->load->library('template');
    $this->load->library('email');
  }

  public function index() {
    $data = [
        'title' => 'Despre companie'
    ];
    $this->template->load('pagefull', 'company', $data);
  }

  public function contact() {
    $config['protocol'] = 'smtp';
    $config['smtp_host'] = 'stefanhalus.ro';
    $config['smtp_user'] = 'busplaces@stefanhalus.ro';
    $config['smtp_pass'] = 'busPlaces';
    $config['smtp_port'] = 465;
    $config['smtp_keepalive'] = TRUE;
    $config['smtp_timeout'] = 15;
    $config['smtp_crypto'] = 'ssl';
//    $config['mailtype'] = 'html';
//    $config['validate'] = 'FALSE';
//    $config['priority'] = '2';
    $config['charset'] = 'utf-8';
    $config['wordwrap'] = TRUE;
    $this->email->initialize($config);
    $this->email->set_mailtype("html");
    $this->email->set_newline("\r\n");
    $this->email->from('busplaces@stefanhalus.ro', 'BusPlaces');
    $this->email->to('office@stefanhalus.ro');
    $this->email->cc($this->input->post('email'));
    $this->email->subject('[Client] ' . $this->input->post('contact_subject'));
    $this->email->message('<p>Bună ziua,</p><p>Un mesaj a fost expediat de ' . $this->input->post('contact_name') . '</p><p>' . $this->input->post('contact_message') . '</p><p></p><p></p><p></p>');
    $this->email->send();
    redirect('/home');
    $data = [
        'title' => 'Trimitem mesajul dumneavoastră',
        'body' => ''
    ];
    $this->load->library('template');
    $this->template->load('pagefull', null, $data);
  }

  public function routes() {
    $data = [
        'title' => 'Despre companie',
        'subtitle' => 'Curse regulate',
        'artimage' => 'bus02.jpg',
        'description' => '<p>Punem la dispoziția călătorilor mai multe curse regulate. </p>
<p>Puteți face rezervări ale locurilor pentru călătoriile dumneavoastră pentru a vă asigura că veți călători confortabil. </p>
<p>Sunt bineveniți la bord și pasagerii care nu fac rezervări, însă în perioadele aglomerate, putem fi puși în situația de a refuza călători fără rezervări.  </p>
<p>Nu vă costă nimic, așadar o rezervare reprezintă o asigurare. </p>
<p>Vă așteptăm, cu drag, la bord!</p>'
    ];
    $this->load->library('template');
    $this->template->load('pagefull', 'companydetails', $data);
  }

  public function travelers() {
    $data = [
        'title' => 'Despre companie',
        'subtitle' => 'Curse la comandă',
        'artimage' => 'travelers03.jpg',
        'description' => '<p>Aveți un grup cu care doriți să călătoriți? Nicio problemă! Vă ajutăm cu plăcere. </p>
<p>Putem discuta cerințele dumneavoastră și vă oferim autocare și șoferi profesioniști și manierați.  </p>
<p>Ruta, opririle și dotările sun importante și le putem adapta în așa fel încât să aveți cel mai convenabil preț. </p>'
    ];
    $this->load->library('template');
    $this->template->load('pagefull', 'companydetails', $data);
  }

  public function lugage() {
    $data = [
        'title' => 'Despre companie',
        'subtitle' => 'Transport pachete/bagaje',
        'artimage' => 'lugage02.jpg',
        'description' => '<p>Ești student și aștepți pachet? Noi ți-l aducem cu drag! </p>
<p>Aveți nevoie să trimiteți ceva unei rude sau unui prieten? Contați pe serviciile noastre.  </p>
<p>Transportăm în siguranță și rapiditate, pachetele dumneavoastră către destinatar.  </p>
<p>Vă așteptăm!</p>
'
    ];
    $this->load->library('template');
    $this->template->load('pagefull', 'companydetails', $data);
  }

}
