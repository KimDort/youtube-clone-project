package com.tube.clone.config.message.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "MESSAGE_CODE_MANAGE")
public class MessageDomain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MESSAGE_CODE_SEQ")
	private int messageCodeSeq;
	
	@Column(name = "MESSAGE_CODE_NAME")
	private String messageCodeName;
	
	@Column(name = "MESSAGE_CODE_LOCALE")
	private String messageCodeLocale;
	
	@Column(name = "MESSAGE_CODE_USE_YN")
	private String messageCodeUseYn;
	
	@Column(name = "MESSAGE_CODE_CONTENT")
	private String messageCodeContent;
	
	@CreationTimestamp
	@Column(name = "MESSAGE_CODE_CREATE_DATE")
	private Timestamp messageCodeCreateDate;
	
	@UpdateTimestamp
	@Column(name = "MESSAGE_CODE_UPDATE_DATE")
	private Timestamp messageCodeUpdateDate;

	/**
	 * @return the messageCodeSeq
	 */
	public int getMessageCodeSeq() {
		return messageCodeSeq;
	}

	/**
	 * @param messageCodeSeq the messageCodeSeq to set
	 */
	public void setMessageCodeSeq(int messageCodeSeq) {
		this.messageCodeSeq = messageCodeSeq;
	}

	/**
	 * @return the messageCodeLocale
	 */
	public String getMessageCodeLocale() {
		return messageCodeLocale;
	}

	/**
	 * @param messageCodeLocale the messageCodeLocale to set
	 */
	public void setMessageCodeLocale(String messageCodeLocale) {
		this.messageCodeLocale = messageCodeLocale;
	}

	/**
	 * @return the messageCodeUseYn
	 */
	public String getMessageCodeUseYn() {
		return messageCodeUseYn;
	}

	/**
	 * @param messageCodeUseYn the messageCodeUseYn to set
	 */
	public void setMessageCodeUseYn(String messageCodeUseYn) {
		this.messageCodeUseYn = messageCodeUseYn;
	}

	/**
	 * @return the messageCodeContent
	 */
	public String getMessageCodeContent() {
		return messageCodeContent;
	}

	/**
	 * @param messageCodeContent the messageCodeContent to set
	 */
	public void setMessageCodeContent(String messageCodeContent) {
		this.messageCodeContent = messageCodeContent;
	}

	/**
	 * @return the messageCodeCreateDate
	 */
	public Timestamp getMessageCodeCreateDate() {
		return messageCodeCreateDate;
	}

	/**
	 * @param messageCodeCreateDate the messageCodeCreateDate to set
	 */
	public void setMessageCodeCreateDate(Timestamp messageCodeCreateDate) {
		this.messageCodeCreateDate = messageCodeCreateDate;
	}

	/**
	 * @return the messageCodeUpdateDate
	 */
	public Timestamp getMessageCodeUpdateDate() {
		return messageCodeUpdateDate;
	}

	/**
	 * @param messageCodeUpdateDate the messageCodeUpdateDate to set
	 */
	public void setMessageCodeUpdateDate(Timestamp messageCodeUpdateDate) {
		this.messageCodeUpdateDate = messageCodeUpdateDate;
	}

	/**
	 * @return the messageCodeName
	 */
	public String getMessageCodeName() {
		return messageCodeName;
	}

	/**
	 * @param messageCodeName the messageCodeName to set
	 */
	public void setMessageCodeName(String messageCodeName) {
		this.messageCodeName = messageCodeName;
	}
	
}
