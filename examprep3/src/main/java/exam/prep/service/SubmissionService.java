package exam.prep.service;

import exam.prep.domain.binding.SubmissionCreateBinding;
import exam.prep.domain.dto.SubmissionDto;

import java.util.List;

public interface SubmissionService {

    void seedSubmission(SubmissionCreateBinding submissionCreateBinding,Long userId, Long problemId);

    List<SubmissionDto> findAllSubmissions(Long userId, Long submissionId);
    SubmissionDto findByUserIdAndProblemId(Long userId, Long submissionId);

}
