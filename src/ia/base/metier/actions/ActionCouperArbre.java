/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;

/**
 *
 * @author dg447135
 */
public class ActionCouperArbre extends Action{
    
    private TypeMouvement directionArbre;
    
    public ActionCouperArbre(TypeMouvement directionArbre) {
        this.directionArbre = directionArbre;
    }

    @Override
    public String getMessage() {
        String res;
        res = "HARVEST|";
        res += directionArbre;
        return res;
    }

    @Override
    public TypeAction getType() {
        return TypeAction.RECOLTE;
    }

    @Override
    public TypeMouvement getDirection() {
        return this.directionArbre;
    }
    
}
