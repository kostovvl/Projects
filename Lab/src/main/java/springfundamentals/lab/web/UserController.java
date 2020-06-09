package springfundamentals.lab.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfundamentals.lab.domain.binding.UserBinding;
import springfundamentals.lab.domain.dto.UserSetDto;
import springfundamentals.lab.service.UserService;

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

    @PostMapping("/register")
    public String registerUser(@Valid UserBinding newUser, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/users/register";
        }

        try{
        UserSetDto userSetDto = this.mapper.map(newUser, UserSetDto.class);
        this.userService.registerUser(userSetDto);
        return "redirect:/";
        } catch (Exception e) {
            return "redirect:/users/register";
        }
    }
}
