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
public class EtatAllerArroser extends Etat{

    private boolean aArrose;
    
    public EtatAllerArroser(Automate automate) {
        super(automate);
        this.aArrose = false;
    }

    @Override
    public Etat transition() {
        Etat etat = new EtatAllerDormir(getAutomate());      
        return etat;
    }

    @Override
    public Action action() {        
        return null;
    }
    
}
