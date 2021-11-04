package com.vs.pluralsightmvctesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest //@WebFluxTest for reactive
public class ProductControllerIntegTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    private Product mockProduct1;

    @BeforeEach
    void setUp() {
        mockProduct1 = new Product(1, "TV", 6, 1);
        mockProduct1 = new Product(1, "Sofa", 6, 1);
    }

    @Test
    void getProduct_forSavedProduct_isReturned() throws Exception {
        Mockito.doReturn(mockProduct1).when(service).findById(1);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("TV"))
                .andExpect(jsonPath("quantity").value(6))
                .andExpect(jsonPath("version").value(1));
    }

    @Test
    void throwException_forSavedProduct_isReturned() throws Exception {
        BDDMockito.given(service.findById(Mockito.anyInt())).willThrow(ProductNotFound.class);

        mockMvc.perform(get("/products/5"))
                .andExpect(status().isNotFound());
    }
}
