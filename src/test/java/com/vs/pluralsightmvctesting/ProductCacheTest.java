package com.vs.pluralsightmvctesting;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ProductCacheTest {

    @Autowired
    ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    CacheManager cacheManager;

    @Test
    @Disabled
    void cacheTest() {
        String name = "TV";
        //BDDMockito.given(productRepository.findByName(name)).willReturn(Optional.of(new Product(name, 10)));
        productRepository.save(new Product(name, 10));

        productService.findByName(name);
        productService.findByName(name);
        productService.findByName(name);

        BDDMockito.then(productRepository).should(BDDMockito.times(1)).findByName(name);
        //cacheManager.getCache("products").evictIfPresent();

        productRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException());
    }

}
