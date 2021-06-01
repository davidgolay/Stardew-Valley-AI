/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.actions.Action;
import ia.base.metier.actions.FabriqueAction;
import ia.base.metier.actions.TypeActionRecolte;
import ia.base.metier.algorithmes.Dijkstra;
import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.objets.TypeObjet;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 *
 * @author David
 */
public class EtatAllerVersArbre extends Etat {
    
    private boolean arbreTrouve;

    public EtatAllerVersArbre(Automate automate) {
        super(automate);
        this.arbreTrouve = false;
    }

    @Override
    public Etat transition() {
        Etat etat = null;
        if(arbreTrouve == false){
            etat = new EtatAllerDormir(this.getAutomate());
        } else {
            etat = new EtatCheckAction(this.getAutomate());
        }
        return etat;
    }

    @Override
    public Action action() {
        // on crée un nouveau dijkstra à partir de la carte
        Dijkstra dijkstra = new Dijkstra(this.getAutomate().getModuleMemoire().getCarte());
        //on calcule toutes les distances à partir de la case du joueur
        dijkstra.calculerDistancesDepuis(this.getAutomate().getModuleMemoire().getCaseJoueur());
        Case caseAvecArbreLaPlusProche = null;
        int distanceMinimale = -1;
        
        //Pour toutes les cases c de la carte 
        for (Case c : this.getAutomate().getModuleMemoire().getCarte().getCases()) {
            //si la case c a un object ET que c'est un ARBRE
            if( (c.getObjet() != null) && (c.getObjet().getType() == TypeObjet.ARBRE) ){
                // si case la plus proche est null OU si la distance à c est inférieure à distanceMinimale
                if( (caseAvecArbreLaPlusProche == null) || 
                    (dijkstra.getDistance(c) < distanceMinimale) ) {                     
                        caseAvecArbreLaPlusProche = c;
                        distanceMinimale = dijkstra.getDistance(c);
                        this.arbreTrouve = true;
                }
            }
        }
        
        if ( (caseAvecArbreLaPlusProche != null) && (this.arbreTrouve == true) ) {
            //on se déplace à la case avec un arbre la plus proche
            this.seDeplacerEn(caseAvecArbreLaPlusProche.getCoordonnee());
            if(!this.getAutomate().getListeDesActionsARealiser().isEmpty()) {                   
                //Suppression de la dernier action
                Action action = this.getAutomate().getListeDesActionsARealiser().get
                    (this.getAutomate().getListeDesActionsARealiser().size()-1);
                this.getAutomate().getListeDesActionsARealiser().remove
                    (this.getAutomate().getListeDesActionsARealiser().size()-1);

                // Ajout d'une double action de coupe
                this.getAutomate().getListeDesActionsARealiser().add(
                    FabriqueAction.creerActionRecolte
                    (TypeActionRecolte.COUPERARBRE, action.getDirection()));
                this.getAutomate().getListeDesActionsARealiser().add(
                    FabriqueAction.creerActionRecolte
                    (TypeActionRecolte.COUPERARBRE, action.getDirection()));
            }
        }
        return null;
    }

    
}
