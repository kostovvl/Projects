package springfundamentals.lab.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfundamentals.lab.domain.dto.ModelGetDto;
import springfundamentals.lab.domain.dto.ModelSetDto;
import springfundamentals.lab.domain.entity.Model;
import springfundamentals.lab.exception.EntityExistsException;
import springfundamentals.lab.repository.BrandRepository;
import springfundamentals.lab.repository.ModelRepository;
import springfundamentals.lab.service.ModelService;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final ModelMapper mapper;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, BrandRepository brandRepository, ModelMapper mapper) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public void addNewModel(ModelSetDto modelSetDto) throws EntityExistsException {

        if (this.modelRepository.findByName(modelSetDto.getName()) != null) {
            throw new EntityExistsException();
        }

        Model newModel = this.mapper.map(modelSetDto, Model.class);
        newModel.setBrand(this.brandRepository.findByName(modelSetDto.getBrand()));
        newModel.setCreated(new Date());
        newModel.setModified(new Date());

        this.modelRepository.saveAndFlush(newModel);

    }

    @Override
    public List<ModelGetDto> getAllModels() {
        return this.modelRepository.findAll().stream()
                .map(m -> this.mapper.map(m, ModelGetDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteModel(long id) {

        this.modelRepository.deleteById(id);
    }
}
