package ru.geekbrains.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.repo.BrandRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class BrandControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BrandRepository brandRepository;

//    @MockBean
//    private StockService stockService;

    @BeforeEach
    public void init() {
        brandRepository.deleteAllInBatch();
    }

    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    public void testBrandCreation() throws Exception {
        mvc.perform(post("/admin/brand")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "-1")
                .param("name", "New brand")
                .with(csrf())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/brands"));

        Optional<Brand> opt = brandRepository.findOne(Example.of(new Brand("New brand")));

        assertTrue(opt.isPresent());
        assertEquals("New brand", opt.get().getName());
    }
}
