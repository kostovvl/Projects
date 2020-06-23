package springfundamentals.examprep.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springfundamentals.examprep.domain.binding.ItemAddBinding;
import springfundamentals.examprep.domain.dto.ItemDto;
import springfundamentals.examprep.domain.entity.CategoryName;
import springfundamentals.examprep.domain.view.ItemView;
import springfundamentals.examprep.service.ItemService;

import javax.validation.Valid;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final ModelMapper mapper;

    public ItemController(ItemService itemService, ModelMapper mapper) {
        this.itemService = itemService;
        this.mapper = mapper;
    }

    @GetMapping("/add")
    public String add(Model model) {

        if (model.getAttribute("itemAdd") == null) {
            model.addAttribute("itemAdd", new ItemAddBinding());
        }
        model.addAttribute("categories", CategoryName.values());

        return "add-item";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("itemAdd")
                             ItemAddBinding itemAddBinding, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("itemAdd", itemAddBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.itemAdd", bindingResult);
            return "redirect:/items/add";
        }

        ItemDto itemDto = this.mapper.map(itemAddBinding, ItemDto.class);

        System.out.println();
        this.itemService.addItem(itemDto);

        return "redirect:/";
    }

    @GetMapping("/details")
    public String details(@RequestParam("id") Long id,  Model model) {

        System.out.println();

        ItemView itemView = this.itemService.findById(id);

        System.out.println();
        model.addAttribute("item", itemView);

        return "details-item";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {

        this.itemService.deleteById(id);

        return "redirect:/";
    }

}
