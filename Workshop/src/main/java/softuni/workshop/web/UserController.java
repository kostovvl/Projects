package softuni.workshop.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.workshop.domain.binding.UserAddBindingModel;
import softuni.workshop.domain.binding.UserLoginBindingModel;
import softuni.workshop.domain.service.LoginUserService;
import softuni.workshop.domain.service.SetUserService;
import softuni.workshop.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;
    private final ModelMapper mapper;

    @Autowired
    public UserController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/users/register")
    public String getRegister() {
        return "register";
    }

    @GetMapping("/users/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/users/register")
    public ModelAndView registerUser(@Valid @ModelAttribute("userAddBindingModel")
                                     UserAddBindingModel userAddBindingModel, BindingResult bindingResult,
                                     ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {

            modelAndView.setViewName("redirect:/users/register");

        } else {
            SetUserService setUserService = this.mapper.map(userAddBindingModel, SetUserService.class);
            this.userService.seedUser(setUserService);
            modelAndView.setViewName("/home");
        }

        return modelAndView;
    }

    @PostMapping("/users/login")
    public ModelAndView loginUser(@ModelAttribute("userLoginBindingModel")
                                  UserLoginBindingModel userLoginBindingModel,
                                  ModelAndView modelAndView) {
        LoginUserService loginUserService = this.mapper.map(userLoginBindingModel, LoginUserService.class);

        if (this.userService.exists(loginUserService)) {
            modelAndView.setViewName("/home");
        } else {
            modelAndView.setViewName("redirect:/users/login");
        }

        return modelAndView;
    }

}
