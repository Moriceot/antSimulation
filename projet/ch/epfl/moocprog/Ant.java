package ch.epfl.moocprog;

import java.util.List;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;
import ch.epfl.moocprog.utils.Time;
import ch.epfl.moocprog.utils.Utils;

public abstract class Ant extends Animal {

	// Attributs**************************************
	private Uid anthillId;

	// Constructeur************************************
	public Ant(ToricPosition position, int hitpoints, Time lifespan, Uid antHillId) {
		super(position, hitpoints, lifespan);
		this.anthillId = antHillId;
	}

	// Getter**************************

	public final Uid getAnthillId() {
		return anthillId;
	}

	// Perception des objets dans l'environnement
	public <S extends Positionable> S isPerceving(List<S> liste) {
		//le parametre liste ne peut etre null
		Utils.requireNonNull("La liste ne peut etre nulle", liste);
		
		// Declaration des variables food et foodDistance
		S perceivedObject = Utils.closestFromPoint(this, liste);
		
		if (perceivedObject==null) {
			return null;
		}
		
		double objectDistance = this.getPosition().toricDistance(perceivedObject.getPosition());
		
		// Verification de la presence de la nourriture dans la zone perceptible
		if (objectDistance < Context.getConfig().getDouble(Config.ANT_MAX_PERCEPTION_DISTANCE)) {
			return perceivedObject;
		} else {
			return null;
		}
	}
	
	

}
