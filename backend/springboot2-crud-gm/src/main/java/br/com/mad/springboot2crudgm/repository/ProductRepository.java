package br.com.mad.springboot2crudgm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mad.springboot2crudgm.model.Product;
import br.com.mad.springboot2crudgm.model.enumeration.CategoryType;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE LOWER(CONCAT ('%', :name, '%'))")
	Page<Product> findProductByName(@Param("name") String name, Pageable pageable);

	@Query("SELECT p FROM Product p " + 
			" WHERE " +
			"( p.name LIKE LOWER(CONCAT ('%', :name, '%')) or :name IS NULL ) "+
			"AND (p.category = :category OR :category IS NULL) ")
	Page<Product> findProductByFilters(@Param("name") String name, @Param("category") CategoryType category, Pageable pageable);
	
}