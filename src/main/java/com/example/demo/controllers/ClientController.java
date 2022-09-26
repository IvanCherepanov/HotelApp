package com.example.demo.controllers;


import com.example.demo.model.dto.RequestRoom;
import com.example.demo.model.entity.Client;
import com.example.demo.model.entity.ContractMaintenance;
import com.example.demo.model.entity.Maintenance;
import com.example.demo.services.ICardService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

@Controller
@RequestMapping(value = "/client")
public class ClientController extends AbstractController<Client, IClientService>{
    private IClientService iUserService;
    private IMaintenanceService iMaintenanceService;
    private IContractMaintenanceService iContractMaintenanceService;
    private ICardService iCardService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    protected ClientController(IClientService service,
                               IMaintenanceService iMaintenanceService,
                               IContractMaintenanceService iContractMaintenanceService,
                               ICardService iCardService) {
        super(service);
        this.iUserService = service;//important
        this.iMaintenanceService = iMaintenanceService;
        this.iContractMaintenanceService =iContractMaintenanceService;
        this.iCardService = iCardService;

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
        return "newmaintenance.html";

    }

    @PostMapping("/createClientDoc")
    public String saveItem(Authentication authentication,
                           @ModelAttribute("contract") ContractMaintenance contractMaintenance ) {
        Long userId = ((Client) (((ClientServiceImpl) iUserService).loadUserByUsername(authentication.getName()))).getId();
        String dateString = contractMaintenance.getInputDate();
        dateString = dateString.replace("T", " ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        iContractMaintenanceService.create(
                userId,
                contractMaintenance.getMaintenanceId(),
                dateTime);
        return "redirect:/client/home";
    }

    @GetMapping("/newRoomContract")
    public String addRoomContract(Model model) {
        RequestRoom requestRoom = new RequestRoom();
        model.addAttribute("dateAndCapacity", requestRoom);
        return "findRoom.html";
    }

    @GetMapping("/rooms")
    public String roomsAvailable(Authentication authentication,
                                 @ModelAttribute("dateAndCapacity") RequestRoom requestRoom ,
                                 final RedirectAttributes redirectAttributes,
                                 Model model) {
        String twoDate = (requestRoom.getRangeDate());
        twoDate=twoDate.replace(" ","");
        String[] parts = twoDate.split("-");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = parts[0];
        LocalDate inputDate = LocalDate.parse(date, formatter);
        System.out.println(inputDate);

        String date1 = parts[1];
        LocalDate outputDate = LocalDate.parse(date1, formatter);
        System.out.println(outputDate);

        System.out.println(requestRoom.getCapacity());
        model.addAttribute("rooms", iCardService.getListByParam(
                inputDate,
                outputDate,
                requestRoom.getCapacity()
        ) );
        redirectAttributes.addFlashAttribute("dateAndCapacity", requestRoom);
        return "rooms.html";
    }

    @PostMapping("/createClientRoom")
    public String saveRoom(Authentication authentication,
                           @ModelAttribute("dateAndCapacity") RequestRoom requestRoom ) {
        Long userId = ((Client) (((ClientServiceImpl) iUserService).loadUserByUsername(authentication.getName()))).getId();
        return "redirect:/client/home";
    }
}
