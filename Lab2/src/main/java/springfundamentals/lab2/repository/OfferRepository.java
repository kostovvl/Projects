package springfundamentals.lab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springfundamentals.lab2.domain.entity.Offer;

import java.util.List;

@Repository
public interface  OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findAll();

    Offer findById(long id);

}
