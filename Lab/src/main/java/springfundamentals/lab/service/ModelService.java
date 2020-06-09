package springfundamentals.lab.service;

import springfundamentals.lab.domain.dto.ModelGetDto;
import springfundamentals.lab.domain.dto.ModelSetDto;
import springfundamentals.lab.exception.EntityExistsException;

import java.util.List;

public interface ModelService {

    void addNewModel(ModelSetDto modelSetDto) throws EntityExistsException;
    List<ModelGetDto> getAllModels();

    void deleteModel(long id);

}
