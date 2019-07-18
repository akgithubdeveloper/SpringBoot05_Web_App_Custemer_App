package org.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.app.model.Customer;
import org.app.repo.CustomerRepository;
import org.app.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository repo;
	
	@Transactional
	@Override
	public Integer saveCustomer(Customer c) {
		
		/*Customer c1=repo.save(c);
		 Integer id =c1.getCudtId();
		 return id;
		*/
		return repo.save(c).getCustId();
	}

	@Transactional(readOnly=true)
	@Override
	public List<Customer> getAllCustomers() {
		
		return repo.findAll();
	}

	@Cacheable(value="emp-cache",key="#custId")
	@Transactional(readOnly=true)
	@Override
	public Customer getOneCustomer(Integer custId) {
	   
		Optional<Customer> c=repo.findById(custId);
		if(c.isPresent()) {
			return c.get();
		}
		return null;
	}

	
	@Transactional
	@CacheEvict(value="emp-cache",key="#custId")
	@Override
	public void deleteCustomer(Integer custId) {
		
		repo.deleteById(custId);
		
	}
	@Transactional
	@CachePut(value="customer-cache",key="#custId")
	@Override
	public Integer updateCustomer(Customer c,Integer custId) {
		
		return repo.save(c).getCustId();
		
	}

}
