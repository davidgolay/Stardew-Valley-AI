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
 *
 * @author dg447135
 */
public class EtatCheckCarte extends Etat {
    
    public EtatCheckCarte(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {
        Etat etat = null;
        //si le module memoire n'a pas de carte
        if (!this.getAutomate().getModuleMemoire().hasCarte()) {
            etat = new EtatDemanderCarte(this.getAutomate());
        } else if(this.getAutomate().getModuleMemoire().hasCarte()) {
            etat = new EtatCheckAction(this.getAutomate());
        }    
        return etat;
    }

    @Override
    public Action action() {
        return null;
    }
    
}
