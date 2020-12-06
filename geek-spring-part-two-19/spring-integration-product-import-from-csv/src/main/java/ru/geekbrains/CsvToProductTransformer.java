package ru.geekbrains;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.file.transformer.AbstractFilePayloadTransformer;
import org.springframework.stereotype.Service;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.repo.BrandRepository;
import ru.geekbrains.persist.repo.CategoryRepository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class CsvToProductTransformer extends AbstractFilePayloadTransformer<List<Product>> {

    private static final Logger logger =
            LoggerFactory.getLogger(CsvToProductTransformer.class);

    @Value("${csv.data.file.separator}")
    private String separator;

    private CSVParser parser;

    private BrandRepository brandRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void init() {

        parser = new CSVParserBuilder()
                .withSeparator(separator.charAt(0))
                .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
    }

    @Override
    protected List<Product> transformFile(File file) {

        List<Product> products = null;

        try (Reader reader = new FileReader(file, StandardCharsets.UTF_8);
             CSVReader csvReader = new CSVReaderBuilder(reader)
                     .withSkipLines(1)
                     .withCSVParser(parser)
                     .build()) {

            products = new ArrayList<>();
            String[] line;

            while ((line = csvReader.readNext()) != null) {
                products.add(getProductFromStringsArray(line));
            }
            logger.info("File: {} is converted, total {} lines", file.getName(), products.size());

        } catch (Exception e) {
            logger.error("Exception thrown when converting the file:" +
                            " {}, message: {}, root cause: {}",
                    file.getName(), e.getMessage(), e.getCause().getMessage());
        }

        System.gc(); // hack to release file to delete
        file.delete();
        logger.info("File: {} is deleted", file.getName());
        return products;
    }

    private Product getProductFromStringsArray(String[] fileLineItems) {

        if (fileLineItems.length != 5) {
            throw new RuntimeException("Incorrect number of columns, " +
                    "there must be 5");
        }

        Product product = new Product();

        try {

//          product = product.getId();
            product.setName(fileLineItems[1]); //
            product.setPrice(new BigDecimal(fileLineItems[2])); //
            product.setBrand(brandRepository.findBrandById(Long.valueOf(fileLineItems[3])));
            product.setCategory(categoryRepository.findCategoryById(Long.valueOf(fileLineItems[4]))); //

        } catch (Exception e) {
            throw new RuntimeException("Exception when converting a file " +
                    "line to Product entity, line: " +
                    Arrays.toString(fileLineItems), e);
        }

        return product;
    }
}