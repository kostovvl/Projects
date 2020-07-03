package javaweb.workshop.service;

import javaweb.workshop.domain.dto.HomeworkDto;

public interface HomeworkService {

    void addHomework(HomeworkDto homeworkDto);
    HomeworkDto findRandomHomework();
}
