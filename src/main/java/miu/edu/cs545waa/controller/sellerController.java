package miu.edu.cs545waa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class sellerController {

    @GetMapping(value = "/sellerIndex")
    public String sellerPage(){
        return "sellerIndex";
    }
}