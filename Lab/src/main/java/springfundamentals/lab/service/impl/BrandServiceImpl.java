package springfundamentals.lab.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfundamentals.lab.domain.dto.BrandGetDto;
import springfundamentals.lab.domain.dto.BrandSetDto;
import springfundamentals.lab.domain.entity.Brand;
import springfundamentals.lab.exception.EntityExistsException;
import springfundamentals.lab.repository.BrandRepository;
import springfundamentals.lab.service.BrandService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandReposittory;
    private final ModelMapper mapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandReposittory, ModelMapper mapper) {
        this.brandReposittory = brandReposittory;
        this.mapper = mapper;
    }

    @Override
    public void addBrand(BrandSetDto brandSetDto) throws EntityExistsException {
        if (this.brandReposittory.findByName(brandSetDto.getName()) != null) {
            throw new EntityExistsException();
        }
        Brand brand = this.mapper.map(brandSetDto, Brand.class);
        brand.setCreated(new Date());
        brand.setModified(new Date());

        this.brandReposittory.saveAndFlush(brand);
    }

    @Override
    public List<BrandGetDto> getAllBrands() {
        return this.brandReposittory.findAll().stream()
                .map(b -> this.mapper.map(b, BrandGetDto.class))
                .collect(Collectors.toList());
    }
}
