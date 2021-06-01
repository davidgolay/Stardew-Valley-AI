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
public class EtatAllerRecolter extends Etat{
    /**
     * Permet de savoir si il faut aller récolter ou non
     */
    private boolean aRecolte;
    
    public EtatAllerRecolter(Automate automate) {
        super(automate);
        this.aRecolte = false;
    }

    @Override
    public Etat transition() {
        Etat etat = new EtatAcheter(getAutomate());
        return etat;
    }
    
    /**
     * Permet de trouver la case la plus proche du joueur avec une plante qui est
     * arrivée à maturité afin de la cueillir
     * @return null
     */
    @Override
    public Action action() {
        return null;
    }
    
}
