package springfundamentals.lab.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfundamentals.lab.domain.binding.UserBinding;
import springfundamentals.lab.domain.dto.UserSetDto;
import springfundamentals.lab.service.UserService;

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
    public String registerUser(UserBinding newUser) {
        UserSetDto userSetDto = this.mapper.map(newUser, UserSetDto.class);
        this.userService.registerUser(userSetDto);
        return "redirect:/";
    }
}
