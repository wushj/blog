package com.wushengjie.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wu on 2017/7/12.
 */
@Configuration
public class PropertyUtil {

    @Value("${aliYun.oss.endpoint}")
    private String endPoint;
    @Value("${aliYun.oss.accesskeyId}")
    private String accesskeyId;
    @Value("${aliYun.oss.accesskeySecret}")
    private String accesskeySecret;
    @Value("${aliYun.oss.bucketName}")
    private String bucketName;
    @Value("${aliYun.oss.dir}")
    private String dir;

    @Value("${mail.host}")
    private String mailHost;
    @Value("${mail.port}")
    private Integer mailPort;
    @Value("${mail.username}")
    private String mailUsername;
    @Value("${mail.password}")
    private String mailPassword;
    @Value("${mail.from}")
    private String mailFrom;
    @Value("${mail.to}")
    private String mailTo;

    public String getEndPoint() {
        return endPoint;
    }

    public String getAccesskeyId() {
        return accesskeyId;
    }

    public String getAccesskeySecret() {
        return accesskeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getDir() {
        return dir;
    }

    public String getMailHost() {
        return mailHost;
    }

    public Integer getMailPort() {
        return mailPort;
    }

    public String getMailUsername() {
        return mailUsername;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public String getMailTo() {
        return mailTo;
    }
}
