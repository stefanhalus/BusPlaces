<div class="tm-container-outer tm-position-relative" id="tm-section-4">
  <div id="google-map"></div>
  <form action="/company/contact" method="post" class="tm-contact-form">
    <div class="form-group tm-name-container">
      <input type="text" id="contact_name" name="contact_name" class="form-control" placeholder="Nume"  required/>
    </div>
    <div class="form-group tm-email-container">
      <input type="email" id="contact_email" name="contact_email" class="form-control" placeholder="Email"  required/>
    </div>
    <div class="form-group">
      <input type="text" id="contact_subject" name="contact_subject" class="form-control" placeholder="Subiect"  required/>
    </div>
    <div class="form-group">
      <textarea id="contact_message" name="contact_message" class="form-control" rows="9" placeholder="Mesaj" required></textarea>
    </div>
    <button type="submit" class="btn btn-primary tm-btn-primary tm-btn-send text-uppercase">Trimite mesaj acum</button>
  </form>
</div> <!-- .tm-container-outer -->
