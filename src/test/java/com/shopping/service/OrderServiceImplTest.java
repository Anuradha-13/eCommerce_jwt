package com.shopping.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.shopping.dto.OrderProductDto;
import com.shopping.entity.Order;
import com.shopping.entity.OrderProducts;
import com.shopping.entity.Product;
import com.shopping.repository.OrderProductsRepository;
import com.shopping.repository.OrderRepository;



@ExtendWith(SpringExtension.class)
public class OrderServiceImplTest {
	
	
    @InjectMocks
    private OrderServiceImpl orderServiceImpl;
     
   
    @Mock
    private OrderRepository orderRepository;
    
    @Mock
    private OrderProductsRepository orderProductsRepository;

   
    private Order order;
    private List<OrderProductDto> orderProductDto = new ArrayList();
    private Product product;
    
	List<OrderProducts> orderProducts = new ArrayList<>();
    

    @BeforeEach
    public void setup(){
    	product = product.builder()
                .id(1)
                .name("cake")
                .price(20)
                .build();
    	
    	
    
       order = new Order();
       order.setId(1);
       order.setDateCreated(LocalDate.now());
      
       orderProductDto.add(new OrderProductDto(product,1));
       
       for (OrderProductDto dto : orderProductDto) {
    	   orderProducts.add(
					new OrderProducts(order, dto.getProduct(),dto.getQuantity()));
				        
		}
       order.setOrderProducts(orderProducts);
       order.setUserId(1);
     
       
      }

	@Test
	public void testCreateOrder() {
		Mockito.when(orderRepository.save(order)).thenReturn(order);
		
		Order savedorder = orderServiceImpl.createOrder(orderProductDto,1) ;
		assertNotNull(savedorder);
		assertEquals(1,savedorder.getUserId());
	

			}
	

}
