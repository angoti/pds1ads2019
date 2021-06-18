package com.educandoweb.course.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.dto.CredentialsDTO;
import com.educandoweb.course.dto.EmailDTO;
import com.educandoweb.course.dto.TokenDTO;
import com.educandoweb.course.services.AuthService;

@CrossOrigin
@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private AuthService service;
	
	@PostMapping(value = "/login")
	public ResponseEntity<TokenDTO> login(@RequestBody CredentialsDTO dto) {
		TokenDTO tokenDTO = service.authenticate(dto);
		return ResponseEntity.ok().body(tokenDTO);
	}
	
	@PostMapping(value = "/refresh_token")
	public ResponseEntity<TokenDTO> refreshToken() {
		TokenDTO tokenDTO = service.refreshToken();
		return ResponseEntity.ok().body(tokenDTO);
	}
	
	@PostMapping(value = "/forgot")
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO dto) {
		service.sendNewPassword(dto.getEmail());
		return ResponseEntity.noContent().build();
	}
}
