package ch.epfl.moocprog;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;

public final class Termite extends Animal{

	public Termite(ToricPosition position) {
		super(position, Context.getConfig().getInt(Config.TERMITE_HP), Context.getConfig().getTime(Config.TERMITE_LIFESPAN));
		//TODO Verifier qu'on ne peut pas faire mieux avec ce constructeur
	}
	

	@Override
	public void accept(AnimalVisitor visitor, RenderingMedia s) {
		visitor.visit(this, s);
	}

	@Override
	public double getSpeed() {
		return Context.getConfig().getDouble(Config.TERMITE_SPEED);
	}
	
}
