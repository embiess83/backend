package br.com.embiess83.core.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.embiess83.core.entity.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, CrudRepository<Product,Long> {

	List<Product> findByName(@Param("name") String name);

}
