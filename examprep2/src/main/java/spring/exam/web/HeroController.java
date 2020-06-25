package spring.exam.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.exam.domain.binding.HeroLoginBinding;
import spring.exam.domain.dto.HeroDto;
import spring.exam.domain.entity.HeroClass;
import spring.exam.service.HeroService;

import javax.validation.Valid;

@Controller
@RequestMapping("/heroes")
public class HeroController {

    private final HeroService heroService;
    private final ModelMapper mapper;

    public HeroController(HeroService heroService, ModelMapper mapper) {
        this.heroService = heroService;
        this.mapper = mapper;
    }

    @GetMapping("/create")
    public String create(Model model) {

        if (model.getAttribute("heroCreate") == null) {
            model.addAttribute("heroCreate", new HeroLoginBinding());
        }

        model.addAttribute("classes", HeroClass.values());

        return "create-hero";
    }

    @PostMapping("/create")
    public String createConfirm(@Valid @ModelAttribute("heroCreate")
                                HeroLoginBinding heroLoginBinding, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("heroCreate", heroLoginBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.heroCreate", bindingResult);
            return "redirect:/heroes/create";
        }

        HeroDto heroDto = this.mapper.map(heroLoginBinding, HeroDto.class);

        this.heroService.createHero(heroDto);


        return "redirect:/";
    }

    @GetMapping("/details")
    public String details(@RequestParam("id") Long id,  Model model) {

        model.addAttribute("hero", this.heroService.findById(id));

        return "details-hero";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        this.heroService.deleteById(id);
        return "redirect:/";
    }

}
