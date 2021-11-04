package com.vs.pluralsightmvctesting;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest //@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE); //mock by default
@DisplayName("Product Service should")
//@Transactional not necessary
class ProductServiceTest {

    @Autowired
    private ProductService service;

    @MockBean
    private ProductRepository repository;

    private Product mockProduct1;
    private Product mockProduct2;
    private Product mockProduct3;
    private Product mockProduct4;

    @BeforeAll
    static void initAll() {

    }

    @BeforeEach
    void setup() {
        mockProduct1 = new Product(1, "Product Name 1", 6, 1);
        mockProduct2 = new Product(2, "Product Name 2", 10, 1);
        mockProduct3 = new Product("Product Name 3", 2 );
        mockProduct4 = new Product(3, "Product Name 4", 2, 2);
    }

    @Test
    @DisplayName("Return Product by Id")
    void findById() {
        Mockito.doReturn(Optional.of(mockProduct1)).when(repository).findById(1);

        Product returnedProduct = service.findById(1);
        assertSame(returnedProduct, mockProduct1);
    }

    @Nested
    @DisplayName("Return all Products")
    class FindAllTest {
        @Test
        @DisplayName("if there are two products in a list")
        void findAll1() {
            Mockito.doReturn(Arrays.asList(mockProduct1, mockProduct2)).when(repository).findAll();

            List<Product> products = service.findAll();
            assertAll("verify all the conditions",
                    () -> assertEquals(2, products.size(), "number of products in the list"),
                    () -> assertSame(10, products.get(1).getQuantity(), "quantity of the second product in the list")
            );
        }

        @Test
        @DisplayName("if there are no products in a list")
        void findAll2() {
            Mockito.doReturn(Collections.emptyList()).when(repository).findAll();

            List<Product> products = service.findAll();
            assertAll(
                    () -> assertEquals(0, products.size(), "number of products in the list"),
                    () -> assertThrows(IndexOutOfBoundsException.class, () -> products.get(0), "first product in the list")
            );
        }

        @RepeatedTest(5)
        @DisplayName("updating")
        @Disabled
        void findAll3(RepetitionInfo rep) {
            for (int i = 0; i < rep.getCurrentRepetition(); i++) {
                Mockito.doReturn(
                        new Product(i, Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())
                ).when(repository).findAll();

                List<Product> products = service.findAll();
                int number = i;
                assertAll(
                        () -> assertEquals(number + 2, products.get(0).getId(), "number of products in the list")
                );
            }
        }
    }

    @Test
    @DisplayName("Save a product")
    void addProduct() {
        Mockito.doReturn(mockProduct1).when(repository).save(Mockito.any());

        Product returnedProduct = service.save(mockProduct1);
        assertNotNull(returnedProduct);
        assertEquals(1, returnedProduct.getVersion());

        returnedProduct = service.save(mockProduct3);
        assertNotNull(returnedProduct);
        assertEquals(1, returnedProduct.getVersion());
    }

    @Test
    @DisplayName("return all Products with quantity more or equals X")
    void findAllProductsWithQuantityMoreOrEqualsThan() {
        Mockito.doReturn(Arrays.asList(mockProduct1, mockProduct2, mockProduct4)).when(repository).findAll();

        Integer minValue = 6;
        List<Product> products = service.getAllEqualsOrMoreThan(minValue);
        assertAll(
                () -> assertEquals(2, products.size(), "number of products in the list is " + minValue)
        );
    }
}