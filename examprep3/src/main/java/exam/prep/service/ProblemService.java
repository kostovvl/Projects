package exam.prep.service;

import exam.prep.domain.dto.ProblemDto;

import java.util.List;

public interface ProblemService {

    void seedProblem(ProblemDto problemDto);
    List<ProblemDto> findAllProblems();
    ProblemDto findById(Long id);

}
