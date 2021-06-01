/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate;

import ia.base.metier.actions.Action;
import ia.base.modules.Module_Decision;
import ia.base.modules.Module_Memoire;
import ia.base.modules.automate.etats.EtatInitial;
import java.util.ArrayList;

/**
 *
 * @author dg447135
 */
public class Automate {
    
    private Etat etatCourant;
    
    private Module_Decision moduleDecision;
    private Module_Memoire moduleMemoire;
    
    ArrayList<Action> listeDesActionsARealiser;
    
    public Automate(Module_Decision moduleDecision){
        this.etatCourant = new EtatInitial(this);
        this.moduleDecision = moduleDecision;
        this.moduleMemoire = moduleDecision.getIA().getModuleMemoire();
        this.listeDesActionsARealiser = new ArrayList<>();   
    }
    
    public Action evoluer() {
        Action prochaineAction = null;
        while(prochaineAction == null) {
            etatCourant = etatCourant.transition();
            prochaineAction = etatCourant.action();
        }    
        return prochaineAction;      
    }

    public Module_Decision getModuleDecision() {
        return moduleDecision;
    }

    public Module_Memoire getModuleMemoire() {
        return moduleMemoire;
    }

    public ArrayList<Action> getListeDesActionsARealiser() {
        return listeDesActionsARealiser;
    } 
}
