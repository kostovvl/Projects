package springfundamentals.lab2.service;

import springfundamentals.lab2.domain.dto.ModelGetDto;
import springfundamentals.lab2.domain.dto.ModelSetDto;
import springfundamentals.lab2.exception.EntityExistsException;

import java.util.List;

public interface ModelService {

    void addNewModel(ModelSetDto modelSetDto) throws EntityExistsException;
    List<ModelGetDto> getAllModels();

    void deleteModel(long id);

}
