package com.tim9.userserviceClient.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tim9.userserviceClient.dtos.UserDTO;

@FeignClient(name="userClient", url = "http://localhost:8762/user/user")
public interface UserClient {
		
	@GetMapping("")
	public List<UserDTO> getUserss();
	
	@GetMapping("/{userId}")
	public UserDTO getUserById(@PathVariable("userId") long id);
	
	@PostMapping("")
	public UserDTO registrationUser(@RequestBody UserDTO agent);
	
	@PutMapping("/{userId}")
	public UserDTO settingsUser(@PathVariable("userId") long id, @RequestBody UserDTO user);
	
	@PutMapping("/pass/{userId}")
	public Boolean changePasswordUser(@PathVariable("userId") long id, @RequestBody UserDTO user);
	
	@PutMapping("/status/{userId}")
	public Boolean changeStatusUser(@PathVariable("userId") long id, @RequestBody UserDTO user);
	
	@DeleteMapping("/{userId}")
	public UserDTO deleteUser(@PathVariable("userId") long id);
	
}
