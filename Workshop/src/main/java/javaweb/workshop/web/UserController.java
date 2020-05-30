package javaweb.workshop.web;

import javaweb.workshop.domain.binding.LoginUserBinding;
import javaweb.workshop.domain.binding.RegisterUserBinding;
import javaweb.workshop.domain.service.SetUserService;
import javaweb.workshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public String getRegisterPage() {
        return "register";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@Valid @ModelAttribute("userRegisterAttribute")
                                             RegisterUserBinding registerUserBinding,
                                     BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/register");
        } else if (!registerUserBinding.getPassword().equals(registerUserBinding.getConfirmPassword())) {

            modelAndView.setViewName("/register");

        } else {
            SetUserService setUserService = this.mapper.map(registerUserBinding, SetUserService.class);
            if (this.userService.userExists(setUserService)) {
                modelAndView.setViewName("/register");
            } else {
                this.userService.seedUser(setUserService);
                modelAndView.setViewName("/home");
            }
        }

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView loginUser(@Valid @ModelAttribute("userLoginAttribute") LoginUserBinding loginUserBinding,
                                  BindingResult bindingResult, ModelAndView modelAndView){
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/login");
        } else {
            SetUserService loginUserService = this.mapper.map(loginUserBinding, SetUserService.class);
            if (this.userService.userExists(loginUserService)) {
                modelAndView.setViewName("/home");
            } else {
                modelAndView.setViewName("/login");
            }
        }


        return modelAndView;
    }
}