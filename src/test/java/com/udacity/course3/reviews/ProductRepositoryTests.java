package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.repository.ProductRepository;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Collection;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(ReviewAPITestConfiguration.class)
public class ProductRepositoryTests {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private ProductRepository productRepository;




    @Test
    public void injectedComponentsAreNotNull(){
        assertThat(dataSource,is(notNullValue()));
     assertThat(jdbcTemplate,is(notNullValue()));
        assertThat(jdbcTemplate,is(notNullValue()));
        assertThat(entityManager,is(notNullValue()));
        assertThat(testEntityManager,is(notNullValue()));
        assertThat(productRepository,is(notNullValue()));
    }

    @Test
    public void testSaveProduct(){

String productName="product1";
        Product product1=saveProduct(productName);
        System.out.println("******************************************");
        System.out.println(product1.getProductName());
        System.out.println(product1.getProductId());
        System.out.println("******************************************");

        assertEquals("Product Name not matching",product1.getProductName(),productName);
        assertNotEquals("Product Id should be auto generated",0,product1.getProductId());

    }

    public Product saveProduct(String name){
        Product product = new Product();
        product.setProductName(name);
        Product product1=productRepository.save(product);
        return product1;
    }

    @Test
    public void testFindByProductId(){
        String productName="product2";
        Product product= saveProduct(productName);
        //find saved product
        Optional<Product> productOptional=productRepository.findById(product.getProductId());
        assertTrue("Product should be retrived from database",productOptional.isPresent());
        assertEquals("Product Name should be matched with the saved one",productName,productOptional.get().getProductName());

    }
    @Test
    public void testListProducts(){
        String productName="product3";
        Product product= saveProduct(productName);
        Iterable<Product> products=productRepository.findAll();
        assertTrue("Should return atleast one product",((Collection<Product>) products).size()>0);
    }
}
