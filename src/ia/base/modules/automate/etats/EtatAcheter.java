/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.actions.Action;
import ia.base.metier.actions.FabriqueAction;
import ia.base.metier.algorithmes.Dijkstra;
import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.cases.CaseHerbe;
import ia.base.metier.carte.ressources.TypeRessource;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 *
 * @author David
 */
public class EtatAcheter extends Etat {

    private boolean vaAcheter;
    
    public EtatAcheter(Automate automate) {
        super(automate);
        this.vaAcheter = false;
    }

    @Override
    public Etat transition() {
        Etat etat = new EtatDemandeMagasin(this.getAutomate());
        // si le stock magasin existe
        if(super.getAutomate().getModuleMemoire().hasStockMagasin()){
            if(this.vaAcheter == true){
                etat = new EtatCheckAction(getAutomate());
            }else{
                etat = new EtatAllerDormir(getAutomate());
            }
        }
        return etat;
    }

    @Override
    public Action action() {
    Action action = null;
        if(this.getAutomate().getModuleMemoire().hasStockMagasin()){
            if(this.getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.GOLD) >= 20){
                if(getAutomate().getModuleMemoire().getStockMagasin(TypeRessource.PARSNIPSEED) >= 1 
                   || (getAutomate().getModuleMemoire().getStockMagasin(TypeRessource.CAULIFLOWERSEED) >=1 )){
                    Dijkstra dijkstra = new Dijkstra(this.getAutomate().getModuleMemoire().getCarte());
                    dijkstra.calculerDistancesDepuis(this.getAutomate().getModuleMemoire().getCaseJoueur());
                    Case storeLeft = new CaseHerbe(getAutomate().getModuleMemoire().getCarte().getCoordonneesMagasin().get(0));
                    Case storeRight = new CaseHerbe(getAutomate().getModuleMemoire().getCarte().getCoordonneesMagasin().get(1));
                    Case caseMagasinPlusProche = null;
                    int distanceMinimale = -1;
                    for (Case c : getAutomate().getModuleMemoire().getCarte().getCases()){
                        if(c.getCoordonnee().equals(storeLeft.getCoordonnee()) || c.getCoordonnee().equals(storeRight.getCoordonnee())){
                            if(caseMagasinPlusProche == null || dijkstra.getDistance(c) < distanceMinimale){
                                caseMagasinPlusProche = c;
                                distanceMinimale = dijkstra.getDistance(c);
                            }
                        }
                    }
                    this.vaAcheter = true;
                    seDeplacerEn(caseMagasinPlusProche.getCoordonnee());   
                    action = FabriqueAction.creerActionAcheter(TypeRessource.PARSNIPSEED);                   
                }
            }
        }
        return action;
    }
    
}
