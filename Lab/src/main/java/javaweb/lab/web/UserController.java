package javaweb.lab.web;

import javaweb.lab.domain.binding.UserBinding;
import javaweb.lab.domain.dto.userdto.UserAddDto;
import javaweb.lab.exception.EntityExistsException;
import javaweb.lab.service.UserService;
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

@Controller()
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
    public String getUsersRegistrationPage () {
        return "register";
    }

    @PostMapping("/registerNew")
    public ModelAndView registerNewUser (@Valid @ModelAttribute("user")UserBinding newUser,
                                         BindingResult bindingResult, ModelAndView model) throws EntityExistsException {

        if (bindingResult.hasErrors()) {
            model.setViewName("redirect:/users/register");
        } else {

            UserAddDto userAddDto = this.mapper.map(newUser, UserAddDto.class);
            try {

                this.userService.addUser(userAddDto);
                model.setViewName("redirect:/");

           } catch (Exception e) {
               model.setViewName("redirect:/users/register");
            }

        }

        return model;
    }

}
