package springfundamentals.lab2.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springfundamentals.lab2.domain.binding.OfferBinding;
import springfundamentals.lab2.service.ModelService;
import springfundamentals.lab2.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class OffersController {

    private final UserService userService;
    private final ModelService modelService;
    private final ModelMapper mapper;

    public OffersController(UserService userService, ModelService modelService, ModelMapper mapper) {
        this.userService = userService;
        this.modelService = modelService;
        this.mapper = mapper;
    }

    @GetMapping()
    public String getAllOffersPage() {
        return "offers";
    }

    @GetMapping("/add")
    public String getOfferAddPage(@Valid @ModelAttribute("offerAddBinding")
                                              OfferBinding offerBinding, BindingResult bindingResult,
                                  Model model) {

        model.addAttribute("models", this.modelService.getAllModels());

        return "offer-add";
    }

    @PostMapping("/add")
    public String confirmOfferAdd(@Valid @ModelAttribute("offerAddBinding")
                                              OfferBinding offerBinding, BindingResult bindingResult,
                                  RedirectAttributes attributes) {


        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("offerAddBinding", offerBinding);
            return "redirect:/add";
        }

        return "redirect:/";

    }
}