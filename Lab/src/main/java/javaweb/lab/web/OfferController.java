package javaweb.lab.web;

import javaweb.lab.domain.entity.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/offers")
public class OfferController {

    @GetMapping()
    public String getOfferAddPage() {
        return "offer-add";
    }

    @GetMapping("/all")
    public ModelAndView getAllOffers(ModelAndView model) {

        model.setViewName("all");
        return model;
    }

}
