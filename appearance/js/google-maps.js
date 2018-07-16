/* Google Maps
 ------------------------------------------------*/
var map = '';
var center;

function initialize() {
  var mapOptions = {
    zoom: 17,
    center: new google.maps.LatLng(46.782099, 23.612742),
    scrollwheel: false
  };

  map = new google.maps.Map(document.getElementById('google-map'), mapOptions);

  google.maps.event.addDomListener(map, 'idle', function () {
    calculateCenter();
  });

  google.maps.event.addDomListener(window, 'resize', function () {
    map.setCenter(center);
  });
}

function calculateCenter() {
  center = map.getCenter();
}

function loadGoogleMap() {
  var script = document.createElement('script');
  script.type = 'text/javascript';
  script.src = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyDVWt4rJfibfsEDvcuaChUaZRS5NXey1Cs&v=3.exp&sensor=false&' + 'callback=initialize';
  document.body.appendChild(script);
}