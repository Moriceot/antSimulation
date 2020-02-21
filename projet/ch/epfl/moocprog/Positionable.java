package ch.epfl.moocprog;

public class Positionable {

	//Attributs************************
	private ToricPosition position;
	
	//Constructeurs*************************************
	public Positionable(ToricPosition position) {
		super();
		this.position = position;
	}
	
	public Positionable() {
		this.position=new ToricPosition(0, 0);
	}
	
	//Getters et Setters********************************
	public ToricPosition getPosition() {
		return position;
	}

	protected final void setPosition(ToricPosition position) {
		this.position = position;
	}
	
	//Affichage ***********************************
	public String toString() {
		return position.toString();
	}
	
	
}
