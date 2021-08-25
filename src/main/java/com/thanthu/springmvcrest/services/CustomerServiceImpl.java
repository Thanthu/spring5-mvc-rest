package com.thanthu.springmvcrest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thanthu.springmvcrest.api.v1.mapper.CustomerMapper;
import com.thanthu.springmvcrest.api.v1.model.CustomerDTO;
import com.thanthu.springmvcrest.domain.Customer;
import com.thanthu.springmvcrest.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerMapper customerMapper;
	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
		this.customerMapper = customerMapper;
		this.customerRepository = customerRepository;
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		return customerRepository.findAll().stream().map(customer -> {
			CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
			customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
			return customerDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {
		return customerRepository.findById(id).map(customerMapper::customerToCustomerDTO)
				.orElseThrow(() -> new RuntimeException(String.format("Customer not found for ID '%d'", id)));
	}

	@Override
	public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
		return saveAndReturnDTO(customerMapper.customerDtoToCustomer(customerDTO));
	}

	private CustomerDTO saveAndReturnDTO(Customer customer) {
		Customer savedCustomer = customerRepository.save(customer);
		CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);
		returnDto.setCustomerUrl("/api/v1/customers/" + savedCustomer.getId());
		return returnDto;
	}

	@Override
	public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
		Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
		customer.setId(id);
		return saveAndReturnDTO(customer);
	}

	@Override
	public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
		return customerRepository.findById(id).map(customer -> {
			if (customerDTO.getFirstname() != null) {
				customer.setFirstname(customerDTO.getFirstname());
			}
			if (customerDTO.getLastname() != null) {
				customer.setLastname(customerDTO.getLastname());
			}
			CustomerDTO returnDto = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
            returnDto.setCustomerUrl("/api/v1/customers/" + id);
            return returnDto;
		}).orElseThrow(() -> new RuntimeException(String.format("Customer not found for ID '%d'", id)));
	}

}
