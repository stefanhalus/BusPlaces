<?php

// dacă lipsește constanta BASEPATH, va fi afișat un mesaj de eroare
if (!defined('BASEPATH'))
  exit('No direct script access allowed');

class Template {

  // definim o variabilă în care să păstrăm instanța curentă
  var $ci;

  // în constructor citim instanța curentă și o salvăm în variabila $ci
  function __construct() {
    $this->ci = & get_instance();
  }

  // metoda load va încărca fișierul. 
  // Poartă acest nume pentru a respecta modul de încărcare e obiectelor frameworkului
  // @param $tpl_view - fișierul template de încărcat
  // @param $body_view - fișierul view file de încărcat
  // @param $data - datele transmise de controller care trebuie redirecționate spre view-ul încărcat
  function load($tpl_view, $body_view = null, $data = null) {
    // verificăm să nu fie goală variabila $body
    if (!is_null($body_view)) {
      $tpl_name = "template name";
      // verificăm existența fișierului body_view fără extensie în dosarul templates
      if (file_exists(APPPATH . 'views/' . $tpl_view . '/' . $body_view)) {
        $body_view_path = $tpl_view . '/' . $body_view;
      } 
      // verificăm existența fișierului body_view cu extensie în dosarul views/$tpl_view
      else if (file_exists(APPPATH . 'views/' . $tpl_view . '/' . $body_view . '.php')) {
        $body_view_path = $tpl_view . '/' . $body_view . '.php';
      } 
      // verificăm existența fișierului body_view fără extensie în dosarul views
      else if (file_exists(APPPATH . 'views/' . $body_view)) {
        $body_view_path = $body_view;
      } 
      // verificăm existența fișierului body_view cu extensie în dosarul views
      else if (file_exists(APPPATH . 'views/' . $body_view . '.php')) {
        $body_view_path = $body_view . '.php';
      } 
      // dacă fișierul body_view nu există, este afișat un mesaj de eroare
      else {
        show_error('Unable to load the requested file: ' . $tpl_name . '/' . $view_name . '.php');
      }
      
      // încărcăm conținulul fișierului $body_fiew și îl populăm cu $data
      $body = $this->ci->load->view($body_view_path, $data, TRUE);
      
      //Dacă variabila $data este goală, se încarcă un șir de caractere
      if (is_null($data)) {
        $data = ['body' => $body];
      } 
      // dacă este array se populează cu un array
      else if (is_array($data)) {
        $data['body'] = $body;
      } 
      // dacă este obiect se populează cu proprietăți
      else if (is_object($data)) {
        $data->body = $body;
      }
    }
    
    // încărcăm template-ul și afișăm pagina completă
    $this->ci->load->view('templates/' . $tpl_view, $data);
  }

}
