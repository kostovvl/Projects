package javaweb.workshop.repository;

import javaweb.workshop.domain.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, String> {

    @Query("select h from Homework as h where h.comments.size <= (select avg(h.comments.size) from Homework as h)")
    Optional<Homework> findWithFewerComments();

}
