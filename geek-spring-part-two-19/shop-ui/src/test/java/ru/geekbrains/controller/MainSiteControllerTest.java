package ru.geekbrains.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.repo.BrandRepository;
import ru.geekbrains.persist.repo.CategoryRepository;
import ru.geekbrains.persist.repo.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

import static java.math.RoundingMode.UNNECESSARY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest(classes = MainSiteController.class)
public class MainSiteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @MockBean
    private EurekaClient eurekaClient;

    @BeforeEach
    public void init() {
        InstanceInfo instanceInfo = mock(InstanceInfo.class);
        when(instanceInfo.getHomePageUrl()).thenReturn("mock-homepage-url");

        when(eurekaClient.getNextServerFromEureka(anyString(), anyBoolean()))
                .thenReturn(instanceInfo);
    }

    @AfterEach
    public void dropTestData(){
        brandRepository.deleteAll();
        categoryRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void testProductDetails() throws Exception {
        Brand brand = brandRepository.save(new Brand("brand"));
        Category category = categoryRepository.save(new Category("Category"));
        Product product = productRepository.save(new Product("Product", new BigDecimal(1234), category, brand));

        mvc.perform(get("/product/" + product.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("single-product"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("product", new BaseMatcher<Product>() {

                    @Override
                    public void describeTo(Description description) {

                    }

                    @Override
                    public boolean matches(Object o) {
                        if (o instanceof ProductRepr) {
                            ProductRepr productRepr = (ProductRepr) o;
                            return productRepr.getId().equals(product.getId());
                        }
                        return false;
                    }
                }));
    }

    @Test
    public void testProductsOnMainPage() throws Exception {
        Brand brand = brandRepository.save(new Brand("brand"));
        Category category = categoryRepository.save(new Category("Category"));
        productRepository.save(new Product("Product", new BigDecimal(1234), category, brand));

        mvc.perform(get("/"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("activePage"))
                .andExpect(model().attribute("activePage", "Home"))
                .andExpect(model().attributeExists("products"));

        List<Product> lst = productRepository.findAll();

        assertTrue(lst.size() > 0);
        assertEquals("Product", lst.get(0).getName());
        assertEquals("brand", lst.get(0).getBrand().getName());
        assertEquals("Category", lst.get(0).getCategory().getName());
        assertEquals(new BigDecimal(1234).setScale(2, UNNECESSARY), lst.get(0).getPrice().setScale(2, UNNECESSARY));
    }

    @Test
    public void testLoginOnMainPage() throws Exception {
        mvc.perform(get("/login"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("activePage"))
                .andExpect(model().attribute("activePage", "Account"));
    }

    @Test
    public void testProductsFilteredBySpecificCategory() throws Exception {
        Brand brand = brandRepository.save(new Brand("brand"));
        Category category = categoryRepository.save(new Category("Category"));
        productRepository.save(new Product("Product1", new BigDecimal(1234), category, brand));
        productRepository.save(new Product("Product2", new BigDecimal(1234), category, brand));
        category = categoryRepository.save(new Category("Category2"));
        productRepository.save(new Product("Product3", new BigDecimal(1234), category, brand));

        Category finalCategory = category;
        mvc.perform(get("/category/" + category.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("by-category"))
                .andExpect(model().attributeExists("activePage"))
                .andExpect(model().attribute("activePage", "byCategory"))
                .andExpect(model().attributeExists("category"))
                .andExpect(model().attributeExists("products"))//;
        // not working, expected value is empty
                .andExpect(model().attribute("category", new BaseMatcher<Category>() {
                    @Override
                    public void describeTo(Description description) {

                    }

                    @Override
                    public boolean matches(Object o) {
                        if (o instanceof Category) {
                            Category catRepr = (Category) o;
                            return catRepr.getId().equals(finalCategory.getId());
                        }
                        return false;
                    }
                }));


        List<Product> lst = productRepository.findByCategoryId(category.getId());
        assertEquals(lst.size(), 1);
        assertEquals("Product3", lst.get(0).getName());
        assertEquals("brand", lst.get(0).getBrand().getName());
        assertEquals("Category2", lst.get(0).getCategory().getName());
        assertEquals(new BigDecimal(1234).setScale(2, UNNECESSARY), lst.get(0).getPrice().setScale(2, UNNECESSARY));
    }

    @Test
    public void testProductsFilteredBySpecificBrand() throws Exception {
        Brand brand = brandRepository.save(new Brand("brand"));
        Category category = categoryRepository.save(new Category("Category"));
        productRepository.save(new Product("Product1", new BigDecimal(1234), category, brand));
        productRepository.save(new Product("Product2", new BigDecimal(1234), category, brand));
        brand = brandRepository.save(new Brand("brand2"));
        productRepository.save(new Product("Product3", new BigDecimal(1234), category, brand));

        Brand finalBrand = brand;
        mvc.perform(get("/brand/" + brand.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("by-brand"))
                .andExpect(model().attributeExists("activePage"))
                .andExpect(model().attribute("activePage", "byBrand"))
                .andExpect(model().attributeExists("brand"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attribute("brand", new BaseMatcher<Brand>() {
                    @Override
                    public void describeTo(Description description) {

                    }

                    @Override
                    public boolean matches(Object o) {
                        if (o instanceof Brand) {
                            Brand brand = (Brand) o;
                            return brand.getId().equals(finalBrand.getId());
                        }
                        return false;
                    }
                }));

        List<Product> lst = productRepository.findByBrandId(brand.getId());
        assertEquals(lst.size(), 1);
        assertEquals("Product3", lst.get(0).getName());
        assertEquals("brand2", lst.get(0).getBrand().getName());
        assertEquals("Category", lst.get(0).getCategory().getName());
        assertEquals(new BigDecimal(1234).setScale(2, UNNECESSARY), lst.get(0).getPrice().setScale(2, UNNECESSARY));
    }
}
