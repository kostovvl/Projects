package springfundamentals.lab2.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springfundamentals.lab2.domain.binding.UserBinding;
import springfundamentals.lab2.domain.entity.User;
import springfundamentals.lab2.service.AuthService;
import springfundamentals.lab2.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;
    private final ModelMapper mapper;

    @Autowired
    public UserController(UserService userService, AuthService authService, ModelMapper mapper) {
        this.userService = userService;
        this.authService = authService;
        this.mapper = mapper;
    }

    @GetMapping("/register")
    public String getUserRegisterPage() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserBinding userBinding,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addAttribute("user", bindingResult);
            return "redirect:/users/register";
        }

        User user = this.mapper.map(userBinding, User.class);
        try {
            this.userService.registerUser(user);
            return "redirect:/users/login";
        } catch (Exception e) {
            redirectAttributes.addAttribute("user", bindingResult);
            return "redirect:/users/register";
        }

    }

    @GetMapping("/login")
    public String getUserLoginPage() {
        return "auth-login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password,
                            HttpSession session) {
        if (this.authService.UserExists(username, password)) {
            session.setAttribute("user", this.userService.findByUsername(username));
            return "offers";
        }
        return "redirect:/users/login";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "offers";
    }

}
