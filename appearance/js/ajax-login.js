$(document).ready(function () {
  $("#inputCheckIn").datepicker({
    dateFormat: 'yy-mm-dd', //check change
    changeMonth: true,
    changeYear: false
  });
  
  $('#userAuthentify').hide();
  $('#userRegister').hide();

  $('input[name=selectRoute]').on('change', function () {
    $('input[name="selectedRoute"]').val($('input[name=selectRoute]:checked').val());
  });
/// Client identification
  $('#findUser').on('input', function (e) {
    e.preventDefault();
    if ($('#findUser').val().length > 5) {
      $.ajax({
        type: 'POST',
        url: '/client/exists/',
        dataType: "JSON",
        data: {findUser: $('#findUser').val()},
        success: function (data) {
          if (data.status === 'ok') {
            $('#findUserStatus').html("Salut <b>" + data.result[0].firstName + " " + data.result[0].lastName + "</b>");
            $('#userRegister').hide();
            $('#userAuthentify').show();
            $('input[name="selectedRoute"]').val($('input[name=selectRoute]:checked').val());
            $('input[name="userId"]').val(data.result[0].id);
          } else {
            $('#userAuthentify').hide();
            $('#findUserStatus').html("Se pare că nu ne cunoaștem!");
            $('#userRegister').html("<button id=\"userRegister\" name=\"userRegister\" class=\"btn btn-primary tm-btn tm-btn-search text-uppercase\" onClick=\"location.href='/client/register'\">Crează un cont!</button>");
            $('#userRegister').show();
          }
        },
        error: function (data) {
//          $('#userLogin').hide();
//          $('#doReservation').hide();
          $('#findUserStatus').html("Cred că nu pnea ne cunoaștem!");
        }
      });
    } else {
//      $('#userLogin').hide();
//      $('#doReservation').hide();
      $('#findUserStatus').html("");
    }
  });



  /// Client authorization
  $('#userAuth').on('click', function (ev) {
    ev.preventDefault();
    $('#findUserStatus').html();
    var userPassVal = $('#userPass').val();
    $.ajax({
      type: 'POST',
      url: '/client/validate/',
      dataType: "JSON",
      data: {userPass: userPassVal},
      success: function (data) {
        if (data.status === 'ok') {
          var msg = "<br><b>SUCCES! <br>Autentificare reușită!</b>";
          $('#findUserStatus').append(msg);
          $('#userAuthentify').hide();
          window.location.reload(true);
        } else {
          var msg = "<br><b>EȘEC! <br>Autentificare nu s-a putut realiza!</b>";
          $('#findUserStatus').append(msg);
        }
      },
      error: function (data) {
        var msg = "<br><b>EROARE! <br>Se pare că unele lucruri nu funcționează corect!</b>";
        $('#findUserStatus').append(msg);
      }
    });
  });

  $('#formRegister').on('submit', function (e) {
    $('#formRegisterHintPassword').html('');
    $('#formRegisterHintPhone').html('');
    $('#formRegisterHintEmail').html('');
    $('#formRegisterHintLastName').html('');
    $('#formRegisterHintFirstName').html('');
    if ($('#formRegisterInputFirstName').val() === "") {
      $('#formRegisterHintFirstName').html('Câmp necompletat!');
      e.preventDefault(e);
    }
    if ($('#formRegisterInputLastName').val() === "") {
      $('#formRegisterHintLastName').html('Câmp necompletat!');
      e.preventDefault(e);
    }
    if ($('#formRegisterInputEmail').val() === "") {
      $('#formRegisterHintEmail').html('Câmp necompletat!');
      e.preventDefault(e);
    }
    if ($('#formRegisterInputPhone').val() === "") {
      $('#formRegisterHintPhone').html('Câmp necompletat!');
      e.preventDefault(e);
    }
    if ($('#formRegisterInputPassword').val() === "") {
      $('#formRegisterHintPassword').html('Câmp necompletat!');
      e.preventDefault(e);
    }
//    if($('#formRegisterSubmit').val() === ""){
//      $('#formRegisterHintPassword').html('Câmp necompletat!');
//    }

  });
  
  $('#btnDoReservation').on('click', function(evreserv){
    if($('input[name="selectedRoute"]').val() === ""){
      alert("Nu ați bifat nicio rută! \nNu puteți face rezervare fără a seleca o rută. \nDați click pe cerculețul rutei, apoi dați din nou click pe acest buton!");
      evreserv.preventDefault(evreserv);
    }
  });


});

