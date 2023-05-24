package br.com.embiess83.core.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.embiess83.core.entity.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>, CrudRepository<Category,Long> {

	List<Category> findByName(@Param("name") String name);

}
