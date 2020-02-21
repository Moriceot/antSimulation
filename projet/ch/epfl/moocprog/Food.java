package ch.epfl.moocprog;


public final class Food extends Positionable{
	
	//Attributs*********************
	private double quantity;

	
	
	//Constructeurs******************************************
	public Food(ToricPosition position, double quantity) {
		super(position);
		if (quantity<0.0) {
			this.quantity=0.0;
		}else {			
			this.quantity = quantity;
		}
	}
	public Food() {
		super();
		this.quantity = 0.0;
	}
	
	
	
	//Getters & Setters***************************************
	public double getQuantity() {
		return quantity;
	}

	public double takeQuantity(double amount) throws IllegalArgumentException{
		if (amount<0) {throw new IllegalArgumentException();}
		else if (amount>quantity) {
			amount=quantity;
			this.quantity=0.0;
		}else {
			this.quantity -=amount;
		}
		return amount;
	}

	@Override
	public String toString() {
		 String s=super.toString()+"\n";
		 s+= String.format("Quantity: %.2f", getQuantity());
		 return s;
	}
	
	
}
