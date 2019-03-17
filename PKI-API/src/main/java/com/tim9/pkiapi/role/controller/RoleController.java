package com.tim9.pkiapi.role.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim9.pkiapi.role.dto.RoleDTO;
import com.tim9.pkiapi.role.service.IRoleService;

@RestController
@RequestMapping("/pki/role")
public class RoleController {

	
	@Autowired
	IRoleService roleService;

	
	
	
	@GetMapping("/")
	public ResponseEntity<List<RoleDTO>> getAllRoles(){
		
		List<RoleDTO> roles = roleService.findAll();
		
		return ( !roles.isEmpty() )? new ResponseEntity<List<RoleDTO>>(roles,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RoleDTO> getOneRoleById (@PathVariable("id") Long id){
		
		RoleDTO roleDto = roleService.findOneById(id);
		
		return ( roleDto.getId()!=null )? new ResponseEntity<RoleDTO>(roleDto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}	
	
	@GetMapping("/name/{name}")
	public ResponseEntity<RoleDTO> getOneRoleByName (@PathVariable("name") String name){
		
		RoleDTO roleDto = roleService.findOneByName(name);
		
		return ( roleDto.getId()!=null )? new ResponseEntity<RoleDTO>(roleDto,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}	
	
	
	@PostMapping("/")
	public ResponseEntity<RoleDTO> saveRole(@RequestBody RoleDTO dto){
		
		RoleDTO savedRole = roleService.save(dto);
		
		return ( savedRole!=null )? new ResponseEntity<RoleDTO>(savedRole,HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<RoleDTO> deleteRole(@PathVariable("id") Long id){
		RoleDTO deletedRole = roleService.deleteById(id);
		
		return (deletedRole.getId() != null ) ? new ResponseEntity<RoleDTO>(deletedRole,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
