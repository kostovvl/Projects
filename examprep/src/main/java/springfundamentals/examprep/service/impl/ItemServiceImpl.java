package springfundamentals.examprep.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import springfundamentals.examprep.domain.dto.ItemDto;
import springfundamentals.examprep.domain.entity.Item;
import springfundamentals.examprep.domain.view.ItemView;
import springfundamentals.examprep.repository.CategoryRepository;
import springfundamentals.examprep.repository.ItemRepository;
import springfundamentals.examprep.service.ItemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, ModelMapper mapper) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public void addItem(ItemDto itemDto) {

        Item item = this.mapper.map(itemDto, Item.class);
        item.setCategory(this.categoryRepository.findByName(itemDto.getCategory().getName()));
        this.itemRepository.saveAndFlush(item);

    }

    @Override
    public List<ItemView> getAllItems() {

        return this.itemRepository.findAll().stream()
                .map(item -> {
                    ItemView itemView = this.mapper.map(item, ItemView.class);
                    itemView.setImage(String.format("/img/%s-%S.jpg", item.getGender(),
                            item.getCategory().getName().name()));
                    return itemView;
                }).collect(Collectors.toList());

    }

    @Override
    public ItemView findById(Long id) {
        Item result =  this.itemRepository.findById(id).orElse(null);

        ItemView result1 = this.mapper.map(result, ItemView.class);

        result1.setImage(String.format("/img/%s-%S.jpg", result.getGender(),
                result.getCategory().getName().name()));

        return result1;
    }

    @Override
    public void deleteById(Long id) {
        this.itemRepository.deleteById(id);
    }
}
