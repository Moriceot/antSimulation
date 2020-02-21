package ch.epfl.moocprog;

import ch.epfl.moocprog.utils.Utils;

public class RotationProbability {
	//Attributs********************************
	private double[] angle;
	private double[]probability;
	
	//Constructeur*******************************************************
	public RotationProbability(double[] angle, double[] probability) {
		super();
		Utils.requireNonNull("Le tableau des angles est null", angle);
		Utils.requireNonNull("Le tableau des probabilites est null", probability);
	
		
		if(angle.length==probability.length) {
			this.angle = angle.clone();
			this.probability = probability.clone();
		}else {throw new IllegalArgumentException();}
	}
	
	//Getters***********************************************************
	public double[] getAngles() {
		return angle.clone();
	}
	public double[] getProbabilities() {
		return probability.clone();
	}
	
	
	
}
