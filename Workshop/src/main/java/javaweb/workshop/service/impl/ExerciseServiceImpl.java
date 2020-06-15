package javaweb.workshop.service.impl;

import javaweb.workshop.domain.dto.ExerciseDto;
import javaweb.workshop.domain.entity.Exercise;
import javaweb.workshop.repository.ExerciseRepository;
import javaweb.workshop.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ModelMapper mapper;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper mapper) {
        this.exerciseRepository = exerciseRepository;
        this.mapper = mapper;
    }

    @Override
    public void addExercise(ExerciseDto exerciseDto) {
        this.exerciseRepository.saveAndFlush(this.mapper.map(exerciseDto, Exercise.class));
    }
}
