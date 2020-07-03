package javaweb.workshop.service;

import javaweb.workshop.domain.binding.AddCommentBinding;
import javaweb.workshop.domain.dto.CommentDto;

public interface CommentService {

    void addComment(AddCommentBinding addCommentBinding);

}
