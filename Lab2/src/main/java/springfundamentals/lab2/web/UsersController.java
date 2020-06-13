package springfundamentals.lab2.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springfundamentals.lab2.domain.binding.UserBinding;
import springfundamentals.lab2.domain.binding.UserLoginBinding;
import springfundamentals.lab2.domain.dto.UserGetDto;
import springfundamentals.lab2.domain.dto.UserSetDto;
import springfundamentals.lab2.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private final UserService userService;
    private final ModelMapper mapper;

    public UsersController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/register")
    public String getUsersRegisterPage(@Valid @ModelAttribute("userRegisterBinding")
                                       UserBinding userBinding, BindingResult bindingResult) {

      return "auth-register";
    }

    @PostMapping("/register")
    public String getRegisterConfirmPage(@Valid @ModelAttribute("userRegisterBinding")
                                       UserBinding userBinding, BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBinding", userBinding);
            return "redirect:/users/register";
        }

        UserSetDto userSetDto = this.mapper.map(userBinding, UserSetDto.class);

        System.out.println();
        try {
        this.userService.registerUser(userSetDto);
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("userExists", true);
            redirectAttributes.addFlashAttribute("userRegisterBinding", userBinding);
            return "redirect:/users/register";
        }


        return "redirect:/";
    }

    @GetMapping("/login")
    public String getUsersLoginPage(@ModelAttribute("userLogin") UserLoginBinding userLoginBinding) {

        return "auth-login";
    }

    @PostMapping("/login")
    public String userLoginConfirm(@ModelAttribute("userLogin")
                                   UserLoginBinding userLoginBinding, RedirectAttributes redirectAttributes,
                                   HttpSession session) {
        UserGetDto userGetDto = this.userService.findByUsername(userLoginBinding.getUsername());

        if (userGetDto == null) {
            redirectAttributes.addFlashAttribute("wrongUsername", true);
            return "redirect:/users/login";
        }
        System.out.println();
        if (!userGetDto.getPassword().equals(userLoginBinding.getPassword())) {
            redirectAttributes.addFlashAttribute("wrongPassword", true);
            return "redirect:/users/login";
        }

        session.setAttribute("user", userGetDto);
        session.setAttribute("id", userGetDto.getId());

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logut(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
