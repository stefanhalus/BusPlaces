package ro.stefanhalus.java.views;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ro.stefanhalus.java.db.EmployeeAdapter;
import ro.stefanhalus.java.models.Employee;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.UUID;

public class LoginRememberPasswordController {

    private EmployeeAdapter employeeAdaper = new EmployeeAdapter();

    @FXML
    private void initialize(){

    }

    @FXML
    private Label lblEmailMissing;

    @FXML
    private TextField tfEmail;

    @FXML
    private Button btnRemember;

    @FXML
    private Button btnClose;

    @FXML
    void btnActClose(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnActRemember(ActionEvent event) {
        Employee emp = employeeAdaper.employeeRememberPassword(tfEmail.getText());
        if(emp == null){
            lblEmailMissing.setText("");
        } else {
            String newPass = UUID.randomUUID().toString();
            Employee empUpdate = new Employee();
            empUpdate.setId(emp.getId());
            empUpdate.setPass(newPass);
            employeeAdaper.employeeResetPassword(empUpdate);

            String to = emp.getEmail();
            String messageText = "Bună ziua, \" + emp.getFirstName() + \" \" + emp.getLastName() + \",\\n\\n\\nNoua dumneavoastră parolă este: " + newPass;
            SendEmail(to, messageText);
        }
    }

    private void SendEmail(String to, String messageText){
        // Recipient's email ID needs to be mentioned.
//        String to = mailTo;

        // Sender's email ID needs to be mentioned
        String from = "employee@busplaces.ro";

        // Assuming you are sending email from localhost
        String host = "stefanhalus.ro";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Recuperare parolă");

            // Now set the actual message
            message.setText(messageText);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
