package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "home";
    }

    @PostMapping(value ="/user/create")
    public String createUser(@RequestParam(name = "name") String name,
                             @RequestParam(name = "city") String city,
                             Model model) {

        userService.add(name, city);
        model.addAttribute("users", userService.getAllUsers());
        return "home";
    }

    @PostMapping(value ="/user/delete")
    public String deleteUser(@RequestParam(name = "id") Long id, Model model) {
        userService.remove(id);
        model.addAttribute("users", userService.getAllUsers());
        return "home";
    }

    @GetMapping(value ="/user/searchUserByCity")
    public String searchUser(@RequestParam(name = "city") String city, Model model) {
        model.addAttribute("users", userService.getUsersByCity(city));
        return "home";
    }

    @GetMapping(value ="/user/edit")
    public String editUser(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userForEdit", userService.getUserById(id));
        return "home";
    }

    @PostMapping(value ="/user/update")
    public String updateUser(@RequestParam(name = "id") Long id,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "city") String city,
                             Model model) {

        userService.updateUser(id, name, city);
        model.addAttribute("users", userService.getAllUsers());
        return "home";
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("title", "CRUD-Operation");
        model.addAttribute("users", userService.getAllUsers());
        return "home";
    }
}
