package springfundamentals.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springfundamentals.lab.domain.entity.Offer;

import java.util.List;

@Repository
public interface  OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findAll();

}
