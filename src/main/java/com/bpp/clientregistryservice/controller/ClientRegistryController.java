/**
 * 
 */
package com.bpp.clientregistryservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bpp.clientregistryservice.dto.ClientDTO;
import com.bpp.clientregistryservice.service.IClientRegistryService;

/**
 * @author Adalarasu
 *
 */
@RestController
@CrossOrigin
@RequestMapping(path = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientRegistryController {
	
	@Autowired
	IClientRegistryService clientRegistryService;
	
	@GetMapping("/check/status")
	public String checkStatus() {
		return "Successfully Checked";
	}
	
	@PostMapping("/validateClient")
	public ResponseEntity<ClientDTO> validateClient(@RequestBody final ClientDTO client) throws Exception {
		return ResponseEntity.ok(clientRegistryService.validateClient(client.getUserName(), client.getPassword()));
	}
	
	@PostMapping("/addClient")
	public ResponseEntity<ClientDTO> addClient(@RequestBody final ClientDTO client) throws Exception {
		return ResponseEntity.ok(clientRegistryService.addClient(client));
	}

}
