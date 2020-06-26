package exam.prep.service.impl;

import exam.prep.domain.binding.SubmissionCreateBinding;
import exam.prep.domain.dto.SubmissionDto;
import exam.prep.domain.entity.Submission;
import exam.prep.repository.ProblemRepository;
import exam.prep.repository.SubmissionRepository;
import exam.prep.repository.UserRepository;
import exam.prep.service.SubmissionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;
    private final ModelMapper mapper;

    public SubmissionServiceImpl(SubmissionRepository submissionRepository, UserRepository userRepository, ProblemRepository problemRepository, ModelMapper mapper) {
        this.submissionRepository = submissionRepository;
        this.userRepository = userRepository;
        this.problemRepository = problemRepository;
        this.mapper = mapper;
    }



    @Transactional
    @Override
    public void seedSubmission(SubmissionCreateBinding submissionCreateBinding, Long userId, Long problemId) {
        Random random = new Random();

        Submission submission = this.mapper.map(submissionCreateBinding, Submission.class);
        submission.setAchievedResult(random.nextInt(101));
        submission.setUser(this.userRepository.findById(userId).orElse(null));
        submission.setProblem(this.problemRepository.findById(problemId).orElse(null));
        submission.setCreatedOn(new Date());
        this.submissionRepository.saveAndFlush(submission);

    }

    @Override
    public List<SubmissionDto> findAllSubmissions(Long userId, Long submissionId) {
        return this.submissionRepository.findAllByUserIdAndProblemId(userId, submissionId)
                .stream()
                .map(s -> this.mapper.map(s, SubmissionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public SubmissionDto findByUserIdAndProblemId(Long userId, Long submissionId) {

        return this.mapper.map(this.submissionRepository.findByUserIdAndProblemId(userId, submissionId),
                SubmissionDto.class);

    }


}
