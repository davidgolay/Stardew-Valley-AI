/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.actions.Action;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 * @author David
 */
public class EtatVendre extends Etat{

    private boolean vaVendre;
    
    public EtatVendre(Automate automate) {
        super(automate);
        this.vaVendre = false;
    }

    @Override
    public Etat transition() {
       Etat etat = new EtatAcheter(getAutomate());
        if(vaVendre){
            etat = new EtatCheckAction(getAutomate());
        }
       return etat;
    }
    
    @Override
    public Action action() {
        Action action = null;
        return null;
    }
    
}