package springfundamentals.lab2.service;

import springfundamentals.lab2.domain.dto.BrandGetDto;
import springfundamentals.lab2.domain.dto.BrandSetDto;
import springfundamentals.lab2.exception.EntityExistsException;

import java.util.List;

public interface BrandService {

    void addBrand(BrandSetDto brandSetDto) throws EntityExistsException;
    List<BrandGetDto> getAllBrands();

}
