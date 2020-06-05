package javaweb.lab.service.impl;

import javaweb.lab.domain.dto.branddto.BrandDto;
import javaweb.lab.domain.entity.Brand;
import javaweb.lab.exception.EntityDoesNotExistsException;
import javaweb.lab.exception.EntityExistsException;
import javaweb.lab.exception.EntityInvalidException;
import javaweb.lab.repository.BrandRepository;
import javaweb.lab.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper mapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper mapper) {
        this.brandRepository = brandRepository;
        this.mapper = mapper;
    }

    @Override
    public void createBrand(BrandDto brandDto) throws EntityExistsException {

        Brand test = this.brandRepository.findByName(brandDto.getName());
        if (this.brandRepository.findByName(brandDto.getName()) != null) {
            throw new EntityExistsException();
        }
        Brand brand = this.mapper.map(brandDto, Brand.class);
        brand.setCreated(new Date());

        this.brandRepository.saveAndFlush(brand);
    }

    @Override
    public void deleteBrand(BrandDto brandDto) throws EntityDoesNotExistsException {

        if (this.brandRepository.findByName(brandDto.getName()) == null) {
            throw new EntityDoesNotExistsException();
        }

        Brand forDeletion = this.brandRepository.findByName(brandDto.getName());
        this.brandRepository.deleteById(forDeletion.getId());

    }

    @Override
    public void updateBrand(BrandDto brandDto) throws EntityInvalidException {

        if (this.brandRepository.findByName(brandDto.getName()) == null) {
            throw new EntityInvalidException();
        }

        Brand forUpdate = this.brandRepository.findByName(brandDto.getName());
        forUpdate.setModified(new Date());
        this.brandRepository.saveAndFlush(forUpdate);

    }

    @Override
    public BrandDto getBrand(String name) {
        return this.mapper.map(this.brandRepository.findByName(name), BrandDto.class);
    }

    @Override
    public long getCount() {
        return this.brandRepository.count();
    }
}
