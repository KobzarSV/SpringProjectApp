package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.exception.UserEmailAlreadyExistException;
import ua.goit.model.UserRole;
import ua.goit.model.UserStatus;
import ua.goit.model.dto.UserDto;
import ua.goit.service.UserService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/registration")
    public String getRegistrationForm() {
        return "registration";
    }

    @PostMapping(path = "registration")
    public String registerUser(@ModelAttribute("userForm")
                               @Valid UserDto user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        try {
            userService.save(user);
        } catch (UserEmailAlreadyExistException ex) {
            model.addAttribute("message", ex.getMessage());
            return "registration";
        }
        return "login";
    }

    @ModelAttribute("userForm")
    public UserDto getDefaultUserDto() {
        return new UserDto();
    }

    @GetMapping(path = "/all")
    public String getAllUsers(Model model) {
        List<UserDto> users = userService.findAll();
        model.addAttribute("users", users);
        return "allUsers";
    }

    @GetMapping(path = "/form/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addUserForm(Model model) {
        List<UserRole> userRoles = Arrays.asList(UserRole.values());
        model.addAttribute("userRoles", userRoles);
        List<UserStatus> userStatuses = Arrays.asList(UserStatus.values());
        model.addAttribute("userStatuses", userStatuses);
        return "addUserForm";
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addUser(@ModelAttribute("userForm")
                          @Valid UserDto user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<UserRole> userRoles = Arrays.asList(UserRole.values());
            model.addAttribute("userRoles", userRoles);
            List<UserStatus> userStatuses = Arrays.asList(UserStatus.values());
            model.addAttribute("userStatuses", userStatuses);
            return "addUserForm";
        }
        try {
            userService.add(user);
        } catch (UserEmailAlreadyExistException ex) {
            model.addAttribute("message", ex.getMessage());
            List<UserRole> userRoles = Arrays.asList(UserRole.values());
            model.addAttribute("userRoles", userRoles);
            List<UserStatus> userStatuses = Arrays.asList(UserStatus.values());
            model.addAttribute("userStatuses", userStatuses);
            return "addUserForm";
        }
        return "redirect:/users/all";
    }

    @GetMapping(path = "/form/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateUserForm(@PathVariable("id") UUID id, Model model) {
        UserDto user = userService.findById(id);
        model.addAttribute("user", user);
        List<UserRole> userRoles = Arrays.asList(UserRole.values());
        model.addAttribute("userRoles", userRoles);
        List<UserStatus> userStatuses = Arrays.asList(UserStatus.values());
        model.addAttribute("userStatuses", userStatuses);
        return "updateUserForm";
    }

    @PostMapping(path = "/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateUser(@PathVariable("id") UUID id, @ModelAttribute("user") @Valid UserDto userDto,
                                   BindingResult bindingResult, ModelAndView model) {

        if (bindingResult.hasErrors()) {
            List<UserRole> userRoles = Arrays.asList(UserRole.values());
            model.addObject("userRoles", userRoles);
            List<UserStatus> userStatuses = Arrays.asList(UserStatus.values());
            model.addObject("userStatuses", userStatuses);
            model.setViewName("updateUserForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        try {
            UserDto user = userService.findById(id);
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setUserRole(userDto.getUserRole());
            user.setUserStatus(userDto.getUserStatus());
            user.setId(userDto.getId());
            if (user.getEmail().equals(userDto.getEmail())) {
                userService.delete(userDto);
            }
            userService.update(user);
            model.setViewName("redirect:/users/all");
        } catch (UserEmailAlreadyExistException ex) {
            model.addObject("message", ex.getMessage());
            List<UserRole> userRoles = Arrays.asList(UserRole.values());
            model.addObject("userRoles", userRoles);
            List<UserStatus> userStatuses = Arrays.asList(UserStatus.values());
            model.addObject("userStatuses", userStatuses);
            model.setViewName("updateUserForm");
        }
        return model;
    }

    @GetMapping(path = "/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUser(@PathVariable("id") UUID userId) {
        UserDto user = userService.findById(userId);
        userService.delete(user);
        return "redirect:/users/all";
    }
}
