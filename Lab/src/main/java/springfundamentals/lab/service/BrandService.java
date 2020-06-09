package springfundamentals.lab.service;

import springfundamentals.lab.domain.dto.BrandGetDto;
import springfundamentals.lab.domain.dto.BrandSetDto;
import springfundamentals.lab.exception.EntityExistsException;

import java.util.List;

public interface BrandService {

    void addBrand(BrandSetDto brandSetDto) throws EntityExistsException;
    List<BrandGetDto> getAllBrands();

}
