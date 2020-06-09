package springfundamentals.lab.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfundamentals.lab.domain.binding.OfferBinding;
import springfundamentals.lab.domain.dto.OfferGetDto;
import springfundamentals.lab.domain.dto.OfferSetDto;
import springfundamentals.lab.domain.view.OfferView;
import springfundamentals.lab.repository.ModelRepository;
import springfundamentals.lab.service.OfferService;
import springfundamentals.lab.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private final UserService userService;
    private final ModelRepository modelRepository;
    private final ModelMapper mapper;

    @Autowired
    public OfferController(OfferService offerService, UserService userService,
                           ModelRepository modelRepository, ModelMapper mapper) {
        this.offerService = offerService;
        this.userService = userService;


        this.modelRepository = modelRepository;
        this.mapper = mapper;
    }

    @GetMapping()
    public String getOfferAddPage(Model model) {

        model.addAttribute("models", this.modelRepository.findAll());
        model.addAttribute("sellers", this.userService.findAllUsers());

        return "offer-add";
    }

    @PostMapping("/add")
    public String addNewOffer(@Valid OfferBinding offerBinding, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/offers";
        }

        OfferSetDto offerSetDto = this.mapper.map(offerBinding, OfferSetDto.class);

        this.offerService.addNewOffer(offerSetDto);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String postAllOffers(Model model) {



        List<OfferView> allOffers = mapAllOffers(this.offerService.getAllOffers());
        model.addAttribute("offers", allOffers);

        return "all";
    }

    private List<OfferView> mapAllOffers(List<OfferGetDto> allOffers) {
        return allOffers.stream()
                .map(o -> {
                    OfferView offerView = this.mapper.map(o, OfferView.class);
                    offerView.setBrand(o.getModel().getBrand().getName());
                    offerView.setSeller(o.getSeller().getUsername());
                    offerView.setModel(o.getModel().getName());
                    return offerView;
                })
                .collect(Collectors.toList());
    }

}
