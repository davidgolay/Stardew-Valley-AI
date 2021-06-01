/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.objets;

import ia.base.metier.carte.cases.Case;

/**
 *
 * @author David
 */
public class FabriqueObjet {
    
    public static Objet creer(Case position, Character lettre) {
        Objet o = null;
        switch (lettre) {
            case 'A' : 
                o = new ObjetArbre(position);
                break;
            case 'M' : 
                o = new ObjetMaison(position);
                break;
            case 'D' : 
                o = new ObjetDepart(position);
                break;
            case 'S' : 
                o = new ObjetEscalier(position);
                break;
        }
    return o;    
    }
    
}
