/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.actions.Action;
import ia.base.metier.actions.FabriqueAction;
import ia.base.metier.actions.TypeActionStatique;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 *
 * @author David
 */
public class EtatAllerDormir extends Etat
{

    public EtatAllerDormir(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {
        Etat etat = null;
        etat = new EtatCheckAction(this.getAutomate());
        return etat;
    }

    @Override
    public Action action() {
        this.seDeplacerEn(getAutomate().getModuleMemoire().getCarte().getCoordonneeDepart());
        this.getAutomate().getListeDesActionsARealiser().add(FabriqueAction.creerActionStatique(TypeActionStatique.DORMIR));
        return null;
    }
    
}
