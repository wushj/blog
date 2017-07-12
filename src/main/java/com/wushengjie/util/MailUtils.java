package com.wushengjie.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by wu on 2017/7/12.
 * 邮件发送工具类
 */
@Component
public class MailUtils {

    @Autowired
    private PropertyUtil propertyUtil;

    private static final Logger logger = LoggerFactory.getLogger(MailUtils.class);

    public boolean sendHtmlMail(String subject,String content,String receiver){
        boolean result = true;
        try{
            JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

            // 设定mail server
            senderImpl.setHost(propertyUtil.getMailHost());
            senderImpl.setPort(propertyUtil.getMailPort());
            senderImpl.setUsername(propertyUtil.getMailUsername());// 根据自己的情况,设置发件邮箱地址
            senderImpl.setPassword(propertyUtil.getMailPassword());// 根据自己的情况, 设置password
            senderImpl.setDefaultEncoding("UTF-8");
            Properties prop = new Properties();
            prop.put("mail.smtp.auth", "true");// 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            senderImpl.setJavaMailProperties(prop);

            // 建立邮件消息,发送简单邮件和html邮件的区别
            MimeMessage mailMessage = senderImpl.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);

            // 设置收件人，寄件人
            messageHelper.setTo(receiver);
            messageHelper.setFrom(propertyUtil.getMailFrom());
            messageHelper.setSubject(subject);
            // true 表示启动HTML格式的邮件
            messageHelper.setText(content,true);

            // 发送邮件
            senderImpl.send(mailMessage);
        } catch (MessagingException e) {
            result = false;
            logger.error("EmailUtils.method [sendHtmlMail]: email send result-" + result +",error message-" + e);
        }
        return result;
    }

}