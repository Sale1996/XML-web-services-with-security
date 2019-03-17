package com.tim9.pkiapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
}
