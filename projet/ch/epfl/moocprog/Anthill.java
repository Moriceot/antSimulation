package ch.epfl.moocprog;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;

public class Anthill extends Positionable{
	//Attributs**************************************
	private Uid antHillId;
	private double stock;
	private double workerProba;
	
	//Constructeur************************************
	public Anthill(ToricPosition position, double workerProba) {
		super(position);
		this.stock=0.0;
		this.workerProba=workerProba;
	}
	
	public Anthill(ToricPosition position) {
		super(position);
		this.stock=0.0;
		this.workerProba=Context.getConfig().getDouble(Config.ANTHILL_WORKER_PROB_DEFAULT);
		this.antHillId=Uid.createUid();
	}
	
	//Getter*****************************
	public double getFoodQuantity() {
		return stock;
	}
	
	public Uid getAnthillId() {
		return antHillId;
	}

	public void setAnthillId(Uid antHillId) {
		this.antHillId = antHillId;
	}

	//Ajout de nourriture dans la fourmilliere
	public void dropFood(double toDrop) {
		if(toDrop<0) {throw new IllegalArgumentException("Impossible de deposer une quantite negative de nourriture");}
		stock+=toDrop;	
	}
	
	//Affichage*************************************************
	public String toString() {
			StringBuilder s=new StringBuilder();
			s.append(super.toString()+"\n");
			s.append("Quantity:"+this.getFoodQuantity());
		return s.toString();
	}
	
}
