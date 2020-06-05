package javaweb.lab.repository;

import javaweb.lab.domain.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {

    Offer findByHeading(String heading);

}
