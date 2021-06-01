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
public class CaseEau extends Case{
    
    public CaseEau(Coordonnee coordonnee) {
        super(coordonnee);
    }
    
    @Override
    public TypeCase getType() {
        return TypeCase.EAU;
    }

    @Override
    public boolean estAccessible() {
        return false;
    }
    
    
}
