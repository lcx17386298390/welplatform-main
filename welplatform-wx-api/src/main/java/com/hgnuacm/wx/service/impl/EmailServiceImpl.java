package com.hgnuacm.wx.service.impl;

import com.hgnuacm.wx.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Resource
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public boolean sendEmail(String toEmail, String text, String message) {
        System.out.println(fromEmail);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //设置发件邮箱
        simpleMailMessage.setFrom(fromEmail);
        //收件人邮箱
        simpleMailMessage.setTo(toEmail);
        //主题标题
        simpleMailMessage.setSubject(text);
        //信息内容
        simpleMailMessage.setText(message);


        //执行发送
        try {//发送可能失败
//            MimeMessage mimeMessage = null;
//            mimeMessage = javaMailSender.createMimeMessage();
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
//            mimeMessageHelper.setFrom(fromEmail);
//            mimeMessageHelper.setText(text);
//            mimeMessageHelper.setTo(toEmail);
//            mimeMessageHelper.setSubject("大学生智慧迎新平台");
            javaMailSender.send(simpleMailMessage);
            //没有异常返回true，表示发送成功
            return true;
        } catch (Exception e) {
            //发送失败，返回false
            e.printStackTrace();
            return false;
        }
    }
}
