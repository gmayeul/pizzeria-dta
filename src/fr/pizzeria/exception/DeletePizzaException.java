package fr.pizzeria.exception;

@SuppressWarnings("serial")
public class DeletePizzaException extends StockageException {
	public DeletePizzaException() {
		super();
	}
	
	public DeletePizzaException(String msg) {
		super(msg);
	}

}
