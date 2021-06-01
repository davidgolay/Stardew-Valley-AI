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
public class ActionDormir extends Action {

    @Override
    public String getMessage() {
        return "SLEEP";
    }

    @Override
    public TypeAction getType() {
        return TypeAction.ACTIONSTATIQUE;
    }

    @Override
    public TypeMouvement getDirection() {
        return null;
    }
    
}
