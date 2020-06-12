package springfundamentals.lab2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfundamentals.lab2.service.BrandService;

@Controller
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping()
    public String getAllBrandsPage(Model model) {

        model.addAttribute("brands", this.brandService.getAllBrands());

        return "brands";
    }



}
