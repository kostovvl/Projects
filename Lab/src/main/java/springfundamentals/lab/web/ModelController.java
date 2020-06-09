package springfundamentals.lab.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfundamentals.lab.domain.binding.ModelBinding;
import springfundamentals.lab.domain.dto.ModelSetDto;
import springfundamentals.lab.service.BrandService;
import springfundamentals.lab.service.ModelService;

import javax.validation.Valid;

@Controller
@RequestMapping("/models")
public class ModelController {

    private final ModelService modelService;
    private final BrandService brandService;
    private final ModelMapper mapper;

    @Autowired
    public ModelController(ModelService modelService, BrandService brandService, ModelMapper mapper) {
        this.modelService = modelService;
        this.brandService = brandService;
        this.mapper = mapper;
    }

    @GetMapping()
    public String getModelsPage(Model model) {

        model.addAttribute("brands", this.brandService.getAllBrands());

        return "model-add";
    }

    @PostMapping("/add")
    public String addNewModel(@Valid ModelBinding modelBinding, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/models";
        }

        try {
        ModelSetDto modelSetDto = this.mapper.map(modelBinding, ModelSetDto.class);
        this.modelService.addNewModel(modelSetDto);
        } catch (Exception e) {
            return "redirect:/models";
        }

        return "redirect:/";
    }

}
