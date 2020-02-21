package ch.epfl.moocprog;

import ch.epfl.moocprog.utils.Vec2d;
import static ch.epfl.moocprog.app.Context.getConfig;
import static ch.epfl.moocprog.config.Config.*;

import java.util.ArrayList;

public final class ToricPosition {
	//Attributs***********************
	private final Vec2d position;

	//Constructeur************************
	
	public ToricPosition() {
		position=clampedPosition(0.0,0.0);
	} 
	
	public ToricPosition(double x, double y) {
		position=clampedPosition(x, y);
	} 
	
	public ToricPosition(Vec2d vec) {
		position=clampedPosition(vec.getX(), vec.getY());
	} 

	//Getters*************************************
	public Vec2d toVec2d() {
		return clampedPosition(position.getX(),position.getY());
	}
	
	public double getX() {return this.position.getX();}
	public double getY() {return this.position.getY();}
	
	//Projection de la position***********
	private static Vec2d clampedPosition(double x, double y) {
		//Creation de 2 variables width et height pour reprendre les valeurs des constantes
		int width=getConfig().getInt(WORLD_WIDTH);
		int height= getConfig().getInt(WORLD_HEIGHT);
		
		if (x<0) {x+=width;}
		if (x>=width){x-=width;}
		if (y<0) {y+=height;}
		if (y>=height) {y-=height;}
		
		return new Vec2d(x, y);
	}
	
	//Somme de 2 vecteurs *****************************************************
	public ToricPosition add(ToricPosition that) {
		return new ToricPosition(this.getX()+that.getX(), this.getY()+that.getY());
	}
	
	public ToricPosition add(Vec2d vec) {
		return new ToricPosition(this.getX()+vec.getX(), this.getY()+vec.getY());
	}
	
	//Calcul du plus petit vecteur entre 2 positions******************************
	
	
	public Vec2d toricVector(ToricPosition that) {
		//Declaration des variables hauteur et largeur du monde
		int width = getConfig().getInt(WORLD_WIDTH);
		int height = getConfig().getInt(WORLD_HEIGHT);
		
		//calcul des 9 cas possibles
		ArrayList<Vec2d> tabDist=new ArrayList<Vec2d>();
		tabDist.add(that.toVec2d());
		tabDist.add(that.toVec2d().add(new Vec2d(width, 0)));
		tabDist.add(that.toVec2d().add(new Vec2d(0, height)));
		tabDist.add(that.toVec2d().add(new Vec2d(-width, 0)));
		tabDist.add(that.toVec2d().add(new Vec2d(0, -height)));
		tabDist.add(that.toVec2d().add(new Vec2d(width, height)));
		tabDist.add(that.toVec2d().add(new Vec2d(width, -height)));
		tabDist.add(that.toVec2d().add(new Vec2d(-width, height)));
		tabDist.add(that.toVec2d().add(new Vec2d(-width, -height)));
		
		//Recherche de la distance minimale
		
		Vec2d vec=that.toVec2d();
		for (Vec2d pos : tabDist) {
			if(this.toVec2d().distance(vec)>this.toVec2d().distance(pos)) {
				vec=new Vec2d(pos.getX(),pos.getY());
			}
		}
		
		return new Vec2d(vec.getX()-this.getX(), vec.getY()-this.getY());
	}
	
	public double toricDistance(ToricPosition that) {
		return this.toricVector(that).length();
	}
	
	
	// Affichage*************************************
	public String toString() {
		return this.toVec2d().toString();
	}
	
	
}
