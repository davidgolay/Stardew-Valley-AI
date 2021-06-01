/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;

/**
 *
 * @author David
 */
public class ActionDemande extends Action {
    
    private String message;
    
    public ActionDemande(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
        
    }

    @Override
    public TypeAction getType() {
        return TypeAction.DEMANDE;
    }

    @Override
    public TypeMouvement getDirection() {
        return null;
    }
    
}
