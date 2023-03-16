package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("titleText", "CRUD-Operation");
        model.addAttribute("H1_Text", "Работа с CRUD операциями.");
        return "home";
    }

    @GetMapping("/users")
    public String geUsersPage(Model model) {
        model.addAttribute("titleText", "CRUD-Operation");
        model.addAttribute("H1_Text", "Список пользователей");
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "Добавление пользователя");
        model.addAttribute("H1", "Добавление нового пользователя.");
        return "create";
    }

    @PostMapping(value = "/user/create")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        userService.add(user);
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/user/delete")
    public String getDeletePage(Model model) {
        model.addAttribute("title", "Удаление пользователя");
        model.addAttribute("H1", "Удаление пользователя по id.");
        return "delete";
    }

    @PostMapping(value = "/user/delete")
    public String deleteUser(@RequestParam(name = "id") Long id, Model model) {
        userService.remove(id);
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/user/edit")
    public String getEditPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "Редактирование пользователя");
        model.addAttribute("H1", "Редактирование пользователя.");
        return "edit";
    }

    @PostMapping(value = "/user/edit")
    public String editUser(@ModelAttribute("user") User user, Model model) {
        userService.updateUser(user);
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/user/find")
    public String getFindPage(Model model) {
        model.addAttribute("title", "Поиск пользователя");
        model.addAttribute("H1", "Поиск пользователя по id.");
        return "find";
    }

    @PostMapping(value = "/user/find")
    public String findUser(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("users", userService.getUserById(id));
        return "users";
    }
}
