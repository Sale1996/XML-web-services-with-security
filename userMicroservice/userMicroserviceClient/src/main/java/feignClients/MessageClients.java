package feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dtos.MessageDTO;

@FeignClient(name="messageClients", url = "https://localhost:8081/message")
public interface MessageClients {
	
	@GetMapping("/{messageId}")
	public ResponseEntity< MessageDTO > getMessageById(@PathVariable("messageId") long id);
	
	@PostMapping("")
	public ResponseEntity< MessageDTO > createMessage(@RequestBody MessageDTO message);
	
	@DeleteMapping("/{messageId}")
	public ResponseEntity< MessageDTO > deleteMessage(@PathVariable("messageId") long id);
	
}
