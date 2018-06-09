package fr.pizzeria.exception;

@SuppressWarnings("serial")
public class UpdatePizzaException extends StockageException {
	public UpdatePizzaException(){
		super();
	}
	
	public UpdatePizzaException(String msg) {
		super(msg);
	}

}
