package ch.epfl.moocprog;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;
import ch.epfl.moocprog.random.NormalDistribution;
import ch.epfl.moocprog.random.UniformDistribution;
import ch.epfl.moocprog.utils.Time;

public final class FoodGenerator {
	/*Attributs********************************/
	private Time t;

	/*Constructeurs***************************/
	public FoodGenerator() {
		super();
		this.t=Time.ZERO;
	}

	/*Methode de mise a jour du temps*******************************/
	public void update(FoodGeneratorEnvironmentView env, Time dt) {
		
		//Declaration des variables locales
		final double tailleX= Context.getConfig().getInt(Config.WORLD_WIDTH);
		final double tailleY=Context.getConfig().getInt(Config.WORLD_HEIGHT);
		final Time fDelay=Context.getConfig().getTime(Config.FOOD_GENERATOR_DELAY);
		final double FQuantityMin=Context.getConfig().getDouble(Config.NEW_FOOD_QUANTITY_MIN);
		final double FQuantityMax=Context.getConfig().getDouble(Config.NEW_FOOD_QUANTITY_MAX);
		
		//incrementation du compteur de temps
		this.t=t.plus(dt);
		
		//Generation de nourriture si le compteur de temps est superieur au delai de creation
		while (t.compareTo(fDelay)>=0) {
			this.t=t.minus(fDelay);
			double qte=UniformDistribution.getValue(FQuantityMin, FQuantityMax);
			
			//Edition aleatoire de l'emplacement de la nourriture
			double x=NormalDistribution.getValue(tailleX/2.0, tailleX*tailleX/16.0);
			double y=NormalDistribution.getValue(tailleY/2.0, tailleY*tailleY/16.0);
			env.addFood(new Food(new ToricPosition(x, y), qte));
		}
	}
	

}

