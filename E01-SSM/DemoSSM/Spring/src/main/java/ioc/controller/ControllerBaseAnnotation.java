package ioc.controller;

import org.springframework.stereotype.Controller;

@Controller(value = "controllerBaseAnnotation")
public class ControllerBaseAnnotation {
    //@Autowired
    //@Qualifier(value = "annotation")
    //private String name;
    //@Autowired
    //@Qualifier(value = "base annotation")
    //private String description;

    public void printSelfDescription() {
        //System.out.println("ControllerBaseAnnotation==>  Name:"+this.name+"  Description:"+this.description);
        System.out.println("ControllerBaseAnnotation");
    }
}
