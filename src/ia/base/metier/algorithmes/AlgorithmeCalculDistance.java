/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.algorithmes;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.Carte;
import ia.base.metier.carte.cases.Case;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author David
 */
public abstract class AlgorithmeCalculDistance {
    
    private Carte carte;
    private HashMap<Case, Integer> distances;
    
    public AlgorithmeCalculDistance(Carte carte){
        this.carte = carte;
        this.distances = new HashMap<Case, Integer>();
    }
    protected Carte getCarte() {
        return this.carte;
    }
    
    protected void setDistance(Case position, int valeur){
        this.distances.put(position, valeur);       
    }
    
    public Integer getDistance(Case arrivee){
        return this.distances.get(arrivee);
    }
    
    protected void reinitialisationDistances() {
        this.distances.clear();       
    }
    
    public abstract void calculerDistancesDepuis(Case depart);
    
    public abstract ArrayList<TypeMouvement> getChemin(Case arrivee);
         
}
