package springfundamentals.lab.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfundamentals.lab.domain.binding.BrandBinding;
import springfundamentals.lab.domain.dto.BrandSetDto;
import springfundamentals.lab.exception.EntityExistsException;
import springfundamentals.lab.service.BrandService;

import javax.validation.Valid;
import java.sql.SQLException;

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
    public String openBrandsPage(){
        return "brand-add";
    }

    @PostMapping("/add")
    public String addNewBrand(@Valid BrandBinding brandBinding, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/brands";
        }
         try {
        BrandSetDto brandSetDto = this.mapper.map(brandBinding, BrandSetDto.class);
        this.brandService.addBrand(brandSetDto);
         } catch (Exception e) {
             return "redirect:/brands";
         }
        return "redirect:/";
    }

}
