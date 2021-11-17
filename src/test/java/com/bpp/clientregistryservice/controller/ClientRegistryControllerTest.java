/**
 * 
 */
package com.bpp.clientregistryservice.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bpp.clientregistryservice.dto.ClientDTO;
import com.bpp.clientregistryservice.service.IClientRegistryService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Adalarasu
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ClientRegistryController.class)
class ClientRegistryControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private IClientRegistryService clientRegistryService;

	/**
	 * Test method for
	 * {@link com.bpp.clientregistryservice.controller.ClientRegistryController#checkStatus()}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testCheckStatus() throws Exception {
		mvc.perform(get("/client/check/status").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	/**
	 * Test method for
	 * {@link com.bpp.clientregistryservice.controller.ClientRegistryController#validateClient(com.bpp.clientregistryservice.dto.ClientDTO)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testValidateClient() throws Exception {
		ClientDTO clientDTO = prepareClientDTO();
		when(clientRegistryService.validateClient(Mockito.anyString(), Mockito.anyString())).thenReturn(clientDTO);
		mvc.perform(post("/client/addClient").content(asJsonString(clientDTO)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		verify(clientRegistryService.validateClient(Mockito.anyString(), Mockito.anyString()),times(1));
	}

	/**
	 * Test method for
	 * {@link com.bpp.clientregistryservice.controller.ClientRegistryController#addClient(com.bpp.clientregistryservice.dto.ClientDTO)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testAddClientSuccess() throws Exception {
		ClientDTO clientDTO = prepareClientDTO();
		when(clientRegistryService.addClient((ClientDTO) Mockito.any())).thenReturn(clientDTO);
		mvc.perform(post("/client/addClient").content(asJsonString(clientDTO)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		verify(clientRegistryService.addClient((ClientDTO) Mockito.any()),times(1));
	}
	
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private ClientDTO prepareClientDTO() {
		ClientDTO clientDTO = new ClientDTO();
		return clientDTO;
	}

}
