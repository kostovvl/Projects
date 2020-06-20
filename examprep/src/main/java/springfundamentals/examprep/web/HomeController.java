package springfundamentals.examprep.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springfundamentals.examprep.service.ItemService;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final ItemService itemService;

    @Autowired
    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        if(session.getAttribute("user") == null) {
            return "index";
        }

        model.addAttribute("items", this.itemService.getAllItems());
        System.out.println();
        return "home";
    }
}
