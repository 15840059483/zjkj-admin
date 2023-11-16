package com.jeebase.system.security.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 公告信息
 * </p>
 */
public class UpdateNoticeInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "文章")
	private String text;

	@ApiModelProperty(value = "备注")
	private String description;

	@ApiModelProperty(value = "级别 0普通 1开发公告 ")
	private String notice_level;

	@ApiModelProperty(value = "有效时间")
	private LocalDateTime activeTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNotice_level() {
		return notice_level;
	}

	public void setNotice_level(String notice_level) {
		this.notice_level = notice_level;
	}

	public LocalDateTime getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(LocalDateTime activeTime) {
		this.activeTime = activeTime;
	}

	

}
