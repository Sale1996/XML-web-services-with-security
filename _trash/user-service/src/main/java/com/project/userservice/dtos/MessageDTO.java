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
    "messageBody",
    "userId",
    "recieved",
    "opened",
    "reservationId"
})
@XmlRootElement(name = "Message")
public class MessageDTO {
	
    @XmlElement(name = "Mesasge_id")
    protected Long messageId;
    
    @XmlElement(name = "Message_body")
    protected String messageBody;
    
    @XmlElement(name = "Message_time")
    protected LocalDateTime messageTime;
    
    @XmlElement(name = "userId")
    protected Long userId;
    
    @XmlElement(name = "recieved")
    protected boolean recieved;
      
    @XmlElement(name = "opened")
    protected boolean opened;
    
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isRecieved() {
		return recieved;
	}

	public void setRecieved(boolean recieved) {
		this.recieved = recieved;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
    
    

     
    
}
