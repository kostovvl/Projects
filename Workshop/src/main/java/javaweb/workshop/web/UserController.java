package javaweb.workshop.web;

import javaweb.workshop.domain.binding.LoginUserBinding;
import javaweb.workshop.domain.binding.RegisterUserBinding;
import javaweb.workshop.domain.dto.UserDto;
import javaweb.workshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper mapper;
    @Autowired
    public UserController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/register")
    public String getRegisterPage(@Valid @ModelAttribute("registerUser")
                                          RegisterUserBinding userBinding, BindingResult bindingResult) {
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("registerUser")
                                              RegisterUserBinding userBinding, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            System.out.println();
            redirectAttributes.addFlashAttribute("registerUser", userBinding);
            return "redirect:/users/register";
        }
        if (!userBinding.getPassword().equals(userBinding.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("registerUser", userBinding);
            redirectAttributes.addFlashAttribute("wrongPassword", true);
            return "redirect:/users/register";
        }

        UserDto userDto = this.mapper.map(userBinding, UserDto.class);
        this.userService.registerUser(userDto);

        return "redirect:/users/login";
    }


    @GetMapping("/login")
    public String getLoginPage(@Valid @ModelAttribute("loinUser")
                                          LoginUserBinding loginUserBinding,
                               BindingResult bindingResult) {
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid @ModelAttribute("loinUser")
                               LoginUserBinding loginUserBinding,  BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, HttpSession session) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loinUser", loginUserBinding);
            return "redirect:/users/login";
        }
        UserDto userDto = this.userService.findByUsername(loginUserBinding.getUsername());
        if (userDto == null || !userDto.getPassword().equals(loginUserBinding.getPassword())) {
            redirectAttributes.addFlashAttribute("wrongCredentials", true);
            redirectAttributes.addFlashAttribute("loinUser", loginUserBinding);
            return "redirect:/users/login";
        }

        session.setAttribute("user", userDto);

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }



}
