package spring.exam.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.exam.domain.binding.ProductAddBinding;
import spring.exam.domain.dto.ProductDto;
import spring.exam.domain.entity.CategoryName;
import spring.exam.service.ProductService;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper mapper;

    @Autowired
    public ProductController(ProductService productService, ModelMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @GetMapping("/add")
    public String add(Model model) {

        if (model.getAttribute("productAdd") == null) {
            model.addAttribute("productAdd", new ProductAddBinding());
        }
        model.addAttribute("categories", CategoryName.values());

        return "product-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("productAdd")
                             ProductAddBinding productAddBinding, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAdd", productAddBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAdd", bindingResult);
            return "redirect:/products/add";
        }


        if (this.productService.productExists(productAddBinding.getName())) {
            redirectAttributes.addFlashAttribute("productAdd", productAddBinding);
            redirectAttributes.addFlashAttribute("productExists", true);
            return "redirect:/products/add";
        }

        this.productService.addProduct(this.mapper.map(productAddBinding, ProductDto.class));

        return "redirect:/";
    }

    @GetMapping("/buy")
    public String buy(@RequestParam("id") String id) {

        this.productService.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("/buy/all")
    public String buyAll () {

        this.productService.deleteAll();

        return "redirect:/";
    }

}
