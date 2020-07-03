package javaweb.workshop.web;

import javaweb.workshop.domain.binding.AddCommentBinding;
import javaweb.workshop.domain.binding.AddHomeworkBinding;
import javaweb.workshop.domain.dto.CommentDto;
import javaweb.workshop.domain.dto.ExerciseDto;
import javaweb.workshop.domain.dto.HomeworkDto;
import javaweb.workshop.domain.dto.UserDto;
import javaweb.workshop.service.CommentService;
import javaweb.workshop.service.ExerciseService;
import javaweb.workshop.service.HomeworkService;
import javaweb.workshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/homework")
public class HomeworkController {

    private final ExerciseService exerciseService;
    private final UserService userService;
    private final HomeworkService homeworkService;
    private final CommentService commentService;
    private final ModelMapper mapper;

    @Autowired
    public HomeworkController(ExerciseService exerciseService, UserService userService,
                              HomeworkService homeworkService, CommentService commentService, ModelMapper mapper) {
        this.exerciseService = exerciseService;
        this.userService = userService;
        this.homeworkService = homeworkService;
        this.commentService = commentService;


        this.mapper = mapper;
    }

    @GetMapping("/add")
    public String getAddPage(@Valid @ModelAttribute("homeworkBinding")
                                         AddHomeworkBinding homeworkBinding, BindingResult bindingResult,
                              Model model) {

        List<String> exercises = this.exerciseService.findAllExercises().stream()
                .map(ExerciseDto::getName).collect(Collectors.toList());
        model.addAttribute("exercises", exercises);

        return "homework-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("homeworkBinding")
                             AddHomeworkBinding homeworkBinding, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, HttpSession session) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("homeworkBinding", homeworkBinding);
            return "redirect:/homework/add";
        }

        System.out.println();

        HomeworkDto homeworkDto = this.mapper.map(homeworkBinding, HomeworkDto.class);
        homeworkDto.setExercise(this.exerciseService.findByName(homeworkBinding.getExercise()));
        UserDto userDto = (UserDto)session.getAttribute("user");
        homeworkDto.setAuthor(this.userService.findByUsername(userDto.getUsername()));
        homeworkDto.setAddedOn(LocalDateTime.now());

        this.homeworkService.addHomework(homeworkDto);

        return "redirect:/home";
    }

    @GetMapping("/check")
    public String getCheckPage(Model model) {
        if (model.getAttribute("addComment") == null) {
            model.addAttribute("addComment", new AddCommentBinding());
        }
        if (model.getAttribute("homework") == null) {
            model.addAttribute("homework", new HomeworkDto());
        }
        return "homework-check";
    }

    @GetMapping("/download")
    public String download(AddCommentBinding addCommentBinding,
                           RedirectAttributes attributes) {

        HomeworkDto result = this.homeworkService.findRandomHomework();
        attributes.addFlashAttribute("homework", result);
        attributes.addFlashAttribute("addComment", addCommentBinding);

        return "redirect:/homework/check";
    }

    @PostMapping("/comment")
    public String commentConfirm(@Valid @ModelAttribute("addComment")
                                 AddCommentBinding addCommentBinding, BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addComment", addCommentBinding);
         return "redirect:/homework/check";
        }

        if (addCommentBinding.getHomeworkId() == null) {
            redirectAttributes.addFlashAttribute("addComment", addCommentBinding);
            redirectAttributes.addFlashAttribute("noHomework",true);
            System.out.println();
            return "redirect:/homework/check";
        }


        this.commentService.addComment(addCommentBinding);
        return "redirect:/home";
    }
}
