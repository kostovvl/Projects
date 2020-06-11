package springfundamentals.lab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfundamentals.lab.domain.dto.OfferGetDto;
import springfundamentals.lab.service.ModelService;
import springfundamentals.lab.service.OfferService;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final OfferService offerService;
    private final ModelService modelService;

    @Autowired
    public CarController(OfferService offerService, ModelService modelService) {
        this.offerService = offerService;
        this.modelService = modelService;
    }

    @GetMapping("/details/id")
    public String getSpecificOffer(@RequestParam("id") long id, Model model) {

       OfferGetDto result = this.offerService.findById(id);
        System.out.println();
        model.addAttribute("offer", result);
        System.out.println();
        return "details";
    }

    @GetMapping("/delete/id")
    public String deleteCar(@RequestParam("id") long id) {

        System.out.println();
        this.modelService.deleteModel(id);

        return "redirect:/";
    }

}
