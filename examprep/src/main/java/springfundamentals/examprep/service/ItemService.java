package springfundamentals.examprep.service;

import springfundamentals.examprep.domain.dto.ItemDto;
import springfundamentals.examprep.domain.view.ItemView;

import java.util.List;

public interface ItemService {

    void saveItem(ItemDto itemDto);
    List<ItemView> getAllItems();
    ItemView findById(Long id);
    void deleteBy(Long id);

}
