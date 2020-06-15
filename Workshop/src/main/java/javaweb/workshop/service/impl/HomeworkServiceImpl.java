package javaweb.workshop.service.impl;

import javaweb.workshop.domain.dto.HomeworkDto;
import javaweb.workshop.domain.entity.Homework;
import javaweb.workshop.repository.HomeworkRepository;
import javaweb.workshop.service.HomeworkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;
    private final ModelMapper mapper;

    @Autowired
    public HomeworkServiceImpl(HomeworkRepository homeworkRepository, ModelMapper mapper) {
        this.homeworkRepository = homeworkRepository;
        this.mapper = mapper;
    }

    @Override
    public void addHomework(HomeworkDto homeworkDto) {
        this.homeworkRepository.saveAndFlush(this.mapper.map(homeworkDto, Homework.class));
    }
}
