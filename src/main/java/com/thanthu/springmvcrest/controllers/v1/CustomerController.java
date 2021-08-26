package com.thanthu.springmvcrest.controllers.v1;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.thanthu.springmvcrest.api.v1.model.CustomerDTO;
import com.thanthu.springmvcrest.api.v1.model.CustomerListDTO;
import com.thanthu.springmvcrest.services.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(description = "APIs to interact with Customers", name = "Customer APIs")
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

	public static final String BASE_URL = "/api/v1/customers";

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Operation(summary = "Get list of Customers", description = "Some additional info about this API can be provided here")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CustomerListDTO getListofCustomers() {
		return new CustomerListDTO(customerService.getAllCustomers());
	}

	@Operation(summary = "Get details of a Customer.")
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO getCustomerById(@PathVariable Long id) {
		return customerService.getCustomerById(id);
	}

	@Operation(summary = "Create Customer.")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDTO createNewCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
		return customerService.createNewCustomer(customerDTO);
	}

	@Operation(summary = "Update Customer.")
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDTO customerDTO) {
		return customerService.saveCustomerByDTO(id, customerDTO);
	}

	@Operation(summary = "Make partial updates on Customer.")
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO patchCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDTO customerDTO) {
		return customerService.patchCustomer(id, customerDTO);
	}

	@Operation(summary = "Delete Customer.")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomerById(id);
	}

}
