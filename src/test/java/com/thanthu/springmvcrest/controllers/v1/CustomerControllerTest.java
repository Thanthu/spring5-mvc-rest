package com.thanthu.springmvcrest.controllers.v1;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.thanthu.springmvcrest.api.v1.model.CustomerDTO;
import com.thanthu.springmvcrest.exxceptions.ResourceNotFoundException;
import com.thanthu.springmvcrest.handlers.RestResponseEntityExceptionHandler;
import com.thanthu.springmvcrest.services.CustomerService;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest extends AbstractRestControllerTest {

	@Mock
	CustomerService customerService;

	@InjectMocks
	CustomerController customerController;

	MockMvc mockMvc;

	static final String CUSTOMER_API_URL = CustomerController.BASE_URL + "/";
	static final Long ID = 1L;
	static final String FIRST_NAME = "Thanthu";
	static final String LAST_NAME = "Nair";
	static final String URL = CUSTOMER_API_URL + ID;
	CustomerDTO customerDto;

	@BeforeEach
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(customerController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler()).build();

		customerDto = new CustomerDTO();
		customerDto.setFirstname(FIRST_NAME);
		customerDto.setLastname(LAST_NAME);
		customerDto.setCustomerUrl(URL);
	}

	@Test
	public void testListCustomers() throws Exception {
		// given
		when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customerDto));

		mockMvc.perform(
				get(CUSTOMER_API_URL).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.customers", hasSize(1)));
	}

	@Test
	public void testGetCustomerById() throws Exception {

		// given
		when(customerService.getCustomerById(anyLong())).thenReturn(customerDto);

		// when
		mockMvc.perform(
				get(CUSTOMER_API_URL + ID).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.firstname", equalTo(FIRST_NAME)));
	}

	@Test
	public void createNewCustomer() throws Exception {
		// given
		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstname(customerDto.getFirstname());
		returnDTO.setLastname(customerDto.getLastname());
		returnDTO.setCustomerUrl(customerDto.getCustomerUrl());

		when(customerService.createNewCustomer(any(CustomerDTO.class))).thenReturn(returnDTO);

		// when/then
		mockMvc.perform(post(CUSTOMER_API_URL).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(customerDto)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.firstname", equalTo(FIRST_NAME)))
				.andExpect(jsonPath("$.customer_url", equalTo(URL)));
	}

	@Test
	public void testUpdateCustomer() throws Exception {
		// given

		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstname(customerDto.getFirstname());
		returnDTO.setLastname(customerDto.getLastname());
		returnDTO.setCustomerUrl(customerDto.getCustomerUrl());

		when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

		// when/then
		mockMvc.perform(put(CUSTOMER_API_URL + ID).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(customerDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstname", equalTo(FIRST_NAME)))
				.andExpect(jsonPath("$.lastname", equalTo(LAST_NAME)))
				.andExpect(jsonPath("$.customer_url", equalTo(URL)));
	}

	@Test
	public void testPatchCustomer() throws Exception {

		// given
		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstname(customerDto.getFirstname());
		returnDTO.setLastname("Flintstone");
		returnDTO.setCustomerUrl(customerDto.getCustomerUrl());

		when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

		mockMvc.perform(patch(CUSTOMER_API_URL + ID).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(customerDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstname", equalTo(FIRST_NAME)))
				.andExpect(jsonPath("$.lastname", equalTo("Flintstone")))
				.andExpect(jsonPath("$.customer_url", equalTo(URL)));
	}

	@Test
	public void testDeleteCustomer() throws Exception {
		mockMvc.perform(delete(CUSTOMER_API_URL + ID).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		verify(customerService, times(1)).deleteCustomerById(anyLong());
	}

	@Test
	public void testNotFoundException() throws Exception {
		when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

		mockMvc.perform(get(CustomerController.BASE_URL + "/222").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

}
