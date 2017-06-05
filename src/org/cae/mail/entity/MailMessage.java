package org.cae.mail.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MailMessage {
	
	@NotNull(message="{mailMessage.type.null}")
	private Integer type;
	@Valid
	@NotNull(message="{mailMessage.mail.null}")
	private Mail mail;
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
}
