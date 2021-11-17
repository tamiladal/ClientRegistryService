/**
 * 
 */
package com.bpp.clientregistryservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bpp.clientregistryservice.domain.Client;
import com.bpp.clientregistryservice.dto.ClientDTO;
import com.bpp.clientregistryservice.repository.ClientRepository;

/**
 * @author Adalarasu
 *
 */
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class ClientRegistryServiceTest {
	
	@InjectMocks
	ClientRegistryServiceImpl clientRegistryServiceImpl;
	
	@Mock
	ClientRepository clientRepository;
	
	@Mock
	ModelMapper modelMapper;
	
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); // this is needed for inititalizytion of mocks, if you use @Mock 
    }

	/**
	 * Test method for {@link com.bpp.clientregistryservice.service.ClientRegistryServiceImpl#validateClient(java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testValidateClientSuccess() throws Exception {
		String password = "ValidPassword";
		Client client = prepareClient(password);
		ClientDTO clientDTO = prepareClientDTO(password);
		when(clientRepository.findByUserName(Mockito.anyString())).thenReturn(client);
		when(modelMapper.map(client, ClientDTO.class)).thenReturn(clientDTO);
		clientRegistryServiceImpl.validateClient(client.getUserName(), password);
		verify(clientRepository,times(1)).findByUserName(Mockito.anyString());
		verify(modelMapper,times(1)).map(client, ClientDTO.class);
	}
	
	/**
	 * Test method for {@link com.bpp.clientregistryservice.service.ClientRegistryServiceImpl#validateClient(java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testValidateClientMismatch() throws Exception {
		String password = "ValidPassword";
		Client client = prepareClient(password);
		when(clientRepository.findByUserName(Mockito.anyString())).thenReturn(client);
		Exception exception = assertThrows(Exception.class, () -> {
			clientRegistryServiceImpl.validateClient(client.getUserName(), "InValidPassword");
	    });
		String expectedMessage = "Client validation Failed";
		assertEquals(expectedMessage, exception.getMessage());
		verify(clientRepository,times(1)).findByUserName(Mockito.anyString());
		verify(modelMapper,times(0)).map(client, ClientDTO.class);	
		
	}

	/**
	 * Test method for {@link com.bpp.clientregistryservice.service.ClientRegistryServiceImpl#addClient(com.bpp.clientregistryservice.dto.ClientDTO)}.
	 */
	@Test
	void testAddClient() {
		fail("Not yet implemented");
	}
	
	private Client prepareClient(String password) {
		Client client = new Client();
		client.setUserName("User Name");
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		client.setPassword(passwordEncoder.encode(password));
		return client;
		
	}
	
	private ClientDTO prepareClientDTO(String password) {
		ClientDTO client = new ClientDTO();
		client.setUserName("User Name");
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		client.setPassword(passwordEncoder.encode(password));
		System.out.println(client.getPassword());
		return client;
		
	}

}
