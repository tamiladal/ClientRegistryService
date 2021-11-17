/**
 * 
 */
package com.bpp.clientregistryservice.service;

import java.security.SecureRandom;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bpp.clientregistryservice.domain.Client;
import com.bpp.clientregistryservice.dto.ClientDTO;
import com.bpp.clientregistryservice.repository.ClientRepository;

/**
 * @author Adalarasu
 *
 */
@Service
public class ClientRegistryServiceImpl implements IClientRegistryService{
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	private int strength = 0;
	private SecureRandom random = null;
	
	public ClientRegistryServiceImpl() {
		strength = 27;
		random = new SecureRandom();
	}
	
	public ClientDTO validateClient(String userName, String password) throws Exception {
		try {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength, random);
			Client client = clientRepository.findByUserName(userName);
			System.out.println("Inside Impl");
			System.out.println(password);
			System.out.println(client.getPassword());
			if(passwordEncoder.matches(password, client.getPassword())) {
				ClientDTO clientDTO = modelMapper.map(client, ClientDTO.class);
				clientDTO.setPassword(null);
				return clientDTO;
			}else
				throw new Exception("Password Not Matching");
		}catch(Exception exception) {
			System.out.println(exception.getMessage());
			throw new Exception("Client validation Failed");
		}
	}
	
	public ClientDTO addClient(ClientDTO clientDTO) throws Exception {
		try {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength, random);
			Client fetchedClient = clientRepository.findByUserName(clientDTO.getUserName());
			if(null == fetchedClient) {
				String encryptedPassword = passwordEncoder.encode(clientDTO.getPassword());
				clientDTO.setPassword(encryptedPassword);
				Client mappedClient = modelMapper.map(clientDTO, Client.class);
				ClientDTO mappedDTO = modelMapper.map(mappedClient,ClientDTO.class);
				mappedDTO.setPassword(null);
				return mappedDTO;
			}else
				throw new Exception("Username Exists");
			
		}catch(Exception exception) {
			throw new Exception("Client addition Failed");
		}
	}

}
