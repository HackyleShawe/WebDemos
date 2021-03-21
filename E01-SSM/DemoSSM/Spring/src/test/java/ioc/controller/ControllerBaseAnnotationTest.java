package ioc.controller;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ControllerBaseAnnotationTest {

    @Test
    public void test01() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("Spring-Annotation.xml");
        ControllerBaseAnnotation controllerBaseAnnotation = ac.getBean("controllerBaseAnnotation", ControllerBaseAnnotation.class);
        controllerBaseAnnotation.printSelfDescription(); //输出：ControllerBaseAnnotation
    }
}
