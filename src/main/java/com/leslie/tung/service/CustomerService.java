package com.leslie.tung.service;

import com.leslie.tung.dto.CustomerData;
import com.leslie.tung.entity.Customer;
import com.leslie.tung.mapper.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    private DemoService demoService;

    @Autowired
    private ApplicationContext applicationContext;


    @PersistenceContext
    EntityManager entityManager;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void customerSave(CustomerData customerData) {
        log.info("customerSave current thread: {}", Thread.currentThread().getId());
        CustomerService bean = applicationContext.getBean(CustomerService.class);
        CompletableFuture.runAsync(() -> {
            bean.customerSaveV2(customerData);
        });
        log.info("customerSave complete.");
    }

    @Transactional
    public void customerSaveV2(CustomerData customerData) {
        log.info("customerSaveV2 current thread: {}", Thread.currentThread().getId());
        Customer customer = populateCustomerEntity(customerData);
        customerRepository.save(customer);
        try {
            log.info("添加日志111");
            demoService.test();
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }
        log.info("customerSaveV2 complete.");
    }


    @Modifying
    @Transactional
    public void test() {
        for (int i = 1; i <= 3; i++) {
            entityManager.persist(
                    new Customer(
                            "High-Performance Java Persistence",
                            String.format("Part %d", i),
                            "leslie_tung@163.com")
            );
        }
    }

    public void list() {
        Customer customer = customerRepository.findById(1L).orElse(null);
        System.out.println(customer);
    }

    private Customer populateCustomerEntity(CustomerData customerData) {
        Customer customer = new Customer();
        customer.setFirstName(customerData.getFirstName());
        customer.setLastName(customerData.getLastName());
        customer.setEmail(customerData.getEmail());
        return customer;
    }
}
