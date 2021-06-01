/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.algorithmes;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.Carte;
import ia.base.metier.carte.cases.Case;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author David
 */
public class ParcoursLargeur extends AlgorithmeCalculDistance{

    public ParcoursLargeur(Carte carte) {
        super(carte);
    }

    @Override
    public void calculerDistancesDepuis(Case depart) {
        Case caseEnCours;
        ArrayList<Case> aTraiter = new ArrayList<Case>();
        super.reinitialisationDistances();
        aTraiter.add(depart);
        super.setDistance(depart, 0);
        while (!aTraiter.isEmpty()){
          caseEnCours = aTraiter.get(0);
          aTraiter.remove(aTraiter.get(0));
          //pour tout voisin de la caseEnCours
          for (Case v : caseEnCours.getVoisins()) {
            if(super.getDistance(v) == null){
              //si les voisins sont accessibles
              if(v.estAccessible()){
                super.setDistance(v, super.getDistance(caseEnCours)+1);
                aTraiter.add(v);
              }
            }
          }
        }           
    } 

    @Override
    public ArrayList<TypeMouvement> getChemin(Case arrivee) {
        //initialisation
        ArrayList<TypeMouvement> resultat = new ArrayList<>();
        Case caseEnCours = arrivee;
        
        //Calcul
        if(super.getDistance(caseEnCours) != null) {
            while(super.getDistance(caseEnCours) > 0){
                Case casePrecedente = null;
                for(Case v : caseEnCours.getVoisins()) {
                    if( (super.getDistance(v) != null) ){
                        if(super.getDistance(v) == (super.getDistance(caseEnCours)-1)){
                            casePrecedente = v;
                            TypeMouvement move = null;
                            move = casePrecedente.getMouvementPourAller(caseEnCours);
                            resultat.add(move);
                            caseEnCours = casePrecedente;
                        }          
                    }
                }
            }      
        }
        Collections.reverse(resultat);
        return resultat;
    }
    
    
}
