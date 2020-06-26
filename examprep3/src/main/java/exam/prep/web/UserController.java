package exam.prep.web;

import exam.prep.domain.binding.UserLoginBinding;
import exam.prep.domain.binding.UserRegisterBinding;
import exam.prep.domain.dto.LoggedUser;
import exam.prep.domain.dto.UserDto;
import exam.prep.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper mapper;

    public UserController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/register")
    public String register(Model model) {

        if (model.getAttribute("userRegister") == null) {
            model.addAttribute("userRegister", new UserRegisterBinding());
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("userRegister")
                                  UserRegisterBinding userRegisterBinding, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegister", userRegisterBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegister", bindingResult);
            return "redirect:/users/register";
        }

        if (!userRegisterBinding.getConfirmPassword().equals(userRegisterBinding.getPassword())) {
            redirectAttributes.addFlashAttribute("userRegister", userRegisterBinding);
            redirectAttributes.addFlashAttribute("wrongPassword", true);
            return "redirect:/users/register";
        }

        UserDto userDto = this.userService.findByUsername(userRegisterBinding.getUsername());

        if (userDto != null) {
            redirectAttributes.addFlashAttribute("userRegister", userRegisterBinding);
            redirectAttributes.addFlashAttribute("userExists", true);
            return "redirect:/users/register";
        }

        this.userService.seedUser(this.mapper.map(userRegisterBinding, UserDto.class));

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login(Model model) {

        if (model.getAttribute("userLogin") == null) {
            model.addAttribute("userLogin", new UserRegisterBinding());
        }

        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid @ModelAttribute("userLogin")
                               UserLoginBinding userLoginBinding, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, HttpSession session) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLogin", userLoginBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLogin", bindingResult);
            return "redirect:/users/login";
        }

        UserDto userDto = this.userService.findByUsername(userLoginBinding.getUsername());

        if (userDto == null || !userDto.getPassword().equals(userLoginBinding.getPassword())) {
            redirectAttributes.addFlashAttribute("userLogin", userLoginBinding);
            redirectAttributes.addFlashAttribute("wrongCredentials", true);
            return "redirect:/users/login";
        }

        LoggedUser loggedUser = this.mapper.map(userDto, LoggedUser.class);

        session.setAttribute("user", loggedUser);
        session.setAttribute("id", loggedUser.getId());

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/";
    }
}
