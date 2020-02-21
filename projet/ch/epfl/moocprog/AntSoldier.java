package ch.epfl.moocprog;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;

public class AntSoldier extends Ant{

	//Attributs**********************************************
	//private double speed;

	public AntSoldier(ToricPosition position,Uid antHillId) {
		super(position, Context.getConfig().getInt(Config.ANT_SOLDIER_HP), Context.getConfig().getTime(Config.ANT_SOLDIER_LIFESPAN),antHillId);
//		this.speed=Context.getConfig().getDouble(Config.ANT_SOLDIER_SPEED);
	}

	@Override
	public void accept(AnimalVisitor visitor, RenderingMedia s) {
		visitor.visit(this, s);
	}

	@Override
	public double getSpeed() {
		return Context.getConfig().getDouble(Config.ANT_SOLDIER_SPEED);
	}

}
