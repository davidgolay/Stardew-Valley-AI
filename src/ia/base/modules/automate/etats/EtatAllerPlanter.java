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
 * @author David
 */
public class EtatAllerPlanter extends Etat{
    
    private boolean  aPlante;
    
    public EtatAllerPlanter(Automate automate) {
        super(automate);
        this.aPlante = false;
    }

    @Override
    public Etat transition() {
        Etat etat = new EtatCheckAction(getAutomate());
        if(!aPlante){
            etat = new EtatAllerArroser(getAutomate());
        }
        return etat;
    }

    @Override
    public Action action() {
        return null;
    }
    
}
