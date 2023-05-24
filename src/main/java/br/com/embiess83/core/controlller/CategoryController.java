package br.com.embiess83.core.controlller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.embiess83.core.entity.Category;
import br.com.embiess83.core.exception.EntityNotFoundException;
import br.com.embiess83.core.repository.CategoryRepository;

@RestController
public class CategoryController {
	private final CategoryRepository repository;

	CategoryController(CategoryRepository repository) {
		this.repository = repository;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/category")
	Iterable<Category> all() {
		return repository.findAll();
	}
	// end::get-aggregate-root[]

	@PostMapping("/category")
	Category create(@RequestBody Category Category) {
		return repository.save(Category);
	}

	@GetMapping("/category/{id}")
	Category one(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
	}

	@PutMapping("/category/{id}")
	Category replace(@RequestBody Category Category, @PathVariable Long id) {

		return repository.findById(id).map(entity -> {
			entity.setName(Category.getName());
			entity.setDescription(Category.getDescription());
			return repository.save(entity);
		}).orElseGet(() -> {
			Category.setId(id);
			return repository.save(Category);
		});
	}

	@DeleteMapping("/category/{id}")
	void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
