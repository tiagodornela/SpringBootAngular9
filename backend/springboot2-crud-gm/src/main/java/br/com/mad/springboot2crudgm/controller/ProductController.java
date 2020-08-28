package br.com.mad.springboot2crudgm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mad.springboot2crudgm.service.ProductServices;
import br.com.mad.springboot2crudgm.vo.ProductVO;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/api/product/v1")
public class ProductController {
	
	@Autowired
	private ProductServices service;
	
	@Autowired
	private PagedResourcesAssembler<ProductVO> assembler;
	
	@ApiOperation(value = "Find all products" )
	@GetMapping
    public ResponseEntity<?> findAll(
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="limit", defaultValue = "12") int limit,
			@RequestParam(value="direction", defaultValue = "asc") String direction){

		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));
		
		Page<ProductVO> product =  service.findAll(pageable);
		/*
		persons
			.stream()
			.forEach(p -> p.add(
					linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()
				)
			);
		
		PagedResources<?> resources = assembler.toResource(persons);*/
		
		return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
