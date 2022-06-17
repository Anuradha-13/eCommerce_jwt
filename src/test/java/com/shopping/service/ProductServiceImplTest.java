package com.shopping.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import javax.transaction.Transactional;

import org.aspectj.lang.annotation.Before;
import org.hibernate.engine.transaction.jta.platform.internal.JRun4JtaPlatform;
import org.hibernate.engine.transaction.jta.platform.spi.JtaPlatform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Server;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.shopping.entity.Product;
import com.shopping.entity.Product.ProductBuilder;
import com.shopping.exception.ProductNotFoundException;
import com.shopping.repository.ProductRepository;


@ExtendWith(SpringExtension.class)
public class ProductServiceImplTest {
	

	@Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    private Product product;

    @BeforeEach
    public void setup(){
       product = product.builder()
                .id(1)
                .name("cake")
                .price(20)
                .build();
    }

	@Test
	public void testGetProductById() {
		Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product));
		Product p= productServiceImpl.getProductById(1);
		assertNotNull(p);
		assertEquals("cake",p.getName());
		
		}
	
	@Test
	public void testGetProductByIdForException() {
		Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product));
		Product p= productServiceImpl.getProductById(2);
				
		Mockito.when(productRepository.findById(5)).thenThrow(new ProductNotFoundException("Product id not found"));
		assertEquals(p,new ProductNotFoundException("Product id not found"));
		
		}
	
}
