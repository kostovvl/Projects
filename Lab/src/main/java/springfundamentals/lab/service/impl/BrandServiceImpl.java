package springfundamentals.lab.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfundamentals.lab.repository.BrandReposittory;
import springfundamentals.lab.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandReposittory brandReposittory;
    private final ModelMapper mapper;

    @Autowired
    public BrandServiceImpl(BrandReposittory brandReposittory, ModelMapper mapper) {
        this.brandReposittory = brandReposittory;
        this.mapper = mapper;
    }
}
