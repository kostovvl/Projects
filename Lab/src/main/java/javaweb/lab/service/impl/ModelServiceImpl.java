package javaweb.lab.service.impl;

import javaweb.lab.domain.dto.branddto.BrandDto;
import javaweb.lab.domain.dto.modeldto.ModelDto;
import javaweb.lab.domain.entity.Brand;
import javaweb.lab.domain.entity.Model;
import javaweb.lab.exception.EntityDoesNotExistsException;
import javaweb.lab.exception.EntityExistsException;
import javaweb.lab.exception.EntityInvalidException;
import javaweb.lab.repository.ModelRepository;
import javaweb.lab.service.BrandService;
import javaweb.lab.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final BrandService brandService;
    private final ModelMapper mapper;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, BrandService brandService, ModelMapper mapper) {
        this.modelRepository = modelRepository;
        this.brandService = brandService;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public void addModel(ModelDto modelDto) throws EntityExistsException {

        if (this.modelRepository.findByName(modelDto.getName()) != null) {
            throw new EntityExistsException();
        }
        Model newModel = this.mapper.map(modelDto, Model.class);
        BrandDto brandDto = this.brandService.getBrand(modelDto.getBrand());

        if (brandDto == null) {
            brandDto = new BrandDto();
            brandDto.setName(modelDto.getBrand());
            brandDto.setCreated(new Date());
            this.brandService.createBrand(brandDto);
        }
        newModel.setBrand(this.mapper.map(brandDto, Brand.class));

        this.modelRepository.saveAndFlush(newModel);
    }

    @Override
    public void deleteModel(ModelDto modelDto) throws EntityDoesNotExistsException {


        if (this.modelRepository.findByName(modelDto.getName()) == null) {
            throw new EntityDoesNotExistsException();
        }

        Model forDeletion = this.modelRepository.findByName(modelDto.getName());
        this.modelRepository.deleteById(forDeletion.getId());
    }

    @Override
    public void updateModel(ModelDto modelDto) throws EntityInvalidException {

        if (this.modelRepository.findByName(modelDto.getName()) == null) {
            throw new EntityInvalidException();
        }

        Model forUpdate = this.modelRepository.findByName(modelDto.getName());
        forUpdate.setModified(new Date());
        this.modelRepository.saveAndFlush(forUpdate);

    }

    @Override
    public long getCount() {
        return this.modelRepository.count();
    }
}
