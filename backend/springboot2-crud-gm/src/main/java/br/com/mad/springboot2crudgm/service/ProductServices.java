package br.com.mad.springboot2crudgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.mad.springboot2crudgm.converter.DozerConverter;
import br.com.mad.springboot2crudgm.exception.ResourceNotFoundException;
import br.com.mad.springboot2crudgm.model.Product;
import br.com.mad.springboot2crudgm.repository.ProductRepository;
import br.com.mad.springboot2crudgm.vo.FilterVO;
import br.com.mad.springboot2crudgm.vo.ProductVO;

@Service
public class ProductServices {
	
	@Autowired
	ProductRepository repository;
		
	public ProductVO create(ProductVO product) {
		var entity = DozerConverter.parseObject(product, Product.class);
		var vo = DozerConverter.parseObject(repository.save(entity), ProductVO.class);
		return vo;
	}
		
	public Page<ProductVO> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToProductVO);
	}
	
	public Page<ProductVO> findProductByName(String product, Pageable pageable) {
		var page = repository.findProductByName(product, pageable);
		return page.map(this::convertToProductVO);
	}
	
	private ProductVO convertToProductVO(Product entity) {
		return DozerConverter.parseObject(entity, ProductVO.class);
	}
	
	public ProductVO findById(Long id) {
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, ProductVO.class);
	}
		
	public ProductVO update(ProductVO product) {
		var entity = repository.findById(product.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setName(product.getName());
		entity.setPrice(product.getPrice());
		entity.setCategory(product.getCategory());
		
		var vo = DozerConverter.parseObject(repository.save(entity), ProductVO.class);
		return vo;
	}	
	
	public void delete(Long id) {
		Product entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

	public Page<ProductVO> findProductByFilters(FilterVO filter, Pageable pageable) {
		var page = repository.findProductByFilters(filter.getName(), filter.getCategory(), pageable);
		return page.map(this::convertToProductVO);
	}

}
