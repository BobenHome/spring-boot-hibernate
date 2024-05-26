package com.leslie.tung.controller;


import com.leslie.tung.dto.CustomerData;
import com.leslie.tung.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/customer/")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping("/save")
  public String customerSave(@RequestBody CustomerData customerData) {
    customerService.customerSave(customerData);
    return "success";
  }

  @PostMapping("/update")
  public String updateName(@RequestBody CustomerData customerData) {
    return "success";
  }

  @GetMapping("/list")
  public String list() {
    customerService.list();
    return "success";
  }
}
