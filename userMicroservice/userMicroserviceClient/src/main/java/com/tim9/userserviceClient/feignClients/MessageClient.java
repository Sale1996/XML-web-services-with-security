package com.tim9.userserviceClient.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tim9.userserviceClient.dtos.MessageDTO;

@FeignClient(name="messageClient", url = "http://localhost:8762/user/message")
public interface MessageClient {
	
	@GetMapping("/{messageId}")
	public MessageDTO getMessageById(@PathVariable("messageId") long id);
	
	@PostMapping("")
	public MessageDTO createMessage(@RequestBody MessageDTO message);
	
	@DeleteMapping("/{messageId}")
	public MessageDTO deleteMessage(@PathVariable("messageId") long id);
	
}
