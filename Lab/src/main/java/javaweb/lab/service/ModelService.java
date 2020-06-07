package javaweb.lab.service;

import javaweb.lab.domain.dto.modeldto.ModelDto;
import javaweb.lab.exception.EntityDoesNotExistsException;
import javaweb.lab.exception.EntityExistsException;
import javaweb.lab.exception.EntityInvalidException;

import java.util.List;

public interface ModelService {

    void addModel(ModelDto modelDto) throws EntityExistsException;
    void deleteModel(ModelDto modelDto) throws EntityDoesNotExistsException;
    void updateModel(ModelDto modelDto) throws EntityInvalidException;
    long getCount();
    List<ModelDto> getAllModels();

}
