package cn.jbit.action;

import java.io.File;
import cn.jbit.entity.Mail;
import cn.jbit.service.MailService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
public class SendMailAction extends ActionSupport{

	private MailService mailService =null;
	private String from;  					//发送人
	private String to;						//接收人
	private String subject;					//邮件标题
	private String content;					//邮件内容
	private File upload;					//上传文件
	private String uploadFileName;			//文件路径名

	public String SendMail() throws Exception {
		Mail mail = new Mail(getFrom(),getTo(),getSubject(),getContent(),getUpload(),getUploadFileName());
		mailService.sendMail(mail);
		return "success";
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
}