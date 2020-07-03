package javaweb.workshop.web;

import javaweb.workshop.domain.binding.AddExerciseBinding;
import javaweb.workshop.domain.dto.ExerciseDto;
import javaweb.workshop.domain.dto.UserDto;
import javaweb.workshop.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ModelMapper mapper;

    @Autowired
    public ExerciseController(ExerciseService exerciseService, ModelMapper mapper) {
        this.exerciseService = exerciseService;
        this.mapper = mapper;
    }

    @GetMapping("/add")
    public String getAddPage(@Valid @ModelAttribute("exerciseBinding")
                                         AddExerciseBinding addExerciseBinding, BindingResult bindingResult,
                              Model model) {
        return "exercise-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("exerciseBinding")
                             AddExerciseBinding addExerciseBinding, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("exerciseBinding", addExerciseBinding);
            return "redirect:/exercises/add";
        }

        ExerciseDto exerciseDto = this.mapper.map(addExerciseBinding, ExerciseDto.class);
        this.exerciseService.addExercise(exerciseDto);

        return "redirect:/home";
    }

}
