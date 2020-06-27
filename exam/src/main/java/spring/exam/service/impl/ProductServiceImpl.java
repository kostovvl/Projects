package spring.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.exam.domain.dto.ProductDto;
import spring.exam.domain.entity.Category;
import spring.exam.domain.entity.Product;
import spring.exam.domain.view.ProductView;
import spring.exam.repository.ProductRepository;
import spring.exam.service.CategoryService;
import spring.exam.service.ProductService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.mapper = mapper;
    }


    @Override
    public boolean productExists(String name) {
        if (this.productRepository.findByName(name).orElse(null) == null) {
        return false;
        }
        return true;
    }

    @Override
    public void addProduct(ProductDto productDto) {

        productDto.setCategory(this.categoryService.findByName(productDto.getCategory().getName()));
        this.productRepository.saveAndFlush(this.mapper.map(productDto, Product.class));
    }

    @Override
    public List<ProductView> findAllProducts() {

        return this.productRepository.findAll().stream()
                .map(p -> this.mapper.map(p, ProductView.class))
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public void deleteById(String id) {
        this.productRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAll() {
        this.productRepository.deleteAll();
    }

    @Override
    public BigDecimal getTotalPrice() {
        BigDecimal result = new BigDecimal("0");
        List<Product> products = this.productRepository.findAll();

        for (Product product : products) {
            result = result.add(product.getPrice());
        }
        return result;
    }


}
