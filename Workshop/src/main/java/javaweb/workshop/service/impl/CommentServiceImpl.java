package javaweb.workshop.service.impl;

import javaweb.workshop.domain.binding.AddCommentBinding;
import javaweb.workshop.domain.entity.Comment;
import javaweb.workshop.repository.CommentRepository;
import javaweb.workshop.repository.HomeworkRepository;
import javaweb.workshop.repository.UserRepository;
import javaweb.workshop.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final HomeworkRepository homeworkRepository;
    private final ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, HomeworkRepository homeworkRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.homeworkRepository = homeworkRepository;
        this.mapper = mapper;
    }


    @Transactional
    @Override
    public void addComment(AddCommentBinding addCommentBinding) {
        Comment comment = new Comment(addCommentBinding.getScore(), addCommentBinding.getTextContent());
        comment.setAuthor(this.userRepository.findById(addCommentBinding.getAuthorId()).orElse(null));
        comment.setHomework(this.homeworkRepository.findById(addCommentBinding.getHomeworkId()).orElse(null));
        this.commentRepository.saveAndFlush(comment);
    }
}
