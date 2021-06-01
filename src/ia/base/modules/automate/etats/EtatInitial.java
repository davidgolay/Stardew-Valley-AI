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
public class EtatInitial extends Etat{

    public EtatInitial(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {    
        return new EtatCheckCarte(this.getAutomate());
    }

    @Override
    public Action action() {
        return null;
    }
    
}
