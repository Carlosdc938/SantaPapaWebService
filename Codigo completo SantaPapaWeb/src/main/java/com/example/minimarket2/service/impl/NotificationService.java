package com.example.minimarket2.service.impl;

import java.util.Optional;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.minimarket2.entity.Usuario;

@Service
public class NotificationService {
	private JavaMailSender javaMailSender;
	
	@Autowired
	public NotificationService(JavaMailSender javaMailSender) {
		this.javaMailSender=javaMailSender;
	}
	
	public void sendNotification() throws MailException{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("cisco2019diegotukekito@gmail.com");
		mail.setFrom("cisco2019diegotukekito@gmail.com");
		mail.setSubject("Manipulación de datos");
		mail.setText("El usuario "+username+" ha modificado la tabla productos");
		javaMailSender.send(mail);
	}
	
	public void sendNotificationCliente(Usuario usuario) throws MailException{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(usuario.getCorreo());
		mail.setFrom("cisco2019diegotukekito@gmail.com");
		mail.setSubject("Registro exitoso");
		mail.setText("Su registro fue completado exitosamente. Usuario: "+usuario.getUsername());
		javaMailSender.send(mail);
	}
}
