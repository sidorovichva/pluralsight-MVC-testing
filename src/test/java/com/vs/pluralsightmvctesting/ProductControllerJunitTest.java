package com.vs.pluralsightmvctesting;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.lang.management.MemoryType;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given Product Controller")
class ProductControllerJunitTest {

    @MockBean
    private ProductService service;

    @Autowired
    private MockMvc mockMvc;

    private Product mockProduct1;
    private Product mockProduct2;
    private Product mockProduct3;

    @BeforeEach
    void setUp() {
        mockProduct1 = new Product(1, "Product Name 1", 6, 1);
        mockProduct2 = new Product(2, "Product Name 2", 10, 1);
        mockProduct3 = new Product("Product Name 3", 2 );
    }

    @Nested
    @DisplayName("should get Product by ID")
    class GetProductById {
        @Test
        @DisplayName("if provided with id")
        void getProductById1() throws Exception {
            Mockito.doReturn(mockProduct1).when(service).findById(1);
            mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", 1))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    //.andExpect(MockMvcResultMatchers.header().string(HttpHeaders.ETAG, "\"1\""))
                    //.andExpect(MockMvcResultMatchers.header().string(HttpHeaders.LOCATION, "/products/1"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Product Name 1")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", Matchers.is(6)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.version", Matchers.is(1)))
            ;
        }
    }

    @Nested
    @DisplayName("should not get Product by ID")
    class GetProductByIdNot {
        @Test
        @DisplayName("if provided with non-int id")
        void getProductById1() throws Exception {
            Mockito.doReturn(mockProduct1).when(service).findById(1);
            mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", 't'))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
            ;
        }
    }

    @Test
    void getAllProducts() {
    }

    @Nested
    @DisplayName("should add Product")
    class AddProduct {
        @Test
        @DisplayName("if provided with name and quantity only")
        void addProduct1() throws Exception {
            Mockito.doReturn(mockProduct1).when(service).save(mockProduct1);
            mockMvc.perform(MockMvcRequestBuilders.post("/products"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    //.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    /*.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Product Name 3")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", Matchers.is(2)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.version", Matchers.is(1)))*/

            ;
        }
    }

    @Nested
    @DisplayName("should not add Product")
    class AddProductNot {
        @Test
        @DisplayName("if provided with nothing")
        void addProduct1() throws Exception {
            Mockito.doReturn(null).when(service).save(new Product());
            mockMvc.perform(MockMvcRequestBuilders.post("/products"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    //.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    /*.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Product Name 3")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", Matchers.is(2)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.version", Matchers.is(1)))*/

            ;
        }
    }
}