package miu.edu.cs545waa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/buyer")
    public String buyer() {
        return "buyer/index";
    }

    @GetMapping("/seller")
    public String seller() {
        return "seller/index";
    }
}
