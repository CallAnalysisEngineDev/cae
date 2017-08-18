package org.cae.mail.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class MailMessage {

	@NotNull(message = "邮件类型不能为空")
	private Integer type;
	@Valid
	@NotNull(message = "邮件对象不能为空")
	private Mail mail;
	@NotNull(message = "token令牌对象为空")
	@NotEmpty(message = "token令牌为空")
	private String token;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
