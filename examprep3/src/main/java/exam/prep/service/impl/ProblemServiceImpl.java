package exam.prep.service.impl;

import exam.prep.domain.dto.ProblemDto;
import exam.prep.domain.entity.Problem;
import exam.prep.repository.ProblemRepository;
import exam.prep.service.ProblemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;
    private final ModelMapper mapper;

    public ProblemServiceImpl(ProblemRepository problemRepository, ModelMapper mapper) {
        this.problemRepository = problemRepository;
        this.mapper = mapper;
    }


    @Override
    public void seedProblem(ProblemDto problemDto) {

        this.problemRepository.saveAndFlush(this.mapper.map(problemDto, Problem.class));
    }

    @Override
    public List<ProblemDto> findAllProblems() {
        return this.problemRepository.findAll()
                .stream()
                .map(p -> this.mapper.map(p, ProblemDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProblemDto findById(Long id) {
        return this.mapper.map(this.problemRepository.findById(id).orElse(null), ProblemDto.class);
    }
}
