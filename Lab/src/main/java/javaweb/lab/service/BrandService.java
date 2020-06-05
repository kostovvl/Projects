package javaweb.lab.service;

import javaweb.lab.domain.dto.branddto.BrandDto;
import javaweb.lab.exception.EntityDoesNotExistsException;
import javaweb.lab.exception.EntityExistsException;
import javaweb.lab.exception.EntityInvalidException;

public interface BrandService {

    void createBrand(BrandDto brandDto) throws EntityExistsException;
    void deleteBrand(BrandDto brandDto) throws EntityDoesNotExistsException;
    void updateBrand(BrandDto brandDto) throws EntityInvalidException;
    BrandDto getBrand(String name);
    long getCount();

}
