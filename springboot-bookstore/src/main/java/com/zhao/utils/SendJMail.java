package com.zhao.utils;


import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author gyf 2018-02-27
 *
 * SMTP（Simple Mail Transfer Protocol）即简单邮件传输协议
 * 》SMTP 服务器就是遵循 SMTP 协议的发送邮件服务器
 * POP3是Post Office Protocol 3的简称，即邮局协议的第3个版本
 * 》POP3服务器则是遵循POP3协议的接收邮件服务器
 */
public class SendJMail {

    /**
     *
     * @param email 接受人的邮箱地址
     * @param emailMsg 邮箱内容
     * @return
     */
    public static boolean sendMail(String email, String emailMsg) {

        String from = "565214727@qq.com"; 				// 邮件发送人的邮件地址
        String to = email; 										// 邮件接收人的邮件地址
        final String username = "565214727@qq.com";  	//发件人的邮件帐户
        final String password = "ryrvfivsjuoibdag";   					//发件人的邮件密码


        //定义Properties对象,设置环境信息
        Properties props = System.getProperties();

        //设置邮件服务器的地址
        props.setProperty("mail.smtp.host", "smtp.qq.com"); // 指定的smtp服务器
        props.setProperty("mail.smtp.auth", "true");    //授权
        props.setProperty("mail.transport.protocol", "smtp");//设置发送邮件使用的协议
        //创建Session对象,session对象表示整个邮件的环境信息
        Session session = Session.getInstance(props);
        //设置输出调试信息
        session.setDebug(true);
        try {
            //Message的实例对象表示一封电子邮件
            MimeMessage message = new MimeMessage(session);
            //设置发件人的地址
            message.setFrom(new InternetAddress(from));
            //设置主题
            message.setSubject("用户激活");
            //设置邮件的文本内容
            //message.setText("Welcome to JavaMail World!");
            message.setContent((emailMsg),"text/html;charset=utf-8");

            //设置附件
            //message.setDataHandler(dh);

            //从session的环境中获取发送邮件的对象
            Transport transport=session.getTransport();
            //连接邮件服务器
            transport.connect("smtp.qq.com",25, username, password);
            //设置收件人地址,并发送消息
            transport.sendMessage(message,new Address[]{new InternetAddress(to)});
            transport.close();
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
