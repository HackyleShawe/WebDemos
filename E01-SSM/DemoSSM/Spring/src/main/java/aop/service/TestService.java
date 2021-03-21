package aop.service;

import org.springframework.stereotype.Service;

@Service(value = "testService")
public class TestService {

    public void testMethod() {
        System.out.println("testService...");
    }
}
