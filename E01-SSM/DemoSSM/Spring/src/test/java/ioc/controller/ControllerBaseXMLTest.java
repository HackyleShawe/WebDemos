package ioc.controller;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ControllerBaseXMLTest {

    @Test
    public void test01() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("Spring-Xml.xml");
        ControllerBaseXML controllerBaseXML = ac.getBean("controllerBaseXML",ControllerBaseXML.class);

        controllerBaseXML.printSelfDescription(); //输出：ControllerBaseXML==> Name:XML  Description:base XML
    }
}
