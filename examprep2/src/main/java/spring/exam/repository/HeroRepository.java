package spring.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.exam.domain.entity.Hero;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {

    List<Hero> findAll();

}
