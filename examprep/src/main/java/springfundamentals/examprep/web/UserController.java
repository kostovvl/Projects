package springfundamentals.examprep.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springfundamentals.examprep.domain.binding.UserLoginBinding;
import springfundamentals.examprep.domain.binding.UserRegisterBinding;
import springfundamentals.examprep.domain.dto.UserDto;
import springfundamentals.examprep.service.UserService;

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

        if (model.getAttribute("registerUser") == null) {
            model.addAttribute("registerUser", new UserRegisterBinding());
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("registerUser")
                                  UserRegisterBinding userRegisterBinding, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerUser", userRegisterBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerUser", bindingResult);
            return "redirect:/users/register";
        }
        if (!userRegisterBinding.getPassword().equals(userRegisterBinding.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("registerUser", userRegisterBinding);
            redirectAttributes.addFlashAttribute("confirmPassword", true);
            return "redirect:/users/register";
        }

        this.userService.registerUser(this.mapper.map(userRegisterBinding, UserDto.class));

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login(Model model) {

        if (model.getAttribute("userLogin") == null) {
            model.addAttribute("userLogin", new UserLoginBinding());
        }

        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid @ModelAttribute("userLogin")
                               UserLoginBinding userLoginBinding, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, HttpSession session) {

        if(bindingResult.hasErrors()) {
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

        session.setAttribute("user", userDto);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        System.out.println();
        return "redirect:/";
    }

}
