package springfundamentals.lab2.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfundamentals.lab2.domain.binding.OfferBinding;
import springfundamentals.lab2.domain.dto.OfferSetDto;
import springfundamentals.lab2.service.BrandService;
import springfundamentals.lab2.service.ModelService;
import springfundamentals.lab2.service.OfferService;

import javax.validation.Valid;

@Controller()
@RequestMapping("/")
public class OfferController {

    private final OfferService offerService;
    private final BrandService brandService;
    private final ModelService modelService;
    private final ModelMapper mapper;

    @Autowired
    public OfferController(OfferService offerService, BrandService brandService, ModelService modelService, ModelMapper mapper) {
        this.offerService = offerService;
        this.brandService = brandService;
        this.modelService = modelService;
        this.mapper = mapper;
    }

    @GetMapping("/")
    public String getAllOffers(Model model) {

        model.addAttribute("offers", this.offerService.getAllOffers());

        return "offers";
    }

    @GetMapping("/add")
    public String getOfferAddPage(Model model) {

        model.addAttribute("brands", this.brandService.getAllBrands());
        model.addAttribute("models", this.modelService.getAllModels());

        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(@Valid OfferBinding binding) {
        try {
            this.offerService.addNewOffer(this.mapper.map(binding, OfferSetDto.class));
            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/offers/add";
        }
    }

}
