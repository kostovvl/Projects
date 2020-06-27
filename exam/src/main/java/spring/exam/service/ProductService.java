package spring.exam.service;

import spring.exam.domain.dto.ProductDto;
import spring.exam.domain.view.ProductView;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    boolean productExists(String name);
    void addProduct(ProductDto productDto);
    List<ProductView> findAllProducts();
    void deleteById(String id);
    void deleteAll();
    BigDecimal getTotalPrice();

}
