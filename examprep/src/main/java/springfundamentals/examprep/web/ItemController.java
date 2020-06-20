package springfundamentals.examprep.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springfundamentals.examprep.domain.binding.ItemAddBindingModel;
import springfundamentals.examprep.domain.dto.ItemDto;
import springfundamentals.examprep.domain.entity.CategoryName;
import springfundamentals.examprep.service.ItemService;

import javax.validation.Valid;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final ModelMapper mapper;

    @Autowired
    public ItemController(ItemService itemService, ModelMapper mapper) {
        this.itemService = itemService;
        this.mapper = mapper;
    }

    @GetMapping("/add")
    public String add(Model model) {

        if (model.getAttribute("itemAddBindingModel") == null) {
            model.addAttribute("itemAddBindingModel", new ItemAddBindingModel());
            model.addAttribute("categoryNames", CategoryName.values());
        }

        return "add-item";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("itemAddBindingModel")
                             ItemAddBindingModel itemAddBindingModel, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.itemAddBindingModel", bindingResult);
            return "redirect:/items/add";
        }

        ItemDto itemDto = this.mapper.map(itemAddBindingModel, ItemDto.class);
        this.itemService.saveItem(itemDto);
        return "redirect:/";
    }

    @GetMapping("/details")
    public String details(@RequestParam("id") Long id, Model model) {

        model.addAttribute("item", this.itemService.findById(id));

        return "details-item";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        this.itemService.deleteBy(id);
        return "redirect:/";
    }
}
