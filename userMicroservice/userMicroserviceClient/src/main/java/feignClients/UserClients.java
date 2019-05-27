package feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dtos.UserDTO;

@FeignClient(name="userClients", url = "https://localhost:8081/user")
public interface UserClients {
		
	@GetMapping("")
	public ResponseEntity< List<UserDTO> > getUserss();
	
	@GetMapping("/{userId}")
	public ResponseEntity< UserDTO > getUserById(@PathVariable("userId") long id);
	
	@PostMapping("")
	public ResponseEntity< UserDTO > registrationUser(@RequestBody UserDTO agent);
	
	@PutMapping("/{userId}")
	public ResponseEntity< UserDTO > settingsUser(@PathVariable("userId") long id, @RequestBody UserDTO user);
	
	@PutMapping("/pass/{userId}")
	public ResponseEntity< Boolean > changePasswordUser(@PathVariable("userId") long id, @RequestBody UserDTO user);
	
	@PutMapping("/status/{userId}")
	public ResponseEntity< Boolean > changeStatusUser(@PathVariable("userId") long id, @RequestBody UserDTO user);
	
	@DeleteMapping("/{userId}")
	public ResponseEntity< UserDTO > deleteUser(@PathVariable("userId") long id);
	
}
