package javaweb.lab.web;

import javaweb.lab.domain.binding.ModelBinding;
import javaweb.lab.domain.dto.modeldto.ModelDto;
import javaweb.lab.exception.EntityExistsException;
import javaweb.lab.repository.ModelRepository;
import javaweb.lab.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/models")
public class ModelsController {

    private final ModelService modelService;
    private final ModelMapper mapper;

    @Autowired
    public ModelsController(ModelService modelService, ModelMapper mapper) {
        this.modelService = modelService;
        this.mapper = mapper;
    }

    @GetMapping()
    public String getAddModelPage() {
        return "model-add";
    }

    @PostMapping("/add")
    public ModelAndView addNewModel(@Valid @ModelAttribute("model")
                                                ModelBinding modelBinding,
                                    BindingResult bindingResult, ModelAndView model) throws EntityExistsException {

        if (bindingResult.hasErrors()) {

        } else {
            try {
            ModelDto modelDto = this.mapper.map(modelBinding, ModelDto.class);
            System.out.println();
            this.modelService.addModel(modelDto);
                model.setViewName("redirect:/");
            } catch (Exception e) {
              model.setViewName("redirect:/models");
           }
        }

        return model;
    }

    @GetMapping("/all")
    public String getAllModels(Model model) {
        model.addAttribute(this.modelService.getAllModels());
        return "all";
    }

}
