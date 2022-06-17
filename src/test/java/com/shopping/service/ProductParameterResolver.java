package com.shopping.service;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.springframework.beans.factory.annotation.Autowired;

import com.shopping.entity.Product;
import com.shopping.repository.ProductRepository;

public class ProductParameterResolver implements ParameterResolver {
	@Autowired
	ProductRepository productRepository;
	  @Override
	  public boolean supportsParameter(ParameterContext parameterContext, 
	    ExtensionContext extensionContext) throws ParameterResolutionException {
	      return parameterContext.getParameter().getType() == ProductServiceImpl.class;
	  }

	  @Override
	  public Object resolveParameter(ParameterContext parameterContext, 
	    ExtensionContext extensionContext) throws ParameterResolutionException {
	      return new ProductServiceImpl();
	  }

	
	}