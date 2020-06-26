package exam.prep.repository;

import exam.prep.domain.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    Submission findByUserIdAndProblemId(Long userId, Long problemId);
    List<Submission> findAllByUserIdAndProblemId(Long userId, Long problemId);
}
