
<footer class="tm-container-outer">
  <p class="mb-0">Copyright © <span class="tm-current-year">2018</span> BusPlaces</p>
</footer>
</div>



</div> <!-- .main-content -->

<!-- load JS files -->
<script src="<?php echo base_url(); ?>appearance/js/jquery-3.3.1.min.js"></script>             <!-- jQuery (https://jquery.com/download/) -->
<script src="<?php echo base_url(); ?>appearance/js/popper.min.js"></script>                    <!-- https://popper.js.org/ -->       
<script src="<?php echo base_url(); ?>appearance/js/bootstrap.min.js"></script>                 <!-- https://getbootstrap.com/ -->
<!--<script src="<?php echo base_url(); ?>appearance/js/datepicker.min.js"></script>                 https://github.com/qodesmith/datepicker -->
<script src="<?php echo base_url(); ?>appearance/js/jquery-ui.min.js"></script>                <!-- https://github.com/qodesmith/datepicker -->
<script src="<?php echo base_url(); ?>appearance/js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
<script src="<?php echo base_url(); ?>appearance/js/jquery.scrollTo.min.js"></script>           <!-- https://github.com/flesler/jquery.scrollTo -->
<script src="<?php echo base_url(); ?>appearance/js/ajax-login.js"></script>           <!-- https://github.com/flesler/jquery.scrollTo -->
<script src="<?php echo base_url(); ?>appearance/js/google-maps.js"></script>           <!-- https://github.com/flesler/jquery.scrollTo -->
<script src="<?php echo base_url(); ?>appearance/slick/slick.min.js"></script>                  <!-- http://kenwheeler.github.io/slick/ -->
<script>
  
  /* DOM is ready
   ------------------------------------------------*/
  $(function () {

    // Change top navbar on scroll
    $(window).on("scroll", function () {
      if ($(window).scrollTop() > 100) {
        $(".tm-top-bar").addClass("active");
      } else {
        $(".tm-top-bar").removeClass("active");
      }
    });

    // Smooth scroll to search form
    $('.tm-down-arrow-link').click(function () {
      $.scrollTo('#tm-section-search', 300, {easing: 'linear'});
    });

    // Update nav links on scroll
//    $('#tm-top-bar').singlePageNav({
//      currentClass: 'active',
//      offset: 60
//    });

    // Close navbar after clicked
    $('.nav-link').click(function () {
      $('#mainNav').removeClass('show');
    });

    // Slick Carousel
    $('.tm-slideshow').slick({
      infinite: true,
      arrows: true,
      slidesToShow: 1,
      slidesToScroll: 1
    });

    loadGoogleMap();                                       // Google Map                
    $('.tm-current-year').text(new Date().getFullYear());  // Update year in copyright           
  });

</script>             

</body>
</html>
