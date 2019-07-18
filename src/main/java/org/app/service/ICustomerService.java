package org.app.service;

import java.util.List;

import org.app.model.Customer;

public interface ICustomerService {
	
	public Integer saveCustomer(Customer c);
	public List<Customer> getAllCustomers();
	public Customer getOneCustomer(Integer custId);
	public  void deleteCustomer(Integer custId);
	public Integer updateCustomer(Customer c,Integer custId);

}
