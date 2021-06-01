/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;

/**
 *
 * @author David
 */
public class FabriqueAction {
    
    public static Action creerMouvement(TypeMouvement mouvement) {
        Action actionMove = new ActionMouvement(mouvement);
        return actionMove;       
    }
    
    public static Action creerDemande(TypeDemande demande) {
        Action actionDemande = null;
        switch(demande){
            case CARTE: actionDemande = new ActionDemande("MAP"); break;
        }
        return actionDemande;      
    }
    
    public static Action creerActionStatique(TypeActionStatique type){
        Action actionStatique = null;
        switch(type){
            case DORMIR: actionStatique = new ActionDormir(); break;
        }
        return actionStatique;
    }
    
    public static Action creerActionRecolte(TypeActionRecolte type, TypeMouvement direction) {
        Action actionRecolte = null;
        switch(type){
            case COUPERARBRE: actionRecolte = new ActionCouperArbre(direction); 
                break;
        }
        return actionRecolte;
    }
    
}
