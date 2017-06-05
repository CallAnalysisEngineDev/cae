package org.cae.mail.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Mail {

	@NotEmpty(message="{mail.title.empty}")
	@Length(max=20,message="{mail.title.max}")
	private String title;
	@NotEmpty(message="{mail.content.empty}")
	@Length(max=150,message="{mail.content.max}")
	private String content;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
