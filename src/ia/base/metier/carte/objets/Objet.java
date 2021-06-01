/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.objets;

import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.ressources.TypeRessource;
import java.util.HashMap;

/**
 *
 * @author dg447135
 */
public abstract class Objet {
    private Case position;
    
    public Objet(Case position) {
        this.position = position;
    }
    
    /**
     * Methode qui retourne le type de l'object placé sur la case
     * @return 
     */
    public abstract TypeObjet getType();
   
    
    public abstract HashMap<TypeRessource,Integer> getLoot() ;

    
    /**
     * Cette méthode indique si la case possède un object bloquant
     * Cette méthode
     * @return renvoie ”true” pour les arbres et la maison et renvoie ”false” pour les
     * autres objets.
     */
    public abstract boolean EstBloquant();
    
    public boolean estRecoltable() {
        return false;
    }
    
    public int coutRecolte(){
        return -1;
    }
    
}
