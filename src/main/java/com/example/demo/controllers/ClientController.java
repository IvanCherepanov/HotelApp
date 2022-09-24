package com.example.demo.controllers;


import com.example.demo.model.entity.Client;
import com.example.demo.model.entity.ContractMaintenance;
import com.example.demo.model.entity.Maintenance;
import com.example.demo.services.IClientService;
import com.example.demo.services.IContractMaintenanceService;
import com.example.demo.services.IMaintenanceService;
import com.example.demo.services.Impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping(value = "/client")
public class ClientController extends AbstractController<Client, IClientService>{
    private IClientService iUserService;
    private IMaintenanceService iMaintenanceService;
    private IContractMaintenanceService iContractMaintenanceService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    protected ClientController(IClientService service,IMaintenanceService iMaintenanceService, IContractMaintenanceService iContractMaintenanceService) {
        super(service);
        this.iUserService = service;//important
        this.iMaintenanceService = iMaintenanceService;
        this.iContractMaintenanceService =iContractMaintenanceService;

    }

    @GetMapping("/list")
    public String readingAll(Authentication authentication,
                             Model model) {
        System.out.println(90);
        model.addAttribute("users", service.getAll());
        System.out.println(100);
        System.out.println(iUserService.getUserRole(authentication));
        System.out.println(110);
        model.addAttribute("userRole", iUserService.getUserRole(authentication));
         model.addAttribute("userId", iUserService.getUserId(authentication));
        System.out.println(456);
        return "admin/admin-user.html";
    }

    @GetMapping("/new")
    public String createUser(Model model) {
        // create student object to hold student form data
        Client user = new Client();
        model.addAttribute("users", service.getAll());
        model.addAttribute("user", user);
        return "admin/admin-user-add.html";
    }

    @PostMapping("/create")
    public String saveUser(@ModelAttribute("user") Client user) {
        service.create(
                user.getEmail(),
                user.getFullName(),
                user.getName(),
                user.getPhone(),
                user.getAddress(),
                user.getPassword(),
                user.getRole());
        return "redirect:/client/list";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", service.findById(id));
        return "admin/admin-user-edit.html";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id,
                             @ModelAttribute("user") Client client,
                             Model model) {

        // get pet from database by id
        Client existingUser = ((Client) (((ClientServiceImpl) iUserService).loadUserByUsername(client.getName())));
        existingUser.setId(id);
        existingUser.setName(client.getName());
        existingUser.setFullName(client.getFullName());
        existingUser.setAddress(client.getAddress());
        existingUser.setPhone(client.getPhone());
        existingUser.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        existingUser.setEmail(client.getEmail());
        existingUser.setRole(client.getRole());
        System.out.println(5435);
        // save updated pet object
        service.update(id, existingUser);
        return "redirect:/client/list";
    }

    // handler method to handle delete student request

    @GetMapping("del/{id}")
    public String deleteUser(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/client/list";
    }

    @GetMapping("/admin")
    public String getAdmin(Authentication authentication,
                           Model model) {
        model.addAttribute("userTypeRole", iUserService.getUserRole(authentication));
        return "admin/admin-start.html";
    }

    @GetMapping("/home")
    public String getHome(Authentication authentication,
                          Model model) {
        //model.addAttribute("userTypeRole", iUserService.getUserRole(authentication));
        return "index.html";
    }

    @GetMapping("/newClientDoc")
    public String createItem(Model model) {
        ContractMaintenance contractMaintenance = new ContractMaintenance();
        model.addAttribute("maintenances", iMaintenanceService.getAll());
        model.addAttribute("contract", contractMaintenance);
        return "newMaintenance.html";

    }

    @PostMapping("/createClientDoc")
    public String saveItem(Authentication authentication,
                           @ModelAttribute("contract") ContractMaintenance contractMaintenance ) {
        //System.out.println(122);

        Long userId = ((Client) (((ClientServiceImpl) iUserService).loadUserByUsername(authentication.getName()))).getId();
        //System.out.println(userId);
        String dateString = (contractMaintenance.getInputTimeString());
        dateString = dateString.replace("T", " ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        //System.out.println(dateTime.toString());
        //iContractMaintenanceService.create(contractMaintenance);
        //System.out.println(233);
        iContractMaintenanceService.create(
                userId,
                contractMaintenance.getMaintenanceId(),
                dateTime);

        return "redirect:/client/home";
    }
}
