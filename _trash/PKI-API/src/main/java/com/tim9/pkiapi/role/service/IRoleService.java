package com.tim9.pkiapi.role.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tim9.pkiapi.role.dto.RoleDTO;


@Service
public interface IRoleService {

	public RoleDTO findOneById ( Long id );
	
	public RoleDTO findOneByName ( String name );
	
	public List<RoleDTO> findAll();
	
	public RoleDTO save ( RoleDTO roleToSave );
	
	public RoleDTO deleteById ( Long id );
	
}
