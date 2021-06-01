/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.ressources.TypeRessource;

/**
 *
 * @author David
 */
public class ActionMouvement extends Action {
    
    private TypeMouvement typeMouvement;
    
    public ActionMouvement(TypeMouvement typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    @Override
    public String getMessage() {
        return "MOVE|"+typeMouvement;     
    }

    @Override
    public TypeAction getType() {
        return TypeAction.MOUVEMENT;
    }

    @Override
    public TypeMouvement getDirection() {
        return this.typeMouvement;
    }

    @Override
    public TypeRessource getTypeRessource() {
        return null;
    }
    
    
    
}
