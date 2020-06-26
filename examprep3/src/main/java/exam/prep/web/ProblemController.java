package exam.prep.web;

import exam.prep.domain.binding.ProblemCreateBinding;
import exam.prep.domain.dto.ProblemDto;
import exam.prep.service.ProblemService;
import exam.prep.service.SubmissionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/problem")
public class ProblemController {

    private final ProblemService problemService;
    private final SubmissionService submissionService;
    private final ModelMapper mapper;

    public ProblemController(ProblemService problemService, SubmissionService submissionService, ModelMapper mapper) {
        this.problemService = problemService;
        this.submissionService = submissionService;
        this.mapper = mapper;
    }

    @GetMapping("/create")
    public String create(Model model) {

        if (model.getAttribute("problemCreate") == null) {
            model.addAttribute("problemCreate", new ProblemCreateBinding());
        }

        return "create-problem";
    }

    @PostMapping("/create")
    public String createConfirm(@Valid @ModelAttribute("problemCreate")
                                ProblemCreateBinding problemCreateBinding, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("problemCreate", problemCreateBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.problemCreate", bindingResult);
            return "redirect:/problem/create";
        }

        this.problemService.seedProblem(this.mapper.map(problemCreateBinding, ProblemDto.class));

        return "redirect:/";
    }

    @GetMapping("/details")
    public String details(@RequestParam("id") Long id, Model model, HttpSession session) {

        model.addAttribute("submissions", this.submissionService.findAllSubmissions((Long) session.getAttribute("id"),
                id));

        return "details-problem";
    }
}
