package com.tim9.agentapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/sync")
@Api(value="sync")
@CrossOrigin("http://localhost:4200")
public class SyncController {
	
	@Autowired
	SyncService syncService;
	
	@GetMapping("")
	@ApiOperation( value = "Syncs all data", httpMethod = "GET")
	@ApiResponses( value = { @ApiResponse( code = 200, message ="OK") } )
	public ResponseEntity< Boolean > sync(){

		Boolean sync = syncService.sync();

		return (sync) ? new ResponseEntity< Boolean > (sync, HttpStatus.OK) : new ResponseEntity< Boolean >( sync, HttpStatus.OK );
	}

}
