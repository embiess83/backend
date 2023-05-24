package br.com.embiess83.core.controlller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.embiess83.core.entity.Product;
import br.com.embiess83.core.exception.EntityNotFoundException;
import br.com.embiess83.core.repository.ProductRepository;

@RestController
public class ProductController {
	private final ProductRepository repository;

	ProductController(ProductRepository repository) {
		this.repository = repository;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/product")
	Iterable<Product> all() {
		return repository.findAll();
	}
	// end::get-aggregate-root[]

	@PostMapping("/product")
	Product create(@RequestBody Product product) {
		return repository.save(product);
	}

	@GetMapping("/product/{id}")
	Product one(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
	}

	@PutMapping("/product/{id}")
	Product replace(@RequestBody Product product, @PathVariable Long id) {

		return repository.findById(id).map(entity -> {
			entity.setName(product.getName());
			entity.setDescription(product.getDescription());
			entity.setCategory(product.getCategory());
			return repository.save(entity);
		}).orElseGet(() -> {
			product.setId(id);
			return repository.save(product);
		});
	}

	@DeleteMapping("/product/{id}")
	void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
