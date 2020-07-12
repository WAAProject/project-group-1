package miu.edu.cs545waa.controller;

import miu.edu.cs545waa.domain.Seller;
import miu.edu.cs545waa.domain.User;
import miu.edu.cs545waa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping
    public String getAdminIndex() {
        return "admin/index";
    }

    @GetMapping("/accounts")
    public String getAccounts(Model model) {
        List<User> userList = userService.getAll();
        System.out.println(userList);
        model.addAttribute("users", userList);
        return "admin/accounts";
    }

    @GetMapping("/sellers")
    public String getSellerAccounts(Model model) {
        List<User> sellerList = userService.findByType("Seller");
        System.out.println(sellerList);
        model.addAttribute("sellers", sellerList);
        return "admin/sellers";
    }
}
