package org.cae.mail.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Mail {

	@NotEmpty(message = "反馈标题不能为空")
	@Length(max = 20, message = "反馈标题超过20个字")
	private String title;
	@NotEmpty(message = "反馈内容不能为空")
	@Length(max = 150, message = "反馈内容超过150个字")
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
