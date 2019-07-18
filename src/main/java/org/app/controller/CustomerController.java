package org.app.controller;

import java.util.List;

import org.app.model.Customer;
import org.app.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerService service;
	
	//1.Display Reg Page With Form Backing Object
	@RequestMapping("/reg")
	public String showReg(Model map) {
		
		//Form Backing Object
		map.addAttribute("customer", new Customer() );
		return "Register";
	}
	//2.On click Submit read from data and save into DB
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveData(
			@ModelAttribute Customer  customer,Model map) {
		
		//insert into DB
		Integer id=service.saveCustomer(customer);
		map.addAttribute("message","customer'"+id+"'Created");
		//Clear Form backing Object
		map.addAttribute("customer",new Customer());
		return "Register";
	}
	//3.Display all records in DB at UI
	@RequestMapping("/all")
	public String showAll(Model map) {
		//fetch data from DB
		List<Customer> cobs=service.getAllCustomers();
		//send data to UI
		map.addAttribute("list", cobs);
		return "Data";
	}
	
	//4.Fetch data based on id and display
	@RequestMapping("/view/{id}")
	public String viewOne(
			@PathVariable Integer id,
			Model map) 
	{
		Customer c=service.getOneCustomer(id);
		map.addAttribute("ob", c);
		return "View";
	}
	//5. delete row based on Id
		@RequestMapping("/delete/{id}")
		public String deleteOne(
				@PathVariable Integer id,
				Model map) 
		{
			service.deleteCustomer(id);
			//fetch all new data
			List<Customer> cobs=service.getAllCustomers();
			//send data to UI
			map.addAttribute("list", cobs);
			//success message 
			map.addAttribute("message", "Customer '"+id+"' deleted");
			return "Data";
		}
		//6.ShowEditPage with Object
		
		@RequestMapping("/edit/{id}")
		public String showEditPage(
				@PathVariable Integer id,ModelMap map) {
			
			  Customer c=service.getOneCustomer(id);
			  
			  map.addAttribute("customer",c);
			  map.addAttribute("MODE","EDIT");
					return "Register";
			
		}
		//7. Do Update Opration
		@RequestMapping(value="/update",method=RequestMethod.POST)
		public String doUpdate(
				@ModelAttribute Customer customer,ModelMap map) {
				
		Integer id=	customer.getCustId();
			Integer custId=service.updateCustomer(customer,id);
				
				map.addAttribute("customer",new Customer());
				map.addAttribute("message","Customer '"+custId+"' Updated!!");
				return "redirect:all";
		}
}
