package ch.epfl.moocprog;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;

public class AntWorker extends Ant {

	//Attributs***************************************************
	private double foodQuantity;
	//private double speed;
	

	//Constructeur************************************************
	public AntWorker(ToricPosition position,Uid antHillId) {
		super(position, Context.getConfig().getInt(Config.ANT_WORKER_HP), Context.getConfig().getTime(Config.ANT_WORKER_LIFESPAN),antHillId);
//		this.speed=Context.getConfig().getDouble(Config.ANT_WORKER_SPEED);
	}
	
	//Getters*****************************************************
	public final double getFoodQuantity() {
		return foodQuantity;
	}

	@Override
	public void accept(AnimalVisitor visitor, RenderingMedia s) {
		visitor.visit(this, s);
		
	}

	@Override
	public double getSpeed() {
		return Context.getConfig().getDouble(Config.ANT_WORKER_SPEED);
	}

	@Override
	public String toString() {
		return  super.toString() + "\n Quantity:"+foodQuantity;
	}
	
	

}
