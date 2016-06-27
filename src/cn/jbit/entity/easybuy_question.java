package cn.jbit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 任锯东 问题实体类
 */
@Entity
@Table(name="easybuy_question")
@SuppressWarnings("serial")
public class easybuy_question implements Serializable {
	
	private String eu_question;		//设置问题

	public String getEu_question() {
		return eu_question;
	}

	public void setEu_question(String eu_question) {
		this.eu_question = eu_question;
	}
}
