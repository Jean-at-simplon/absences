package fr.simplon.services.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import fr.simplon.domain.Absence;
import fr.simplon.exception.ServiceException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@Service
public class EmailService {

	public boolean sendEmail(Absence absence, String emailDest, String subject, int numero) throws ServiceException, IOException, TemplateException, MessagingException {

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("src/main/resources/javamail.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		final String senderPassword = properties.getProperty("mail.smtp.password");
		final String senderMail = properties.getProperty("mail.smtp.from");

		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderMail, senderPassword);
			}
		});

		try {
			prepareAndSendMessage(absence, emailDest, subject, senderMail, session, numero);
			return true;
		} catch (IOException e) {
			throw new IOException (e);
		}

	}

	private void prepareAndSendMessage(Absence absence, String destinataire, String subject, String senderMail,
			Session session, int numero) throws ServiceException, IOException, TemplateException, MessagingException {

		try {
			SimpleDateFormat  dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderMail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinataire));
			message.setSubject(subject);

			MimeBodyPart body = new MimeBodyPart();

			// freemaraker debut de configuration
			Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
			configuration.setDirectoryForTemplateLoading(new File("src/main/resources/template"));
			configuration.setDefaultEncoding("UTF-8");
			configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			configuration.setLogTemplateExceptions(false);
			
			Template template = configuration.getTemplate("email"+numero+".ftlh");

			Map<String, String> rootMap = new HashMap<String, String>();
//			rootMap.put("statut", absence.getStatut());
			rootMap.put("nom", absence.getEmploye().getNom());
			rootMap.put("prenom", absence.getEmploye().getPrenom());
			rootMap.put("typeConge", absence.getType().getNom());
			rootMap.put("debutConge", dateFormat.format(absence.getDebut()).toString());
			rootMap.put("finConge", dateFormat.format(absence.getFin()).toString());
			rootMap.put("numDemande", absence.getNumDemande());
			rootMap.put("commentaire", absence.getCommentaire());
			Writer out = new StringWriter();
			template.process(rootMap, out);
			// freemarker stuff ends.

			/* Insertion du template dans le body. */
			body.setContent(out.toString(), "text/html;charset=UTF-8");

			MimeMultipart multipart = new MimeMultipart("related");
			multipart.addBodyPart(body);

			message.setContent(multipart);

			Transport.send(message);


		} catch (ServiceException e) {
			throw new ServiceException("Le mail n'a pas été envoyé");
		}

	}

}
