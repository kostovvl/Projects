package exam.prep.web;

import exam.prep.domain.binding.SubmissionCreateBinding;
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
@RequestMapping("/submission")
public class SubmissionController {

    private final SubmissionService submissionService;
    private final ProblemService problemService;
    private final ModelMapper mapper;

    public SubmissionController(SubmissionService submissionService, ProblemService problemService, ModelMapper mapper) {
        this.submissionService = submissionService;
        this.problemService = problemService;
        this.mapper = mapper;
    }

    @GetMapping("/create")
    public String create(@RequestParam("id") Long id, Model model) {

        if (model.getAttribute("submissionCreate") == null) {
            model.addAttribute("submissionCreate", new SubmissionCreateBinding());
            model.addAttribute("problem", this.problemService.findById(id));
        }

        return "create-submission";
    }

    @PostMapping("/create")
    public String createConfirm(@RequestParam("id") Long id, @Valid @ModelAttribute("submissionCreate")
                                            SubmissionCreateBinding submissionCreateBinding,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                HttpSession session) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("submissionCreate", submissionCreateBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.submissionCreate", bindingResult);
            return "redirect:/submission/create";
        }

       this.submissionService.seedSubmission(submissionCreateBinding,
               (Long) session.getAttribute("id"), id);

        return "redirect:/";
    }

    @GetMapping("/details")
    public String details(@RequestParam("id") Long id, Model model, HttpSession session) {

        model.addAttribute("submission", this.submissionService.findByUserIdAndProblemId(
                (Long) session.getAttribute("id"), id));

        return "details-submission";
    }

}
