package com.example.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.Customer;
import com.example.service.CustomerService;
import com.example.service.LoginUserDetails;

@Controller
@RequestMapping("customers")
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@ModelAttribute
	CustomerForm setUpForm() {
		return new CustomerForm();
	}

	@GetMapping
	String list(Model model) {
		List<Customer> customers = customerService.findAll();
		model.addAttribute("customers", customers);
		return "customers/list";
	}

	@GetMapping(path = "create")
	String returnCreate() {
		return "redirect:/customers";
	}

	@PostMapping(path = "create")
	String create(@Validated CustomerForm form, BindingResult result, Model model,
		@AuthenticationPrincipal	LoginUserDetails userDetails) {
		System.out.println("create");
		if(result.hasErrors()) {
			return list(model);
		}
		Customer customer = new Customer();
		BeanUtils.copyProperties(form, customer);
		customerService.create(customer, userDetails.getUser());
		return  "redirect:/customers";
	}

	@GetMapping(path = "edit")
	String returnEdit() {
		return "redirect:/customers";
	}

	@GetMapping(path = "edit", params = "form")
	String editForm(@RequestParam Integer id, CustomerForm form) {
		System.out.println("editForm");
		Optional<Customer> customer = customerService.findById(id);
		BeanUtils.copyProperties(customer.orElse(null), form);
		return "customers/edit";
	}

	@PostMapping(path = "edit")
	String edit(@RequestParam Integer id, @Validated CustomerForm form, BindingResult result,
			@AuthenticationPrincipal LoginUserDetails userDetails) {
		System.out.println("edit");
		if(result.hasErrors()) {
			return editForm(id, form);
		}
		Customer customer = new Customer();
		BeanUtils.copyProperties(form, customer);
		customer.setId(id);
		customerService.upDate(customer, userDetails.getUser());
		return "redirect:/customers";
	}

	@PostMapping(path = "edit", params = "goToTop")
	String goToTop() {
		System.out.println("goToTop");
		return "redirect:/customers";
	}

	@PostMapping(path = "delete")
	String delete(@RequestParam Integer id) {
		System.out.println("delete");
		customerService.delete(id);
		return "redirect:/customers";
	}
}






