package br.com.embiess83.core.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8137719117801534747L;

	public EntityNotFoundException(Long id) {
		super("Could not find product " + id);
	}
}
