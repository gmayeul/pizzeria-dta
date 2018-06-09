package fr.pizzeria.exception;

@SuppressWarnings("serial")
public class SavePizzaException extends StockageException {
	public SavePizzaException(){
		super();
	}
	
	public SavePizzaException(String msg) {
		super(msg);
	}

}
