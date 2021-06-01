/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.actions.Action;
import ia.base.metier.actions.ActionCouperArbre;
import ia.base.metier.actions.TypeAction;
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.objets.TypeObjet;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 *
 * @author David
 */
public class EtatRealiserAction extends Etat{

    public EtatRealiserAction(Automate automate) {
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
        //récupération de la première action de la liste
        Action actionARealiser = this.getAutomate().getListeDesActionsARealiser().get(0);
        //si l'action 0 est de type mouvement
        if(actionARealiser.getType() == TypeAction.MOUVEMENT) {
            //recupération de la case actuelle du joueur
            Case caseJoueur = this.getAutomate().getModuleMemoire().getCaseJoueur();
           //coordonnée obtenu en partant de celle du joueur et en se déplaçant suivant la direction de l'action
            Coordonnee coordonneeDestination = caseJoueur.getCoordonnee().getVoisin(actionARealiser.getDirection());
            //case située aux coordonnée de destination
            Case caseDestination = this.getAutomate().getModuleMemoire().getCarte().getCase(coordonneeDestination);
            //si la case de destination possède un objet et que celui−ci est un arbre
            if(caseDestination.getObjet() != null && caseDestination.getObjet().getType() == TypeObjet.ARBRE) {
                //ajouter en position 0 deux actions ”COUPERARBRE” de même direction que l ”action”.
                for(int i=0; i<2; i++) {
                    ActionCouperArbre action = new ActionCouperArbre(actionARealiser.getDirection());
                    this.getAutomate().getListeDesActionsARealiser().add(0, action);
                }              
            }         
        }
        actionARealiser = this.getAutomate().getListeDesActionsARealiser().get(0);
        //suppresion de la première action de la liste
        this.getAutomate().getListeDesActionsARealiser().remove(0);
        return actionARealiser;
    }
    
    
}
