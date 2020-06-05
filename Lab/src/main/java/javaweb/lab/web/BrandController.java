package javaweb.lab.web;

import javaweb.lab.domain.binding.BrandBinding;
import javaweb.lab.domain.dto.branddto.BrandDto;
import javaweb.lab.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;
    private final ModelMapper mapper;

    @Autowired
    public BrandController(BrandService brandService, ModelMapper mapper) {
        this.brandService = brandService;
        this.mapper = mapper;
    }

    @GetMapping()
    public String getAddBrandPage() {
        return "brand-add";
    }

    @PostMapping("/add")
    public ModelAndView addNewBrand(@Valid @ModelAttribute("brand")
                                    BrandBinding brandBinding, BindingResult bindingResult,
                                    ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("redirect:/brands");
        } else {
            BrandDto brandDto = this.mapper.map(brandBinding, BrandDto.class);
            try{
            this.brandService.createBrand(brandDto);
            modelAndView.setViewName("redirect:/");
            } catch (Exception e) {
                modelAndView.setViewName("redirect:/brands");
            }
        }

        return modelAndView;
    }

}
