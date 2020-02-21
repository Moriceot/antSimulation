package ch.epfl.moocprog;


import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;
import ch.epfl.moocprog.random.UniformDistribution;
import ch.epfl.moocprog.utils.Time;
import ch.epfl.moocprog.utils.Utils;
import ch.epfl.moocprog.utils.Vec2d;

public abstract class Animal extends Positionable{
	//Attributs**************************
	private double direction;
	private int hitpoints;
	private Time lifespan;
	private Time rotationDelay; 
		
	

	public Animal(ToricPosition position, int hitpoints, Time lifespan) {
		super(position);
		this.direction = UniformDistribution.getValue(0.0, 2*Math.PI);
		this.hitpoints=hitpoints;
		this.lifespan=lifespan;
		this.rotationDelay=Time.ZERO;
	}
	//Getters & Setters***********************************
	public final double getDirection() {
		return direction;
	}
	
	public final void setDirection(double angle) {
		this.direction=angle;
	}

	public abstract double getSpeed();

	
	public final int getHitpoints() {
		return hitpoints;
	}

	public final Time getLifespan() {
		return lifespan;
	}
	
	public final Time getRotationDelay() {
		return rotationDelay;
	}
	
	
	//Autre methodes ****************************************
	public abstract void accept(AnimalVisitor visitor, RenderingMedia s);

	@Override
	public String toString() {
		StringBuilder s= new StringBuilder();
		s.append(super.toString()+"\n");
		s.append("Speed :"+ this.getSpeed()+"\n");
		s.append("HitPoins :"+ this.getHitpoints()+"\n");
		s.append("LifeSpan :"+this.getLifespan().toSeconds()+"\n");
		
		return s.toString();
	}

	public final boolean isDead() {
		if ((this.getLifespan().toMilliseconds()<=0)||(this.getHitpoints()<=0)) {
			return true;
		}else return false;
	}
	
	public void update(AnimalEnvironmentView env, Time dt) {
		Time delta= dt.times(Context.getConfig().getDouble(Config.ANIMAL_LIFESPAN_DECREASE_FACTOR));
		this.lifespan=this.lifespan.minus(delta);
//		rotationDelay=rotationDelay.plus(dt);
//		Time animalRotationDelay=Context.getConfig().getTime(Config.ANIMAL_NEXT_ROTATION_DELAY);
		
//		while(this.rotationDelay.compareTo(animalRotationDelay)>=0){
			if (this.isDead()==false) {
				this.move(dt);
			}
//		}
	}	
	
	/*Gestion du deplacement de l'animal*/
	protected final void move(Time dt) {
		this.rotate(dt);
		Vec2d angle=Vec2d.fromAngle(this.getDirection());
		Vec2d vec=angle.scalarProduct(dt.toSeconds()*this.getSpeed());
		this.setPosition(this.getPosition().add(vec));
	}
	
	/*Gestion de la rotation aleatoire des trajectoires*/
	protected RotationProbability computeRotationProbs() {
		double[] angle= {Math.toRadians(-180),Math.toRadians(-100),Math.toRadians(-55),Math.toRadians(-25),Math.toRadians(-10),Math.toRadians(0),Math.toRadians(10),Math.toRadians(25),Math.toRadians(55),Math.toRadians(100),Math.toRadians(180)};
		
		
		double[] probability= {0.0000,0.0000,0.0005,0.0010,0.0050,0.9870,0.0050,0.0010,0.0005,0.0000,0.0000};
		return new RotationProbability(angle, probability);
	}
	
	/*Rotation avant deplacement*/
	public void rotate(Time dt) {

		//Incrementation du temps de rotation
		rotationDelay=rotationDelay.plus(dt);
		
		//Verification que le temps est venu de tourner
		
		Time animalRotationDelay=Context.getConfig().getTime(Config.ANIMAL_NEXT_ROTATION_DELAY);
		while(this.rotationDelay.compareTo(animalRotationDelay)>=0){
			rotationDelay=rotationDelay.minus(animalRotationDelay);
			
			//Recuperation d'une valeur de rotation
			RotationProbability rotationPossible=computeRotationProbs();
			double rotation=Utils.pickValue(rotationPossible.getAngles(), rotationPossible.getProbabilities());
			
			//Reglage de la nouvelle direction
			this.setDirection(this.getDirection()+rotation);
			
		}
	}
	
	
	
	
}
