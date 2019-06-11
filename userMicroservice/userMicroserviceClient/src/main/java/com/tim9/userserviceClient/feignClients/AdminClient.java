package com.tim9.userserviceClient.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tim9.userserviceClient.dtos.AdminDTO;

@FeignClient(name="adminClient", url = "http://localhost:8080/admin")
public interface AdminClient {

	@GetMapping("")
	public List<AdminDTO> getAdmins();
		
	@GetMapping("/{adminId}")
	public AdminDTO getAdminById(@PathVariable("adminId") long id);
	
	@PostMapping("")
	public AdminDTO registrationAdmin(@RequestBody AdminDTO admin);
		
	@PutMapping("/{adminId}")
	public AdminDTO settingsAdmin(@PathVariable("adminId") long id, @RequestBody AdminDTO admin);
	
	@PutMapping("/pass/{adminId}")
	public Boolean changePasswordUser(@PathVariable("adminId") long id, @RequestBody AdminDTO admin);
	
	
	@DeleteMapping("/{adminId}")
	public AdminDTO deleteAdmin(@PathVariable("adminId") long id);
	
}
