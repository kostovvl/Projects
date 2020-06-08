package springfundamentals.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springfundamentals.lab.domain.entity.Offer;

@Repository
public interface  OfferRepository extends JpaRepository<Offer, Long> {
}
