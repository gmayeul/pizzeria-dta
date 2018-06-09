package fr.pizzeria.exception;

@SuppressWarnings("serial")
public abstract class StockageException extends Exception {
	public StockageException() {
		
	}
	
	public StockageException(String msg) {
		super(msg);
	}

}
