package com.leslie.tung;

import com.leslie.tung.dto.CustomerData;
import com.leslie.tung.entity.Customer;
import com.leslie.tung.mapper.CustomerRepository;
import com.leslie.tung.service.CustomerService;
import com.leslie.tung.util.TransactionUtil;
import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class SpringBootHibernateApplicationTests {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionUtil txUtil;

    @Resource
    protected SessionFactory sessionFactory;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testSaveMany() throws Exception {
        List<Customer> list = new ArrayList<>();
        long begin = System.currentTimeMillis();
        log.info("batch insert sql begin: {}", begin);
        for (int i = 1; i <= 100000; i++) {
            Customer customer = new Customer();
            customer.setFirstName("朱温" + i);
            customer.setLastName("梁" + i);
            customer.setEmail("梁+" + i + "+@163.com");
            list.add(customer);
            if (i % 1000 == 0) {
                customerRepository.saveAllAndFlush(list);
                list.clear();
            }
        }
        long end = System.currentTimeMillis() - begin;
        log.info("batch insert sql begin: {}", end);
    }

    @Test
    public void testTransaction() {

        customerService.customerSave(new CustomerData("朱温", "梁", "梁@163.com"));
        // 让异步线程走完才能测出效果
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testJavaStreamGroup() {
        List<Customer> customers = customerRepository.findAll();

        Map<String, Customer> customerMap = customers.parallelStream().collect(
                Collectors.toMap(Customer::getLastName, Function.identity(),
                        (c1, c2) -> c1.getId() > c2.getId() ? c1 : c2));
        System.out.println(customerMap);
    }
}
