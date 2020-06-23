package springfundamentals.examprep.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springfundamentals.examprep.service.ItemService;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final ItemService itemService;

    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String index(HttpSession session, Model model) {

        model.addAttribute("items", this.itemService.getAllItems());

        if (session.getAttribute("user") == null) {
        return "index";
        }
        return "home";
    }

}
