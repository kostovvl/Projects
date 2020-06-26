package exam.prep.web;

import exam.prep.service.ProblemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller()
public class HomeController {

    private final ProblemService problemService;

    public HomeController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GetMapping("/")
    public String index(HttpSession session, Model model) {


        if (session.getAttribute("user") == null) {
            return "index";
        }

        model.addAttribute("problems", this.problemService.findAllProblems());
        return "home";
    }

}
