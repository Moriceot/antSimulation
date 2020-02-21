package ch.epfl.moocprog;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;
import ch.epfl.moocprog.gfx.EnvironmentRenderer;
import ch.epfl.moocprog.utils.Time;
import ch.epfl.moocprog.utils.Utils;

/**
 * @author cyril.mailhe
 *
 */
public final class Environment implements FoodGeneratorEnvironmentView, AnimalEnvironmentView, AnthillEnvironmentView, AntEnvironmentView, AntWorkerEnvironmentView {
	/* Attributs ************************************************************************************************************/
	private FoodGenerator fGen;
	private List<Food> fList;
	private List<Animal> aList;
	private List<Anthill> anthillList;

	

	/* Constructeur *********************************************************************************************************/
	public Environment() {
		super();
		this.fGen = new FoodGenerator();
		this.fList = new LinkedList<Food>();
		this.aList=new LinkedList<Animal>();
		this.anthillList=new LinkedList<Anthill>();
	}

	
	//Ajout des objets sur la carte (Food, Fourmilliere, Animal)*************************************************************
	@Override
	public void addFood(Food food) {
		// Verification que le parametre "food" n'est pas null
		Utils.requireNonNull(food);

		// Ajout de nourriture a la liste
		this.fList.add(food);

	}
	
	public void addAnthill(Anthill anthill) {
		Utils.requireNonNull(anthill);
		this.anthillList.add(anthill);
	}

	public void addAnimal(Animal animal) {
		// Verification que le parametre "animal" n'est pas null
		Utils.requireNonNull(animal);

		// Ajout de nourriture a la liste
		this.aList.add(animal);
	}
	
	//Ajout d'une fourmi a l'environnement
	@Override
	public void addAnt(Ant ant) {
		Utils.requireNonNull(ant);
		this.addAnimal(ant);
	}
	
	//Getters avances********************************************************************************************************
	
	/* Recuperation de la largeur du monde */
	public int getWidth() {
		return Context.getConfig().getInt(Config.WORLD_WIDTH);
	}
	
	/* Recuperation de la hauteur du monde */
	public int getHeight() {
		return Context.getConfig().getInt(Config.WORLD_HEIGHT);
	}
	
	/*Recuperation de la quantite de nourriture pour chaque tas*/
	public List<Double> getFoodQuantities() {
		// Creation d'une liste qui stockera les quantites de tous les types de
		// nourriture de l'environnement
		List<Double> amountList = new ArrayList<Double>();

		// Parcours de la liste fList pour remplir la liste amountList
		for (Food f : fList) {
			amountList.add(f.getQuantity());
		}
		return amountList;
	}
	
	/*Recuperation de la position de chaque animal*/
	public List<ToricPosition> getAnimalsPosition(){
		List<ToricPosition>pList=new ArrayList<ToricPosition>();
		for (Animal animal : aList) {
			pList.add(animal.getPosition());
		}
		return pList;
		
	}
	
	
	
	
	//Actualisation de l'environnement en fonction du temps**************************************************************************

	public void update(Time dt) {
		fGen.update(this, dt);

		//Appel de la methode "update" de animal
		aList.removeIf(a -> a.isDead()==true);
		for (Animal animal : aList) {
			animal.update(this, dt);
		}

		// Supression des nourritures epuisees
		fList.removeIf(f -> f.getQuantity() <= 0);
		
	}
	
	//Gestion de l'apparence des objets dans l'interface graphique***************************************************************************
	public void renderEntities(EnvironmentRenderer environmentRenderer) {
		fList.forEach(environmentRenderer::renderFood);
		aList.forEach(environmentRenderer::renderAnimal);

	}


	@Override
	public Food getClosestFoodForAnt(AntWorker antWorker) {
		Utils.requireNonNull("Une fourmi doit etre passee en parametre", antWorker);
		return antWorker.isPerceving(fList);
		
		
	}


	@Override
	public boolean dropFood(AntWorker antWorker) {
		Utils.requireNonNull("Une fourmi doit etre passee en parametre", antWorker);
		for (Anthill anthill : anthillList) {
			if(anthill.getAnthillId()==antWorker.getAnthillId()) {
				if(antWorker.getPosition().toricDistance(anthill.getPosition())<Context.getConfig().getDouble(Config.ANT_MAX_PERCEPTION_DISTANCE)) {
					return true;
				}
			}
		}
		
		return false;
	}

	

	

	
}
