package com.thanthu.springmvcrest.controllers.v1;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.thanthu.springmvcrest.services.CustomerService;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

	@Mock
	CustomerService customerService;

	@InjectMocks
	CustomerController customerController;

	MockMvc mockMvc;

	static final Long ID = 1L;
	static final String FIRST_NAME = "Thanthu";
	static final String LAST_NAME = "Nair";
	CustomerDTO customerDto;

	@BeforeEach
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

		customerDto = new CustomerDTO();
		customerDto.setFirstname(FIRST_NAME);
		customerDto.setLastname(LAST_NAME);
		customerDto.setCustomerUrl("/api/v1/customer/" + ID);
	}

	@Test
	public void testListCustomers() throws Exception {
		// given
		when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customerDto));

		mockMvc.perform(get("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.customers", hasSize(1)));
	}

	@Test
	public void testGetCustomerById() throws Exception {

		// given
		when(customerService.getCustomerById(anyLong())).thenReturn(customerDto);

		// when
		mockMvc.perform(get("/api/v1/customers/" + ID).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.firstname", equalTo(FIRST_NAME)));
	}

}
