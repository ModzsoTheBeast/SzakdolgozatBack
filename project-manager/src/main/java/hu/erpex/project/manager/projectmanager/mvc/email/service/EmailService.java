package hu.erpex.project.manager.projectmanager.mvc.email.service;

import hu.erpex.project.manager.projectmanager.mvc.email.dto.EmailSendDTO;
import hu.erpex.project.manager.projectmanager.mvc.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    /*
    private final JavaMailSender javaMailSender;


    @Autowired
    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(EmailSendDTO email) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        String message = "valaki" + "szeretné, hogy csatlakozz a projektjéhez! A csatlakozáshoz kattints ide.";
        mail.setTo(email.getToEmail());
        mail.setFrom(email.getFromEmail());
        mail.setSubject("Hi! ");
        mail.setText(message);

        javaMailSender.send(mail);
    }


     */
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private ProjectRepository projectRepository;

    public void sendSimpleMessage(
            String toEmail, Long projectId) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("devoopsemail@gmail.com");
        message.setTo(toEmail);
        message.setSubject("DEVoops ;)");
        message.setText("Önt meginvitálták a " + projectRepository.findById(projectId).getProjectName() + " nevű projektbe!");
        emailSender.send(message);

    }

}
