package br.com.mad.springboot2crudgm.converter.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.mad.springboot2crudgm.model.Product;
import br.com.mad.springboot2crudgm.model.enumeration.CategoryType;
import br.com.mad.springboot2crudgm.vo.ProductVO;

public class MockProduct {


    public Product mockEntity() {
    	return mockEntity(0);
    }
    
    public ProductVO mockVO() {
    	return mockVO(0);
    }
    
    public List<Product> mockEntityList() {
        List<Product> products = new ArrayList<Product>();
        for (int i = 0; i < 14; i++) {
        	products.add(mockEntity(i));
        }
        return products;
    }

    public List<ProductVO> mockVOList() {
        List<ProductVO> products = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
        	products.add(mockVO(i));
        }
        return products;
    }
    
    private Product mockEntity(Integer number) {
    	Product product = new Product();
    	product.setName("Name Teste "+ number);
    	product.setCategory(((number % 2)==0) ? CategoryType.non_perishable : CategoryType.perishable);
    	product.setPrice(number.doubleValue());
        product.setId(number.longValue());
        return product;
    }

    private ProductVO mockVO(Integer number) {
    	ProductVO product = new ProductVO();
    	product.setName("Name Teste "+ number);
    	product.setCategory(((number % 2)==0) ? CategoryType.non_perishable : CategoryType.perishable);
    	product.setPrice(number.doubleValue());
        product.setId(number.longValue());
        return product;
    }

}
