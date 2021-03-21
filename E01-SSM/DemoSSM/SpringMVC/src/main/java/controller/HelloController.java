package controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {


    @RequestMapping("/test01")
    public String test01(Model model) {
        System.out.println("/hello/test01 is come in!");

        model.addAttribute("msg","hello,kyle!");

        return "success";
    }

}
