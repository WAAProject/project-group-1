package miu.edu.cs545waa.controller;

import miu.edu.cs545waa.domain.Buyer;
import miu.edu.cs545waa.domain.Seller;
import miu.edu.cs545waa.domain.User;
import miu.edu.cs545waa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            @RequestParam(value = "registerSuccess", required = false) String registerSuccess,
                            Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username or Password is incorrect !!";
        }
        if (logout != null) {
            errorMessage = "You have been successfully logged out !!";
        }

        if (registerSuccess != null) {
            model.addAttribute("registerSuccess", registerSuccess);
        }

        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @GetMapping("/denied")
    public String accessDenied(){
        return "accessDenied";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute User user) {
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute User user, BindingResult bindingResult, Model model, @RequestParam String userType, RedirectAttributes redirectAttributes) {

        System.out.println(userType);

        if (userService.findByEmail(user.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.user","This email address has been registered already");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (userType.equalsIgnoreCase("buyer")) {
            Buyer buyer = new Buyer(user.getFirstName(), user.getLastName(), user.getEmail(), passwordEncoder.encode(user.getPassword()));
            userService.save(buyer);
        } else if (userType.equalsIgnoreCase("seller")){
            Seller seller = new Seller(user.getFirstName(), user.getLastName(), user.getEmail(), passwordEncoder.encode(user.getPassword()));
            userService.save(seller);
        }

         redirectAttributes.addFlashAttribute("registerSuccess", "You have been registered successfully. Now you can login");
         return "redirect:/login";
    }
}
