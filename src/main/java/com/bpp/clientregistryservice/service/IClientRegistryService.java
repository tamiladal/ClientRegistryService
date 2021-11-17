/**
 * 
 */
package com.bpp.clientregistryservice.service;

import com.bpp.clientregistryservice.dto.ClientDTO;

/**
 * @author Adalarasu
 *
 */
public interface IClientRegistryService {
	
	ClientDTO validateClient(String username, String password) throws Exception;
	
	ClientDTO addClient(ClientDTO client) throws Exception;

}
