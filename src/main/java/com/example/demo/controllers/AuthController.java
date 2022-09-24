package com.example.demo.controllers;

import com.example.demo.model.entity.Client;
import com.example.demo.model.enumeration.RoleType;
import com.example.demo.services.IClientService;
import com.example.demo.services.Impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController extends AbstractController<Client, IClientService>{
    private final IClientService iClientService;

    @Autowired
    public AuthController(IClientService service) {
        super(service);//important
        this.iClientService = service;//important
    }


    @GetMapping("/login-error")
    public String login(Authentication authentication,
                        HttpServletRequest request,
                        Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("userRole", iClientService.getUserRole(authentication));
        return "login.html";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping("/sign")
    public String getRegistrationPage(@ModelAttribute("user") Client client) {
        System.out.println("from auth get req");
        return "registration.html";
    }

    public void authWithHttpServletRequest(HttpServletRequest request, String login, String password) {
        try {
            request.login(login, password);
        } catch (ServletException e) { }
    }

    @PostMapping("/sign")
    public String signCreate(HttpServletRequest request,
                             @RequestParam String email,
                             @RequestParam String login,
                             @RequestParam String name,
                             @RequestParam String numberPhone,
                             @RequestParam String address,
                             @RequestParam String password,
                             Model model) {
        System.out.println("signCreate");
        System.out.println(((ClientServiceImpl) iClientService).getClientByLogin(login));
        if  (((ClientServiceImpl) iClientService).getClientByLogin(login) != null) {
            model.addAttribute("Status", "user_exists");
            System.out.println("HERE");
            return "registration.html";
        }
        else {
            ((ClientServiceImpl) iClientService).
                    create(email, login, name, address, numberPhone,password, RoleType.USER.name());
            System.out.println("sign-else");
            authWithHttpServletRequest(request, login, password);
            return "redirect:/user/home";
        }
    }
}
