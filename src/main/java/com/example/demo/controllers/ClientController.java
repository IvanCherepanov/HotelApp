package com.example.demo.controllers;


import com.example.demo.model.dto.RequestRoom;
import com.example.demo.model.entity.*;
import com.example.demo.services.*;
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
import java.util.List;

@Controller
@RequestMapping(value = "/client")
public class ClientController extends AbstractController<Client, IClientService>{
    private IClientService iUserService;
    private IMaintenanceService iMaintenanceService;
    private IContractMaintenanceService iContractMaintenanceService;
    private ICardService iCardService;
    private IFoodService iFoodService;
    private ICleaningService iCleaningService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    protected ClientController(IClientService service,
                               IMaintenanceService iMaintenanceService,
                               IContractMaintenanceService iContractMaintenanceService,
                               IFoodService iFoodService,
                               ICleaningService iCleaningService,
                               ICardService iCardService) {
        super(service);
        this.iUserService = service;//important
        this.iMaintenanceService = iMaintenanceService;
        this.iContractMaintenanceService =iContractMaintenanceService;
        this.iCardService = iCardService;
        this.iFoodService = iFoodService;
        this.iCleaningService = iCleaningService;
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
        model.addAttribute("userTypeRole", iUserService.getUserRole(authentication));
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
        return "redirect:/contractMaintenance/myList";
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
        model.addAttribute("input", inputDate.toString());
        model.addAttribute("output", outputDate);
        model.addAttribute("capacity", requestRoom.getCapacity());
        return "/rooms.html";
    }

    @PostMapping("/createClientRoom")
    public String saveRoom(Authentication authentication,
                           @RequestParam(name = "inputThing", required = false) String input,
                           @RequestParam(name = "outputThing", required = false) String output,
                           @RequestParam Long id) {
        Long userId = ((Client) (((ClientServiceImpl) iUserService).loadUserByUsername(authentication.getName()))).getId();
        System.out.println(userId);
        System.out.println(input);
        System.out.println(output);
//        System.out.println(requestRoom.getCapacity());
//        System.out.println(requestRoom.getRangeDate());
        System.out.println(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inputDate = LocalDate.parse(input, formatter);
        LocalDate outputDate = LocalDate.parse(output, formatter);
        iCardService.create(inputDate,outputDate,userId,id);
        return "redirect:/client/myRoomList";
    }

    @GetMapping("/myRoomList")
    public String readingAllByClient(Authentication authentication,
                                     Model model) {
        Long userId = ((Client) (((ClientServiceImpl) iUserService).loadUserByUsername(authentication.getName()))).getId();
        model.addAttribute("cards", iCardService.getListRoomCostById(userId));
        //List<Object[]> temp = iContractMaintenanceService.getListById(userId);
        System.out.println(123);
        return "cardsUser.html";
    }

    @GetMapping("delRoom/{id}")
    public String deleteClientRoom(@PathVariable Long id) {
        iCardService.delete(id);
        return "redirect:/client/myRoomList";
    }

    @GetMapping("/editRoom/{id}")
    public String editClientRoom(Authentication authentication,@PathVariable Long id, Model model) {
        Long userId = ((Client) (((ClientServiceImpl) iUserService).loadUserByUsername(authentication.getName()))).getId();
        Object[] temp = iCardService.getObjectById(id,iCardService.getListRoomCostById(userId));
        Card card = new Card();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inputDate = LocalDate.parse(temp[1].toString(), formatter);
        LocalDate outputDate = LocalDate.parse(temp[2].toString(), formatter);
        card.setInputDate(inputDate);
        card.setOutputDate(outputDate);

        card.setRoomId(Long.valueOf(temp[3].toString()));
        card.setFoodId(Long.valueOf(temp[5].toString()));
        card.setCleaningId(Long.valueOf(temp[8].toString()));

        card.setId(Long.valueOf(temp[10].toString()));

        model.addAttribute("card", card);
        model.addAttribute("foods", iFoodService.getAll());
        model.addAttribute("cleanings",iCleaningService.getAll());
        return "editRoom.html";
    }

    @PostMapping("/updateRoom/{id}")
    public String updateClientRoom(@PathVariable Long id,
                             @ModelAttribute("card") Card card,
                             Model model) {

        // get pet from database by id
        Card existingCard = iCardService.findById(id);
        existingCard.setFoodId(card.getFoodId());
        existingCard.setCleaningId(card.getCleaningId());
        System.out.println(5435);
        // save updated pet object
        iCardService.update(id, existingCard);
        return "redirect:/client/myRoomList";
    }
}
