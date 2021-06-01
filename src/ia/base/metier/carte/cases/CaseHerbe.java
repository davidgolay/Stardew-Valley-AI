/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.cases;

import ia.base.metier.carte.Coordonnee;

/**
 *
 * @author David
 */
public class CaseHerbe extends Case {
    
    public CaseHerbe(Coordonnee coordonnee) {
        super(coordonnee);
    }
    
    @Override
    public TypeCase getType() {
        return TypeCase.HERBE;
    }

    @Override
    public boolean estAccessible() {
        boolean res = true;
        if ( (this.getObjet() != null) && (this.getObjet().EstBloquant()) ){
            res = false;
        }
        return res;
    }
    
    
    
}
