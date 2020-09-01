package br.com.mad.springboot2crudgm.controller;

//import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mad.springboot2crudgm.service.ProductServices;
import br.com.mad.springboot2crudgm.vo.FilterVO;
import br.com.mad.springboot2crudgm.vo.ProductVO;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/product/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
	
	@Autowired
	private ProductServices service;
	
	@ApiOperation(value = "Find all products" )
	@GetMapping
    public ResponseEntity<?> findAll(
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="limit", defaultValue = "12") int limit,
			@RequestParam(value="direction", defaultValue = "asc") String direction){

		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));
		
		Page<ProductVO> products =  service.findAll(pageable);
		
		return new ResponseEntity<>(products, HttpStatus.OK);
    }
	
	@ApiOperation(value = "Find a specific product by name" ) 
	@GetMapping(value = "/findProductByName/{name}")
	public ResponseEntity<?> findProductByName(
			@PathVariable("name") String name,
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="limit", defaultValue = "12") int limit,
			@RequestParam(value="direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));
		
		Page<ProductVO> products =  service.findProductByName(name, pageable);
		
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find a specific product by filters name and category" ) 
	@PostMapping(value = "/findProductByFilter")
	public ResponseEntity<?> findProductByFilter(@RequestBody FilterVO filter,
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="limit", defaultValue = "12") int limit,
			@RequestParam(value="direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));
		
		Page<ProductVO> products =  service.findProductByFilters(filter, pageable);
		
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find a specific product by your ID" )
	@GetMapping(value = "/{id}")
	public ProductVO findById(@PathVariable("id") Long id) {
		ProductVO personVO = service.findById(id);
		return personVO;
	}	
	
	@ApiOperation(value = "Create a new product") 
	@PostMapping
	public ProductVO create(@RequestBody ProductVO product) {
		ProductVO personVO = service.create(product);
		return personVO;
	}
	
	@ApiOperation(value = "Update a specific product")
	@PutMapping
	public ProductVO update(@RequestBody ProductVO product) {
		ProductVO personVO = service.update(product);
		return personVO;
	}
	
	@ApiOperation(value = "Delete a specific product by your ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
