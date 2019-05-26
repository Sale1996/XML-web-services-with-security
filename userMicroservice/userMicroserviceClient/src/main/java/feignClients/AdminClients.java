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

import dtos.AdminDTO;

@FeignClient(name="adminClients", url = "https://localhost:8081/admin")
public interface AdminClients {

	@GetMapping("")
	public ResponseEntity< List<AdminDTO> > getAdmins();
		
	@GetMapping("/{adminId}")
	public ResponseEntity< AdminDTO > getAdminById(@PathVariable("adminId") long id);
	
	@PostMapping("")
	public ResponseEntity< AdminDTO > registrationAdmin(@RequestBody AdminDTO admin);
		
	@PutMapping("/{adminId}")
	public ResponseEntity< AdminDTO > settingsAdmin(@PathVariable("adminId") long id, @RequestBody AdminDTO admin);
	
	@PutMapping("/pass/{adminId}")
	public ResponseEntity< Boolean > changePasswordUser(@PathVariable("adminId") long id, @RequestBody AdminDTO admin);
	
	
	@DeleteMapping("/{adminId}")
	public ResponseEntity< AdminDTO > deleteAdmin(@PathVariable("adminId") long id);
	
}
