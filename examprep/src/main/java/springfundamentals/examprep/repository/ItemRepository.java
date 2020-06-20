package springfundamentals.examprep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springfundamentals.examprep.domain.entity.Item;
import springfundamentals.examprep.domain.view.ItemView;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAll();

}
