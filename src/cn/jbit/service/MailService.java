package cn.jbit.service;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import cn.jbit.entity.Mail;

/**
 * @author 任锯东
 */
public class MailService {

	private JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	// 发送邮件方法
	public void sendMail(Mail mail) throws Exception {

		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();// 调用createMimeMessage方法
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,
					"UTF-8");
			helper.setFrom("981028102@qq.com");
			helper.setTo(mail.getTo());

			helper.setSubject(mail.getSubject());
			helper.setText(mail.getContent());
			// （1）要直接使用带后缀的文件名全称， （2）需要处理中文乱码问题
			helper.addAttachment(MimeUtility.encodeWord(mail.getFileName()),
					mail.getFile());
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			// TODO Auto-generated method stub
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
}
