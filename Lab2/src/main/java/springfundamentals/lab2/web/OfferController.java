package springfundamentals.lab2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfundamentals.lab2.domain.entity.Offer;
import springfundamentals.lab2.service.OfferService;

@Controller
@RequestMapping("/")
public class OfferController {

    @Autowired
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping()
    public String getOffersEmptyPage(Model model) {

        model.addAttribute("offers", null);

        return "offers";
    }

}
