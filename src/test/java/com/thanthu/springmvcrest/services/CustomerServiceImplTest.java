package com.thanthu.springmvcrest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.thanthu.springmvcrest.api.v1.mapper.CustomerMapper;
import com.thanthu.springmvcrest.api.v1.model.CustomerDTO;
import com.thanthu.springmvcrest.domain.Customer;
import com.thanthu.springmvcrest.repositories.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

	@Mock
	CustomerRepository customerRepository;

	CustomerMapper customerMapper = CustomerMapper.INSTANCE;

	CustomerService customerService;

	static final Long ID = 1L;
	static final String FIRST_NAME = "Thanthu";
	static final String LAST_NAME = "Nair";
	Customer customer;
	CustomerDTO customerDTO;

	@BeforeEach
	public void setUp() throws Exception {
		customerService = new CustomerServiceImpl(customerMapper, customerRepository);

		customer = new Customer();
		customer.setId(ID);
		customer.setFirstname(FIRST_NAME);
		customer.setLastname(LAST_NAME);

		customerDTO = new CustomerDTO();
		customerDTO.setFirstname(FIRST_NAME);
		customerDTO.setLastname(LAST_NAME);
	}

	@Test
	public void getAllCustomers() throws Exception {
		// given
		when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));

		// when
		List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

		// then
		assertEquals(1, customerDTOS.size());

	}

	@Test
	public void getCustomerById() throws Exception {
		// given
		when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer));

		// when
		CustomerDTO customerDTO = customerService.getCustomerById(ID);

		assertEquals(FIRST_NAME, customerDTO.getFirstname());
	}

	@Test
	public void createNewCustomer() throws Exception {

		// given
		when(customerRepository.save(any(Customer.class))).thenReturn(customer);

		// when
		CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

		// then
		assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
		assertEquals("/api/v1/customers/" + ID, savedDto.getCustomerUrl());
	}

	@Test
	public void saveCustomerByDTO() throws Exception {
		// given
		Customer savedCustomer = new Customer();
		savedCustomer.setFirstname(customerDTO.getFirstname());
		savedCustomer.setLastname(customerDTO.getLastname());
		savedCustomer.setId(ID);

		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

		// when
		CustomerDTO savedDto = customerService.saveCustomerByDTO(ID, customerDTO);

		// then
		assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
		assertEquals("/api/v1/customers/" + ID, savedDto.getCustomerUrl());
	}

	@Test
	public void deleteCustomerById() throws Exception {
		customerRepository.deleteById(1L);
		verify(customerRepository, times(1)).deleteById(1L);
	}

}
