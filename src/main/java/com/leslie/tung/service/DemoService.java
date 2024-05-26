package com.leslie.tung.service;

import com.leslie.tung.entity.Customer;
import com.leslie.tung.mapper.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dongliangliang
 * @date 2023/3/31 15:51:16
 */
@Service
@Slf4j
public class DemoService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void test() {
        log.info("DemoService current thread: {}", Thread.currentThread().getId());
        customerRepository.save(
                new Customer(
                        "High-Performance Java Persistence",
                        "java",
                        "leslie_tung@163.com")
        );
        int a = 10 / 0;
    }
}
