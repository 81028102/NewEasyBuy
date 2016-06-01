package cn.jbit.entity;

import java.io.File;
/**
 * @author 任锯东 邮箱实体类
 */
public class Mail {
	
	private String from;		//发件人邮箱
	private String to;			//收件人邮箱
	private String subject;		//邮箱主题
	private String content;		//邮箱内容
	private File file;			//文件(选择附件)
	private String fileName;	//文件名称
	
	public Mail(){}
	public Mail(String from, String to, String subject, String content, File file, String fileName){
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.content = content;
		this.file = file;
		this.fileName = fileName;
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
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}