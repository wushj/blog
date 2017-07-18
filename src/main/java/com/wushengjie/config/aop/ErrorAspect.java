package com.wushengjie.config.aop;

import com.wushengjie.util.DateUtil;
import com.wushengjie.util.PropertyUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
public class ErrorAspect {

    @Autowired
    private PropertyUtil propertyUtil;

    @Autowired
    private JavaMailSender mailSender;

    private Logger logger = LoggerFactory.getLogger(ErrorAspect.class);
    @Pointcut("execution(* com.wushengjie.controller.*.*(..))")
    public void exceptionLog(){}

    /**
     * 异常切入方法
     * 记录异常到数据库
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "exceptionLog()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
        }
        HttpServletRequest request = requestAttributes.getRequest();
        String ip = String.valueOf(request.getRemoteAddr());
        String method = String.valueOf(request.getMethod());
        String requestURL = String.valueOf(request.getRequestURL());
        String args = Arrays.toString(joinPoint.getArgs());
        String classMethod = String.valueOf(joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        String time = DateUtil.getDateTime(new Date());
        String errorMessege = String.valueOf(e.getLocalizedMessage());

        String title = "Error Log";
        String html = biuldHtml(ip, method, requestURL, args, classMethod, time, errorMessege);
        //发送邮件
        sendMail(title, html);
        logger.error(errorMessege);
    }

    /**
     * 发送邮件
     * @param title
     * @param html
     */
    private void sendMail(String title, String html){
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(propertyUtil.getMailFrom());
            helper.setTo(propertyUtil.getMailTo());
            helper.setSubject(title);
            helper.setText(html,true);
            mailSender.send(message);
        } catch (MessagingException e) {
            logger.error("发送邮件失败！：" + e.getMessage());
            e.printStackTrace();
        }

    }


    private String biuldHtml(String ip, String method, String requestURL, String args, String classMethod, String time, String errorMessege) {
        return "<html><head></head><body>"
                            + "<div style=\"padding-left:20px\">"
                            + "<table border=\"1px\" cellspacing=\"0px\" style=\"border-collapse:collapse\">"
                            + "<tr>"
                            + "<td>ip</td><td>"+ip+"</td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td>method</td><td>"+method+"</td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td>requestURL</td><td>"+requestURL+"</td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td>args</td><td>"+args+"</td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td>classMethod</td><td>"+classMethod+"</td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td>time</td><td>"+time+"</td>"
                            + "</tr>"
                            + "</table></div>"
                            + "<p>"+errorMessege+"</p>"
                            + "</body></html>";
    }
}
