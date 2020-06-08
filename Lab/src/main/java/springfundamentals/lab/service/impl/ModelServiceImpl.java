package springfundamentals.lab.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfundamentals.lab.repository.ModelRepository;
import springfundamentals.lab.service.ModelService;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final ModelMapper mapper;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, ModelMapper mapper) {
        this.modelRepository = modelRepository;
        this.mapper = mapper;
    }
}
