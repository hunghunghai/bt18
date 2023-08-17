package rikkei.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.model.Customer;
import rikkei.academy.service.CustomerServiceIMPL;
import rikkei.academy.service.ICustomerService;

import java.util.List;


@Controller
@RequestMapping(value = {"/", "/customer"})
public class CustomerController {
	private final ICustomerService customerService = new CustomerServiceIMPL();
	
	@GetMapping
	public String index(Model model) {
		List<Customer> customerList = customerService.findAll();
		model.addAttribute("customers", customerList);
		return "index";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute Customer customer) {
		customerService.save(customer);
		return "redirect:/";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("customer", new Customer());
		return "create";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable int id, Model model) {
		model.addAttribute("customer", customerService.findById(id));
		return "edit";
	}
	
	@PostMapping("/update")
	public String update(Customer customer) {
		customerService.update(customer.getId(), customer);
		return "redirect:/";
	}
	
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable int id) {
		customerService.remove(id);
		return "redirect:/";
	}
	
	@GetMapping("/{id}/view")
	public String view(@PathVariable int id, Model model) {
		model.addAttribute("customer", customerService.findById(id));
		return "view";
	}
	
}


