package javaweb.workshop.web;

import javaweb.workshop.domain.dto.UserDto;
import javaweb.workshop.domain.entity.User;
import javaweb.workshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private final UserService userService;

    public RoleController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    public String getAddRole(Model model, HttpSession session) {

        UserDto logged = (UserDto) session.getAttribute("user");
        List<String> users = this.userService.findAllUsernames().stream()
                .filter(u -> !u.equals(logged.getUsername()))
                .collect(Collectors.toList());

        UserDto user = (UserDto) session.getAttribute("user");
        model.addAttribute("users", users);
        model.addAttribute("user", user);
        return "role-add";
    }

    @PostMapping("/add")
    public String addRoleConfirm(@RequestParam("username") String username, @RequestParam("role") String role,
                                 Model model, HttpSession session) {

        System.out.println();
        this.userService.changeRole(username, role);

        return "redirect:/home";
    }

}
