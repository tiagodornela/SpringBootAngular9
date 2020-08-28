package br.com.mad.springboot2crudgm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mad.springboot2crudgm.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE LOWER(CONCAT ('%', :name, '%'))")
	Page<Product> findProductByName(@Param("name") String name, Pageable pageable);
	
}