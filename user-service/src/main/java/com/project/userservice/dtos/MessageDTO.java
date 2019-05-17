package com.project.userservice.dtos;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "messageId",
    "messageTime",
    "senderId",
    "recieverId"
})
@XmlRootElement(name = "Message")
public class MessageDTO {
	
	
    @XmlElement(name = "Mesasge_id")
    protected Long messageId;
    
    @XmlElement(name = "Message_body")
    protected String messageBody;
    
    @XmlElement(name = "Message_time")
    protected LocalDateTime messageTime;
    
    @XmlElement(name = "Sender")
    protected Long senderId;
    
    @XmlElement(name = "Reciever")
    protected Long recieverId;
    
    @XmlElement(name = "reservationId")
    protected Long reservationId;


	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public LocalDateTime getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(LocalDateTime messageTime) {
		this.messageTime = messageTime;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(Long recieverId) {
		this.recieverId = recieverId;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
    
    
}
