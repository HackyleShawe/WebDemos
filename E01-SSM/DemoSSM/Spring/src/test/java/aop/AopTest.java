package aop;

import aop.service.TestService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {

    @Test
    public void test01() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("Spring-aop.xml");
        TestService testService = ac.getBean("testService",TestService.class);
        testService.testMethod();
    }
}
