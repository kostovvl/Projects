package javaweb.workshop.web;

import javaweb.workshop.domain.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView getIndexPage(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
       return modelAndView;
    }

    @GetMapping("home")
    public String hetHome(HttpSession session, Model model) {
        UserDto user = (UserDto) session.getAttribute("user");
        model.addAttribute("user", user);
        return "home";
    }

}
