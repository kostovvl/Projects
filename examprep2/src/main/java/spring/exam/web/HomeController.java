package spring.exam.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.exam.service.HeroService;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final HeroService heroService;

    public HomeController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/")
    public String index(HttpSession session, Model model) {

        model.addAttribute("heroes", this.heroService.getAllHeroes());

        if (session.getAttribute("user") != null) {
            return "home";
        }

        return "index";
    }

}
