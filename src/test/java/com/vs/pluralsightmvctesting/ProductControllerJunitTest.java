package com.vs.pluralsightmvctesting;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.TypedArgumentConverter;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given Product Controller")
@Slf4j
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
        mockProduct3 = new Product(2, "Product Name 2", 10, 1);
        //mockProduct3 = new Product("Product Name 3", 2 );
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

    @TestFactory
    Collection<DynamicTest> dynamicTestsFromCollection() {
        return Arrays.asList(
                DynamicTest.dynamicTest(
                        "1st dynamic Test",
                        () -> assertEquals(1, 1)),
                DynamicTest.dynamicTest(
                        "2nd dynamic Test",
                        () -> assertEquals(2, 2))
        );
    }

    @Nested
    @DisplayName("Parametrized Test")
    class MyParamTest {

        @BeforeEach
        void setup() {
            //log.info("Before each");
        }

        @ParameterizedTest(name = "Test #{index}: number={0}")
        @ValueSource(longs = {22, 16, -5, 66, 3533})
        @DisplayName("DN")
        void myParamTest(long number) {
            assertTrue(number > 2);

        }

        @ParameterizedTest(name = "Test #{index}: number={0}")
        @ValueSource(longs = {22, 16, -5, 66, 3533})
        @DisplayName("DN2")
        void myParamTest200(long number, TestInfo testInfo, TestReporter testReporter) {
            System.out.println(testInfo.getDisplayName());
            testReporter.publishEntry(String.valueOf(number));
            assertTrue(number > 2);

        }
    }

    @Nested
    @DisplayName("Parametrized Test 2")
    class MyParamTest2 {

        @BeforeEach
        void setup(TestInfo testInfo) {
            System.out.println(testInfo.getDisplayName());
        }

        @ParameterizedTest(name = "Test #{index}: number={0}")
        @ValueSource(longs = {22, 16, -5, 66, 3533})
        @DisplayName("DN2")
        void myParamTest200(long number, TestInfo testInfo, TestReporter testReporter) {
            testReporter.publishEntry(String.valueOf(number));
            assertTrue(number > 2);

        }
    }

    @Nested
    @DisplayName("Parametrized Test with Enum")
    class MyParamTestWithEnum{

        @BeforeEach
        void setup(TestInfo testInfo) {
            System.out.println(testInfo.getDisplayName());
        }

        @ParameterizedTest(name = "Test #{index}: number={0}")
        @EnumSource(Products.class)
        @DisplayName("DN3")
        void myParamTest200(Products product) {
            assertEquals(Products.SMALL, product);

        }

        @ParameterizedTest(name = "Test #{index}: number={0}")
        @EnumSource(value = Products.class, names = {"SMALL"})
        @DisplayName("DN3")
        void myParamTest300(Products product) {
            assertEquals(Products.SMALL, product);

        }
    }

    @AllArgsConstructor
    enum Products {
        SMALL(1),
        BIG(2);

        private int productId;

        public int getProductId() {
            return productId;
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    @DisplayName("Parametrized Test with Method Source Annotation")
    class MyParamTestWithMethodSourceAnnotation{

        private LongStream getIds() {
            return LongStream.range(1, 6);
        }

        @ParameterizedTest(name = "Test #{index}: number={0}")
        @MethodSource("getIds")
        @DisplayName("DN1")
        void myParamTest200(long number) {
            assertTrue(number > 2);
        }

        private Stream<Arguments> getPoints() {
            return getIds().mapToObj(
                    id -> Arguments.of(id, 100 * id)
            );
        }

        @ParameterizedTest(name = "Test #{index}: number={0}")
        @MethodSource("getPoints")
        @DisplayName("DN2")
        void myParamTest300(long number, long points) {
            assertTrue(number > 2 && points > 200);
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    @DisplayName("Parametrized Test with Parameter Converter")
    class MyParamTestWithParameterConverter {

        @ParameterizedTest
        @ValueSource(strings = { "1,TV,10,1", "2,Radio,5,4"})
        @DisplayName("DN1")
        void myParamTest200(@ConvertWith(ProductArgumentConverter.class) Product product) {
            assertTrue(product.getQuantity() > 4);
        }
    }

    static class ProductArgumentConverter extends TypedArgumentConverter<String, Product> {

        protected ProductArgumentConverter() {
            super(String.class, Product.class);
        }

        @Override
        protected Product convert(String s) throws ArgumentConversionException {
            String[] productString = s.split(",");
            Product product = new Product(
                    Integer.parseInt(productString[0]),
                    productString[1].trim(),
                    Integer.parseInt(productString[2]),
                    Integer.parseInt(productString[3])
            );
            return product;
        }
    }
}