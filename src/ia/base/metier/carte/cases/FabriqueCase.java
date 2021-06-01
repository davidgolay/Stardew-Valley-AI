/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.cases;

import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.objets.FabriqueObjet;

/**
 *
 * @author David
 */
public class FabriqueCase {
    public static Case creer(Coordonnee coordonnee, Character lettre) {
        Case c = null;
        
        switch (lettre) {
            case 'A' : 
                c = new CaseHerbe(coordonnee);
                c.setObjet(FabriqueObjet.creer(c, lettre));
                break;
            case 'E' : 
                c = new CaseEau(coordonnee);
                break;
            case 'T' : 
                c = new CaseTerre(coordonnee);               
                break;
            case 'H' : 
                c = new CaseHerbe(coordonnee);
                break;
            case 'M' : 
                c = new CaseHerbe(coordonnee);
                c.setObjet(FabriqueObjet.creer(c, lettre));
                break;
            case 'D' : 
                c = new CaseHerbe(coordonnee);
                c.setObjet(FabriqueObjet.creer(c, lettre));
                break;
            case 'S' : 
                c = new CaseHerbe(coordonnee);
                c.setObjet(FabriqueObjet.creer(c, lettre));
                break;
        }
    return c;    
    }
    
}
