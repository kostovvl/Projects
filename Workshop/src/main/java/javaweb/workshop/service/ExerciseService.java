package javaweb.workshop.service;

import javaweb.workshop.domain.dto.ExerciseDto;

import java.util.List;

public interface ExerciseService {

    void addExercise(ExerciseDto exerciseDto);
    List<ExerciseDto> findAllExercises();
    ExerciseDto findByName(String name);

}
