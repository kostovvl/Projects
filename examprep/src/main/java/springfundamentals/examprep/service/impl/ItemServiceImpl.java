package springfundamentals.examprep.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, ModelMapper mapper) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public void saveItem(ItemDto itemDto) {
        Item item = this.mapper.map(itemDto, Item.class);
        System.out.println();
        item.setCategory(this.categoryRepository.findByCategoryName(itemDto.getCategory().getName()));
        System.out.println();
        this.itemRepository.saveAndFlush(item);
    }

    @Override
    public List<ItemView> getAllItems() {

        return this.itemRepository.findAll().stream()
                .map(item -> {
                    ItemView itemView = this.mapper.map(item, ItemView.class);
                    itemView.setImage(String.format("/img/%s-%s.jpg",
                            item.getGender(), item.getCategory().getCategoryName().name().toUpperCase()));
                    return itemView;
                }).collect(Collectors.toList());
    }

    @Override
    public ItemView findById(Long id) {
        return this.itemRepository.findById(id).map(item -> {
            ItemView itemView = this.mapper.map(item, ItemView.class);
            itemView.setImage(String.format("/img/%s-%s.jpg",
                    item.getGender(), item.getCategory().getCategoryName().name().toUpperCase()));
            return itemView;
        }).orElse(null);
    }

    @Override
    public void deleteBy(Long id) {
        this.itemRepository.deleteById(id);
    }
}
