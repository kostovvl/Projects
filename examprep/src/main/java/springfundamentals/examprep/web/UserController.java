package springfundamentals.examprep.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public UserController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/register")
    public String register(Model model) {

        if (!model.containsAttribute("userRegisterBinding")) {
            model.addAttribute("userRegisterBinding", new UserRegisterBinding());
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("userRegisterBinding")
                                              UserRegisterBinding userRegisterBinding, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBinding", userRegisterBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBinding", bindingResult);
            return "redirect:/users/register";
        }
        if (!userRegisterBinding.getPassword().equals(userRegisterBinding.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBinding", userRegisterBinding);
            redirectAttributes.addFlashAttribute("passwordConfirm", true);
            return "redirect:/users/register";
        }

        this.userService.registerUser(this.mapper.map(userRegisterBinding, UserDto.class));
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login(Model model) {

        if (!model.containsAttribute("userLoginBinding")) {
            model.addAttribute("userLoginBinding", new UserLoginBinding());
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid @ModelAttribute("userLoginBinding")UserLoginBinding userLoginBinding,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBinding", userLoginBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBinding", bindingResult);
            return "redirect:/users/login";
        }

        UserDto userDto = this.userService.findByUsername(userLoginBinding.getUsername());

        if (userDto == null || !userDto.getPassword().equals(userLoginBinding.getPassword())) {
            redirectAttributes.addFlashAttribute("userLoginBinding", userLoginBinding);
            redirectAttributes.addFlashAttribute("wrongUser" , true);
            return "redirect:/users/login";
        }

        session.setAttribute("user", userDto);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

}
