package com.endava.parkinglot.services.impl;

import com.endava.parkinglot.exceptions.FailedEmailNotificationException;
import com.endava.parkinglot.services.EmailNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromAddress;

    private static final String MESSAGE_SUBJECT = "Parking-Lot-Application: Notification about granted admin authority";

    private static final String MESSAGE_TEXT = "You have been granted an Admin role for Parking Lot app.";

    @Override
    public void sendNotificationAboutGrantedAdminRole(final String userEmail) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromAddress);
            message.setTo(userEmail);
            message.setSubject(MESSAGE_SUBJECT);
            message.setText(MESSAGE_TEXT);

            mailSender.send(message);
            logger.info("Email has been sent to: '" + userEmail + "'");
        } catch (MailException mailException) {
            throw new FailedEmailNotificationException("Could not send the email to: '" + userEmail + "'");
        }
    }

    public void sendNotificationAboutAddedToParkingLot(final String userEmail, String parkingLotName) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromAddress);
            message.setTo(userEmail);
            message.setSubject("You have been added to Parking Lot");
            message.setText("You have been added to " + parkingLotName);

            mailSender.send(message);
            logger.info("Email has been sent to: '" + userEmail + "'");
        } catch (MailException mailException) {
            throw new FailedEmailNotificationException("Could not send the email to: '" + userEmail + "'");
        }
    }
}
